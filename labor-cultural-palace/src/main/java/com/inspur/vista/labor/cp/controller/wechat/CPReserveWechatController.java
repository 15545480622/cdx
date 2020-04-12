package com.inspur.vista.labor.cp.controller.wechat;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPReserveInfoListVO;
import com.inspur.vista.labor.cp.entity.CPReserveInfoVO;
import com.inspur.vista.labor.cp.service.CPReserveInfoService;
import com.inspur.vista.labor.cp.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: CPReserveWechatController
 * @Description: 微信端的预约接口
 * @Author: gengpeng
 * @CreateDate: 2020/3/30 8:58
 * @Version: 1.0
 */
@Api(value = "微信端的预约接口", tags = {"微信小程序预约核销接口"})
@RestController
@RequestMapping("/api/wechat/cp/reserve")
public class CPReserveWechatController {

    private static final Logger logger = LoggerFactory.getLogger(CPReserveWechatController.class);

    @Autowired
    private CPReserveInfoService reserveInfoService;

    /**
     * 扫码校验预约记录
     *
     * @param param
     * @return ResponseDTO
     */
    @ApiOperation(value = "小程序扫码校验预约记录", notes = "")
    @GetMapping(value = "/check")
    public ResponseDTO<CPReserveInfoVO> check(@ApiParam(name = "param", value = "二维码信息中的param参数的值", required = true) @RequestParam String param,
                                              @ApiParam(name = "cpId", value = "文化宫id，登录用户所在的文化宫id", required = true) @RequestParam String cpId) {
        ResponseDTO responseDTO;
        Map<String, Object> paramMap = CPUtils.decryptQRCodeParam(param);
        String reserveId = Convert.toStr(paramMap.get("reserveId"));
        CPReserveInfoVO reserveInfoVO = reserveInfoService.getCPReserveInfo(reserveId);
        if (!cpId.equals(reserveInfoVO.getCpId())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "预约的场地不在当前文化宫");
        } else {
            return WebUtils.createSuccessResponse(reserveInfoVO);
        }
        return responseDTO;
    }

    /**
     * 扫码校验预约记录
     *
     * @param param
     * @return ResponseDTO
     */
    @ApiOperation(value = "小程序扫码确认预约记录", notes = "")
    @PostMapping(value = "/confirm")
    public ResponseDTO confirm(@ApiParam(name = "param", value = "参数", required = true) @RequestParam String param) {
        ResponseDTO responseDTO;
        try {
            Map<String, Object> paramMap = CPUtils.decryptQRCodeParam(param);
            String reserveId = Convert.toStr(paramMap.get("reserveId"));
            String userCode = Convert.toStr(paramMap.get("userCode"));
            CPReserveInfoVO reserveInfoVO = reserveInfoService.getCPReserveInfo(reserveId);
            if (reserveInfoVO.getState().equals(CPConstants.RESERVE_STATE_SUCCESS)) {
                reserveInfoService.confirmReserve(reserveInfoVO, userCode);
                responseDTO = WebUtils.createSuccessResponse("");
            } else {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "确认失败，请检查是否预约成功");
            }
        } catch (Exception ex) {
            logger.error("小程序扫码确认预约记录失败", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "小程序扫码确认预约记录失败");
        }

        return responseDTO;
    }

    /**
     * 获取文化宫当天的预约记录
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取文化宫当天的预约记录", notes = "")
    @PostMapping(value = "/reserve/list")
    public ResponseDTO listCPArenaReserveApp(@ApiParam(name = "parameters", value = "查询条件，cpId(文化宫id),page,pageSize", required = true) @RequestBody Map<String, Object> parameters
    ) {
        ResponseDTO responseDTO;
        try {
            parameters.put("currentDay", DateUtil.today());
            parameters.put("state",CPConstants.RESERVE_STATE_SUCCESS);
            Page<CPReserveInfoListVO> reserveInfoList = reserveInfoService.listCPReserveInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(reserveInfoList);
        } catch (Exception e) {
            logger.error("获取文化宫当天的预约记录出错", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "获取文化宫当天的预约记录出错");
        }
        return responseDTO;
    }
}
