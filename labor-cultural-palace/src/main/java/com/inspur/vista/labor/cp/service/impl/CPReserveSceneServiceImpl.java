package com.inspur.vista.labor.cp.service.impl;


import com.inspur.vista.labor.cp.dao.CPReserveSceneMapper;
import com.inspur.vista.labor.cp.service.CPReserveSceneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: CPReserveSceneServiceImpl
 * @Description: 预约场次记录服务类
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@Service
public class CPReserveSceneServiceImpl implements CPReserveSceneService {

    private static final Logger logger = LoggerFactory.getLogger(CPReserveSceneServiceImpl.class);

    @Autowired
    private CPReserveSceneMapper cpReserveSceneMapper;
}
