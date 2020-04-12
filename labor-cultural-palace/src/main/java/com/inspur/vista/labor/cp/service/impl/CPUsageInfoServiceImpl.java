package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.util.IdUtil;
import com.inspur.vista.labor.cp.dao.CPUsageInfoMapper;
import com.inspur.vista.labor.cp.entity.CPUsageInfoEntity;
import com.inspur.vista.labor.cp.entity.CPUsageInfoListVO;
import com.inspur.vista.labor.cp.service.CPUsageInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPUsageInfoServiceImpl
 * @Description:
 * @authur: wangxueying01
 * @CreatDate: 2020/3/13 10:16
 */
@Service
public class CPUsageInfoServiceImpl implements CPUsageInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPUsageInfoServiceImpl.class);

    @Autowired
    private CPUsageInfoMapper cpUsageInfoMapper;

    /**
     * 查询场所使用记录
     *
     * @param parameters
     * @return
     */
    @Override
    public List<CPUsageInfoListVO> listCPUsageInfo(Map<String, Object> parameters) {
        List<CPUsageInfoListVO> cpUsageInfoListVOS = cpUsageInfoMapper.listCPUsageInfo(parameters);
        return cpUsageInfoListVOS;
    }

    /**
     * 新增场所使用记录
     *
     * @param cpUsageInfoEntity
     * @return
     */
    @Override
    public CPUsageInfoEntity saveCPUsageInfo(CPUsageInfoEntity cpUsageInfoEntity) {
        cpUsageInfoEntity.setId(IdUtil.fastSimpleUUID());
        cpUsageInfoMapper.insertSelective(cpUsageInfoEntity);
        return cpUsageInfoEntity;
    }

    /**
     * 使用场地
     *
     * @param userCode
     * @param reserveId
     */
    @Override
    public void useCourt(String userCode, String... reserveId) {

    }
}
