package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPTrainingPlanMapper;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanEntity;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanListVO;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanVO;
import com.inspur.vista.labor.cp.service.CPTrainingPlanService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPTrainingPlanServiceImpl
 * @Description: //TODO
 * @Author: gengpeng
 * @CreateDate: 2019/12/30 17:44
 * @Version: 1.0
 */
@Service
public class CPTrainingPlanServiceImpl implements CPTrainingPlanService {

    @Autowired
    private CPTrainingPlanMapper trainingPlanMapper;

    /**
     * 获取人才培养计划
     *
     * @param id
     * @return
     */
    @Override
    public CPTrainingPlanVO getCPTrainingPlan(String id) {
        return trainingPlanMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过年份查询人才培养计划
     *
     * @param year
     * @return
     */
    @Override
    public CPTrainingPlanEntity getCPTrainingPlanByYear(String year) {
        return trainingPlanMapper.selectByYear(year);
    }

    /**
     * 保存人才培养计划
     *
     * @param cpTrainingPlanEntity
     * @return
     */
    @Override
    public void saveCPTrainingPlan(CPTrainingPlanEntity cpTrainingPlanEntity) throws Exception {
        if (null == cpTrainingPlanEntity.getId()) {
            cpTrainingPlanEntity.setId(IdUtil.fastSimpleUUID());
            cpTrainingPlanEntity.setState(CPConstants.INFO_VALID);
            trainingPlanMapper.insertSelective(cpTrainingPlanEntity);
        } else {
            trainingPlanMapper.updateByPrimaryKeySelective(cpTrainingPlanEntity);
        }
    }

    /**
     * 查询人才培养计划
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPTrainingPlanListVO> listCPTrainingPlan(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPTrainingPlanListVO> p = new Page<>(page, pageSize);
        List<CPTrainingPlanListVO> trainingPlanList = trainingPlanMapper.listCPTrainingPlan(p, parameters);
        p.setRecords(trainingPlanList);
        return p;
    }

    /**
     * 根据id删除人才培养计划
     *
     * @param id
     * @return
     */
    @Override
    public int removeCPTrainingPlanById(String id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("modifier", "");
        // 单个删除
        paramMap.put("id", id);
        return trainingPlanMapper.deleteCPTrainingPlanById(paramMap);
    }
}
