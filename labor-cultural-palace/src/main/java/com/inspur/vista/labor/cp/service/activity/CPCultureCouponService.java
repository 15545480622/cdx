package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;

import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020/3/25 10:49
 * @Version: 1.0
 */
public interface CPCultureCouponService extends IService<CPCultureCouponEntity> {

    /**
     * 添加优惠券数量
     * @param id 主键
     * @param count 增加的数量
     * @return 是否成功
     */
    boolean addCouponCount(Integer id,Integer count);

    /**
     * 优惠券上下线
     * @param id 主键
     * @param status 0下架 1上架
     * @return 是否成功
     */
    boolean updateCouponsOnLine(Integer id,Integer status);

    /**
     * 更新优惠券的部分内容
     *  文化宫名
     *  优惠券名
     *  优惠券数量
     *  有效日期
     *  优惠券金额
     *  次数
     *  使用条件
     *  简介
     * @param couponEntity 参数
     * @return
     */
    boolean updateCoupons(CPCultureCouponEntity couponEntity);


    /**
     * 查询领券活动与优惠券相关联
     * @param page 分页信息
     * @param laborId 组织机构ID
     * @param activityName 活动名称
     * @param couponName 优惠券名称
     */
    Page<CPCultureCouponEntity>  getVerificationCouponList(Page<CPCultureCouponEntity> page,String laborId,String activityName,String couponName);

}
