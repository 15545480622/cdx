package com.inspur.vista.labor.cp.controller.activity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.controller.BaseController;
import com.inspur.vista.labor.cp.entity.UserAdminInfo;
import com.inspur.vista.labor.cp.entity.activity.CpActivityCouponRecordsEntity;
import com.inspur.vista.labor.cp.service.activity.CpActivityCouponRecordsService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;
import com.inspur.vista.labor.cp.service.activity.CPCultureCouponService;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @Title: CPCultureCouponController
 * @Description: 文化宫优惠券
 * @Author: ljs
 * @CreateDate: 2020/3/25 10:55
 * @Version: 1.0
 */
@Api(value = "文化宫优惠券控制类", tags = {"文化宫优惠券接口"})
@RestController
@RequestMapping("/api/cp/activity/coupon")
public class CPCultureCouponController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPCultureCouponController.class);

    @Autowired
    private CPCultureCouponService cpCultureCouponService;

    @Autowired
    private CpActivityCouponRecordsService cpActivityCouponRecordsService;


    /**
     * 查询所有优惠券信息
     */
    @ApiOperation(value = "查询所有优惠券信息", notes = "根据条件查询所有优惠券信息laborId(工会ID)，name(优惠券名称)，type(优惠券类型)，" +
            "cultureId(文化宫ID)，status(优惠券状态),startTime(开始时间)，endTime(结束时间),maxMoney(最大金额),minMoney(最小金额)，" +
            "maxCount(最大数量)，minCount(最小数量),page(当前页)，size(每页数量)", response = ResponseDTO.class)
    @GetMapping(value = "/list")
    public Object listCouponInfo(@ApiParam(name = "param", value = "查询条件") @RequestParam Map<String, Object> param) {
        ResponseDTO responseDTO;
        try {
            //分页
            Page<CPCultureCouponEntity> page = new Page<>();
            if(param.containsKey("page")){
                page.setCurrent(Integer.parseInt((String) param.get("page")));
            }else{
                page.setCurrent(1);
            }
            if(param.containsKey("size")){
                page.setSize(Integer.parseInt((String) param.get("size")));
            }else{
                page.setSize(10);
            }

            //条件注入
            EntityWrapper<CPCultureCouponEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("deleted",0);

            if(isExistNotEmpty(param,"laborId")){
                wrapper.eq("labor_id",(String)param.get("laborId"));
            }
            if(isExistNotEmpty(param,"name")){
                wrapper.like("name",(String)param.get("name"));
            }
            // 暂时不加入type
            /*if(isExistNotEmpty(param,"type")){
                wrapper.eq("type",(String)param.get("type"));
            }*/
            if(isExistNotEmpty(param,"cultureId")){
                wrapper.eq("culture_id",(String)param.get("cultureId"));
            }
            if(isExistNotEmpty(param,"cultureName")){
                wrapper.like("culture_name",(String)param.get("cultureName"));
            }
            if(isExistNotEmpty(param,"status")){
                wrapper.eq("status",Integer.parseInt(param.get("status").toString()));
            }
            if(isExistNotEmpty(param,"startTime")){
                wrapper.ge("start_time",param.get("startTime"));
            }
            if(isExistNotEmpty(param,"endTime")){
                wrapper.le("end_time",param.get("endTime"));
            }
            if(isExistNotEmpty(param,"maxMoney")){
                wrapper.le("amount",new BigDecimal(param.get("maxMoney").toString()));
            }
            if(isExistNotEmpty(param,"minMoney")){
                wrapper.ge("amount",new BigDecimal(param.get("minMoney").toString()));
            }
            if(isExistNotEmpty(param,"maxCount")){
                wrapper.le("count",Integer.parseInt(param.get("maxCount").toString()));
            }
            if(isExistNotEmpty(param,"minCount")){
                wrapper.ge("count",Integer.parseInt(param.get("minCount").toString()));
            }
            ArrayList<String> orderList = new ArrayList<>();
            orderList.add("id");
            wrapper.orderDesc(orderList);
            page = cpCultureCouponService.selectPage(page,wrapper);
            Date nowDate = new Date();

            //设置是否过期
            page.getRecords().forEach(key ->{
                //System.out.println(key.getStartTime() + "---" + key.getEndTime());
                if(key.getStartTime() != null || key.getEndTime() !=  null){
                    if(key.getStartTime().getTime() <= nowDate.getTime() && key.getEndTime().getTime() >= nowDate.getTime()){
                        //进行中
                        key.setIsPastDue(1);
                    }else if(key.getStartTime().getTime() > nowDate.getTime()){
                        //未开始
                        key.setIsPastDue(0);
                    }else{
                        //已结束
                        key.setIsPastDue(2);
                    }
                }
            });

            responseDTO = WebUtils.createSuccessResponse(page);
        } catch (Exception e) {
            logger.error("查询优惠券信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询优惠券信息失败");
        }
        return responseDTO;
    }

    /**
     * 添加优惠券数量
     */
    @ApiOperation(value = "添加优惠券数量", notes = "添加优惠券数量 id ， count（数量）", response = ResponseDTO.class)
    @PostMapping(value = "/addCouponCount")
    public Object addCouponCount(@ApiParam(name = "id", value = "查询条件：id ",required = true)Integer id,
                                 @ApiParam(name = "count", value = "查询条件：count（数量） 必须大于0",required = true)Integer count) {
        ResponseDTO responseDTO;
        try {
            if(count > 0){
                responseDTO = WebUtils.createSuccessResponse(cpCultureCouponService.addCouponCount(id,count) ? 1 : 0);
            }else{
                throw new Exception("添加数量错误");
            }
        } catch (Exception e) {
            logger.error("添加优惠券数量失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "添加优惠券数量失败");
        }
        return responseDTO;
    }

    /**
     * 添加优惠券
     */
    @ApiOperation(value = "添加优惠券", notes = "添加优惠券", response = ResponseDTO.class)
    @PostMapping(value = "/addCoupon")
    public Object addCoupon(@ApiParam(name = "param", value = "增加优惠券的值 ",required = true)@RequestBody CPCultureCouponEntity couponEntity) {
        ResponseDTO responseDTO;
        try {
            UserAdminInfo userAdminInfo = getUser();

            if(couponEntity.getTotalCount() < 0 || couponEntity.getAmount().compareTo(new BigDecimal(0)) < 0 || "".equals(couponEntity.getName())){
                throw new Exception("参数错误");
            }


            //暂时没有文化宫ID 暂时不传入
            couponEntity.setCultureId("a28f691d470441f88bb1f2ec2ecb965c");
            couponEntity.setCultureName("济南市第一文化宫");
            couponEntity.setCultureLogo("暂无Logo");
            couponEntity.setCultureAddress("暂无地址");

            couponEntity.setLaborCode(userAdminInfo.getLaborCode());
            couponEntity.setLaborId(userAdminInfo.getLaborId());
            couponEntity.setLaborName(userAdminInfo.getLaborName());
            couponEntity.setOperatorId(userAdminInfo.getUserId());
            couponEntity.setOperatorName(userAdminInfo.getUserName());

            couponEntity.setCount(couponEntity.getTotalCount());
            couponEntity.setStatus(0);
            couponEntity.setDeleted(0);
            couponEntity.setCreateTime(new Date());
            responseDTO = WebUtils.createSuccessResponse(cpCultureCouponService.insert(couponEntity) ? 1 : 0);

        } catch (Exception e) {
            logger.error("添加优惠券失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "添加优惠券失败");
        }
        return responseDTO;
    }

    /**
     * 修改优惠券信息
     */
    @ApiOperation(value = "修改优惠券信息", notes = "修改优惠券信息", response = ResponseDTO.class)
    @PostMapping(value = "/updateCoupon")
    public Object updateCoupon(@ApiParam(name = "param", value = "修改优惠券信息 ",required = true)@RequestBody CPCultureCouponEntity couponEntity) {
        ResponseDTO responseDTO;
        try {
            responseDTO = WebUtils.createSuccessResponse(cpCultureCouponService.updateCoupons(couponEntity) ? 1 : 0);
        } catch (Exception e) {
            logger.error("修改优惠券信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "修改优惠券信息失败");
        }
        return responseDTO;
    }


    /**
     * 删除优惠券
     */
    @ApiOperation(value = "删除优惠券", notes = "删除优惠券 id", response = ResponseDTO.class)
    @PostMapping(value = "/deleteCoupon")
    public Object deleteCoupon(@ApiParam(name = "id", value = "查询条件：id ",required = true)Integer id) {
        ResponseDTO responseDTO;
        try {
            EntityWrapper<CPCultureCouponEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("id",id);
            boolean isDel = cpCultureCouponService.delete(wrapper);
            if(isDel){
                wrapper = new EntityWrapper<>();
                wrapper.eq("id",id);
                wrapper.eq("deleted",1);


                responseDTO = WebUtils.createSuccessResponse(cpCultureCouponService.selectList(wrapper).size() == 0 ? 1 : 0);
            }else{
                responseDTO = WebUtils.createSuccessResponse(0);
            }
        } catch (Exception e) {
            logger.error("删除优惠券失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "删除优惠券失败");
        }
        return responseDTO;
    }

    /**
     * 优惠券上下线
     */
    @ApiOperation(value = "优惠券上下线", notes = "优惠券上下线 id ， status（0下架 1上架）", response = ResponseDTO.class)
    @PostMapping(value = "/updateCouponsOnLine")
    public Object updateCouponsOnLine(@ApiParam(name = "id", value = "查询条件：id ",required = true)Integer id,
                                 @ApiParam(name = "status", value = "查询条件：status（0下架 1上架）",required = true)Integer status) {
        ResponseDTO responseDTO;
        try {
            responseDTO = WebUtils.createSuccessResponse(cpCultureCouponService.updateCouponsOnLine(id,status) ? 1 : 0);
        } catch (Exception e) {
            logger.error("优惠券上下线失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "优惠券上下线失败");
        }
        return responseDTO;
    }


    /**
     * 查询核销优惠券信息
     */
    @ApiOperation(value = "查询核销优惠券信息", notes = "laborId（工会ID） activityId(活动ID)  " +
            "page(当前页)，size(每页数量)", response = ResponseDTO.class)
    @GetMapping(value = "/verificationCouponList")
    public Object verificationCouponList(@ApiParam(name = "laborId", value = "所属组织机构ID") @RequestParam(required = false) String laborId,
                                         @ApiParam(name = "activityName", value = "活动名称") @RequestParam(required = false) String activityName,
                                         @ApiParam(name = "couponName", value = "卡券名称") @RequestParam(required = false) String couponName,
                                         @ApiParam(name = "current", value = "第几页") @RequestParam(required = false) Integer current,
                                         @ApiParam(name = "size", value = "每页几条") @RequestParam(required = false) Integer size) {
        ResponseDTO responseDTO;
        try {
            //分页
            Page<CPCultureCouponEntity> page = new Page<>();
            if(current != null){
                page.setCurrent(current);
            }else{
                page.setCurrent(1);
            }
            if(size != null){
                page.setSize(size);
            }else{
                page.setSize(10);
            }


            responseDTO = WebUtils.createSuccessResponse(cpCultureCouponService.getVerificationCouponList(page,laborId,activityName,couponName));
        } catch (Exception e) {
            logger.error("查询优惠券信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询优惠券信息失败");
        }
        return responseDTO;
    }

    /**
     * 查询核销优惠券领取信息
     */
    @ApiOperation(value = "查询核销优惠券领取信息", notes = "查询核销优惠券领取信息 " +
            "page(当前页)，size(每页数量)", response = ResponseDTO.class)
    @GetMapping(value = "/verificationDetailList")
    public Object verificationDetailList(@ApiParam(name = "couponId", value = "兑换券ID", required = true) @RequestParam Integer couponId,
                                         @ApiParam(name = "activityId", value = "活动ID", required = true) @RequestParam Integer activityId,
                                         /*@ApiParam(name = "activityName", value = "活动名称") @RequestParam(required = false) String activityName,
                                         @ApiParam(name = "name", value = "优惠券名称") @RequestParam(required = false) String name,*/
                                         @ApiParam(name = "userName", value = "用户名称") @RequestParam(required = false) String userName,
                                         @ApiParam(name = "isConversion", value = "是否是已兑换") @RequestParam(required = false) String isConversion,
                                         @ApiParam(name = "current", value = "第几页") @RequestParam(required = false) Integer current,
                                         @ApiParam(name = "size", value = "每页几条") @RequestParam(required = false) Integer size) {
        ResponseDTO responseDTO;
        try {
            //分页
            Page<CpActivityCouponRecordsEntity> page = new Page<>();
            if(current != null){
                page.setCurrent(current);
            }else{
                page.setCurrent(1);
            }
            if(size != null){
                page.setSize(size);
            }else{
                page.setSize(10);
            }

            EntityWrapper<CpActivityCouponRecordsEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("activity_id", activityId);
            wrapper.eq("coupon_id", couponId);
            if("1".equals(isConversion)){
                wrapper.eq("status","1");
            }
            if(userName != null && !"".equals(userName)){
                wrapper.like("user_name",userName);
            }


            responseDTO = WebUtils.createSuccessResponse(cpActivityCouponRecordsService.selectPage(page,wrapper));
        } catch (Exception e) {
            logger.error("查询优惠券信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询优惠券信息失败");
        }
        return responseDTO;
    }


    /**
     * map中是否存在key 且toString不为空值
     * @param map map
     * @param key key
     * @return  存在且不为空值 返回true 否则false
     */
    static boolean isExistNotEmpty(Map map, Object key){
        return map.containsKey(key) && !map.get(key).toString().equals("");
    }

}
