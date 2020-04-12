package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPWorkPlanDetailVO;
import com.inspur.vista.labor.cp.entity.CPWorkPlanEntity;
import com.inspur.vista.labor.cp.entity.CPWorkPlanListVO;

import java.util.Map;

/**
 * @ClassName: CPWorkPlanService
 * @Description: 文化宫工作计划服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 15:42
 */
public interface CPWorkPlanService {

    /**
     * 获取工作计划
     * @param valueOf
     * @return
     */
    CPWorkPlanDetailVO getCPWorkPlan(Long valueOf);

    /**
     * 查询工作计划
     * @param parameters
     * @return
     */
    Page<CPWorkPlanListVO> listCPWorkPlan(Map<String, Object> parameters);

    /**
     * 保存工作计划
     * @param cpWorkPlanEntity
     */
    void saveCPWorkPlan(CPWorkPlanEntity cpWorkPlanEntity);

    /**
     * 删除工作计划
     * @param idArr
     * @return
     */
    int removeCPWorkPlanById(String[] idArr);
}