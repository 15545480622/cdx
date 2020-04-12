package com.inspur.vista.labor.cp.service.activity.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.labor.cp.dao.activity.CPActivityPollederMapper;
import com.inspur.vista.labor.cp.dao.activity.CPCultureCouponMapper;
import com.inspur.vista.labor.cp.entity.activity.CPActivityPollederEntity;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;
import com.inspur.vista.labor.cp.service.activity.CPActivityPollederService;
import com.inspur.vista.labor.cp.service.activity.CPCultureCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020-03-31 10:39:19
 * @Version: 1.0
 */
@Service
public class CPActivityPollederServiceImpl extends ServiceImpl<CPActivityPollederMapper, CPActivityPollederEntity>
        implements CPActivityPollederService {

    @Autowired
    CPActivityPollederMapper activityPollederMapper;

    @Override
    public boolean updatePolleder(CPActivityPollederEntity activityPolleder) {
        return activityPollederMapper.updatePolleder(activityPolleder) == 1;
    }
}
