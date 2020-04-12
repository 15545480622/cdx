package com.inspur.vista.labor.cp.dao;


import com.inspur.vista.labor.cp.entity.OrganEntity;

import java.util.List;
import java.util.Map;

/**
 * @Title: OrganMapper
 * @Description: 工会组织
 * @Author: gengpeng
 * @CreateDate: 2019/9/14 12:12
 * @Version: 1.0
 */
public interface OrganMapper {

    /**
     * 通过organId获取organ
     *
     * @param organId
     * @return
     */
    OrganEntity selectByOrganId(String organId);

    /**
     * 通过struId获取organ
     *
     * @param struId
     * @return
     */
    OrganEntity selectByStruId(String struId);

    /**
     * 通过struPath模糊查询
     *
     * @param param struPath;level
     * @return
     */
    List<OrganEntity> listByStruPathLike(Map param);

    /**
     * 通过struPath精确查询
     *
     * @param struPath
     * @return
     */
    OrganEntity listByStruPath(String struPath);
}
