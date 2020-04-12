package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPFundsMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPFundsMonthEntity;
import com.inspur.vista.labor.cp.entity.CPFundsMonthListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPFundsMonthMapper
 * @Description: 月度经费收支
 * @authur: wangxueying01
 * @CreatDate: 2020/1/13 8:47
 */
public interface CPFundsMonthMapper {

    /**
     * 获取月度经费收支
     * @param id
     * @return
     */
    CPFundsMonthDetailVO selectByPrimaryKey(Long id);

    /**
     * 查询月度经费收支
     * @param p
     * @param parameters
     * @return
     */
    List<CPFundsMonthListVO> listCPFundsMonth(Page<CPFundsMonthListVO> p, Map<String, Object> parameters);

    /**
     * 新增月度经费收支
     * @param cpFundsMonthEntity
     */
    void insertSelective(CPFundsMonthEntity cpFundsMonthEntity);

    /**
     * 更新月度经费收支
     * @param cpFundsMonthEntity
     */
    void updateByPrimaryKeySelective(CPFundsMonthEntity cpFundsMonthEntity);
}