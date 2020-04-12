package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPInfoApplyEntity;
import com.inspur.vista.labor.cp.entity.CPInfoApplyVO;

/**
 * @Title: CPInfoDao
 * @Description: 文化宫基本信息
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 17:02
 * @Version: 1.0
 */
public interface CPInfoApplyMapper {

    /**
     * 获取文化宫申请信息
     *
     * @param id 申请表id
     * @return CPInfoVO
     */
    CPInfoApplyVO selectByPrimaryKey(String id);

    /**
     * 新增文化宫申请
     *
     * @param applyEntity
     * @return
     */
    int insertSelective(CPInfoApplyEntity applyEntity);

    /**
     * 更新文化宫申请
     *
     * @param applyEntity
     * @return
     */
    int updateByPrimaryKeySelective(CPInfoApplyEntity applyEntity);

    /**
     * 获取未完成的文化宫申请
     *
     * @param cpId
     * @return
     */
    CPInfoApplyVO selectNoFinishApply(String cpId);
}
