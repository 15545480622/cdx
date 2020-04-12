package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPAiequipmentFirmInfoMapper;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentFirmInfoVO;
import com.inspur.vista.labor.cp.service.CPAiequipmentFirmInfoService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPAiequipmentFirmInfoServiceImpl
 * @Description: 智能设备厂商信息服务类
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:05
 * @Version: 1.0
 */
@Service
public class CPAiequipmentFirmInfoServiceImpl implements CPAiequipmentFirmInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPAiequipmentFirmInfoServiceImpl.class);

    @Autowired
    private CPAiequipmentFirmInfoMapper cpAiequipmentFirmInfoMapper;

    /**
     * 获取智能设备厂商信息
     *
     * @param id 智能设备厂商信息id
     * @return
     */
    @Override
    public CPAiequipmentFirmInfoVO getCPAiequipmentFirmInfo(String id) {
        CPAiequipmentFirmInfoVO cpAiequipmentFirmInfoVO = cpAiequipmentFirmInfoMapper.selectByPrimaryKey(id);
        return cpAiequipmentFirmInfoVO;
    }

    /**
     * 查询智能设备厂商信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPAiequipmentFirmInfoListVO> listCPAiequipmentFirmInfo(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPAiequipmentFirmInfoListVO> p = new Page<>(page, pageSize);
        List<CPAiequipmentFirmInfoListVO> cpAiequipmentFirmInfoList = cpAiequipmentFirmInfoMapper.listCPAiequipmentFirmInfo(p, parameters);
        p.setRecords(cpAiequipmentFirmInfoList);
        return p;
    }

    /**
     * 保存智能设备厂商信息
     *
     * @param cpAiequipmentFirmInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPAiequipmentFirmInfoEntity saveCPAiequipmentFirmInfo(CPAiequipmentFirmInfoEntity cpAiequipmentFirmInfo) {
        if (null == cpAiequipmentFirmInfo.getId() || "".equals(cpAiequipmentFirmInfo.getId())) {
            cpAiequipmentFirmInfo.setId(IdUtil.fastSimpleUUID());
            cpAiequipmentFirmInfo.setState(CPConstants.INFO_VALID);
            cpAiequipmentFirmInfoMapper.insertSelective(cpAiequipmentFirmInfo);
        } else {
            cpAiequipmentFirmInfoMapper.updateByPrimaryKeySelective(cpAiequipmentFirmInfo);
        }
        return cpAiequipmentFirmInfo;
    }

    /**
     * 通过id删除智能设备厂商信息
     *
     * @param ids 智能设备厂商信息id的字符串数组
     * @return
     */
    @Override
    public int removeCPAiequipmentFirmInfoById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpAiequipmentFirmInfoMapper.deleteCPAiequipmentFirmInfoById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpAiequipmentFirmInfoMapper.batchDeleteCPAiequipmentFirmInfoById(paramMap);
        }
        return result;
    }
}
