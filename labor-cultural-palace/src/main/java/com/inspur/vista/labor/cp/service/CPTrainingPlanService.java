package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanEntity;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanListVO;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanVO;

import java.util.Map;

/**
 * @Title: CPTrainingPlanService
 * @Description: 人才培养计划
 * @Author: gengpeng
 * @CreateDate: 2019/12/30 17:44
 * @Version: 1.0
 */
public interface CPTrainingPlanService {

    /**
     * 获取人才培养计划
     *
     * @param id
     * @return
     */
    CPTrainingPlanVO getCPTrainingPlan(String id);

    /**
     * 通过年份查询人才培养计划
     *
     * @param year
     * @return
     */
    CPTrainingPlanEntity getCPTrainingPlanByYear(String year);

    /**
     * 保存人才培养计划
     *
     * @param cpTrainingPlanEntity
     * @return
     */
    void saveCPTrainingPlan(CPTrainingPlanEntity cpTrainingPlanEntity) throws Exception;

    /**
     * 查询人才培养计划
     *
     * @param parameters
     * @return
     */
    Page<CPTrainingPlanListVO> listCPTrainingPlan(Map<String, Object> parameters);

    /**
     * 根据id删除人才培养计划
     *
     * @param id
     * @return
     */
    int removeCPTrainingPlanById(String id);
}
