package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.entity.activity.CPActivityPollederEntity;
import com.inspur.vista.labor.cp.entity.activity.CPActivityVoteRecordsEntity;

/**
 * @Title:
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020-03-31 10:39:24
 * @Version: 1.0
 */
public interface CPActivityVoteRecordsService extends IService<CPActivityVoteRecordsEntity> {

    /**
     * 添加一次投票
     * @param appUserInfo 投票人信息
     * @param activityId 活动ID
     * @param pollederId 投票对象ID
     */
    boolean addVotes(AppUserInfo appUserInfo, Integer activityId, Integer pollederId) throws Exception;

}
