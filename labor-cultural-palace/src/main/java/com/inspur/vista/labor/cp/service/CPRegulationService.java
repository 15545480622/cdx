package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPRegulationEntity;
import com.inspur.vista.labor.cp.entity.CPRegulationInfoVO;
import com.inspur.vista.labor.cp.entity.CPRegulationListVO;

import java.util.Map;

/**
 * @ClassName: CPRegulationService
 * @Description: 文化宫制度管理服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 10:15
 */
public interface CPRegulationService {

    /**
     * 获取文化宫制度信息
     *
     * @param id
     * @return
     */
    CPRegulationInfoVO getCPRegulation(String id);

    /**
     * 保存文化宫制度信息
     *
     * @param cpRegulationEntity
     * @return
     */
    void saveCPRegulation(CPRegulationEntity cpRegulationEntity);

    /**
     * 查询文化宫制度信息
     *
     * @param parameters
     * @return
     */
    Page<CPRegulationListVO> listCPRegulation(Map<String, Object> parameters);

    /**
     * 删除文化宫制度信息
     *
     * @param idArr
     * @return
     */
    int removeCPRegulationById(String[] idArr);
}