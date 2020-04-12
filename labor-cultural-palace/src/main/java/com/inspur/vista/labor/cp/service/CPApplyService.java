package com.inspur.vista.labor.cp.service;

import com.inspur.vista.labor.cp.entity.CPApplyCheck;

/**
 * @Title: CPApplyService
 * @Description: 审核服务
 * @Author: gengpeng
 * @CreateDate: 2020/4/1 13:49
 * @Version: 1.0
 */
public interface CPApplyService {


    /**
     * 审核
     *
     * @param applyCheck
     */
    void check(CPApplyCheck applyCheck);
}
