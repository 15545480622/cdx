package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.vista.labor.cp.entity.activity.CPActivityPollederEntity;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;

/**
 * @Title:
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020-03-31 10:39:24
 * @Version: 1.0
 */
public interface CPActivityPollederService extends IService<CPActivityPollederEntity> {

    /**
     * 更新投票对象的编号、名字、简介、头像
     * @return 成功true 失败false
     */
    boolean updatePolleder(CPActivityPollederEntity activityPolleder);
}
