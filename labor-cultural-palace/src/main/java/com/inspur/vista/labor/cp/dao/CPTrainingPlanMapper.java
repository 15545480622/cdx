package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanEntity;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanListVO;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanVO;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPTrainingPlanMapper
 * @Description: 人才培养计划
 * @Author: gengpeng
 * @CreateDate: 2019/12/27 9:15
 * @Version: 1.0
 */
public interface CPTrainingPlanMapper {

    /**
     * 获取人才培养计划信息
     *
     * @param id
     * @return
     */
    CPTrainingPlanVO selectByPrimaryKey(String id);

    /**
     * 通过年份查询人才培养计划
     *
     * @param year
     * @return
     */
    CPTrainingPlanEntity selectByYear(String year);

    /**
     * 查询人才培养计划
     *
     * @param page
     * @param parameters
     * @return
     */
    List<CPTrainingPlanListVO> listCPTrainingPlan(Page page, Map<String, Object> parameters);

    /**
     * 新增人才培养计划
     *
     * @param trainingPlan
     */
    int insertSelective(CPTrainingPlanEntity trainingPlan);

    /**
     * 更新人才培养计划
     *
     * @param trainingPlan
     */
    int updateByPrimaryKeySelective(CPTrainingPlanEntity trainingPlan);

    /**
     * 通过id删除人才培养计划
     *
     * @param paramMap modifier:修改人; id
     * @return
     */
    int deleteCPTrainingPlanById(Map<String, Object> paramMap);
}
