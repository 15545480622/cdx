package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPEvaluationMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPEvaluationService;
import com.inspur.vista.labor.cp.service.OrganService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPEvaluationServiceImpl
 * @Description: 评价记录服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@Service
public class CPEvaluationServiceImpl implements CPEvaluationService {

    private static final Logger logger = LoggerFactory.getLogger(CPEvaluationServiceImpl.class);

    @Autowired
    private CPEvaluationMapper cpEvaluationMapper;

    @Autowired
    private OrganService organService;

    /**
     * 查询评价记录
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPEvaluationListVO> listCPEvaluation(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPEvaluationListVO> p = new Page<>(page, pageSize);
        List<CPEvaluationListVO> cpPlaceEvaluationList = cpEvaluationMapper.listCPEvaluation(p, parameters);
        p.setRecords(cpPlaceEvaluationList);
        return p;
    }

    /**
     * 保存评价记录
     *
     * @param cpEvaluationEntity
     * @return
     */
    @Override
    public CPEvaluationEntity saveCPEvaluation(CPEvaluationEntity cpEvaluationEntity) {
        cpEvaluationEntity.setId(IdUtil.fastSimpleUUID());
        cpEvaluationMapper.insertSelective(cpEvaluationEntity);
        return cpEvaluationEntity;
    }

    /**
     * 评价星级排名
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPPlaceStarListVO> listCPStar(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        // 登录用户的
        String organId = Convert.toStr(parameters.get("organId"));
        OrganEntity organEntity = organService.getOrganByOrganId(organId);
        parameters.put("struPath", organEntity.getStruPath());
        Page<CPPlaceStarListVO> p = new Page<>(page, pageSize);
        List<CPPlaceStarListVO> cpPlaceStarList = cpEvaluationMapper.listCPStar(p, parameters);
        p.setRecords(cpPlaceStarList);
        return p;
    }

    /**
     * 获取文化宫平均星级
     *
     * @param cpId
     * @return
     */
    @Override
    public CPStarVO getCPStar(String cpId) {
        CPStarVO cpStarVO = cpEvaluationMapper.selectByCpId(cpId);
        return cpStarVO;
    }
}
