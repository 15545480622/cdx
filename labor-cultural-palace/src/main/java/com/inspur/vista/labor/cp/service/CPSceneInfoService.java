package com.inspur.vista.labor.cp.service;


import com.inspur.vista.labor.cp.entity.CPCourtInfoVO;
import com.inspur.vista.labor.cp.entity.CPSceneInfoVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPSceneDefinitionService
 * @Description: 预约场次定义服务类
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
public interface CPSceneInfoService {

    /**
     * 获取场次
     *
     * @param parameters courtId:场地id；cxBeginDate:开始日期；cxEndDate:结束日期；fileterExpireScene:是否排除过期的场次，默认true
     * @return
     */
    List<CPSceneInfoVO> listCPScene(Map<String, Object> parameters);

    /**
     * 初始化场次时间列表
     *
     * @param courtInfoVO 场地
     * @param startDate
     * @param endDate
     * @param sceneList   生成的场次列表
     * @return
     */
    void initSceneList(CPCourtInfoVO courtInfoVO, String startDate, String endDate, List<CPSceneInfoVO> sceneList);
}



