package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthEntity;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPLiabilitiesMonthMapper
 * @Description: 月度资产负债
 * @authur: wangxueying01
 * @CreatDate: 2020/1/9 15:56
 */
public interface CPLiabilitiesMonthMapper {


    /**
     * 获取月度资产负债
     * @param id
     * @return
     */
    CPLiabilitiesMonthDetailVO selectByPrimaryKey(Long id);

    /**
     * 查询月度资产负债
     * @param p
     * @param parameters
     * @return
     */
    List<CPLiabilitiesMonthListVO> listCPLiabilitiesMonth(Page<CPLiabilitiesMonthListVO> p, Map<String, Object> parameters);

    /**
     * 新增月度资产负债
     * @param cpLiabilitiesMonthEntity
     */
    void insertSelective(CPLiabilitiesMonthEntity cpLiabilitiesMonthEntity);

    /**
     * 更新月度资产负债
     * @param cpLiabilitiesMonthEntity
     */
    void updateByPrimaryKeySelective(CPLiabilitiesMonthEntity cpLiabilitiesMonthEntity);
}