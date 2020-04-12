package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPRegulationEntity;
import com.inspur.vista.labor.cp.entity.CPRegulationInfoVO;
import com.inspur.vista.labor.cp.entity.CPRegulationListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPRegulationMapper
 * @Description: 文化宫制度管理
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 10:18
 */
public interface CPRegulationMapper {

    /**
     * 获取文化宫制度
     * @param id
     * @return
     */
    CPRegulationInfoVO selectByPrimaryKey(String id);

    /**
     * 查询文化宫制度
     * @param p
     * @param parameters
     * @return
     */
    List<CPRegulationListVO> listCPRegulation(Page<CPRegulationListVO> p, Map<String, Object> parameters);

    /**
     * 新增文化宫制度
     * @param cpRegulationEntity
     */
    void insertSelective(CPRegulationEntity cpRegulationEntity);

    /**
     * 更新文化宫制度
     * @param cpRegulationEntity
     */
    void updateByPrimaryKeySelective(CPRegulationEntity cpRegulationEntity);

    /**
     * 单个删除文化宫制度
     * @param paramMap
     * @return
     */
    int deleteCPRegulationById(Map<String, Object> paramMap);

    /**
     * 批量删除文化宫制度
     * @param paramMap
     * @return
     */
    int batchDeleteCPRegulationById(Map<String, Object> paramMap);
}