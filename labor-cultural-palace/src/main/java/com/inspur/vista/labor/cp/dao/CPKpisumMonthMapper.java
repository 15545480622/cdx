package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthEntity;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPKpisumMonthMapper
 * @Description: 文化宫月度统计
 * @authur: wangxueying01
 * @CreatDate: 2020/1/8 11:32
 */
public interface CPKpisumMonthMapper {

    /**
     * 获取月度统计
     * @param id
     * @return
     */
    CPKpisumMonthDetailVO selectByPrimaryKey(Long id);

    /**
     * 查询月度统计
     * @param p
     * @param parameters
     * @return
     */
    List<CPKpisumMonthListVO> listCPKpisumMonth(Page<CPKpisumMonthListVO> p, Map<String, Object> parameters);

    /**
     * 新增月度统计
     * @param cpKpisumMonthEntity
     */
    void insertSelective(CPKpisumMonthEntity cpKpisumMonthEntity);
}