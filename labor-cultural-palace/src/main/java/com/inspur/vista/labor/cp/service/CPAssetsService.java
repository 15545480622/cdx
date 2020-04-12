package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoVO;

import java.util.Map;


/**
 * @Title: CPAssetsService
 * @Description: 文化宫资产服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
public interface CPAssetsService {

    /**
     * 获取文化宫资产
     *
     * @param id
     * @return
     */
    CPAssetsInfoVO getCPAssets(String id);

    /**
     * 通过年度和文化宫id获取资产
     *
     * @param cpId
     * @param year
     * @return
     */
    CPAssetsInfoVO getCPAssetsByYearAndCPId(String cpId, String year);

    /**
     * 查询文化宫资产
     *
     * @param parameters
     * @return
     */
    Page<CPAssetsInfoListVO> listCPAssets(Map<String, Object> parameters);

    /**
     * 保存文化宫资产
     *
     * @param cpAssets
     * @return
     */
    CPAssetsInfoEntity saveCPAssets(CPAssetsInfoEntity cpAssets) throws Exception;



}



