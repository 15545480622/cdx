package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.PubApplyTrackMapper;
import com.inspur.vista.labor.cp.entity.PubApplyTrackEntity;
import com.inspur.vista.labor.cp.service.PubApplyTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: PubApplyTrackServiceImpl
 * @Description: 申请单轨迹
 * @Author: gengpeng
 * @CreateDate: 2020/2/26 18:29
 * @Version: 1.0
 */
@Service
public class PubApplyTrackServiceImpl implements PubApplyTrackService {

    @Autowired
    private PubApplyTrackMapper applyTrackMapper;

    @Override
    public void createTrack(PubApplyTrackEntity pubApplyTrackEntity) {
        applyTrackMapper.insertSelective(pubApplyTrackEntity);
    }
}
