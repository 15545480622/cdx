package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.util.IdUtil;
import com.inspur.vista.labor.cp.dao.CPOrderMapper;
import com.inspur.vista.labor.cp.entity.CPOrderEntity;
import com.inspur.vista.labor.cp.entity.CPOrderListVO;
import com.inspur.vista.labor.cp.entity.CPOrderVO;
import com.inspur.vista.labor.cp.service.CPOrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPOrderServiceImpl
 * @Description: 交易流水
 * @authur: wangxueying01
 * @CreatDate: 2020/3/24 19:24
 */
@Service
public class CPOrderServiceImpl implements CPOrderService {

    private static final Logger logger = LoggerFactory.getLogger(CPOrderServiceImpl.class);

    @Autowired
    private CPOrderMapper cpOrderMapper;

    /**
     * 获取交易流水
     *
     * @param id
     * @return
     */
    @Override
    public CPOrderVO getCPOrderInfo(String id) {
        CPOrderVO cpOrderVO = cpOrderMapper.selectByPrimaryKey(id);
        return cpOrderVO;
    }

    /**
     * 查询交易流水
     *
     * @param parameters
     * @return
     */
    @Override
    public List<CPOrderListVO> listCPOrderInfo(Map<String, Object> parameters) {
        List<CPOrderListVO> cpOrderListVOS = cpOrderMapper.listCPOrderInfo(parameters);
        return cpOrderListVOS;
    }

    /**
     * 保存交易流水
     *
     * @param cpOrderEntity
     * @return
     */
    @Override
    public CPOrderEntity saveCPOrderInfo(CPOrderEntity cpOrderEntity) {
        if (StringUtils.isBlank(cpOrderEntity.getId())){
            cpOrderEntity.setId(IdUtil.fastSimpleUUID());
            cpOrderMapper.insertSelective(cpOrderEntity);
        }else{
            cpOrderMapper.updateByPrimaryKeySelective(cpOrderEntity);
        }
            return cpOrderEntity;
    }
}
