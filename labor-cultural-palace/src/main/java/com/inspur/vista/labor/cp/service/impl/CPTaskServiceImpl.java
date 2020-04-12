package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPInfoApplyMapper;
import com.inspur.vista.labor.cp.dao.CPTaskMapper;
import com.inspur.vista.labor.cp.entity.CPTaskEntity;
import com.inspur.vista.labor.cp.entity.CPTaskListVO;
import com.inspur.vista.labor.cp.entity.CPTaskVO;
import com.inspur.vista.labor.cp.service.CPTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Title: PubTaskTodoServiceImpl
 * @Description: 待办任务服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
@Service
public class CPTaskServiceImpl implements CPTaskService {

    private static final Logger logger = LoggerFactory.getLogger(CPTaskServiceImpl.class);

    @Autowired
    private CPTaskMapper CPTaskMapper;

    @Autowired
    private CPInfoApplyMapper infoApplyMapper;

    /**
     * 获取待办任务
     *
     * @param id 待办任务id
     * @return
     */
    @Override
    public CPTaskVO getPubTaskTodo(String id) {
        CPTaskVO CPTaskVO = CPTaskMapper.selectByPrimaryKey(id);
        return CPTaskVO;
    }

    /**
     * 查询待办任务
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPTaskListVO> listPubTaskTodo(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPTaskListVO> p = new Page<>(page, pageSize);
        List<CPTaskListVO> pubTaskTodoList = CPTaskMapper.listCPTask(p, parameters);
        p.setRecords(pubTaskTodoList);
        return p;
    }

    /**
     * 保存待办任务
     *
     * @param pubTaskTodo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPTaskEntity savePubTaskTodo(CPTaskEntity pubTaskTodo) {
        CPTaskMapper.insertSelective(pubTaskTodo);
        return pubTaskTodo;
    }

}
