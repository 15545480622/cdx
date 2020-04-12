package com.inspur.vista.labor.cp.service;

import com.alipay.api.AlipayApiException;
import com.inspur.vista.labor.cp.entity.CPSceneBlacklistVO;

/**
 * @Title: CPSceneBlacklistService
 * @Description: 场次黑名单
 * @Author: gengpeng
 * @CreateDate: 2020/3/17 11:04
 * @Version: 1.0
 */
public interface CPSceneBlacklistService {


    /**
     * 新增黑名单记录
     *
     * @param sceneBlacklistVO
     */
    void addToBlacklist(CPSceneBlacklistVO sceneBlacklistVO, String modifier) throws AlipayApiException;

    /**
     * 删除黑名单记录
     *
     * @param sceneBlacklistVO
     */
    void removeFromBlacklist(CPSceneBlacklistVO sceneBlacklistVO);
}
