package com.inspur.vista.labor.cp.dao.activity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.vista.labor.cp.entity.activity.CPActivityPollederEntity;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Title: CPActivityPollederMapper
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020-03-31 10:39:33
 * @Version: 1.0
 */
public interface CPActivityPollederMapper extends BaseMapper<CPActivityPollederEntity> {


    int updatePolleder(CPActivityPollederEntity activityPolleder);

    /**
     * 增加一次投票
     */
    public void addVotes(@Param("activityId") int activityId, @Param("pollederId") int pollederId);
}
