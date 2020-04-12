package com.inspur.vista.labor.cp.dao.activity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.activity.CpActivityCouponRecordsEntity;

import java.util.List;
import java.util.Map;

/**
 * 券使用记录
 * 
 * @author 
 * @email 
 * @date 2020-03-27 10:38:38
 */
public interface CpActivityCouponRecordsDao extends BaseMapper<CpActivityCouponRecordsEntity> {

    /**
     * 查询我的优惠券
     */
    List<Map> selectCouponByUserId(Page page, Map params);

    /**
     * 获取会员当天领取优惠券次数
     * @param record
     * @return
     */
    int selectCurrentDayCount(CpActivityCouponRecordsEntity record);

    /**
     * 领券活动小程序扫码列表
     * @param p
     * @param params
     * @return
     */
    List<Map> scanTicketActivityList(Page<Map> p, Map params);

    /**
     * 领券活动详情列表
     * @param p
     * @param params
     * @return
     */
    List<Map> scanJoinDetailsActivityList(Page<Map> p, Map params);

    /**
     * 获取领券参与情况
     */
    List<Map> selectRecordsByActivityId(Page<Map> p, Map params);
}
