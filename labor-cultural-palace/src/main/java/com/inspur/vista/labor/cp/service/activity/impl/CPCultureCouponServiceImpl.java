package com.inspur.vista.labor.cp.service.activity.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.labor.cp.dao.activity.CPCultureCouponMapper;
import com.inspur.vista.labor.cp.dao.activity.CpActivityCouponRecordsDao;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityCouponRecordsEntity;
import com.inspur.vista.labor.cp.service.activity.CPCultureCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020/3/25 10:51
 * @Version: 1.0
 */
@Service
public class CPCultureCouponServiceImpl extends ServiceImpl<CPCultureCouponMapper, CPCultureCouponEntity>
        implements CPCultureCouponService {

    @Autowired
    private CPCultureCouponMapper cpCultureCouponMapper;

    @Autowired
    private CpActivityCouponRecordsDao cpActivityCouponRecordsDao;


    @Override
    public boolean addCouponCount(Integer id, Integer count) {
        return cpCultureCouponMapper.addCouponCount(id,count) == 1;
    }

    @Override
    public boolean updateCouponsOnLine(Integer id, Integer status) {
        return cpCultureCouponMapper.updateCouponsOnLine(id,status) == 1;
    }

    @Override
    public boolean updateCoupons(CPCultureCouponEntity couponEntity) {
        return cpCultureCouponMapper.updateCoupons(couponEntity) == 1;
    }

    @Override
    public Page<CPCultureCouponEntity> getVerificationCouponList(Page<CPCultureCouponEntity> page,String laborId,String activityName,String couponName) {
        List<CPCultureCouponEntity> dataList = cpCultureCouponMapper.getVerificationCouponList(page,laborId,activityName,couponName);
        dataList.forEach((key) -> {

            EntityWrapper<CpActivityCouponRecordsEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("coupon_id",key.getId());
            List<CpActivityCouponRecordsEntity> entityList = cpActivityCouponRecordsDao.selectList(wrapper);
            //判断卡券的已兑换数量
            int conversionCount = 0;
            for(CpActivityCouponRecordsEntity c : entityList){
                if("1".equals(c.getStatus())){
                    conversionCount++;
                }
            }

            //设置领取数量和已使用数量
            key.setConversionCount(conversionCount);
            key.setGetCount(entityList.size());

        });
        page.setRecords(dataList);

        return page;
    }
}
