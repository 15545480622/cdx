package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPVisitorsFlowrateMapper;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateEntity;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateListVO;
import com.inspur.vista.labor.cp.service.CPVisitorsFlowrateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPVisitorsFlowrateServiceImpl
 * @Description: 访问记录服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:47
 * @Version: 1.0
 */
@Service
public class CPVisitorsFlowrateServiceImpl implements CPVisitorsFlowrateService {

    private static final Logger logger = LoggerFactory.getLogger(CPVisitorsFlowrateServiceImpl.class);

    @Autowired
    private CPVisitorsFlowrateMapper cpVisitorsFlowrateMapper;

    /**
     * 查询访问记录
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPVisitorsFlowrateListVO> listCPVisitorsFlowrate(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPVisitorsFlowrateListVO> p = new Page<>(page, pageSize);
        List<CPVisitorsFlowrateListVO> cpVisitorsFlowrateList = cpVisitorsFlowrateMapper.listCPVisitorsFlowrate(p, parameters);
        p.setRecords(cpVisitorsFlowrateList);
        return p;
    }

    /**
     * 新增访问记录
     *
     * @param cpVisitorsFlowrate
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPVisitorsFlowrateEntity saveCPVisitorsFlowrate(CPVisitorsFlowrateEntity cpVisitorsFlowrate) {
        cpVisitorsFlowrateMapper.insertSelective(cpVisitorsFlowrate);
        return cpVisitorsFlowrate;
    }

}
