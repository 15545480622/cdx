package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTaskEntity;
import com.inspur.vista.labor.cp.entity.CPTaskListVO;
import com.inspur.vista.labor.cp.entity.CPTaskVO;

import java.util.Map;


/**
 * @Title: PubTaskTodoService
 * @Description: 待办任务服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public interface CPTaskService {

    /**
     * 获取待办任务
     *
     * @param id
     * @return
     */
    CPTaskVO getPubTaskTodo(String id);

    /**
     * 查询待办任务
     *
     * @param parameters
     * @return
     */
    Page<CPTaskListVO> listPubTaskTodo(Map<String, Object> parameters);

    /**
     * 保存待办任务
     *
     * @param pubTaskTodo
     * @return
     */
    CPTaskEntity savePubTaskTodo(CPTaskEntity pubTaskTodo);


}



