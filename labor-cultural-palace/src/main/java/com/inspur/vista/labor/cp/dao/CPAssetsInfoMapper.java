package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPAssetsMapper
 * @Description: 文化宫资产
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
public interface CPAssetsInfoMapper {

    /**
     * 获取文化宫资产情况
     *
     * @param id
     * @return
     */
    CPAssetsInfoVO selectByPrimaryKey(String id);

    /**
     * 通过年度和文化宫id获取资产
     *
     * @param cpId
     * @param year
     * @return
     */
    CPAssetsInfoVO selectByYearAndCPId(@Param("cpId") String cpId, @Param("year") String year);

    /**
     * 查询文化宫资产情况
     *
     * @param parameters
     * @return
     */
    List<CPAssetsInfoListVO> listCPAssets(Page page, Map<String, Object> parameters);

    /**
     * 新增文化宫资产情况
     *
     * @param cpAssets
     * @return
     */
    int insertSelective(CPAssetsInfoEntity cpAssets);

    /**
     * 更新文化宫资产情况
     *
     * @param cpAssets
     * @return
     */
    int updateByPrimaryKeySelective(CPAssetsInfoEntity cpAssets);
}



