package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPFundsMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPFundsMonthEntity;
import com.inspur.vista.labor.cp.entity.CPFundsMonthListVO;

import java.util.Map;

/**
 * @ClassName: CPFundsMonthService
 * @Description: 月度经费收支服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/13 8:50
 */
public interface CPFundsMonthService {

    /**
     * 获取月度经费收支
     * @param valueOf
     * @return
     */
    CPFundsMonthDetailVO getCPFundsMonth(Long valueOf);

    /**
     * 查询月度经费收支
     * @param parameters
     * @return
     */
    Page<CPFundsMonthListVO> listCPFundsMonth(Map<String, Object> parameters);

    /**
     * 保存月度经费收支
     * @param cpFundsMonthInfo
     */
    void saveCPFundsMonth(CPFundsMonthEntity cpFundsMonthInfo);
}