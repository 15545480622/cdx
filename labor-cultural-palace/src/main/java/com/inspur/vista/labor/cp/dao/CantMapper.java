package com.inspur.vista.labor.cp.dao;


import com.inspur.vista.labor.cp.entity.CantVO;

import java.util.List;

/**
 * @Title: CantMapper
 * @Description: 区划
 * @Author: gengpeng
 * @CreateDate: 2019/9/20 13:59
 * @Version: 1.0
 */
public interface CantMapper {

    /**
     * 获取下级区划
     *
     * @param superCode
     * @return
     */
    List<CantVO> selectDirectCant(String superCode);

    /**
     * 通过区划编码获取区划信息
     *
     * @param cantCode
     * @return
     */
    CantVO selectByCantCode(String cantCode);

    /**
     * 根据区划名称获取区划编码
     *
     * @param cantName
     * @return
     */
    String selectCantCodeByCantName(String cantName);
}
