package com.inspur.vista.labor.cp.service;

import com.inspur.vista.labor.cp.entity.PubApplyTrackEntity;

/**
 * @Title: PubApplyTrackService
 * @Description: 申请单轨迹
 * @Author: gengpeng
 * @CreateDate: 2020/2/26 18:29
 * @Version: 1.0
 */
public interface PubApplyTrackService {

    /**
     * 新增申请单轨迹
     *
     * @param pubApplyTrackEntity
     */
    void createTrack(PubApplyTrackEntity pubApplyTrackEntity);
}
