package com.inspur.vista.labor.cp.controller.app.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CantMapper;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.entity.CantVO;
import com.inspur.vista.labor.cp.entity.activity.CpActivityJoinRecordsEntity;
import com.inspur.vista.labor.cp.exception.RRException;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import com.inspur.vista.labor.cp.service.activity.CpActivityJoinRecordsService;
import com.inspur.vista.labor.cp.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @version 1.0
 * @description app报名相关接口
 * @date 2020/03/23 10/08
 **/
@Api(tags = "文化宫活动-报名app接口")
@RestController
@RequestMapping("/api/app/cp/activity/join")
public class CpActivityJoinAppController {

    @Autowired
    private CpActivityJoinRecordsService cpActivityJoinRecordsService;
    @Autowired
    private CpActivityBaseService cpActivityBaseService;
    @Autowired
    private CantMapper cantMapper;

    @PostMapping("insert")
    @ApiOperation("报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动Id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="recordJson", value = "用户参与报名活动表单(非必填)", required = false, paramType = "query", type = "string"),
    })
    public ResponseDTO join(@ApiIgnore CpActivityJoinRecordsEntity entity, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {


        if (userInfo == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未获取到用户信息");
        }

        if (userInfo.getAuth() == null || !userInfo.getAuth()) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未认证会员不可参与活动");
        }

        entity.setUserId(userInfo.getUserCode());
        entity.setUserName(userInfo.getUserName());
        entity.setUserPhone(userInfo.getPhone());
        entity.setLaborId(userInfo.getLaborId());
        entity.setLaborName(userInfo.getLaborName());
        entity.setDistrictCode(userInfo.getDistrict());
        //获取区划名称
        CantVO cantVO = cantMapper.selectByCantCode(userInfo.getDistrict());
        if (cantVO != null) {
            entity.setDistrictName(cantVO.getCantName());
        }
        cpActivityJoinRecordsService.join(entity);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, null);
    }

    @PostMapping("groupJoin")
    @ApiOperation("团体报名")
    public ResponseDTO groupJoin(@RequestBody List<CpActivityJoinRecordsEntity> list) {
        List<String> strings = cpActivityJoinRecordsService.groupJoin(list);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, strings);
    }

    @GetMapping("groupJoinList")
    @ApiOperation("团体报名列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value =  "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value =  "条数", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="title", value =  "标题", paramType = "query", type = "string"),
            @ApiImplicitParam(name="startTime", value =  "开始时间", paramType = "query", type = "string"),
            @ApiImplicitParam(name="endTime", value =  "结束时间", paramType = "query", type = "string"),
            @ApiImplicitParam(name="laborId", value =  "基层工会id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO groupJoinList(@ApiIgnore @RequestParam Map params) {
        Page page = cpActivityBaseService.groupJoinList(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, page);
    }

    @PostMapping("getJoinCodeInfo")
    @ApiOperation("获取报名二维码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO getJoinCodeInfo(@ApiIgnore @RequestParam Map params, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {
        if (userInfo == null) {
            throw new RRException("未获取到用户信息");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("activityId", params.get("activityId"));
        map.put("userId", userInfo.getUserCode());
        String param = CPUtils.encryptQRCodeParam(map);
        String info = "type=" + QRCodeConstants.QR_CODE_TYPE_SIGN_UP_ACTIVITY + "&param=" + param;
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, info);
    }

    @PostMapping("isUse")
    @ApiOperation("二维码是否已被使用 0未使用，1已使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="userId", value = "用户id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO isUse(@ApiIgnore @RequestParam Map params) {
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, cpActivityJoinRecordsService.isUse(params));
    }

    @PostMapping("appScan")
    @ApiOperation("齐鲁工会app报名活动扫码获取活动及用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="param", value = "加密后字符串", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="userCode", value = "用户code", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO appScan(@RequestParam @ApiIgnore Map params) {

        Map info = cpActivityJoinRecordsService.appScan(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, info);
    }

    @PostMapping("appScan/confirm")
    @ApiOperation("齐鲁工会app报名活动确认扫码到场")
    @ApiImplicitParams({
            @ApiImplicitParam(name="param", value = "加密后字符串", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="userCode", value = "用户code", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO appScanConfirm(@ApiIgnore @RequestParam  Map params) {

        cpActivityJoinRecordsService.appConfirmScan(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS);
    }





}
