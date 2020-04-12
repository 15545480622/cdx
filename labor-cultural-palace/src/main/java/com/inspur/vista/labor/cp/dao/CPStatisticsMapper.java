package com.inspur.vista.labor.cp.dao;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName: CPStatisticsMapper
 * @Description: 文化宫统计
 * @authur: wangxueying01
 * @CreatDate: 2019/12/14 15:28
 */
public interface CPStatisticsMapper {

    /**
     * 根据cpId 当天24个小时每个小时的访问量
     *
     * @param cpId
     * @return List
     */
    List<LinkedHashMap<String, Object>> selectCountHourDataByCpId(String cpId);

    /**
     * 根据cpId 近7天每天的访问量
     *
     * @param cpId
     * @return List
     */
    List<LinkedHashMap<String, Object>> selectCountWeekDataByCpId(String cpId);

    /**
     * 根据cpId 近30天每天的访问量
     *
     * @param cpId
     * @return List
     */
    List<LinkedHashMap<String, Object>> selectCountMonthDataByCpId(String cpId);

    /**
     * 根据cpId 近一年每月的访问量
     *
     * @param cpId
     * @return List
     */
    List<LinkedHashMap<String, Object>> selectCountYearDataByCpId(String cpId);
}