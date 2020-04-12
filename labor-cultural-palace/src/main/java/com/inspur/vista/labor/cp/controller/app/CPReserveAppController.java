package com.inspur.vista.labor.cp.controller.app;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.framework.leader.zookeeper.ZkClient;
import com.inspur.vista.labor.cp.controller.BaseController;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.entity.CPReserveAdd;
import com.inspur.vista.labor.cp.entity.CPReserveInfoListVO;
import com.inspur.vista.labor.cp.entity.CPReserveInfoVO;
import com.inspur.vista.labor.cp.service.CPReserveInfoService;
import com.inspur.vista.labor.cp.service.CPReserveUserService;
import com.inspur.vista.labor.cp.service.UacUserService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPArenaInfoAppController
 * @Description: app端预约控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@Api(value = "app端预约控制器", tags = {"app端预约接口"})
@RestController
@RequestMapping("/api/app/cp/reserve/")
public class CPReserveAppController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPReserveAppController.class);

    @Autowired
    private CPReserveInfoService reserveInfoService;

    @Autowired
    private CPReserveUserService reserveUserService;

    @Autowired
    private UacUserService uacUserService;

    @Autowired
    private ZkClient zkClient;


    /**
     * 查询手机号是否注册齐鲁工惠
     *
     * @param phones 手机号，逗号分隔
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询手机号是否注册齐鲁工惠", notes = "")
    @PostMapping(value = "/phone/search")
    public ResponseDTO searchPhone(@ApiParam(name = "phones", value = "手机号", required = true) String phones) {
        ResponseDTO responseDTO;
        List<String> unRegisterList = new ArrayList<>();
        String[] phoneArray = phones.split(",");
        for (String phone : phoneArray) {
            Map userMap = uacUserService.getUserInfoByPhone(phone);
            if (null != userMap) {
                unRegisterList.add(phone);
            }
        }
        if (!unRegisterList.isEmpty()) {
            responseDTO = WebUtils.createSuccessResponse("以下手机号未注册齐鲁工惠：" + ArrayUtil.join(unRegisterList.toArray(), ","));
        } else {
            responseDTO = WebUtils.createSuccessResponse("");
        }
        return responseDTO;
    }

    /**
     * 预定场地
     *
     * @param reserveAdd 预定记录
     * @return ResponseDTO
     */
    @ApiOperation(value = "预定场地", notes = "")
    @PostMapping(value = "/add")
    public ResponseDTO addReserveInfo(@ApiParam(name = "reserveAdd", value = "预定信息", required = true) @RequestBody CPReserveAdd reserveAdd,
                                      @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {
        ResponseDTO responseDTO;
        try {
            reserveAdd.setUserCode(userInfo.getUserCode());
            reserveAdd.setUserPhone(userInfo.getPhone());
            reserveAdd.setUserName(userInfo.getUserName());
            AtomicReference<String> msg = new AtomicReference<>("");
            Map<String, String> userList = new LinkedHashMap<>();
            userList.put(reserveAdd.getUserPhone(), reserveAdd.getUserCode());
            if (StringUtils.isNotBlank(reserveAdd.getCompanionPhone())) {
                String phones = reserveAdd.getCompanionPhone();
                String[] phoneArray = phones.split(",");
                for (String phone : phoneArray) {
                    if (StringUtils.isNotBlank(phone)) {
                        Map userMap = uacUserService.getUserInfoByPhone(phone);
                        if (null != userMap) {
                            userList.put(phone, Convert.toStr(userMap.get("userCode")));
                        } else {
                            userList.put(phone, null);
                        }
                    }
                }
            }
            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                reserveAdd.setUserCode(reserveAdd.getUserCode());
                reserveAdd.setUserPhone(reserveAdd.getUserPhone());
                reserveAdd.setType(CPConstants.RESERVE_WAY_APP);

                // 获取锁定路径
                String lockPath = zkClient.getLockPath("cp_reserve" + reserveAdd.getCourtId());
                responseDTO = zkClient.lockAndDeal(lockPath, 30L, () -> {
                    ResponseDTO result;
                    try {
                        result = reserveInfoService.addReserveInfo(reserveAdd, userList);
                    } catch (RuntimeException e) {
                        logger.error("保存预约相关信息失败", e);
                        result = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, e.getMessage());
                    }
                    return result;
                });
            }
        } catch (Exception e) {
            logger.error("预定场地出错", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "预约出错");
        }
        return responseDTO;
    }

    /**
     * 获取我的预约记录
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取我的预约记录", notes = "")
    @PostMapping(value = "/reserve/list")
    public ResponseDTO listCPArenaReserveApp(@ApiParam(name = "parameters", value = "查询条件，placeId(场所id),currentDay(若需查当天，此字段不为空),page,pageSize", required = true) @RequestBody Map<String, Object> parameters,
                                             @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {

        ResponseDTO responseDTO;
        try {
            if (null != parameters.get("currentDay") && (!"".equals(parameters.get("currentDay")))) {
                parameters.put("currentDay", DateUtil.today());
            }
            parameters.put("userCode", userInfo.getUserCode());
            Page<CPReserveInfoListVO> reserveInfoList = reserveInfoService.listCPReserveInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(reserveInfoList);
        } catch (Exception e) {
            logger.error("获取我的预约记录出错", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "获取我的预约记录出错");
        }
        return responseDTO;
    }

    /**
     * 获取预定记录
     *
     * @param id 场地预约记录id
     * @return ResponseDTO
     */
    @ApiOperation(value = "通过记录id获取预定记录信息", notes = "")
    @GetMapping(value = "/detail/{id}")
    public ResponseDTO<CPReserveInfoVO> getCPReserveInfo(@ApiParam(name = "id", value = "记录id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPReserveInfoVO cpReserveInfo = reserveInfoService.getCPReserveInfo(id);
        if (null != cpReserveInfo) {
            responseDTO = WebUtils.createSuccessResponse(cpReserveInfo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到预定记录", "");
        }
        return responseDTO;
    }

    /**
     * 取消预约
     *
     * @param id 场地预约记录id
     * @return ResponseDTO
     */
    @ApiOperation(value = "取消预定", notes = "取消预定的原因：1、不想预定了；2、预定错了，重新预定；3、其他")
    @PostMapping(value = "/cancel")
    public ResponseDTO cancelReserve(@ApiParam(name = "id", value = "预约记录id", required = true) @RequestParam(value = "id") String id,
                                     @ApiParam(name = "cancelReasonType", value = "取消预定的原因的类型", required = true) @RequestParam(value = "cancelReasonType") Integer cancelReasonType,
                                     @ApiParam(name = "cancelReasonExt", value = "取消预定的原因，如果类型为其他时填写", required = false) @RequestParam(value = "cancelReasonExt", required = false) String cancelReasonExt,
                                     @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {
        ResponseDTO responseDTO;
        try {
            if (StringUtils.isNotBlank(id)) {
                CPReserveInfoVO reserveInfoVO = reserveInfoService.getCPReserveInfo(id);
                if (null == reserveInfoVO) {
                    responseDTO = WebUtils.createSuccessResponse("记录不存在");
                } else {
                    // 判断状态是否是待支付或预约成功状态
                    if (CPConstants.RESERVE_STATE_NOT_PAY.equals(reserveInfoVO.getState()) || CPConstants.RESERVE_STATE_SUCCESS.equals(reserveInfoVO.getState())) {
                        responseDTO = reserveInfoService.cancelReserve(cancelReasonType, cancelReasonExt, id, userInfo.getUserCode(), false);
                    } else {
                        responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "取消失败，订单状态错误");
                    }
                }
            } else {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
            }
        } catch (Exception e) {
            logger.error("取消异常", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "取消异常");
        }
        return responseDTO;
    }

    /**
     * 齐鲁工惠app场所二维码使用场地
     *
     * @param reserveIds 预约记录id
     * @return ResponseDTO
     */
    @ApiOperation(value = "齐鲁工惠app场所二维码使用场地", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/use")
    public ResponseDTO useCourt(@ApiParam(name = "reserveIds", value = "预约记录id", required = true) @RequestParam String reserveIds,
                                @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {
        ResponseDTO responseDTO;
        try {
            String[] reserveIdArray = reserveIds.split(",");
            for (String reserveId : reserveIdArray) {
                CPReserveInfoVO reserveInfoVO = reserveInfoService.getCPReserveInfo(reserveId);
                reserveInfoService.confirmReserve(reserveInfoVO, userInfo.getUserCode());
            }
            responseDTO = WebUtils.createSuccessResponse("");
        } catch (Exception e) {
            logger.error("新增/修改场所使用记录", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }
}
