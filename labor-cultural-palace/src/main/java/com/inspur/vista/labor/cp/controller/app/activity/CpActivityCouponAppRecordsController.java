package com.inspur.vista.labor.cp.controller.app.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.entity.activity.CpActivityCouponRecordsEntity;
import com.inspur.vista.labor.cp.service.activity.CpActivityCouponRecordsService;
import com.inspur.vista.labor.cp.util.*;
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
 * 券使用记录
 * 
 * @author 
 * @email 
 * @date 2020-03-27 10:38:38
 */
@Api(tags = "文化宫活动app--领券接口")
@RestController
@RequestMapping("api/app/cp/activity/coupon/records")
public class CpActivityCouponAppRecordsController {


	@Autowired
	private CpActivityCouponRecordsService cpActivityCouponRecordsService;


	@ApiOperation("领取活动券")
	@GetMapping(value = "/ticket")
	@ApiImplicitParams({
			@ApiImplicitParam(name="activityId", value = "活动Id", required = true, paramType = "query", type = "string")
	})
	public ResponseDTO getTicket(@ApiIgnore CpActivityCouponRecordsEntity recordsEntity, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {

        if (userInfo == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未获取到用户信息");
        }

        if (userInfo.getAuth() == null || !userInfo.getAuth()) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未认证会员不可参与活动");
        }

        recordsEntity.setUserId(userInfo.getUserCode());
        recordsEntity.setUserName(userInfo.getUserName());
        recordsEntity.setUserPhone(userInfo.getPhone());
        recordsEntity.setLaborId(userInfo.getLaborId());
        recordsEntity.setLaborName(userInfo.getLaborName());
        recordsEntity.setDistrictCode(userInfo.getDistrict());
        recordsEntity.setUserCardNum(userInfo.getMaskingIdNo());

		cpActivityCouponRecordsService.getTicket(recordsEntity);
		return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, null);
	}

	@ApiOperation("我的优惠券列表")
	@GetMapping(value = "/getMycoupons")
	@ApiImplicitParams({
			@ApiImplicitParam(name="type", value = "type 券类型：0未使用，1已使用， 2过期", required = false, paramType = "query", type = "string"),
			@ApiImplicitParam(name="page", value = "页码", required = false, paramType = "query", type = "string"),
			@ApiImplicitParam(name="pageSize", value = "条数", required = false, paramType = "query", type = "string")
	})
	public ResponseDTO getMycoupons(@RequestParam @ApiIgnore Map params, @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {

	    //获取用户id
        if (userInfo == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未获取到用户信息");
        }
        params.put("userId", userInfo.getUserCode());
		Page page = cpActivityCouponRecordsService.selectCouponByUserId(params);
		return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, page);
	}

    @PostMapping("getTicketCodeInfo")
    @ApiOperation("获取领券活动二维码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value = "活动领取记录id", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO getTicketCodeInfo(@ApiIgnore @RequestParam Map params) {
        Map<String, Object> map = new HashMap<>();
        map.put("recordId", params.get("id"));
        String param = CPUtils.encryptQRCodeParam(map);
        String info = "type=" + QRCodeConstants.QR_CODE_TYPE_COUPON_ACTIVITY + "&param=" + param;
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, info);
    }





}
