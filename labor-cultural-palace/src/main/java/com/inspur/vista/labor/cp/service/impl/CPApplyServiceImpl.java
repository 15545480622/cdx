package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.*;
import com.inspur.vista.labor.cp.entity.CPApplyCheck;
import com.inspur.vista.labor.cp.service.CPApplyService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.tinyflow.core.util.HandleResultEnum;
import com.inspur.vista.tinyflow.core.util.TinyflowHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: CPApplyServiceImpl
 * @Description: 审核服务
 * @Author: gengpeng
 * @CreateDate: 2020/4/1 13:50
 * @Version: 1.0
 */
@Service
public class CPApplyServiceImpl implements CPApplyService {

    @Autowired
    private CPApplyInfoMapper applyInfoMapper;

    @Autowired
    private CPApplyTrackMapper applyTrackMapper;

    @Autowired
    private CPTaskMapper taskMapper;

    @Autowired
    private CPInfoApplyMapper applyMapper;

    @Autowired
    private CPInfoMapper cpInfoMapper;

    @Autowired
    private PubFilesMapper filesMapper;

    @Autowired
    private CPApplyFileMapper applyFileMapper;

    /**
     * 审核
     *
     * @param applyCheck
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void check(CPApplyCheck applyCheck) {
        TinyflowHelper.buildTask().bsn(CPConstants.APPLY_TYPE_CP_CHANGE, applyCheck.getApplyId())
                .process("admin", "operatorOrgan", HandleResultEnum.AGREE, "同意")
                .next("", "")
                .validAndFinish();

    }
}
