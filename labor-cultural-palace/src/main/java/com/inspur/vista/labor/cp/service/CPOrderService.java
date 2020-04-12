package com.inspur.vista.labor.cp.service;

import com.inspur.vista.labor.cp.entity.CPOrderEntity;
import com.inspur.vista.labor.cp.entity.CPOrderListVO;
import com.inspur.vista.labor.cp.entity.CPOrderVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPOrderService
 * @Description: 交易流水
 * @authur: wangxueying01
 * @CreatDate: 2020/3/24 19:24
 */
public interface CPOrderService {

    /**
     * 获取交易流水
     * @param id
     * @return
     */
    CPOrderVO getCPOrderInfo(String id);

    /**
     * 查询交易流水
     * @param parameters
     * @return
     */
    List<CPOrderListVO> listCPOrderInfo(Map<String, Object> parameters);

    /**
     * 保存交易流水
     * @param cpOrderEntity
     * @return
     */
    CPOrderEntity saveCPOrderInfo(CPOrderEntity cpOrderEntity);


}