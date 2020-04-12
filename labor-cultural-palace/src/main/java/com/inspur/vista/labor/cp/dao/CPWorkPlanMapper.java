package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPWorkPlanDetailVO;
import com.inspur.vista.labor.cp.entity.CPWorkPlanEntity;
import com.inspur.vista.labor.cp.entity.CPWorkPlanListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPWorkPlanMapper
 * @Description: 文化宫工作计划
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 15:42
 */
public interface CPWorkPlanMapper {

    /**
     * 获取工作计划
     * @param id
     * @return
     */
    CPWorkPlanDetailVO selectByPrimaryKey(Long id);

    /**
     * 查询工作计划
     * @param p
     * @param parameters
     * @return
     */
    List<CPWorkPlanListVO> listCPWorkPlan(Page<CPWorkPlanListVO> p, Map<String, Object> parameters);

    /**
     * 新增工作计划
     * @param cpWorkPlanEntity
     */
    void insertSelective(CPWorkPlanEntity cpWorkPlanEntity);

    /**
     * 更新工作计划
     * @param cpWorkPlanEntity
     */
    void updateByPrimaryKeySelective(CPWorkPlanEntity cpWorkPlanEntity);

    /**
     * 单个删除工作计划
     * @param paramMap
     * @return
     */
    int deleteCPWorkPlanById(Map<String, Object> paramMap);

    /**
     * 批量删除工作计划
     * @param paramMap
     * @return
     */
    int batchDeleteCPWorkPlanById(Map<String, Object> paramMap);
}