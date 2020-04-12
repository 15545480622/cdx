package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthEntity;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthListVO;

import java.util.Map;

/**
 * @ClassName: CPKpisumMonthService
 * @Description: 文化宫月度统计服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/8 11:33
 */
public interface CPKpisumMonthService {

    /**
     * 获取月度统计
     * @param id
     * @return
     */
    CPKpisumMonthDetailVO getCPKpisumMonth(Long id);

    /**
     * 查询月度统计
     * @param parameters
     * @return
     */
    Page<CPKpisumMonthListVO> listCPKpisumMonth(Map<String, Object> parameters);

    /**
     * 新增月度统计
     * @param cpKpisumMonthEntity
     */
    void saveKpisumMonth(CPKpisumMonthEntity cpKpisumMonthEntity);
}