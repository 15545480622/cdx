package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPApplyInfoEntity;
import com.inspur.vista.labor.cp.entity.CPApplyInfoVO;

/**
 * @Title: CPApplyInfoMapper
 * @Description: 申请相关
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 14:56
 * @Version: 1.0
 */
public interface CPApplyInfoMapper {

    /**
     * 获取申请表信息
     *
     * @param applyId
     * @return
     */
    CPApplyInfoVO selectByPrimaryKey(String applyId);

    /**
     * 新增申请表信息
     *
     * @param applyInfoEntity
     * @return
     */
    int insertSelective(CPApplyInfoEntity applyInfoEntity);


    /**
     * 更新申请表信息
     *
     * @param applyInfoEntity
     * @return
     */
    int updateByPrimaryKeySelective(CPApplyInfoEntity applyInfoEntity);
}
