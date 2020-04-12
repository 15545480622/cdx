package com.inspur.vista.labor.cp.service.activity.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.framework.leader.zookeeper.ZkClient;
import com.inspur.vista.labor.cp.dao.activity.CheckEntity;
import com.inspur.vista.labor.cp.dao.activity.CpActivityDrawRecordsDao;
import com.inspur.vista.labor.cp.entity.activity.CpActivityBaseEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityDrawRecordsEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityGoodsEntity;
import com.inspur.vista.labor.cp.exception.RRException;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import com.inspur.vista.labor.cp.service.activity.CpActivityDrawRecordsService;
import com.inspur.vista.labor.cp.service.activity.CpActivityGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service("cpActivityDrawRecordsService")
public class CpActivityDrawRecordsServiceImpl extends ServiceImpl<CpActivityDrawRecordsDao, CpActivityDrawRecordsEntity> implements CpActivityDrawRecordsService {

    @Autowired
    private CpActivityBaseService cpActivityBaseService;
    @Autowired
    private ZkClient zkClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CpActivityGoodsService cpActivityGoodsService;

    /**
     * 抽奖
     */
    @Override
    public Integer draw(CpActivityDrawRecordsEntity record) {

        //基础参与条件校验
        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setActivityId(record.getActivityId());
        checkEntity.setUserId(record.getUserId());
        checkEntity.setLaborId(record.getLaborId());
        cpActivityBaseService.isMeet(checkEntity);
        //获取活动详情
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(record.getActivityId());
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        //查看参与次数是否超过限制
        if (activity.getCountLimit() != null && activity.getCountLimit() != 0) {
            EntityWrapper<CpActivityDrawRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("activity_id", record.getActivityId());
            Integer totalCount = baseMapper.selectCount(recordWrapper);
            if (totalCount != null && totalCount > 0) {
                throw new RRException("参与人数已达上限");
            }
        }

        //抽奖限制
        if ("everyDay".equals(activity.getCondition())) {
            //每天限制次数，判断当天次数是否满足
            Integer takeCount = activity.getTakeCount();
            //获取该用户当天领取优惠券数量
            int currentDayCount = baseMapper.selectCurrentDayCount(record);
            if (currentDayCount > takeCount) {
                throw new RRException("当天领取次数已达上限");
            }

        }
        if (!"everyDay".equals(activity.getCondition())) {
            //只能领取一次，查看用户是否领取过
            EntityWrapper<CpActivityDrawRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("user_id", record.getUserId());
            recordWrapper.eq("activity_id", record.getActivityId());
            Integer count = baseMapper.selectCount(recordWrapper);
            if (count != null && count > 0) {
                throw new RRException("该活动只能参与一次");
            }
        }

        //获取中奖率
        BigDecimal winningRate = activity.getWinningRate();
        BigDecimal multiply = winningRate.multiply(BigDecimal.valueOf(100));
        int i = multiply.intValue();
        int random = (int) (Math.random()*10000 + 1);
        if (random > i) {
            //未中奖，保存参与记录
            baseMapper.insert(record);
            return null;
        }

        // 获取锁定路径
        String lockPath = zkClient.getLockPath("act_lock_" + activity.getId());
        //加锁
        Integer goodsId = zkClient.lockAndDeal(lockPath, 30L, () -> {
            List<CpActivityGoodsEntity> goods = null;
            //从reids获取商品
            String goodsStr = (String) redisTemplate.opsForValue().get("activity:draw:" + activity.getId());
            if (StringUtils.isNotBlank(goodsStr)) {
                Type type = new TypeReference<List<CpActivityGoodsEntity>>() {}.getType();
                goods = JSON.parseObject(goodsStr, type);
            }
            if (goods == null) {
                //如果未获取到去MySQL查询
                EntityWrapper<CpActivityGoodsEntity> rocordWrapper = new EntityWrapper<>();
                rocordWrapper.eq("activity_id", activity.getId());
                goods = cpActivityGoodsService.selectList(rocordWrapper);
                redisTemplate.opsForValue().set("activity:draw:" + activity.getId(), JSON.toJSONString(goods));
            }
            if (goods == null || goods.size() == 0) {
                return 0;
            }
            //随机抽取一件奖品
            int index = (int) (Math.random() * goods.size());
            CpActivityGoodsEntity cpActivityGoodsEntity = goods.get(index);
            if (cpActivityGoodsEntity.getMargin() > 0) {
                //保存参与记录
                record.setGoodsId(cpActivityGoodsEntity.getId());
                baseMapper.insert(record);
                //修改MySQL奖品数量
                cpActivityGoodsEntity.setMargin(cpActivityGoodsEntity.getMargin() - 1);
                cpActivityGoodsService.updateById(cpActivityGoodsEntity);
                //修改缓存
                redisTemplate.opsForValue().set("activity:draw:" + activity.getId(), JSON.toJSONString(goods));
                return cpActivityGoodsEntity.getId();
            }
            return 0;
        });

        if (goodsId != null && goodsId == 0) {
            throw new RRException("参与活动人数过多，请重试");
        }
        return goodsId;
    }

    /**
     * 获取活动中将记录
     *
     * @param id
     * @return
     */
    @Override
    public List<Map> selectWinRecords(Integer id) {
        return baseMapper.selectWinRecords(id);
    }

    /**
     * 抽奖活动参与情况
     *
     * @param params
     * @return
     */
    @Override
    public Page<Map> drawRecordPage(Map params) {
        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.drawRecordPage(p, params);
        p.setRecords(list);
        return p;
    }
}
