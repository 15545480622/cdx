package com.inspur.vista.labor.cp.controller.app.activity;

import com.inspur.vista.labor.cp.controller.BaseController;
import com.inspur.vista.labor.cp.dao.CantMapper;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.entity.CantVO;
import com.inspur.vista.labor.cp.entity.activity.CpActivityDrawRecordsEntity;
import com.inspur.vista.labor.cp.service.activity.CpActivityDrawRecordsService;
import com.inspur.vista.labor.cp.service.activity.CpActivityGoodsService;
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

import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @version 1.0
 * @description 抽奖活动相关接口
 * @date 2020/03/19 13/31
 **/
@Api(tags = "文化宫活动app-抽奖接口")
@RestController
@RequestMapping("api/app/cp/activity/draw")
public class CpActivityDrawAppController extends BaseController {

    @Autowired
    private CpActivityDrawRecordsService cpActivityDrawRecordsService;
    @Autowired
    private CpActivityGoodsService cpActivityGoodsService;
    @Autowired
    private CantMapper cantMapper;

    @PostMapping("/draw")
    @ApiOperation("抽奖")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value =  "活动id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO draw(@ApiIgnore CpActivityDrawRecordsEntity record, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {

        if (userInfo == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未获取到用户信息");
        }

        if (userInfo.getAuth() == null || !userInfo.getAuth()) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未认证会员不可参与活动");
        }

        record.setUserId(userInfo.getUserCode());
        record.setUserName(userInfo.getUserName());
        record.setUserPhone(userInfo.getPhone());
        record.setLaborId(userInfo.getLaborId());
        record.setLaborName(userInfo.getLaborName());
        record.setDistrictCode(userInfo.getDistrict());
        //获取区划名称
        CantVO cantVO = cantMapper.selectByCantCode(userInfo.getDistrict());
        if (cantVO != null) {
            record.setDistrictName(cantVO.getCantName());
        }
        Integer goodsId = cpActivityDrawRecordsService.draw(record);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, goodsId);
    }

    @GetMapping("/currWinningRecord")
    @ApiOperation("当前用户某活动中将记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value =  "活动id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO currWinningRecord(Integer activityId, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {

        if (userInfo == null || userInfo.getUserCode() == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未获取到当前用户信息");
        }
        List<Map> maps = cpActivityDrawRecordsService.selectWinRecords(activityId);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, maps);
    }




















}
