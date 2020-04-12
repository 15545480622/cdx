package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.framework.leader.zookeeper.ZkClient;
import com.inspur.vista.labor.cp.entity.CPReserveInfoListVO;
import com.inspur.vista.labor.cp.entity.CPReserveInfoVO;
import com.inspur.vista.labor.cp.service.CPReserveInfoService;
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

import java.util.Map;

/**
 * @Title: CPReserveInfoController
 * @Description: 场地预约记录控制器
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@Api(value = "场地预定接口", tags = {"场地预定接口"})
@RestController
@RequestMapping("/api/cp/reserve")
public class CPReserveInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPReserveInfoController.class);

    @Autowired
    private CPReserveInfoService cpReserveInfoService;

    @Autowired
    private ZkClient zkClient;

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
        CPReserveInfoVO cpReserveInfo = cpReserveInfoService.getCPReserveInfo(id);
        if (null != cpReserveInfo) {
            responseDTO = WebUtils.createSuccessResponse(cpReserveInfo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到预定记录", "");
        }
        return responseDTO;
    }

    /**
     * 查询预定记录，分页
     * 管理端可以根据姓名、时间段、场地查询
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询预定的记录", notes = "查询条件：userId：用户id，userName：姓名，userPhone：手机号，page、pageSize，管理端可以通过场地来查询（courtId）")
    @PostMapping(value = "/list")
    public ResponseDTO listCPReserveInfo(@ApiParam(name = "parameters", value = "查询条件，userId：用户id，userName：姓名，userPhone：手机号，page、pageSize", required = true) @RequestBody Map<String, Object> parameters) {
        Page<CPReserveInfoListVO> reserveInfoListVOPage = cpReserveInfoService.listCPReserveInfo(parameters);
        return WebUtils.createSuccessResponse(reserveInfoListVOPage);
    }

    /**
     * 通过场次的起止时间查询预约记录，分页
     * 管理端可以根据姓名、时间段、场地查询
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "通过场次的起止时间查询预约记录", notes = "")
    @PostMapping(value = "/listByScene")
    public ResponseDTO listCPReserveInfoByScene(@ApiParam(name = "parameters", value = "查询条件，courtId：场地id，sceneBeginTime：场次开始时间，sceneEndTime：场次结束时间，page、pageSize", required = true) @RequestBody Map<String, Object> parameters) {
        Page<CPReserveInfoListVO> reserveInfoListVOPage = cpReserveInfoService.listCPReserveInfoByScene(parameters);
        return WebUtils.createSuccessResponse(reserveInfoListVOPage);
    }

    /**
     * 取消预约
     *
     * @param ids 场地预约记录id
     * @return ResponseDTO
     */
    @ApiOperation(value = "取消预定", notes = "取消预定的原因：1、不想预定了；2、预定错了，重新预定；3、其他")
    @PostMapping(value = "/cancel")
    public ResponseDTO cancelReserve(@ApiParam(name = "ids", value = "预约记录id，多个id用逗号分隔", required = true) @RequestParam(value = "ids") String ids,
                                     @ApiParam(name = "cancelReasonType", value = "取消预定的原因的类型", required = true) @RequestParam(value = "cancelReasonType") Integer cancelReasonType,
                                     @ApiParam(name = "cancelReasonExt", value = "取消预定的原因，如果类型为其他时填写", required = false) @RequestParam(value = "cancelReasonExt", required = false) String cancelReasonExt) {
        ResponseDTO responseDTO;
        try {
            String modifier = getUser().getUserId();
            if (StringUtils.isNotBlank(ids)) {
                String[] idArr = ids.split(",");
                if (idArr.length > 0) {
                    for (String id : idArr) {
                        cpReserveInfoService.cancelReserve(cancelReasonType, cancelReasonExt, id, modifier, true);
                    }
                    responseDTO = WebUtils.createSuccessResponse("");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
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
}
