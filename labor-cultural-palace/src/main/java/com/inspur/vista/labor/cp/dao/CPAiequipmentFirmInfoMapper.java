package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPAiequipmentFirmInfoMapper
 * @Description: 智能设备厂商信息
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:05
 * @Version: 1.0
 */
public interface CPAiequipmentFirmInfoMapper {

    /**
     * 获取智能设备厂商信息
     *
     * @param id
     * @return
     */
    CPAiequipmentFirmInfoVO selectByPrimaryKey(String id);

    /**
     * 查询智能设备厂商信息
     *
     * @param parameters
     * @return
     */
    List<CPAiequipmentFirmInfoListVO> listCPAiequipmentFirmInfo(Page page, Map<String, Object> parameters);

    /**
     * 新增智能设备厂商信息
     *
     * @param cpAiequipmentFirmInfo
     * @return
     */
    int insertSelective(CPAiequipmentFirmInfoEntity cpAiequipmentFirmInfo);

    /**
     * 更新智能设备厂商信息
     *
     * @param cpAiequipmentFirmInfo
     * @return
     */
    int updateByPrimaryKeySelective(CPAiequipmentFirmInfoEntity cpAiequipmentFirmInfo);

    /**
     * 通过id删除智能设备厂商信息
     *
     * @param paramMap modifier:修改人; id:智能设备厂商信息id
     * @return
     */
    int deleteCPAiequipmentFirmInfoById(Map paramMap);

    /**
     * 通过ids批量删除智能设备厂商信息
     *
     * @param paramMap modifier:修改人; id:智能设备厂商信息id的字符串数组
     * @return
     */
    int batchDeleteCPAiequipmentFirmInfoById(Map paramMap);

}



