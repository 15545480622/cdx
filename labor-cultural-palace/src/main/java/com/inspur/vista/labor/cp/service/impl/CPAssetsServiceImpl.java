package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPAssetsInfoMapper;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoVO;
import com.inspur.vista.labor.cp.service.CPAssetsService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPAssetsServiceImpl
 * @Description: 文化宫资产服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
@Service
public class CPAssetsServiceImpl implements CPAssetsService {

    private static final Logger logger = LoggerFactory.getLogger(CPAssetsServiceImpl.class);

    @Autowired
    private CPAssetsInfoMapper cpAssetsInfoMapper;

    @Autowired
    private PubFilesService filesService;

    /**
     * 获取文化宫资产
     *
     * @param id 文化宫资产id
     * @return
     */
    @Override
    public CPAssetsInfoVO getCPAssets(String id) {
        return cpAssetsInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过年度和文化宫id获取资产
     *
     * @param cpId
     * @param year
     * @return
     */
    @Override
    public CPAssetsInfoVO getCPAssetsByYearAndCPId(String cpId, String year) {
        return cpAssetsInfoMapper.selectByYearAndCPId(cpId, year);
    }

    /**
     * 查询文化宫资产
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPAssetsInfoListVO> listCPAssets(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPAssetsInfoListVO> p = new Page<>(page, pageSize);
        List<CPAssetsInfoListVO> cpAssetsList = cpAssetsInfoMapper.listCPAssets(p, parameters);
        p.setRecords(cpAssetsList);
        return p;
    }

    /**
     * 保存文化宫资产
     *
     * @param cpAssets
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPAssetsInfoEntity saveCPAssets(CPAssetsInfoEntity cpAssets) throws Exception {
        if (StringUtils.isBlank(cpAssets.getId())) {
            cpAssets.setId(IdUtil.fastSimpleUUID());
            cpAssets.setState(CPConstants.INFO_VALID);
            cpAssetsInfoMapper.insertSelective(cpAssets);
        } else {
            cpAssetsInfoMapper.updateByPrimaryKeySelective(cpAssets);
        }
        return cpAssets;
    }
}
