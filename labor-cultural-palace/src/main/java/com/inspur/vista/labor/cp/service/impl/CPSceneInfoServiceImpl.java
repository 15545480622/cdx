package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.NumberUtil;
import com.inspur.vista.labor.cp.dao.CPCourtFeeMapper;
import com.inspur.vista.labor.cp.dao.CPCourtInfoMapper;
import com.inspur.vista.labor.cp.dao.CPReserveSceneMapper;
import com.inspur.vista.labor.cp.dao.CPSceneBlacklistMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPSceneInfoService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.CPUtils;
import com.inspur.vista.labor.cp.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Title: CPSceneDefinitionServiceImpl
 * @Description: 预约场次定义，根据预约计划自动生成服务类
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@Service
public class CPSceneInfoServiceImpl implements CPSceneInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPSceneInfoServiceImpl.class);

    @Autowired
    private CPCourtInfoMapper cpCourtInfoMapper;

    @Autowired
    private CPReserveSceneMapper sceneMapper;

    @Autowired
    private CPSceneBlacklistMapper sceneBlacklistMapper;

    @Autowired
    private CPCourtFeeMapper courtFeeMapper;


    /**
     * 查询预约场次定义，根据预约计划自动生成
     *
     * @param parameters courtId:场地id；cxBeginDate:开始日期；cxEndDate:结束日期；fileterExpireScene:是否排除过期的场次，默认true
     * @return
     */
    @Override
    public List<CPSceneInfoVO> listCPScene(Map<String, Object> parameters) {
        String courtId = Convert.toStr(parameters.get("courtId"));
        String startDate = Convert.toStr(parameters.get("cxBeginDate"));
        String endDate = Convert.toStr(parameters.get("cxEndDate"));
        boolean fileterExpireScene = Convert.toBool(parameters.get("fileterExpireScene"), true);
        CPCourtInfoVO courtInfoVO = cpCourtInfoMapper.selectByPrimaryKey(courtId);
        List<CPSceneInfoVO> sceneList = new ArrayList<>();
        if (null != courtInfoVO) {
            //1.按照最小起租时间对每天有效的时间段进行分割
            initSceneList(courtInfoVO, startDate, endDate, sceneList);
            //2.删除过期的场次，app查询需要删除，后台不需要
            if (fileterExpireScene) {
                sceneList = removeExpireScene(sceneList);
            }
            //3.通过黑名单筛选
            filterSceneListByBlacklist(courtInfoVO, startDate, endDate, sceneList);
            //4.通过预约记录过滤
            filterSceneListByReserve(courtInfoVO, startDate, endDate, sceneList);
            //5.添加预约费用和使用费用
            addFee(courtInfoVO, sceneList);
        }

        return sceneList;
    }

    /**
     * 初始化场次时间列表
     *
     * @param courtInfoVO 场地
     * @param startDate
     * @param endDate
     * @param sceneList   生成的场次列表
     * @return
     */
    @Override
    public void initSceneList(CPCourtInfoVO courtInfoVO, String startDate, String endDate, List<CPSceneInfoVO> sceneList) {
        List<DateTime> dateList = DateUtil.
                rangeToList(DateUtil.parse(startDate, "yyyy-MM-dd"), DateUtil.parse(endDate, "yyyy-MM-dd"), DateField.DAY_OF_MONTH);
        for (DateTime dateTime : dateList) {
            boolean include = true;
            // 判断计划是否包含周末，0.不包含；1.包含
            if (courtInfoVO.getIncludeWeekend() == 0) {
                if (CalendarUtil.getInstance().isWeekends(dateTime.toDateStr())) {
                    include = false;
                }
            }
            // 判断计划是否包含法定节假日，0.不包含；1.包含
            if (courtInfoVO.getIncludeHolidays() == 0) {
                if (CalendarUtil.getInstance().isLawHoliday(dateTime.toDateStr())) {
                    include = false;
                }
            }
            if (include) {
                DateTime start;
                DateTime end;
                Calendar cal = Calendar.getInstance();
                //3.处理上午的时间
                if (null != courtInfoVO.getMorningStartTime() && null != courtInfoVO.getMorningEndTime()) {
                    start = DateUtil.parse(dateTime.toDateStr() + " " + courtInfoVO.getMorningStartTime(), "yyyy-MM-dd HH:mm:ss");
                    end = DateUtil.parse(dateTime.toDateStr() + " " + courtInfoVO.getMorningEndTime(), "yyyy-MM-dd HH:mm:ss");
                    while (start.before(end)) {
                        cal.setTime(start);

                        CPSceneInfoVO sceneInfo = new CPSceneInfoVO();
                        sceneInfo.setCourtId(courtInfoVO.getId());
                        sceneInfo.setBeginTime(start.toJdkDate());
                        cal.add(Calendar.MINUTE, (int) (courtInfoVO.getMinimumHireHour() * 60));
                        sceneInfo.setEndTime(cal.getTime());
                        sceneInfo.setState(CPConstants.INFO_VALID);
                        start = DateUtil.date(cal.getTime());
                        sceneList.add(sceneInfo);
                    }
                }
                //4.处理下午的时间
                if (null != courtInfoVO.getAfternoonStartTime() && null != courtInfoVO.getAfternoonEndTime()) {
                    start = DateUtil.parse(dateTime.toDateStr() + " " + courtInfoVO.getAfternoonStartTime(), "yyyy-MM-dd HH:mm:ss");
                    end = DateUtil.parse(dateTime.toDateStr() + " " + courtInfoVO.getAfternoonEndTime(), "yyyy-MM-dd HH:mm:ss");
                    while (start.before(end)) {
                        cal.setTime(start);

                        CPSceneInfoVO sceneInfo = new CPSceneInfoVO();
                        sceneInfo.setCourtId(courtInfoVO.getId());
                        sceneInfo.setBeginTime(start.toJdkDate());
                        cal.add(Calendar.MINUTE, (int) (courtInfoVO.getMinimumHireHour() * 60));
                        sceneInfo.setEndTime(cal.getTime());
                        sceneInfo.setState(CPConstants.INFO_VALID);
                        start = DateUtil.date(cal.getTime());
                        sceneList.add(sceneInfo);
                    }
                }
                //4.处理晚上的时间
                if (null != courtInfoVO.getEveningStartTime() && null != courtInfoVO.getEveningEndTime()) {
                    start = DateUtil.parse(dateTime.toDateStr() + " " + courtInfoVO.getEveningStartTime(), "yyyy-MM-dd HH:mm:ss");
                    end = DateUtil.parse(dateTime.toDateStr() + " " + courtInfoVO.getEveningEndTime(), "yyyy-MM-dd HH:mm:ss");
                    while (start.before(end)) {
                        cal.setTime(start);

                        CPSceneInfoVO sceneInfo = new CPSceneInfoVO();
                        sceneInfo.setCourtId(courtInfoVO.getId());
                        sceneInfo.setBeginTime(start.toJdkDate());
                        cal.add(Calendar.MINUTE, (int) (courtInfoVO.getMinimumHireHour() * 60));
                        sceneInfo.setEndTime(cal.getTime());
                        sceneInfo.setState(CPConstants.INFO_VALID);
                        start = DateUtil.date(cal.getTime());
                        sceneList.add(sceneInfo);
                    }
                }
            }
        }
    }

    /**
     * 通过黑名单过滤
     *
     * @param courtInfoVO
     * @param startDate
     * @param endDate
     * @param sceneList
     */
    private void filterSceneListByBlacklist(CPCourtInfoVO courtInfoVO, String startDate, String endDate, List<CPSceneInfoVO> sceneList) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("courtId", courtInfoVO.getId());
        parameters.put("cxBeginDate", startDate);
        parameters.put("cxEndDate", endDate);
        List<CPSceneBlacklistEntity> sceneBlacklistList = sceneBlacklistMapper.listSceneBlacklist(parameters);
        for (CPSceneInfoVO sceneInfoVO : sceneList) {
            for (CPSceneBlacklistEntity sceneBlacklist : sceneBlacklistList) {
                if (CPUtils.isOverlap(sceneInfoVO.getBeginTime(), sceneInfoVO.getEndTime(), sceneBlacklist.getBeginTime(), sceneBlacklist.getEndTime())
                        && sceneInfoVO.getState().equals(CPConstants.SCENE_STATE_CAN_RESERVE)) {
                    sceneInfoVO.setState(CPConstants.SCENE_STATE_NO_RESERVE);
                }
            }
        }
    }

    /**
     * 通过预约记录过滤场次
     *
     * @param courtInfoVO
     * @param startDate
     * @param endDate
     * @param sceneList
     */
    private void filterSceneListByReserve(CPCourtInfoVO courtInfoVO, String startDate, String endDate, List<CPSceneInfoVO> sceneList) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("courtId", courtInfoVO.getId());
        parameters.put("cxBeginDate", startDate);
        parameters.put("cxEndDate", endDate);
        // 待支付、预约成功、完成
        parameters.put("states", new int[]{0, 1, 5});
        List<CPReserveSceneListVO> reserveSceneList = sceneMapper.listByBeginDateAndEndDate(parameters);
        if (CPConstants.RESERVE_TYPE_SCENE_LIMIT_TIMES == courtInfoVO.getReserveType()) {
            for (CPSceneInfoVO sceneInfoVO : sceneList) {
                sceneInfoVO.setRemainingNumber(-1);
                for (CPReserveSceneListVO reserveSceneListVO : reserveSceneList) {
                    if (DateUtil.isSameTime(sceneInfoVO.getBeginTime(), reserveSceneListVO.getBeginTime())
                            && DateUtil.isSameTime(sceneInfoVO.getEndTime(), reserveSceneListVO.getEndTime())) {
                        sceneInfoVO.setReserveId(reserveSceneListVO.getReserveId());
                        sceneInfoVO.setState(CPConstants.SCENE_STATE_HAS_RESERVE);
                    }
                }
            }
        } else {
            for (CPSceneInfoVO sceneInfoVO : sceneList) {
                int count = 0;
                for (CPReserveSceneListVO reserveSceneListVO : reserveSceneList) {
                    if (DateUtil.isSameTime(sceneInfoVO.getBeginTime(), reserveSceneListVO.getBeginTime())
                            && DateUtil.isSameTime(sceneInfoVO.getEndTime(), reserveSceneListVO.getEndTime())) {
                        count = count + reserveSceneListVO.getUserNum();
                    }
                }
                if (courtInfoVO.getReserveCapacity() != 0) {
                    if (courtInfoVO.getReserveCapacity() == count) {
                        sceneInfoVO.setState(CPConstants.SCENE_STATE_HAS_RESERVE);
                        sceneInfoVO.setRemainingNumber(0);
                    } else {
                        sceneInfoVO.setRemainingNumber(courtInfoVO.getReserveCapacity() - count);
                    }
                } else {
                    sceneInfoVO.setRemainingNumber(-1);
                }
            }
        }
    }

    /**
     * 给场次增加费用
     *
     * @param courtInfoVO
     * @param sceneList
     */
    private void addFee(CPCourtInfoVO courtInfoVO, List<CPSceneInfoVO> sceneList) {
        String tmpDate = "";
        Double reserveFee = 0d;
        Double useFee = 0d;
        for (CPSceneInfoVO sceneInfoVO : sceneList) {
            String date = DateUtil.format(sceneInfoVO.getBeginTime(), "yyyy-MM-dd");
            if (!tmpDate.equals(date)) {
                tmpDate = date;
                CPCourtFeeVO cpCourtFeeVO = courtFeeMapper.selectByCourtIdAndDate(courtInfoVO.getId(), date);
                if (null != cpCourtFeeVO) {
                    reserveFee = cpCourtFeeVO.getReserveFee();
                    useFee = cpCourtFeeVO.getUseFee();
                }
            }
            sceneInfoVO.setReserveFee(reserveFee);
            sceneInfoVO.setUseFee(NumberUtil.round(useFee * courtInfoVO.getMinimumHireHour(), 2).doubleValue());
        }
    }


    /**
     * 删除过期的场次
     *
     * @param sceneList
     */
    private List<CPSceneInfoVO> removeExpireScene(List<CPSceneInfoVO> sceneList) {
        sceneList = CollUtil.filter(sceneList, new Editor<CPSceneInfoVO>() {
            @Override
            public CPSceneInfoVO edit(CPSceneInfoVO cpSceneInfoVO) {
                if (DateUtil.date().after(cpSceneInfoVO.getEndTime()) || DateUtil.isSameTime(DateUtil.date(), cpSceneInfoVO.getEndTime())) {
                    return null;
                }
                return cpSceneInfoVO;
            }
        });
        return sceneList;
    }
}
