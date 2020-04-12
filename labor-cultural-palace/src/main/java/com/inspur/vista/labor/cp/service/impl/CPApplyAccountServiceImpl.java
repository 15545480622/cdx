package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.CPApplyAccountMapper;
import com.inspur.vista.labor.cp.entity.CPApplyAccountEntity;
import com.inspur.vista.labor.cp.entity.PubApplyTrackEntity;
import com.inspur.vista.labor.cp.service.CPApplyAccountService;
import com.inspur.vista.labor.cp.service.PubApplyTrackService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: CPApplyAccountChangeServiceImpl
 * @Description: 收款账号变更申请服务
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 15:03
 * @Version: 1.0
 */
@Service
public class CPApplyAccountServiceImpl implements CPApplyAccountService {

    @Autowired
    private CPApplyAccountMapper applyAccountMapper;

    @Autowired
    private PubApplyTrackService applyTrackService;

    /**
     * 保存收款账号新增申请
     *
     * @param applyAccountEntity
     */
    @Override
    public void createApply(CPApplyAccountEntity applyAccountEntity) {
        applyAccountMapper.insertSelective(applyAccountEntity);
        PubApplyTrackEntity applyTrackEntity = new PubApplyTrackEntity();
        applyTrackEntity.setApplyId(applyAccountEntity.getId());
        applyTrackEntity.setApplyType(CPConstants.APPLY_TYPE_ACCOUNT_CREATE);
//        applyTrackEntity.setHandlerState(CPConstants.TRACK_STATE_APPLY);
        applyTrackEntity.setHandler(applyAccountEntity.getCreator());
        applyTrackService.createTrack(applyTrackEntity);
    }

    @Override
    public void checkAppy(Long applyId, int checkResult) {

    }
}
