package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPApplyAccountEntity;

/**
 * @Title: CPApplyAccountChangeMapper
 * @Description: 收款账号变更申请
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 14:56
 * @Version: 1.0
 */
public interface CPApplyAccountMapper {

    /**
     * 新增文化宫收款账号变更申请
     *
     * @param applyAccountEntity
     * @return
     */
    int insertSelective(CPApplyAccountEntity applyAccountEntity);
}
