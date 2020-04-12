package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.CantMapper;
import com.inspur.vista.labor.cp.entity.CantVO;
import com.inspur.vista.labor.cp.service.CantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: CantService
 * @Description: 行政区划服务类
 * @Author: gengpeng
 * @CreateDate: 2019/9/20 18:48
 * @Version: 1.0
 */
@Service
public class CantServiceImpl implements CantService {

    @Autowired
    private CantMapper cantMapper;

    @Override
    public List<CantVO> selectDirectCant(String superCode) {
        return cantMapper.selectDirectCant(superCode);
    }

    @Override
    public String selectCantCodeByCodeName(String cantName) {
        return cantMapper.selectCantCodeByCantName(cantName);
    }
}
