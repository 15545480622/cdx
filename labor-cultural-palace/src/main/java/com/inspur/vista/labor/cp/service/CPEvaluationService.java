package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPEvaluationEntity;
import com.inspur.vista.labor.cp.entity.CPStarVO;
import com.inspur.vista.labor.cp.entity.CPEvaluationListVO;
import com.inspur.vista.labor.cp.entity.CPPlaceStarListVO;

import java.util.Map;


/**
 * @Title: CPEvaluationService
 * @Description: 评价记录服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
public interface CPEvaluationService {

    /**
     * 查询评价记录
     *
     * @param parameters
     * @return
     */
    Page<CPEvaluationListVO> listCPEvaluation(Map<String, Object> parameters);

    /**
     * 新增评价记录
     *
     * @param cpEvaluationEntity
     * @return
     */
    CPEvaluationEntity saveCPEvaluation(CPEvaluationEntity cpEvaluationEntity);

    /**
     * 评价星级排名
     *
     * @param parameters
     * @return
     */
    Page<CPPlaceStarListVO> listCPStar(Map<String, Object> parameters);

    /**
     * 获取文化宫星级
     *
     * @param cpId
     * @return
     */
    CPStarVO getCPStar(String cpId);
}



