package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPApplyTrackEntity;

/**
 * @Title: CPApplyTrackMapper
 * @Description: 申请轨迹
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 14:56
 * @Version: 1.0
 */
public interface CPApplyTrackMapper {

    /**
     * 新增轨迹
     *
     * @param trackEntiry
     * @return
     */
    int insertSelective(CPApplyTrackEntity trackEntiry);
}
