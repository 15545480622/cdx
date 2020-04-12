package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.inspur.vista.labor.cp.entity.activity.CpActivityCouponRecordsEntity;

import java.util.Map;

/**
 * 券使用记录
 * 
 * @author 
 * @email 
 * @date 2020-03-27 10:38:38
 */
public interface CpActivityCouponRecordsService extends IService<CpActivityCouponRecordsEntity> {

    /**
     * 领取活动券
     */
    void getTicket(CpActivityCouponRecordsEntity recordsEntity);


    /**
     * 获取我的优惠券列表
     * @param params
     * @return
     */
    Page selectCouponByUserId(Map params);

    /**
     * 领券活动扫码信息确认
     * @param params
     * @return
     */
    Map<String, Object> scan(Map params);

    /**
     * 小程序完成扫码操作
     * @param params
     */
    void confirmScan(Map params);

    /**
     * 领券活动扫码列表
     * @param params
     * @return
     */
    Page<Map> scanTicketActivityList(Map params);

    /**
     * 领券活动扫码详情列表
     * @param params
     * @return
     */
    Page<Map> scanTicketDetailsActivityList(Map params);

    /**
     * 领券活动扫码详情列表
     */
    Page<Map> couponRecordPage(Map params);
}
