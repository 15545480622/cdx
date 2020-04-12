package com.inspur.vista.labor.cp.service.activity.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.labor.cp.dao.CPInfoMapper;
import com.inspur.vista.labor.cp.dao.activity.CPActivityPollederMapper;
import com.inspur.vista.labor.cp.dao.activity.CPActivityVoteRecordsMapper;
import com.inspur.vista.labor.cp.dao.activity.CheckEntity;
import com.inspur.vista.labor.cp.dao.activity.CpActivityCouponRecordsDao;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.entity.CPInfoVO;
import com.inspur.vista.labor.cp.entity.activity.CPActivityPollederEntity;
import com.inspur.vista.labor.cp.entity.activity.CPActivityVoteRecordsEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityBaseEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityCouponRecordsEntity;
import com.inspur.vista.labor.cp.exception.RRException;
import com.inspur.vista.labor.cp.service.CPInfoService;
import com.inspur.vista.labor.cp.service.activity.CPActivityPollederService;
import com.inspur.vista.labor.cp.service.activity.CPActivityVoteRecordsService;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author: ljs
 * @CreateDate: 2020-03-31 10:39:19
 * @Version: 1.0
 */
@Service
public class CPActivityVoteRecordsServiceImpl extends ServiceImpl<CPActivityVoteRecordsMapper, CPActivityVoteRecordsEntity>
        implements CPActivityVoteRecordsService {

    @Autowired
    private CPInfoMapper cpInfoMapper;

    @Autowired
    private CpActivityBaseService activityBaseService;

    @Autowired
    private CpActivityCouponRecordsDao activityCouponRecordsDao;

    @Autowired
    private CPActivityPollederMapper activityPollederMapper;

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addVotes(AppUserInfo appUserInfo, Integer activityId, Integer pollederId) throws Exception {
        //获取投票用户所属工会信息
        CPInfoVO infoVO = cpInfoMapper.selectByPrimaryKey(appUserInfo.getLaborId());
        //获取活动详情
        CpActivityBaseEntity activityBaseEntity = activityBaseService.selectById(activityId);

        //判断活动起止时间
        Date nowDate = new Date();
        if(activityBaseEntity.getStartTime().getTime() > nowDate.getTime()
                || activityBaseEntity.getEndTime().getTime() < nowDate.getTime()){
            throw new RRException("不在活动时间内");
        }

        //判断是否有资格参加活动
        if(!appUserInfo.getAuth()){
            throw new RRException("非会员");
        }
        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setActivityId(activityId);
        checkEntity.setLaborId(appUserInfo.getLaborId());
        checkEntity.setUserId(appUserInfo.getUserCode());
        activityBaseService.isMeet(checkEntity);

        //判断活动类型
        if(!"vote".equals(activityBaseEntity.getType())){
            throw new RRException("活动类型不符");
        }

        //判断投票限制
        CpActivityCouponRecordsEntity recordsEntity = new CpActivityCouponRecordsEntity();
        recordsEntity.setActivityId(activityId);
        recordsEntity.setUserId(appUserInfo.getUserCode());
        if ("everyDay".equals(activityBaseEntity.getCondition())) {
            //每天限制次数，判断当天次数是否满足
            Integer takeCount = activityBaseEntity.getTakeCount();
            //获取该用户当天领取优惠券数量
            int currentDayCount = activityCouponRecordsDao.selectCurrentDayCount(recordsEntity);
            if (currentDayCount > takeCount) {
                throw new RRException("当天领取次数已达上限");
            }

        }
        if (!"everyDay".equals(activityBaseEntity.getCondition())) {
            //只能领取一次，查看用户是否领取过
            EntityWrapper<CpActivityCouponRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("user_id", recordsEntity.getUserId());
            recordWrapper.eq("activity_id", recordsEntity.getActivityId());
            Integer count = activityCouponRecordsDao.selectCount(recordWrapper);
            if (count != null && count > 0) {
                throw new RRException("该活动只能参与一次");
            }
        }

        //判断是否为该活动的投票对象
        CPActivityPollederEntity activityPollederEntity = activityPollederMapper.selectById(pollederId);
        if(activityPollederEntity == null || activityPollederEntity.getActivityId().compareTo(activityId) != 0){
            throw new RRException("找不到该活动对应的投票对象");
        }


        //投票，保存投票记录
        CPActivityVoteRecordsEntity activityVoteRecordsEntity = new CPActivityVoteRecordsEntity();
        activityVoteRecordsEntity.setActivityId(activityId);
        activityVoteRecordsEntity.setPollederId(pollederId);
        activityVoteRecordsEntity.setVoteId(appUserInfo.getUserCode());
        activityVoteRecordsEntity.setVoteName(appUserInfo.getUserName());
        activityVoteRecordsEntity.setVotePhone(appUserInfo.getPhone());
        activityVoteRecordsEntity.setVoteLaborName(appUserInfo.getLaborName());
        activityVoteRecordsEntity.setVoteLaborId(appUserInfo.getLaborId());
        activityVoteRecordsEntity.setDistrictCode(appUserInfo.getDistrict());
        //activityVoteRecordsEntity.setDistrictName(infoVO.getDistrict());
        //TODO 加入Redis
        //redisTemplate.opsForList().rightPush(activityKey + ":records", activityVoteRecordsEntity);
        //插入记录
        insert(activityVoteRecordsEntity);
        //增加一次记录
        activityPollederMapper.addVotes(activityId, pollederId);

        return true;
    }
}
