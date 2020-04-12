package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTaskHistoryEntity;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryDetailVO;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryListVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: PubTaskHistoryMapper
 * @Description: 历史任务
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public interface PubTaskHistoryMapper {

    /**
     * 获取历史任务
     *
     * @param id
     * @return
     */
    PubTaskHistoryDetailVO selectByPrimaryKey(Long id);

    /**
     * 查询历史任务
     *
     * @param parameters
     * @return
     */
    List<PubTaskHistoryListVO> listPubTaskHistory(Page page, Map<String, Object> parameters);

    /**
     * 新增历史任务
     *
     * @param pubTaskHistory
     * @return
     */
    int insertSelective(CPTaskHistoryEntity pubTaskHistory);

    /**
     * 更新历史任务
     *
     * @param pubTaskHistory
     * @return
     */
    int updateByPrimaryKeySelective(CPTaskHistoryEntity pubTaskHistory);


}



