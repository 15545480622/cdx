package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoVO;

import java.util.Map;


/**
 * @Title: CPAiequipmentFirmInfoService
 * @Description: 智能设备厂商信息服务类
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:05
 * @Version: 1.0
 */
public interface CPAiequipmentFirmInfoService {

    /**
     * 获取智能设备厂商信息
     *
     * @param id
     * @return
     */
    CPAiequipmentFirmInfoVO getCPAiequipmentFirmInfo(String id);

    /**
     * 查询智能设备厂商信息
     *
     * @param parameters
     * @return
     */
    Page<CPAiequipmentFirmInfoListVO> listCPAiequipmentFirmInfo(Map<String, Object> parameters);

    /**
     * 保存智能设备厂商信息
     *
     * @param cpAiequipmentFirmInfo
     * @return
     */
    CPAiequipmentFirmInfoEntity saveCPAiequipmentFirmInfo(CPAiequipmentFirmInfoEntity cpAiequipmentFirmInfo);

    /**
     * 通过id删除智能设备厂商信息
     *
     * @param ids
     * @return
     */
    int removeCPAiequipmentFirmInfoById(String[] ids);

}



