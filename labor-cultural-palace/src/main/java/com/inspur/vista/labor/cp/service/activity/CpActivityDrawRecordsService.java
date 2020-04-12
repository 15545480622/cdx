package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
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
public interface CpActivityDrawRecordsService extends IService<CpActivityDrawRecordsEntity> {

    /**
     * 抽奖
     * @param record
     */
    Integer draw(CpActivityDrawRecordsEntity record);

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
    Page<Map> drawRecordPage(Map params);
}
