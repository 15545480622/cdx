package com.inspur.vista.labor.cp.controller.app.activity;

import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzi
 * @version 1.0
 * @description 活动app接口
 * @date 2020/03/20 10/49
 **/
@Api(tags = "文化宫活动app接口")
@RestController
@RequestMapping("/api/app/cp/activity")
public class CpActivityBaseAppController {

    @Autowired
    private CpActivityBaseService cpActivityBaseService;

    @GetMapping("list")
    @ApiOperation("获取活动分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cpId", value = "文化宫id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="isStart", value = "开始状态(0未开始，1进行中，2已结束)", paramType = "query", type = "string"),
            @ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value = "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO getList(@RequestParam @ApiIgnore Map params) {

        return WebUtils.createSuccessResponse(cpActivityBaseService.getListForApp(params));
    }

    @GetMapping("getMyList")
    @ApiOperation("获取我参与的活动分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="isStart", value = "开始状态(0未开始，1进行中，2已结束)", paramType = "query", type = "string"),
            @ApiImplicitParam(name="page", value = "页码", paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value = "条数", paramType = "query", type = "string"),
    })
    public ResponseDTO getMyList(@RequestParam @ApiIgnore Map params, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {

        if (userInfo == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未获取到用户信息");
        }
        params.put("userId", userInfo.getUserCode());
        return WebUtils.createSuccessResponse(cpActivityBaseService.getMyList(params));
    }

    @GetMapping("info")
    @ApiOperation("获取活动详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value = "活动id", required = true, paramType = "query", type = "string")
    })
    public ResponseDTO getInfo(@RequestParam @ApiIgnore Map params, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {

        if(userInfo != null) {
            params.put("userId", userInfo.getUserCode());
        }

        Map map = cpActivityBaseService.getInfoForApp(params);
        return WebUtils.createSuccessResponse(map);
    }

    @GetMapping("/me/activityStatus/{activityId}")
    @ApiOperation("用户是否还可再参与活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", required = true, paramType = "path", type = "string")
    })
    public ResponseDTO getMyActivityStatus(@PathVariable @ApiIgnore String activityId, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {
        Map map = new HashMap();
        //空用户或未认证直接反回可参与
        if (userInfo == null || userInfo.getAuth() == null) {
            map.put("canjoin", true);
            return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, map);
        }
        boolean canjoin =  cpActivityBaseService.getMyActivityStatus(activityId, userInfo.getUserCode());
        map.put("canjoin", canjoin);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, map);
    }




}
