package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPRegulationMapper;
import com.inspur.vista.labor.cp.entity.CPRegulationEntity;
import com.inspur.vista.labor.cp.entity.CPRegulationInfoVO;
import com.inspur.vista.labor.cp.entity.CPRegulationListVO;
import com.inspur.vista.labor.cp.service.CPRegulationService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPRegulationServiceImpl
 * @Description: 文化宫制度管理服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 10:16
 */
@Service
public class CPRegulationServiceImpl implements CPRegulationService {

    @Autowired
    private CPRegulationMapper cpRegulationMapper;

    /**
     * 获取文化宫制度
     *
     * @param id 文化宫制度id
     * @return
     */
    @Override
    public CPRegulationInfoVO getCPRegulation(String id) {
        return cpRegulationMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询文化宫制度
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPRegulationListVO> listCPRegulation(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPRegulationListVO> p = new Page<>(page, pageSize);
        List<CPRegulationListVO> cpRegulationListVOList = cpRegulationMapper.listCPRegulation(p, parameters);
        p.setRecords(cpRegulationListVOList);
        return p;
    }

    /**
     * 保存文化宫制度
     *
     * @param cpRegulationEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCPRegulation(CPRegulationEntity cpRegulationEntity) {
        if (StringUtils.isBlank(cpRegulationEntity.getId())) {
            cpRegulationEntity.setId(IdUtil.fastSimpleUUID());
            cpRegulationEntity.setState(CPConstants.INFO_VALID);
            cpRegulationMapper.insertSelective(cpRegulationEntity);
        } else {
            cpRegulationMapper.updateByPrimaryKeySelective(cpRegulationEntity);
        }
    }

    /**
     * 通过id删除文化宫制度
     *
     * @param ids 文化宫制度id的字符串数组
     * @return
     */
    @Override
    public int removeCPRegulationById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpRegulationMapper.deleteCPRegulationById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpRegulationMapper.batchDeleteCPRegulationById(paramMap);
        }
        return result;
    }
}
