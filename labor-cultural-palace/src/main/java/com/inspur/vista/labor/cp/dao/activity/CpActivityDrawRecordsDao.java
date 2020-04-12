package com.inspur.vista.labor.cp.dao.activity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.activity.CpActivityDrawRecordsEntity;

import java.util.List;
import java.util.Map;

/**
 * 抽奖记录表
 * 
 * @author 
 * @email 
 * @date 2020-04-01 10:06:47
 */
public interface CpActivityDrawRecordsDao extends BaseMapper<CpActivityDrawRecordsEntity> {

    /**
     * 获取当天抽奖次数
     * @param record
     * @return
     */
    int selectCurrentDayCount(CpActivityDrawRecordsEntity record);

    /**
     * 获取活动中将记录
     * @param id
     * @return
     */
    List<Map> selectWinRecords(Integer id);

    /**
     * 抽奖活动参与情况
     * @param params
     * @return
     */
    List<Map> drawRecordPage(Page page, Map params);
}
