package com.inspur.vista.labor.cp.service.activity.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.labor.cp.dao.activity.CPCultureCouponMapper;
import com.inspur.vista.labor.cp.dao.activity.CheckEntity;
import com.inspur.vista.labor.cp.dao.activity.CpActivityCouponRecordsDao;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityBaseEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityCouponRecordsEntity;
import com.inspur.vista.labor.cp.exception.RRException;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import com.inspur.vista.labor.cp.service.activity.CpActivityCouponRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("cpActivityCouponRecordsService")
public class CpActivityCouponRecordsServiceImpl extends ServiceImpl<CpActivityCouponRecordsDao, CpActivityCouponRecordsEntity> implements CpActivityCouponRecordsService {

    @Autowired
    private CpActivityBaseService cpActivityBaseService;
    @Autowired
    private CpActivityCouponRecordsService cpActivityCouponRecordsService;
    @Autowired
    private CPCultureCouponMapper cpCultureCouponMapper;


    /**
     * 领取活动券
     */
    @Override
    public void getTicket(CpActivityCouponRecordsEntity recordsEntity) {

        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setActivityId(recordsEntity.getActivityId());
        checkEntity.setUserId(recordsEntity.getUserId());
        checkEntity.setLaborId(recordsEntity.getLaborId());
        cpActivityBaseService.isMeet(checkEntity);
        //获取活动信息
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(recordsEntity.getActivityId());
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        //获取优惠券信息
        CPCultureCouponEntity coupon = cpCultureCouponMapper.selectById(activity.getCouponId());
        if (coupon == null) {
            throw new RRException("未获取到优惠券信息");
        }
        if (new Date().after(coupon.getEndTime())){
            throw new RRException("优惠券已过期，不可领取");
        }
        //判断优惠券剩余数量
        EntityWrapper<CpActivityCouponRecordsEntity> couponRecordsWrapper = new EntityWrapper<>();
        couponRecordsWrapper.eq("activity_id", recordsEntity.getActivityId());
        couponRecordsWrapper.eq("user_id", recordsEntity.getUserId());
        int receivedCount = cpActivityCouponRecordsService.selectCount(couponRecordsWrapper);
        if (receivedCount >= activity.getCouponCount()) {
            throw new RRException("券已领光");
        }
        //优惠券领取限制
        if ("everyDay".equals(activity.getCondition())) {
            //每天限制次数，判断当天次数是否满足
            Integer takeCount = activity.getTakeCount();
            //获取该用户当天领取优惠券数量
            int currentDayCount = baseMapper.selectCurrentDayCount(recordsEntity);
            if (currentDayCount > takeCount) {
                throw new RRException("当天领取次数已达上限");
            }

        }
        if (!"everyDay".equals(activity.getCondition())) {
            //只能领取一次，查看用户是否领取过
            EntityWrapper<CpActivityCouponRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("user_id", recordsEntity.getUserId());
            recordWrapper.eq("activity_id", recordsEntity.getActivityId());
            Integer count = baseMapper.selectCount(recordWrapper);
            if (count != null && count > 0) {
                throw new RRException("该活动只能参与一次");
            }
        }
        recordsEntity.setCouponId(activity.getCouponId());
        cpActivityCouponRecordsService.insert(recordsEntity);
    }

    /**
     * 获取我的优惠券列表
     */
    @Override
    public Page selectCouponByUserId(Map params) {

        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.selectCouponByUserId(p, params);
        p.setRecords(list);
        return p;
    }

    /**
     * 领券活动扫码信息确认
     */
    @Override
    public Map<String, Object> scan(Map params) {
        return checkScan(params);
    }

    /**
     * 小程序完成扫码操作
     *
     * @param params
     */
    @Override
    public void confirmScan(Map params) {
        checkScan(params);
        Integer recordId = Integer.valueOf(String.valueOf(params.get("recordId")));
        CpActivityCouponRecordsEntity recordEntity = new CpActivityCouponRecordsEntity();
        recordEntity.setId(recordId);
        recordEntity.setStatus("1");
        recordEntity.setUseTime(new Date());
        recordEntity.setScanUserId((String) params.get("scanUserId"));
        baseMapper.updateById(recordEntity);
    }

    /**
     * 领券活动扫码列表
     *
     * @param params
     * @return
     */
    @Override
    public Page<Map> scanTicketActivityList(Map params) {
        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.scanTicketActivityList(p, params);
        p.setRecords(list);
        return p;
    }

    /**
     * 领券活动扫码详情列表
     *
     * @param params
     * @return
     */
    @Override
    public Page<Map> scanTicketDetailsActivityList(Map params) {
        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.scanJoinDetailsActivityList(p, params);
        p.setRecords(list);
        return p;
    }

    /**
     * 扫码信息校验
     * @param params
     * @return
     */
    private Map<String, Object> checkScan(Map params) {
        Map<String, Object> result = new HashMap<>();

        Integer recordId = Integer.valueOf(String.valueOf(params.get("recordId")));
        String cpId = String.valueOf(params.get("cpId"));
        //获取活动参与记录
        CpActivityCouponRecordsEntity record = baseMapper.selectById(recordId);
        if (record == null) {
            throw new RRException("未获取到活动参与记录");
        }
        if ("1".equals(record.getStatus())) {
            throw new RRException("优惠券已被使用");
        }
        //获取活动信息
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(record.getActivityId());
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        //判断文化宫是否匹配
        if (!cpId.equals(activity.getCpId())) {
            throw new RRException("扫码者文化宫id与活动文化宫id不匹配");
        }
        //获取优惠券信息
        CPCultureCouponEntity coupon = cpCultureCouponMapper.selectById(activity.getCouponId());
        if (coupon == null) {
            throw new RRException("未获取到优惠券信息");
        }
        Date date = new Date();
        if (date.before(coupon.getStartTime())) {
            throw new RRException("未到优惠券使用时间");
        }
        if (date.after(coupon.getEndTime())) {
            throw new RRException("优惠券已过期");
        }

        //返回确认信息
        result.put("activityTitle", activity.getTitle());
        result.put("activityStartTime", activity.getStartTime());
        result.put("activityEndTime", activity.getEndTime());
        result.put("userName", record.getUserName());
        result.put("userLaborName", record.getLaborName());
        return result;
    }

    /**
     * 领券活动参与情况
     */
    @Override
    public Page<Map> couponRecordPage(Map params) {
        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.selectRecordsByActivityId(p, params);
        p.setRecords(list);
        return p;
    }
}
