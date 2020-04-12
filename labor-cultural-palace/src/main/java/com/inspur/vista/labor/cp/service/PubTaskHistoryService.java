package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTaskHistoryEntity;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryDetailVO;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryListVO;

import java.util.Map;


/**
 * @Title: PubTaskHistoryService
 * @Description: 历史任务服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public interface PubTaskHistoryService {

    /**
     * 获取历史任务
     *
     * @param id
     * @return
     */
    PubTaskHistoryDetailVO getPubTaskHistory(Long id);

    /**
     * 查询历史任务
     *
     * @param parameters
     * @return
     */
    Page<PubTaskHistoryListVO> listPubTaskHistory(Map<String, Object> parameters);

    /**
     * 保存历史任务
     *
     * @param pubTaskHistory
     * @return
     */
    CPTaskHistoryEntity savePubTaskHistory(CPTaskHistoryEntity pubTaskHistory);


}



