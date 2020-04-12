package com.inspur.vista.labor.cp.service;


import com.inspur.vista.labor.cp.entity.OrganEntity;
import com.inspur.vista.labor.cp.entity.TreeDataVO;

import java.util.List;

/**
 * @Title: OrganService
 * @Description: 工会组织服务类
 * @Author: gengpeng
 * @CreateDate: 2019/9/20 19:41
 * @Version: 1.0
 */
public interface OrganService {

    /**
     * 获取组织树，到区县及市直企业、产业和机关
     *
     * @param organId
     * @return
     */
    List<TreeDataVO> getOrganTree(String organId);

    /**
     * 通过organId获取组织信息
     *
     * @param organId
     * @return
     */
    OrganEntity getOrganByOrganId(String organId);
}
