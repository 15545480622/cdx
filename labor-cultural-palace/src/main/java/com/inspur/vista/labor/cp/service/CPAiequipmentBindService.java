package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindVO;

import java.util.Map;


/**
 * @Title: CPAiequipmentBindService
 * @Description: 智能设备绑定信息服务类
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:54
 * @Version: 1.0
 */
public interface CPAiequipmentBindService {

    /**
     * 获取智能设备绑定信息
     *
     * @param id
     * @return
     */
    CPAiequipmentBindVO getCPAiequipmentBind(String id);

    /**
     * 查询智能设备绑定信息
     *
     * @param parameters
     * @return
     */
    Page<CPAiequipmentBindListVO> listCPAiequipmentBind(Map<String, Object> parameters);

    /**
     * 保存智能设备绑定信息
     *
     * @param cpAiequipmentBind
     * @return
     */
    CPAiequipmentBindEntity saveCPAiequipmentBind(CPAiequipmentBindEntity cpAiequipmentBind);

    /**
     * 通过id删除智能设备绑定信息
     *
     * @param ids
     * @return
     */
    int removeCPAiequipmentBindById(String[] ids);

}



