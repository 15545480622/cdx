package com.inspur.vista.labor.cp.controller.activity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.inspur.vista.labor.cp.controller.BaseController;
import com.inspur.vista.labor.cp.entity.UserAdminInfo;
import com.inspur.vista.labor.cp.entity.activity.CpActivityFormEntity;
import com.inspur.vista.labor.cp.service.activity.CpActivityFormService;
import com.inspur.vista.labor.cp.util.CPUtils;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzi
 * @version 1.0
 * @description 报名活动相关接口
 * @date 2020/03/19 13/31
 **/
@Api(tags = "文化宫活动报名接口")
@RestController
@RequestMapping("api/cp/activity/join")
public class CpActivityJoinController extends BaseController {

    @Autowired
    private CpActivityFormService cpActivityFormService;

    /**
     * 保存活动表单
     * @param activityFormEntity
     * @return
     */
    @PostMapping("/activityForm")
    @ApiOperation("创建报名活动表单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", paramType = "path", type = "int"),
            @ApiImplicitParam(name="contentJson", value = "表单json", paramType = "query", type = "string")
    })
    public ResponseDTO saveActivityForm(@RequestBody CpActivityFormEntity activityFormEntity) {

        UserAdminInfo user = getUser();
        if (user == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR,"未获取到当前用户信息");
        }

        activityFormEntity.setLaborId(user.getLaborId());
        activityFormEntity.setLaborId(user.getUserId());
        activityFormEntity.setLaborName(user.getLaborName());
        activityFormEntity.setLaborCode(user.getLaborCode());
        cpActivityFormService.insert(activityFormEntity);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,"");
    }

    /**
     * 修改表单
     * @param activityFormEntity
     * @return
     */
    @PutMapping("/updateActivityForm")
    @ApiOperation("修改活动表单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", paramType = "path", type = "int"),
            @ApiImplicitParam(name="contentJson", value = "表单json", paramType = "query", type = "string")
    })
    public ResponseDTO updateActivityForm(@RequestBody CpActivityFormEntity activityFormEntity) {
        EntityWrapper<CpActivityFormEntity> formWrapper = new EntityWrapper<>();
        formWrapper.eq("activity_id", activityFormEntity.getActivityId());
        cpActivityFormService.update(activityFormEntity, formWrapper);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,null);
    }

    /**
     * 获取表单
     * @param activityId
     */
    @GetMapping("/getActivityForm/{activityId}")
    @ApiOperation("获取报名活动表单")
    @ApiImplicitParam(name="activityId", value = "活动id", paramType = "query", type = "string")
    public ResponseDTO getActivityForm(@PathVariable int activityId) {
        EntityWrapper<CpActivityFormEntity> formWrapper = new EntityWrapper<>();
        formWrapper.eq("activity_id", activityId);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, cpActivityFormService.selectOne(formWrapper));
    }

    /**
     * 获取表单
     */
    @GetMapping("/getJoinScanInfo/{activityId}")
    @ApiOperation("获取文化宫活动二维码信息")
    @ApiImplicitParam(name="activityId", value = "活动id", paramType = "path", type = "string")
    public ResponseDTO getJoinScanInfo(@PathVariable String activityId) {
        Map<String, Object> params = new HashMap<>();
        params.put("activityId", activityId);
        Map<String, Object> map = new HashMap<>();
        map.put("type", "palaceJoinActivity");
        map.put("param", CPUtils.encryptQRCodeParam(params));
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, map);
    }



}
