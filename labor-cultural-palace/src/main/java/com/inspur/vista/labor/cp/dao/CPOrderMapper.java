package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPOrderEntity;
import com.inspur.vista.labor.cp.entity.CPOrderListVO;
import com.inspur.vista.labor.cp.entity.CPOrderVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPOrderMapper
 * @Description: 交易流水
 * @authur: wangxueying01
 * @CreatDate: 2020/3/24 19:28
 */
public interface CPOrderMapper {

    /**
     * 获取交易流水
     * @param id
     * @return
     */
    CPOrderVO selectByPrimaryKey(String id);

    /**
     * 查询交易流水
     * @param parameters
     * @return
     */
    List<CPOrderListVO> listCPOrderInfo(Map<String, Object> parameters);

    /**
     * 新增交易流水
     * @param cpOrderEntity
     * @return
     */
    int insertSelective(CPOrderEntity cpOrderEntity);

    /**
     * 更新交易流水
     * @param cpOrderEntity
     * @return
     */
    int updateByPrimaryKeySelective(CPOrderEntity cpOrderEntity);
}