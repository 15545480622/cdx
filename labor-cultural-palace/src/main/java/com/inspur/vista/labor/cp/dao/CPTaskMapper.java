package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTaskEntity;
import com.inspur.vista.labor.cp.entity.CPTaskHistoryEntity;
import com.inspur.vista.labor.cp.entity.CPTaskListVO;
import com.inspur.vista.labor.cp.entity.CPTaskVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: PubTaskTodoMapper
 * @Description: 待办任务
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public interface CPTaskMapper {

    /**
     * 获取待办任务
     *
     * @param bsnId
     * @return
     */
    CPTaskVO selectByPrimaryKey(String bsnId);

    /**
     * 查询待办任务
     *
     * @param parameters
     * @return
     */
    List<CPTaskListVO> listCPTask(Page page, Map<String, Object> parameters);

    /**
     * 新增待办任务
     *
     * @param taskEntity
     * @return
     */
    int insertSelective(CPTaskEntity taskEntity);

    /**
     * 新增已办任务
     *
     * @param taskHistoryEntity
     * @return
     */
    int insertHisSelective(CPTaskHistoryEntity taskHistoryEntity);

    /**
     * 删除任务
     *
     * @param bsnId
     * @return
     */
    int deleteTaskById(String bsnId);
}



