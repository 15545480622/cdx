package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoVO;

import java.util.Map;


/**
 * @Title: CPAiequipmentInfoService
 * @Description: 智能设备信息服务类
 * @Author:  liuzq
 * @CreateDate: 2020/03/07 14:37
 * @Version: 1.0
 */
public interface CPAiequipmentInfoService {

    /**
     * 获取智能设备信息
     *
     * @param id
     * @return
     */
    CPAiequipmentInfoVO getCPAiequipmentInfo(String id);

		/**
		 * 查询智能设备信息
		 *
		 * @param parameters
		 * @return
		 */
		Page<CPAiequipmentInfoListVO> listCPAiequipmentInfo(Map<String, Object> parameters);

		/**
		 * 保存智能设备信息
		 *
		 * @param cpAiequipmentInfo
		 * @return
		 */
		CPAiequipmentInfoEntity saveCPAiequipmentInfo(CPAiequipmentInfoEntity cpAiequipmentInfo);

		/**
		 * 通过id删除智能设备信息
		 *
		 * @param ids
		 * @return
		 */
		int removeCPAiequipmentInfoById(String[] ids);

}



