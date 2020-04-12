package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.*;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.*;
import com.inspur.vista.labor.cp.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Title: CPReserveInfoServiceImpl
 * @Description: 场地预约记录服务类
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@Service
public class CPReserveInfoServiceImpl implements CPReserveInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPReserveInfoServiceImpl.class);

    @Autowired
    private CPReserveInfoMapper cpReserveInfoMapper;

    @Autowired
    private CPReserveSceneMapper reserveSceneMapper;

    @Autowired
    private CPCourtInfoMapper courtInfoMapper;

    @Autowired
    private CPReserveUserService reserveUserService;

    @Autowired
    private CPReserveUserMapper cpReserveUserMapper;

    @Autowired
    private CPCourtFeeMapper courtFeeMapper;

    @Autowired
    private CPUsageInfoMapper usageInfoMapper;

    @Autowired
    private CPPayServce payServce;

    @Autowired
    private UacUserService uacUserService;

    @Autowired
    private JobService jobService;

    @Value("${config.reserve-time}")
    private int reserveTime;

    /**
     * 获取场地预约记录
     *
     * @param id 场地预约记录id
     * @return
     */
    @Override
    public CPReserveInfoVO getCPReserveInfo(String id) {
        CPReserveInfoVO cpReserveInfoVO = cpReserveInfoMapper.selectByPrimaryKey(id);
        if (null != cpReserveInfoVO) {
            List<String> phones = cpReserveUserMapper.selectByReserveId(id);
            //去除预约人的手机号
            phones = CollUtil.filter(phones, new Editor<String>() {
                @Override
                public String edit(String s) {
                    if (cpReserveInfoVO.getUserPhone().equals(s)) {
                        return null;
                    } else {
                        return s;
                    }
                }
            });
            if (phones.size() != 0) {
                String companionPhone = ArrayUtil.join(phones.toArray(), ",");
                cpReserveInfoVO.setCompanionPhone(companionPhone);
            }
            List<CPReserveSceneVO> reserveSceneList = reserveSceneMapper.listByReserveId(id);
            cpReserveInfoVO.setReserveSceneList(reserveSceneList);

            if (CPConstants.RESERVE_STATE_NOT_PAY.equals(cpReserveInfoVO.getState())) {
                Date endPayTime = DateUtil.offset(cpReserveInfoVO.getCreateTime(), DateField.MINUTE, reserveTime);
                long second = DateUtil.between(new Date(), endPayTime, DateUnit.SECOND);
                cpReserveInfoVO.setEndPaySecond(second);
            }
        }
        return cpReserveInfoVO;
    }

    /**
     * 查询场地预约记录
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPReserveInfoListVO> listCPReserveInfo(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPReserveInfoListVO> p = new Page<>(page, pageSize);
        List<CPReserveInfoListVO> cpReserveInfoList = cpReserveInfoMapper.listCPReserveInfo(p, parameters);
        for (CPReserveInfoListVO reserveInfo : cpReserveInfoList) {
            Map<String, Object> param = new HashMap<>();
            param.put("reserveId", reserveInfo.getId());
            param.put("userCode", parameters.get("userCode"));
            String qrCodeParam = "type=" + QRCodeConstants.QR_CODE_TYPE_RESERVE + "&param=" + CPUtils.encryptQRCodeParam(param);
            reserveInfo.setQrCodeParam(qrCodeParam);
        }
        p.setRecords(cpReserveInfoList);
        return p;
    }

    /**
     * 通过场次的起止时间查询预约记录
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPReserveInfoListVO> listCPReserveInfoByScene(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPReserveInfoListVO> p = new Page<>(page, pageSize);
        List<CPReserveInfoListVO> cpReserveInfoList = cpReserveInfoMapper.listCPReserveInfoByScene(p, parameters);
        p.setRecords(cpReserveInfoList);
        return p;
    }

    /**
     * 预定场次
     *
     * @param reserveAdd
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public ResponseDTO addReserveInfo(CPReserveAdd reserveAdd, Map<String, String> reserveUser) throws RuntimeException {
        ResponseDTO responseDTO;
        String[] sceneArray = reserveAdd.getScenes().split(",");
        // 预约满的场次
        List<String> fullReserveTimeList = new ArrayList<>();
        CPCourtInfoVO courtInfoVO = courtInfoMapper.selectByPrimaryKey(reserveAdd.getCourtId());
        for (String scene : sceneArray) {
            String[] sceneTime = scene.split("_");
            String beginTime = sceneTime[0];
            String endTime = sceneTime[1];
            Map<String, String> param = new HashMap<>();
            param.put("courtId", reserveAdd.getCourtId());
            param.put("beginTime", beginTime);
            param.put("endTime", endTime);
            List<CPReserveSceneListVO> reserveSceneVOList = reserveSceneMapper.selectByTimeAndCourt(param);
            if (CPConstants.RESERVE_TYPE_SCENE_LIMIT_TIMES == courtInfoVO.getReserveType()) {
                if (!reserveSceneVOList.isEmpty()) {
                    fullReserveTimeList.add(scene);
                }
            } else {
                if (courtInfoVO.getReserveCapacity() != 0) {
                    int sumUserNum = 0;
                    for (CPReserveSceneListVO reserveSceneVO : reserveSceneVOList) {
                        sumUserNum += reserveSceneVO.getUserNum();
                    }
                    if (courtInfoVO.getReserveCapacity() < reserveUser.size() + sumUserNum) {
                        fullReserveTimeList.add(scene);
                    }
                }
            }
        }
        if (CPConstants.RESERVE_TYPE_SCENE_NOT_LIMIT_TIMES == courtInfoVO.getReserveType() && !fullReserveTimeList.isEmpty()) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_EXIST, "以下场次已约满：" + ArrayUtil.join(fullReserveTimeList.toArray(), ","));
        } else {
            // 判断有没有重复预约的人
            Set<String> hasReRecordPhoneList = new HashSet<>();
            for (String phone : reserveUser.keySet()) {
                for (String scene : sceneArray) {
                    String[] sceneTime = scene.split("_");
                    String beginTime = sceneTime[0];
                    String endTime = sceneTime[1];
                    Map<String, Object> param = new HashMap<>();
                    param.put("phone", phone);
                    param.put("beginTime", beginTime);
                    param.put("endTime", endTime);
                    CPReserveSceneVO reserveSceneVO = reserveSceneMapper.selectByTimeAndUser(param);
                    if (null != reserveSceneVO) {
                        hasReRecordPhoneList.add(phone);
                    }
                }
            }
            if (!hasReRecordPhoneList.isEmpty()) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_EXIST, "以下人员存在重复预约的场次：" + ArrayUtil.join(hasReRecordPhoneList.toArray(), ","));
            } else {
                String cpId = courtInfoMapper.getCpIdByCourtId(reserveAdd.getCourtId());
                if (StringUtils.isBlank(cpId)) {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "场次信息错误");
                } else {
                    // 1.预约记录主要信息保存
                    CPReserveInfoEntity reserveInfoEntity = new CPReserveInfoEntity();
                    reserveInfoEntity.setId(IdUtil.fastSimpleUUID());
                    reserveInfoEntity.setCpId(cpId);
                    String no = IdUtil.createSnowflake(1, 2).nextIdStr();
                    reserveInfoEntity.setOutTradeNo(DateUtil.format(new Date(), "yyyyMMdd") + no);
                    reserveInfoEntity.setCourtId(reserveAdd.getCourtId());
                    reserveInfoEntity.setUserCode(reserveAdd.getUserCode());
                    reserveInfoEntity.setUserName(reserveAdd.getUserName());
                    reserveInfoEntity.setUserPhone(reserveAdd.getUserPhone());
                    reserveInfoEntity.setLaborId(reserveAdd.getLaborId());
                    reserveInfoEntity.setLaborName(reserveAdd.getLaborName());
                    reserveInfoEntity.setType(reserveAdd.getType());
                    reserveInfoEntity.setPersonNum(reserveUser.size());

                    // 2.批量保存预约记录与场次关系
                    List<CPReserveSceneEntity> reserveSceneList = new ArrayList<>();
                    Double reserveFee = 0d;
                    Double useFee = 0d;
                    for (String scene : sceneArray) {
                        String[] sceneTime = scene.split("_");
                        String beginTime = sceneTime[0];
                        String endTime = sceneTime[1];
                        CPReserveSceneEntity reserveScene = new CPReserveSceneEntity();
                        reserveScene.setReserveId(reserveInfoEntity.getId());
                        reserveScene.setBeginTime(DateUtil.parse(beginTime, "yyyy-MM-dd HH:mm:ss"));
                        reserveScene.setEndTime(DateUtil.parse(endTime, "yyyy-MM-dd HH:mm:ss"));
                        reserveSceneList.add(reserveScene);
                        CPCourtFeeVO cpCourtFeeVO = courtFeeMapper.selectByCourtIdAndDate(courtInfoVO.getId(), beginTime.substring(0, 10));
                        if (null != cpCourtFeeVO) {
                            reserveFee = reserveFee + cpCourtFeeVO.getReserveFee();
                            useFee = useFee + cpCourtFeeVO.getUseFee();
                        }

                    }
                    reserveInfoEntity.setReserveMoney(reserveFee);
                    reserveInfoEntity.setUserMoney(useFee);
                    if ((null != reserveInfoEntity.getReserveMoney() && 0 != reserveInfoEntity.getReserveMoney())
                            || (null != reserveInfoEntity.getUserMoney() && 0 != reserveInfoEntity.getUserMoney())) {
                        reserveInfoEntity.setState(CPConstants.RESERVE_STATE_NOT_PAY);
                    } else {
                        reserveInfoEntity.setState(CPConstants.RESERVE_STATE_SUCCESS);
                    }
                    cpReserveInfoMapper.insertSelective(reserveInfoEntity);
                    reserveSceneMapper.batchInsert(reserveSceneList);
                    // 3.批量保存预约的用户
                    reserveUserService.saveCPReserveUser(reserveUser, reserveInfoEntity.getId());

                    // 如果应支付金额不为0，则计算订单截至时间并创建定时任务
                    if ((null != reserveInfoEntity.getReserveMoney() && 0 != reserveInfoEntity.getReserveMoney())
                            || (null != reserveInfoEntity.getUserMoney() && 0 != reserveInfoEntity.getUserMoney())) {
                        Calendar reserveEndTime = DateUtil.offset(new Date(), DateField.MINUTE, reserveTime).toCalendar();
                        QuartzJobAdd quartz = new QuartzJobAdd();
                        quartz.setJobName("taskId" + reserveInfoEntity.getId());
                        quartz.setJobGroup(CPConstants.JOB_GROUP_RESERVE);
                        quartz.setDescription("文化宫预约倒计时");
                        quartz.setCronExpression(reserveEndTime.get(Calendar.SECOND) + " " + reserveEndTime.get(Calendar.MINUTE)
                                + " " + reserveEndTime.get(Calendar.HOUR_OF_DAY) + " " + reserveEndTime.get(Calendar.DAY_OF_MONTH)
                                + " " + (reserveEndTime.get(Calendar.MONTH) + 1) + " ? " + reserveEndTime.get(Calendar.YEAR));
                        quartz.setJobClassName("com.inspur.vista.labor.cp.job.ReserveStateDealJob");

                        List<Map<String, Object>> jobData = new ArrayList<>();
                        Map<String, Object> param = new LinkedHashMap<>();
                        param.put("reserveId", reserveInfoEntity.getId());
                        jobData.add(param);
                        quartz.setJobDataParam(jobData);
                        jobService.saveJob(quartz);
                        responseDTO = WebUtils.createSuccessResponse(ErrorCodeEnum.B_NEED_PAY, reserveInfoEntity.getId());
                    } else {
                        responseDTO = WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, reserveInfoEntity.getId());
                    }
                }
            }

        }
        return responseDTO;
    }

    /**
     * 完成支付，关联订单
     *
     * @param outTradeNo
     * @param orderId
     */
    @Override
    public void saveOrder(String outTradeNo, String orderId) {
        String reserveId = cpReserveInfoMapper.selectIdByOutTradeNo(outTradeNo);
        CPReserveInfoEntity reserveInfoEntity = new CPReserveInfoEntity();
        reserveInfoEntity.setId(reserveId);
        reserveInfoEntity.setOrderId(orderId);
        reserveInfoEntity.setState(CPConstants.RESERVE_STATE_SUCCESS);
        cpReserveInfoMapper.updateByPrimaryKeySelective(reserveInfoEntity);
    }

    /**
     * 通过id删除场地预约记录
     *
     * @param cancelReasonType 取消原因
     * @param cancelReasonExt  其他取消原因
     * @param id               预约记录id
     * @param modifier         修改人
     * @param allowCancel      是否允许强制取消
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO cancelReserve(Integer cancelReasonType, String cancelReasonExt, String id, String modifier, boolean allowCancel) throws AlipayApiException {
        ResponseDTO responseDTO;
        List<CPReserveSceneVO> reserveSceneList = reserveSceneMapper.listByReserveId(id);
        if (!reserveSceneList.isEmpty()) {
            CPReserveSceneVO reserveScene = reserveSceneList.get(0);
            if (DateUtil.offset(new Date(), DateField.HOUR, 2).after(reserveScene.getBeginTime()) && !allowCancel) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "已超过允许取消的时间，无法取消");
                return responseDTO;
            }
        }
        // 1.修改预约记录为无效
        CPReserveInfoVO reserveInfoInDb = cpReserveInfoMapper.selectByPrimaryKey(id);
        CPReserveInfoEntity reserveInfoEntity = new CPReserveInfoEntity();
        reserveInfoEntity.setId(id);
        reserveInfoEntity.setState(CPConstants.RESERVE_STATE_CANCEL);
        reserveInfoEntity.setCancelTime(new Date());
        reserveInfoEntity.setCancelReasonType(cancelReasonType);
        reserveInfoEntity.setCancelReasonExt(cancelReasonExt);
        reserveInfoEntity.setModifier(modifier);

        if (StringUtils.isNotBlank(reserveInfoInDb.getOrderId())) {
            payServce.refundAll(id);
        }
        cpReserveInfoMapper.updateByPrimaryKeySelective(reserveInfoEntity);
        return responseDTO = WebUtils.createSuccessResponse("");
    }

    /**
     * 关闭预约订单
     *
     * @param id
     */
    @Override
    public void closeReserve(String id) {
        CPReserveInfoEntity reserveInfoEntity = new CPReserveInfoEntity();
        reserveInfoEntity.setId(id);
        reserveInfoEntity.setState(CPConstants.RESERVE_STATE_CLOSE);
        cpReserveInfoMapper.updateByPrimaryKeySelective(reserveInfoEntity);
    }

    /**
     * 确认预约订单
     *
     * @param reserveInfoVO 订单信息
     * @param userCode      用户编码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmReserve(CPReserveInfoVO reserveInfoVO, String userCode) throws AlipayApiException {

        CPReserveInfoEntity reserveInfoEntity = new CPReserveInfoEntity();
        reserveInfoEntity.setId(reserveInfoVO.getId());
        reserveInfoEntity.setState(CPConstants.RESERVE_STATE_FINISH);
        cpReserveInfoMapper.updateByPrimaryKeySelective(reserveInfoEntity);
        CPUsageInfoEntity usageInfoEntity = new CPUsageInfoEntity();

        CPCourtInfoVO courtInfoVO = courtInfoMapper.selectByPrimaryKey(reserveInfoVO.getCourtId());
        usageInfoEntity.setId(IdUtil.fastSimpleUUID());
        usageInfoEntity.setPlaceId(courtInfoVO.getPlaceId());
        usageInfoEntity.setReserveId(reserveInfoVO.getId());
        Map userMap = uacUserService.getUserInfoByUserCode(userCode);
        String userPhone = Convert.toStr(userMap.get("phone"));
        usageInfoEntity.setUserCode(userCode);
        usageInfoEntity.setUserPhone(userPhone);
        usageInfoMapper.insertSelective(usageInfoEntity);

        payServce.refundPart(reserveInfoVO.getId(), reserveInfoVO.getReserveMoney());
    }
}
