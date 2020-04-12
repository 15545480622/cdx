package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPWorkPlanMapper;
import com.inspur.vista.labor.cp.entity.CPWorkPlanDetailVO;
import com.inspur.vista.labor.cp.entity.CPWorkPlanEntity;
import com.inspur.vista.labor.cp.entity.CPWorkPlanListVO;
import com.inspur.vista.labor.cp.service.CPWorkPlanService;
import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPWorkPlanServiceImpl
 * @Description: 文化宫工作计划服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 15:42
 */
@Service
public class CPWorkPlanServiceImpl implements CPWorkPlanService {


    private static final Logger logger = LoggerFactory.getLogger(CPWorkPlanServiceImpl.class);

    @Autowired
    private CPWorkPlanMapper cpWorkPlanMapper;

    @Autowired
    private CommonService commonService;

    /**
     * 获取工作计划
     *
     * @param id 工作计划id
     * @return
     */
    @Override
    public CPWorkPlanDetailVO getCPWorkPlan(Long id) {
        return cpWorkPlanMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询工作计划
     *
     * @param parameters 查询参数
     * @return
     */
    @Override
    public Page<CPWorkPlanListVO> listCPWorkPlan(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPWorkPlanListVO> p = new Page<>(page, pageSize);
        List<CPWorkPlanListVO> cpWorkPlanVOList = cpWorkPlanMapper.listCPWorkPlan(p, parameters);
        p.setRecords(cpWorkPlanVOList);
        return p;
    }

    /**
     * 保存工作计划
     *
     * @param cpWorkPlanEntity 工作计划
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCPWorkPlan(CPWorkPlanEntity cpWorkPlanEntity) {
        if (cpWorkPlanEntity.getId() == null) {
            cpWorkPlanEntity.setState(CPConstants.INFO_VALID);
            cpWorkPlanMapper.insertSelective(cpWorkPlanEntity);
        } else {
            cpWorkPlanMapper.updateByPrimaryKeySelective(cpWorkPlanEntity);

        }

    }

    /**
     * 通过id删除工作计划
     *
     * @param ids 工作计划id数组
     * @return
     */
    @Override
    public int removeCPWorkPlanById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpWorkPlanMapper.deleteCPWorkPlanById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpWorkPlanMapper.batchDeleteCPWorkPlanById(paramMap);
        }
        return result;
    }
}
