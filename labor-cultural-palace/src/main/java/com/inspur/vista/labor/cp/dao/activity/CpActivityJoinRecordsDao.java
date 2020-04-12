package com.inspur.vista.labor.cp.dao.activity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.activity.CpActivityJoinRecordsEntity;

import java.util.List;
import java.util.Map;

/**
 * 报名参与记录表
 * 
 * @author 
 * @email 
 * @date 2020-03-18 14:28:24
 */
public interface CpActivityJoinRecordsDao extends BaseMapper<CpActivityJoinRecordsEntity> {

    List<CpActivityJoinRecordsEntity> selectPageByActivityId(Page<CpActivityJoinRecordsEntity> p, Map params);

    List<Map> scanJoinDetailsActivityList(Page<Map> p, Map params);
}
