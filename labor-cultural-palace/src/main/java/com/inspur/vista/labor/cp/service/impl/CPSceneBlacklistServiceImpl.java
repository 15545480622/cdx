package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alipay.api.AlipayApiException;
import com.inspur.vista.labor.cp.dao.CPCourtInfoMapper;
import com.inspur.vista.labor.cp.dao.CPReserveSceneMapper;
import com.inspur.vista.labor.cp.dao.CPSceneBlacklistMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPReserveInfoService;
import com.inspur.vista.labor.cp.service.CPSceneBlacklistService;
import com.inspur.vista.labor.cp.service.CPSceneInfoService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPSceneBlacklistServiceImpl
 * @Description: 场次黑名单
 * @Author: gengpeng
 * @CreateDate: 2020/3/17 11:04
 * @Version: 1.0
 */
@Service
public class CPSceneBlacklistServiceImpl implements CPSceneBlacklistService {

    @Autowired
    private CPSceneBlacklistMapper blacklistMapper;

    @Autowired
    private CPSceneInfoService sceneInfoService;

    @Autowired
    private CPReserveSceneMapper reserveSceneMapper;

    @Autowired
    private CPReserveInfoService reserveInfoService;

    @Autowired
    private CPCourtInfoMapper cpCourtInfoMapper;

    /**
     * 新增黑名单记录
     *
     * @param sceneBlacklistVO
     */
    @Override
    public void addToBlacklist(CPSceneBlacklistVO sceneBlacklistVO, String modifier) throws AlipayApiException {
        // 如果是场地的黑名单
        if (1 == sceneBlacklistVO.getType()) {
            CPCourtInfoVO courtInfoVO = cpCourtInfoMapper.selectByPrimaryKey(sceneBlacklistVO.getCourtId());
            List<CPSceneInfoVO> sceneList = new ArrayList<>();
            sceneInfoService.initSceneList(courtInfoVO, sceneBlacklistVO.getBeginTime(),
                    sceneBlacklistVO.getEndTime(), sceneList);
            List<CPSceneBlacklistEntity> sceneBlacklistList = new ArrayList<>();
            for (CPSceneInfoVO sceneInfoVO : sceneList) {
                CPSceneBlacklistEntity sceneBlacklistEntity = new CPSceneBlacklistEntity();
                sceneBlacklistEntity.setId(IdUtil.fastSimpleUUID());
                sceneBlacklistEntity.setCourtId(sceneBlacklistVO.getCourtId());
                sceneBlacklistEntity.setBeginTime(sceneInfoVO.getBeginTime());
                sceneBlacklistEntity.setEndTime(sceneInfoVO.getEndTime());
                sceneBlacklistList.add(sceneBlacklistEntity);
            }
            blacklistMapper.batchInsert(sceneBlacklistList);
            // 判断有没有预约的人，如果有则取消预约
            Map paramMap = new HashMap();
            paramMap.put("courtId", sceneBlacklistVO.getCourtId());
            paramMap.put("cxBeginDate", sceneBlacklistVO.getBeginTime());
            paramMap.put("cxEndDate", sceneBlacklistVO.getEndTime());
            paramMap.put("states", new int[]{0, 1});
            List<CPReserveSceneListVO> reserveSceneList = reserveSceneMapper.listByBeginDateAndEndDate(paramMap);
            List<String> reserveIdList = new ArrayList<>();
            for (CPReserveSceneListVO reserveSceneListVO : reserveSceneList) {
                reserveIdList.add(reserveSceneListVO.getReserveId());
            }
            if (!reserveIdList.isEmpty()) {
                for (String reserveId : reserveIdList) {
                    reserveInfoService.cancelReserve(CPConstants.CANCEL_REASON_TYPE_OTHER, sceneBlacklistVO.getCancelReasonExt(),
                            reserveId, modifier, true);
                }
            }
        } else {
            CPSceneBlacklistEntity sceneBlacklistEntity = new CPSceneBlacklistEntity();
            sceneBlacklistEntity.setId(IdUtil.fastSimpleUUID());
            sceneBlacklistEntity.setCourtId(sceneBlacklistVO.getCourtId());
            sceneBlacklistEntity.setBeginTime(DateUtil.parse(sceneBlacklistVO.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
            sceneBlacklistEntity.setEndTime(DateUtil.parse(sceneBlacklistVO.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            blacklistMapper.insertSelective(sceneBlacklistEntity);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("courtId", sceneBlacklistVO.getCourtId());
            paramMap.put("beginTime", sceneBlacklistVO.getBeginTime());
            paramMap.put("endTime", sceneBlacklistVO.getEndTime());
            List<CPReserveSceneListVO> reserveSceneVOList = reserveSceneMapper.selectByTimeAndCourt(paramMap);
            if (!reserveSceneVOList.isEmpty()) {
                for (CPReserveSceneListVO reserveSceneVO : reserveSceneVOList) {
                    reserveInfoService.cancelReserve(CPConstants.CANCEL_REASON_TYPE_OTHER, sceneBlacklistVO.getCancelReasonExt(),
                            reserveSceneVO.getReserveId(), modifier, true);
                }
            }
        }
    }

    /**
     * 删除黑名单记录
     *
     * @param sceneBlacklistVO
     */
    @Override
    public void removeFromBlacklist(CPSceneBlacklistVO sceneBlacklistVO) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courtId", sceneBlacklistVO.getCourtId());
        paramMap.put("cxBeginTime", sceneBlacklistVO.getBeginTime());
        paramMap.put("cxEndTime", sceneBlacklistVO.getEndTime());
        //如果是场地黑名单
        if (1 == sceneBlacklistVO.getType()) {
            blacklistMapper.batchDeleteSceneBlacklist(paramMap);
        } else {
            blacklistMapper.deleteSceneBlacklist(paramMap);
        }
    }
}
