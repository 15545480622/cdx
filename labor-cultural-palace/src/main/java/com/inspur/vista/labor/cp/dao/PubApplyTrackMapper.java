package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.PubApplyTrackEntity;

/**
 * @Title: PubApplyTrackMapper
 * @Description: 申请轨迹
 * @Author: gengpeng
 * @CreateDate: 2020/2/26 18:18
 * @Version: 1.0
 */
public interface PubApplyTrackMapper {

    /**
     * 新增申请处理轨迹
     *
     * @param applyTrackEntity
     * @return
     */
    int insertSelective(PubApplyTrackEntity applyTrackEntity);
}
