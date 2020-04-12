package com.inspur.vista.labor.cp.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName: CPStatisticsService
 * @Description: 文化宫统计服务类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/14 15:25
 */
public interface CPStatisticsService {

    /**
     * 根据cpId 当天按小时统计文化宫流量
     *
     * @param cpId
     * @return
     */
    List<LinkedHashMap<String, Object>> listCountHourDataByCpId(String cpId);

    /**
     * 根据cpId 近七天按天统计文化宫流量
     *
     * @param cpId
     * @return
     */
    List<LinkedHashMap<String, Object>> listCountWeekDataByCpId(String cpId);

    /**
     * 根据cpId 近30天按天统计文化宫流量
     *
     * @param cpId
     * @return
     */
    List<LinkedHashMap<String, Object>> listCountMonthDataByCpId(String cpId);

    /**
     * 根据cpId 近一年按月统计文化宫流量
     *
     * @param cpId
     * @return
     */
    List<LinkedHashMap<String, Object>> listCountYearDataByCpId(String cpId);
}