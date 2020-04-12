package com.inspur.vista.labor.cp.dao.activity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Title: CPCultureCouponMapper
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020/3/25 10:20
 * @Version: 1.0
 */
public interface CPCultureCouponMapper extends BaseMapper<CPCultureCouponEntity> {

    @Update("UPDATE cp_activity_coupon SET total_count = total_count + #{count},count = count + #{count} WHERE id = #{id}")
    int addCouponCount(@Param("id") int id, @Param("count") int count);

    @Update("UPDATE cp_activity_coupon SET status = #{status} WHERE id = #{id}")
    int updateCouponsOnLine(@Param("id") int id, @Param("status") int status);

    @Update("UPDATE cp_activity_coupon SET instruction = #{c.instruction}, brief = #{c.brief}"  +
            "WHERE id = #{c.id}")
    int updateCoupons(@Param("c") CPCultureCouponEntity c);

    /**
     * 减去已使用优惠券数量
     */
    void updateCount(@Param("couponId") Integer couponId, @Param("count") Integer count);

    /**
     *  查询领券活动与优惠券相关联
     */
    List<CPCultureCouponEntity> getVerificationCouponList(Page<CPCultureCouponEntity> page,@Param("laborId") String laborId,
                                                          @Param("activityName") String activityName,@Param("couponName") String couponName);

}
