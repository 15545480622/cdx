package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.CPStatisticsMapper;
import com.inspur.vista.labor.cp.service.CPStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName: CPStatisticsServiceImpl
 * @Description: 文化宫统计服务类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/14 15:26
 */
@Service
public class CPStatisticsServiceImpl implements CPStatisticsService {

    @Autowired
    private CPStatisticsMapper cpStatisticsMapper;

    /**
     * 根据cpId 当天按小时统计文化宫流量
     *
     * @param cpId
     * @return
     */
    @Override
    public List<LinkedHashMap<String, Object>> listCountHourDataByCpId(String cpId) {
        return cpStatisticsMapper.selectCountHourDataByCpId(cpId);
    }

    /**
     * 根据cpId 近七天按天统计文化宫流量
     *
     * @param cpId
     * @return
     */
    @Override
    public List<LinkedHashMap<String, Object>> listCountWeekDataByCpId(String cpId) {
        List<LinkedHashMap<String, Object>> dataList = cpStatisticsMapper.selectCountWeekDataByCpId(cpId);
        for (LinkedHashMap<String, Object> map : dataList) {
            switch (map.get("week").toString()) {
                case "1":
                    map.put("week", "星期日");
                    break;
                case "2":
                    map.put("week", "星期一");
                    break;
                case "3":
                    map.put("week", "星期二");
                    break;
                case "4":
                    map.put("week", "星期三");
                    break;
                case "5":
                    map.put("week", "星期四");
                    break;
                case "6":
                    map.put("week", "星期五");
                    break;
                case "7":
                    map.put("week", "星期六");
                    break;
                default:
                    map.put("week", "未知");
                    break;
            }
        }
        return dataList;
    }

    /**
     * 根据cpId 近30天按天统计文化宫流量
     *
     * @param cpId
     * @return
     */
    @Override
    public List<LinkedHashMap<String, Object>> listCountMonthDataByCpId(String cpId) {
        return cpStatisticsMapper.selectCountMonthDataByCpId(cpId);
    }

    /**
     * 根据cpId 近一年按月统计文化宫流量
     *
     * @param cpId
     * @return
     */
    @Override
    public List<LinkedHashMap<String, Object>> listCountYearDataByCpId(String cpId) {
        return cpStatisticsMapper.selectCountYearDataByCpId(cpId);
    }
}
