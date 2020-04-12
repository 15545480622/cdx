package com.inspur.vista.labor.cp.service;

import com.inspur.vista.labor.cp.entity.CPApplyAccountEntity;

/**
 * @Title: CPApplyAccountChangeService
 * @Description: 收款账号变更申请
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 15:02
 * @Version: 1.0
 */
public interface CPApplyAccountService {

    /**
     * 创建收款账号新增申请
     *
     * @param applyAccountEntity
     */
    void createApply(CPApplyAccountEntity applyAccountEntity);

    /**
     * 审核申请
     *
     * @param applyId
     * @param checkResult
     */
    void checkAppy(Long applyId, int checkResult);
}
