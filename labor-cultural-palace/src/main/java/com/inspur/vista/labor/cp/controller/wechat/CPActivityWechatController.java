package com.inspur.vista.labor.cp.controller.wechat;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import com.inspur.vista.labor.cp.service.activity.CpActivityCouponRecordsService;
import com.inspur.vista.labor.cp.service.activity.CpActivityJoinRecordsService;
import com.inspur.vista.labor.cp.util.CPUtils;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author wangzi
 * @version 1.0
 * @description 小程序 活动扫码相关接口
 * @date 2020/03/31 13/41
 **/
@Api(tags = "微信小程序活动扫码相关接口")
@RestController
@RequestMapping("/api/wechat/cp/activity")
public class CPActivityWechatController {

    @Autowired
    private CpActivityJoinRecordsService cpActivityJoinRecordsService;
    @Autowired
    private CpActivityBaseService cpActivityBaseService;
    @Autowired
    private CpActivityCouponRecordsService cpActivityCouponRecordsService;

    @PostMapping("join/scan")
    @ApiOperation("报名小程序扫码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="param", value = "二维码加密字符串", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="cpId", value = "文化宫id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO joinScan(@ApiIgnore @RequestParam Map params) {
        //二维码解密，获取活动id和用户id
        String param = (String) params.get("param");
        Map<String, Object> info = CPUtils.decryptQRCodeParam(param);
        params.put("activityId", info.get("activityId"));
        params.put("userId", info.get("userId"));
        Map<String, Object> map = cpActivityJoinRecordsService.scan(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, map);
    }

    @PostMapping("join/confirmScan")
    @ApiOperation("报名小程序确认扫码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="param", value = "二维码加密字符串", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="scanUserId", value = "扫码者id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="cpId", value = "文化宫id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO joinConfirmScan(@ApiIgnore @RequestParam Map params) {
        //二维码解密，获取活动id和用户id
        String param = (String) params.get("param");
        Map<String, Object> info = CPUtils.decryptQRCodeParam(param);
        params.put("activityId", info.get("activityId"));
        params.put("userId", info.get("userId"));
        cpActivityJoinRecordsService.confirmScan(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, null);
    }

    @PostMapping("join/scanJoinActivityList")
    @ApiOperation("小程序-扫描报名活动列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cpId", value = "文化宫id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value = "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO scanJoinActivityList(@RequestParam @ApiIgnore Map params) {

        Page<Map> page = cpActivityBaseService.scanJoinActivityList(params);
        return WebUtils.createSuccessResponse(page);
    }

    @PostMapping("join/scanJoinDetailsActivityList")
    @ApiOperation("小程序-扫描报名活动详情列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value = "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO scanJoinDetailsActivityList(@RequestParam @ApiIgnore Map params) {

        Page<Map> page = cpActivityJoinRecordsService.scanJoinDetailsActivityList(params);
        return WebUtils.createSuccessResponse(page);
    }

    @PostMapping("coupon/scan")
    @ApiOperation("领券小程序扫码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="param", value = "二维码加密字符串", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="cpId", value = "文化宫id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO couponScan(@ApiIgnore @RequestParam Map params) {
        //二维码解密，获取活动参与记录id
        String param = (String) params.get("param");
        Map<String, Object> info = CPUtils.decryptQRCodeParam(param);
        params.put("recordId", info.get("recordId"));
        Map<String, Object> map = cpActivityCouponRecordsService.scan(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, map);
    }

    @PostMapping("coupon/confirmScan")
    @ApiOperation("领券确认扫码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="param", value = "二维码加密字符串", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="scanUserId", value = "扫码者id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="cpId", value = "文化宫id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO confirmScan(@ApiIgnore @RequestParam Map params) {
        //二维码解密，获取活动参与记录id
        String param = (String) params.get("param");
        Map<String, Object> info = CPUtils.decryptQRCodeParam(param);
        params.put("recordId", info.get("recordId"));
        cpActivityCouponRecordsService.confirmScan(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, null);
    }

    @PostMapping("coupon/scanJoinActivityList")
    @ApiOperation("小程序-获取领券活动扫码列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cpId", value = "文化宫id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value = "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO scanTicketActivityList(@RequestParam @ApiIgnore Map params) {

        Page<Map> page = cpActivityCouponRecordsService.scanTicketActivityList(params);
        return WebUtils.createSuccessResponse(page);
    }

    @PostMapping("coupon/scanTicketDetailsActivityList")
    @ApiOperation("小程序-扫码领券活动详情列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value = "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO scanTicketDetailsActivityList(@RequestParam @ApiIgnore Map params) {

        Page<Map> page = cpActivityCouponRecordsService.scanTicketDetailsActivityList(params);
        return WebUtils.createSuccessResponse(page);
    }



}
