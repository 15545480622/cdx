package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateEntity;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateListVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPVisitorsFlowrateMapper
 * @Description: 访问记录
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:47
 * @Version: 1.0
 */
public interface CPVisitorsFlowrateMapper {

    /**
     * 查询访问记录
     *
     * @param parameters
     * @return
     */
    List<CPVisitorsFlowrateListVO> listCPVisitorsFlowrate(Page page, Map<String, Object> parameters);

    /**
     * 新增访问记录
     *
     * @param cpVisitorsFlowrate
     * @return
     */
    int insertSelective(CPVisitorsFlowrateEntity cpVisitorsFlowrate);

}



