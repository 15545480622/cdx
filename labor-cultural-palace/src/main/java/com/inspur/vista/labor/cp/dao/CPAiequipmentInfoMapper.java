package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPAiequipmentInfoMapper
 * @Description: 智能设备信息
 * @Author: liuzq
 * @CreateDate: 2020/03/07 14:37
 * @Version: 1.0
 */
public interface CPAiequipmentInfoMapper {

    /**
     * 获取智能设备信息
     *
     * @param id
     * @return
     */
    CPAiequipmentInfoVO selectByPrimaryKey(String id);

		/**
		 * 查询智能设备信息
		 *
		 * @param parameters
		 * @return
		 */
		List<CPAiequipmentInfoListVO> listCPAiequipmentInfo(Page page, Map<String, Object> parameters);

		/**
		 * 新增智能设备信息
		 *
		 * @param cpAiequipmentInfo
		 * @return
		 */
		int insertSelective(CPAiequipmentInfoEntity cpAiequipmentInfo);

        /**
         * 更新智能设备信息
         *
         * @param cpAiequipmentInfo
         * @return
         */
        int updateByPrimaryKeySelective(CPAiequipmentInfoEntity cpAiequipmentInfo);

        /**
         * 通过id删除智能设备信息
         * @param paramMap modifier:修改人; id:智能设备信息id
         * @return
         */
        int deleteCPAiequipmentInfoById(Map paramMap);

        /**
         * 通过ids批量删除智能设备信息
         * @param paramMap modifier:修改人; id:智能设备信息id的字符串数组
         * @return
         */
        int batchDeleteCPAiequipmentInfoById(Map paramMap);

}



