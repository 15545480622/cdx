package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateEntity;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateListVO;

import java.util.Map;


/**
 * @Title: CPVisitorsFlowrateService
 * @Description: 访问记录服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:47
 * @Version: 1.0
 */
public interface CPVisitorsFlowrateService {

    /**
     * 查询访问记录
     *
     * @param parameters
     * @return
     */
    Page<CPVisitorsFlowrateListVO> listCPVisitorsFlowrate(Map<String, Object> parameters);

    /**
     * 新增访问记录
     *
     * @param cpVisitorsFlowrate
     * @return
     */
    CPVisitorsFlowrateEntity saveCPVisitorsFlowrate(CPVisitorsFlowrateEntity cpVisitorsFlowrate);

}



