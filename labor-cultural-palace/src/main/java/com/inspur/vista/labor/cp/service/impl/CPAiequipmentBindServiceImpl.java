package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPAiequipmentBindMapper;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindVO;
import com.inspur.vista.labor.cp.service.CPAiequipmentBindService;
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
 * @Title: CPAiequipmentBindServiceImpl
 * @Description: 智能设备绑定信息服务类
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:54
 * @Version: 1.0
 */
@Service
public class CPAiequipmentBindServiceImpl implements CPAiequipmentBindService {

    private static final Logger logger = LoggerFactory.getLogger(CPAiequipmentBindServiceImpl.class);

    @Autowired
    private CPAiequipmentBindMapper cpAiequipmentBindMapper;

    /**
     * 获取智能设备绑定信息
     *
     * @param id 智能设备绑定信息id
     * @return
     */
    @Override
    public CPAiequipmentBindVO getCPAiequipmentBind(String id) {
        CPAiequipmentBindVO cpAiequipmentBindVO = cpAiequipmentBindMapper.selectByPrimaryKey(id);
        return cpAiequipmentBindVO;
    }

    /**
     * 查询智能设备绑定信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPAiequipmentBindListVO> listCPAiequipmentBind(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPAiequipmentBindListVO> p = new Page<>(page, pageSize);
        List<CPAiequipmentBindListVO> cpAiequipmentBindList = cpAiequipmentBindMapper.listCPAiequipmentBind(p, parameters);
        p.setRecords(cpAiequipmentBindList);
        return p;
    }

    /**
     * 保存智能设备绑定信息
     *
     * @param cpAiequipmentBind
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPAiequipmentBindEntity saveCPAiequipmentBind(CPAiequipmentBindEntity cpAiequipmentBind) {
        if (null == cpAiequipmentBind.getId() || "".equals(cpAiequipmentBind.getId())) {
            cpAiequipmentBind.setId(IdUtil.fastSimpleUUID());
            cpAiequipmentBind.setState(CPConstants.INFO_VALID);
            cpAiequipmentBindMapper.insertSelective(cpAiequipmentBind);
        } else {
            cpAiequipmentBindMapper.updateByPrimaryKeySelective(cpAiequipmentBind);
        }
        return cpAiequipmentBind;
    }

    /**
     * 通过id删除智能设备绑定信息
     *
     * @param ids 智能设备绑定信息id的字符串数组
     * @return
     */
    @Override
    public int removeCPAiequipmentBindById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpAiequipmentBindMapper.deleteCPAiequipmentBindById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpAiequipmentBindMapper.batchDeleteCPAiequipmentBindById(paramMap);
        }
        return result;
    }
}
