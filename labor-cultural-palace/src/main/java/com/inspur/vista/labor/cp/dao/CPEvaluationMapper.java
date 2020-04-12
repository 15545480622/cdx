package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPEvaluationEntity;
import com.inspur.vista.labor.cp.entity.CPStarVO;
import com.inspur.vista.labor.cp.entity.CPEvaluationListVO;
import com.inspur.vista.labor.cp.entity.CPPlaceStarListVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPEvaluationMapper
 * @Description: 文化宫评价记录
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
public interface CPEvaluationMapper {

    /**
     * 查询评价记录
     *
     * @param parameters
     * @return
     */
    List<CPEvaluationListVO> listCPEvaluation(Page page, Map<String, Object> parameters);

    /**
     * 新增评价记录
     *
     * @param cpEvaluationEntity
     * @return
     */
    int insertSelective(CPEvaluationEntity cpEvaluationEntity);

    /**
     * 评价星级排名
     *
     * @param parameters
     * @return
     */
    List<CPPlaceStarListVO> listCPStar(Page page, Map<String, Object> parameters);

    /**
     * 获取文化宫星级
     *
     * @param cpId
     * @return
     */
    CPStarVO selectByCpId(String cpId);
}



