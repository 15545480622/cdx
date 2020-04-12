package com.inspur.vista.labor.cp.controller;

import com.inspur.vista.labor.cp.service.CPStatisticsService;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName: CPStatisticsController
 * @Description: 文化宫统计接口类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/14 15:24
 */
@RestController
@RequestMapping("/api/statistics")
public class CPStatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(CPStatisticsController.class);

    @Autowired
    private CPStatisticsService cpStatisticsService;

    /**
     * 根据文化宫id---当天按小时统计
     *
     * @param cpId
     * @return
     */
    @RequestMapping("/flow/hour/{cpId}")
    public ResponseDTO countHourData(@PathVariable("cpId") String cpId) {

        List<LinkedHashMap<String, Object>> dayDataList = cpStatisticsService.listCountHourDataByCpId(cpId);
        return WebUtils.createSuccessResponse(dayDataList);
    }

    /**
     * 根据文化宫id---近7天按天统计
     *
     * @param cpId
     * @return
     */
    @RequestMapping("/flow/weekcount/{cpId}")
    public ResponseDTO countWeekData(@PathVariable("cpId") String cpId) {

        List<LinkedHashMap<String, Object>> dayDataList = cpStatisticsService.listCountWeekDataByCpId(cpId);
        return WebUtils.createSuccessResponse(dayDataList);
    }

    /**
     * 根据文化宫id---近30天按天统计
     *
     * @param cpId
     * @return
     */
    @RequestMapping("/flow/monthcount/{cpId}")
    public ResponseDTO countMonthData(@PathVariable("cpId") String cpId) {

        List<LinkedHashMap<String, Object>> dayDataList = cpStatisticsService.listCountMonthDataByCpId(cpId);
        return WebUtils.createSuccessResponse(dayDataList);
    }

    /**
     * 根据文化宫id---近一年按月统计
     *
     * @param cpId
     * @return
     */
    @RequestMapping("/flow/yearcount/{cpId}")
    public ResponseDTO countYearData(@PathVariable("cpId") String cpId) {

        List<LinkedHashMap<String, Object>> dayDataList = cpStatisticsService.listCountYearDataByCpId(cpId);
        return WebUtils.createSuccessResponse(dayDataList);
    }

}
