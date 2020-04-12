package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.OrganCantEntity;

/**
 * @Title: OrganCantMapper
 * @Description: 工会与区划的对应关系
 * @Author: gengpeng
 * @CreateDate: 2020/3/18 14:53
 * @Version: 1.0
 */
public interface OrganCantMapper {

    /**
     * 通过organId获取organCant
     *
     * @param organId
     * @return
     */
    OrganCantEntity selectByOrganId(String organId);

    /**
     * 通过区划编码获取organCant
     *
     * @param cantCode
     * @return
     */
    OrganCantEntity selectByCantCode(String cantCode);
}
