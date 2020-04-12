package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthEntity;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthListVO;

import java.util.Map;

/**
 * @ClassName: CPLiabilitiesMonthService
 * @Description: 月度资产负债
 * @authur: wangxueying01
 * @CreatDate: 2020/1/9 15:58
 */
public interface CPLiabilitiesMonthService {

    /**
     * 获取月度资产负债
     * @param valueOf
     * @return
     */
    CPLiabilitiesMonthDetailVO getCPLiabilitiesMonth(Long valueOf);

    /**
     * 查询月度资产负债
     * @param parameters
     * @return
     */
    Page<CPLiabilitiesMonthListVO> listCPLiabilitiesMonth(Map<String, Object> parameters);

    /**
     * 保存月度资产负债
     * @param cpLiabilitiesMonthInfo
     */
    void saveCPLiabilitiesMonth(CPLiabilitiesMonthEntity cpLiabilitiesMonthInfo);
}