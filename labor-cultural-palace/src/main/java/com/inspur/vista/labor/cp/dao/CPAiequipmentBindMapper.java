package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPAiequipmentBindMapper
 * @Description: 智能设备绑定信息
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:54
 * @Version: 1.0
 */
public interface CPAiequipmentBindMapper {

    /**
     * 获取智能设备绑定信息
     *
     * @param id
     * @return
     */
    CPAiequipmentBindVO selectByPrimaryKey(String id);

    /**
     * 查询智能设备绑定信息
     *
     * @param parameters
     * @return
     */
    List<CPAiequipmentBindListVO> listCPAiequipmentBind(Page page, Map<String, Object> parameters);

    /**
     * 新增智能设备绑定信息
     *
     * @param cpAiequipmentBind
     * @return
     */
    int insertSelective(CPAiequipmentBindEntity cpAiequipmentBind);

    /**
     * 更新智能设备绑定信息
     *
     * @param cpAiequipmentBind
     * @return
     */
    int updateByPrimaryKeySelective(CPAiequipmentBindEntity cpAiequipmentBind);

    /**
     * 通过id删除智能设备绑定信息
     *
     * @param paramMap modifier:修改人; id:智能设备绑定信息id
     * @return
     */
    int deleteCPAiequipmentBindById(Map paramMap);

    /**
     * 通过ids批量删除智能设备绑定信息
     *
     * @param paramMap modifier:修改人; id:智能设备绑定信息id的字符串数组
     * @return
     */
    int batchDeleteCPAiequipmentBindById(Map paramMap);

}



