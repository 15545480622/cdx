package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.PubTaskHistoryMapper;
import com.inspur.vista.labor.cp.entity.CPTaskHistoryEntity;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryDetailVO;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryListVO;
import com.inspur.vista.labor.cp.service.PubTaskHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Title: PubTaskHistoryServiceImpl
 * @Description: 历史任务服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
@Service
public class PubTaskHistoryServiceImpl implements PubTaskHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(PubTaskHistoryServiceImpl.class);

    @Autowired
    private PubTaskHistoryMapper pubTaskHistoryMapper;

    /**
     * 获取历史任务
     *
     * @param id 历史任务id
     * @return
     */
    @Override
    public PubTaskHistoryDetailVO getPubTaskHistory(Long id) {
        PubTaskHistoryDetailVO pubTaskHistoryDetailVO = pubTaskHistoryMapper.selectByPrimaryKey(id);
        return pubTaskHistoryDetailVO;
    }

    /**
     * 查询历史任务
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<PubTaskHistoryListVO> listPubTaskHistory(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<PubTaskHistoryListVO> p = new Page<>(page, pageSize);
        List<PubTaskHistoryListVO> pubTaskHistoryList = pubTaskHistoryMapper.listPubTaskHistory(p, parameters);
        p.setRecords(pubTaskHistoryList);
        return p;
    }

    /**
     * 保存历史任务
     *
     * @param pubTaskHistory
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPTaskHistoryEntity savePubTaskHistory(CPTaskHistoryEntity pubTaskHistory) {
        if (null == pubTaskHistory.getId()) {
            pubTaskHistoryMapper.insertSelective(pubTaskHistory);
        } else {
            pubTaskHistoryMapper.updateByPrimaryKeySelective(pubTaskHistory);
        }
        return pubTaskHistory;
    }

}
