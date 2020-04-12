package com.inspur.vista.labor.cp.service;


import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPReserveAdd;
import com.inspur.vista.labor.cp.entity.CPReserveInfoListVO;
import com.inspur.vista.labor.cp.entity.CPReserveInfoVO;
import com.inspur.vista.labor.cp.util.ResponseDTO;

import java.util.Map;


/**
 * @Title: CPReserveInfoService
 * @Description: 场地预约记录服务类
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
public interface CPReserveInfoService {

    /**
     * 获取场地预约记录
     *
     * @param id
     * @return
     */
    CPReserveInfoVO getCPReserveInfo(String id);

    /**
     * 查询场地预约记录
     *
     * @param parameters
     * @return
     */
    Page<CPReserveInfoListVO> listCPReserveInfo(Map<String, Object> parameters);

    /**
     * 通过场次的起止时间查询预约记录
     *
     * @param parameters
     * @return
     */
    Page<CPReserveInfoListVO> listCPReserveInfoByScene(Map<String, Object> parameters);

    /**
     * 预定场次
     *
     * @param reserveAdd
     * @param reserveUser 预约的用户信息
     * @return
     */
    ResponseDTO addReserveInfo(CPReserveAdd reserveAdd, Map<String, String> reserveUser) throws RuntimeException;

    /**
     * 完成支付，关联订单
     *
     * @param outTradeNo
     * @param orderId
     */
    void saveOrder(String outTradeNo, String orderId);

    /**
     * 取消预约
     *
     * @param cancelReasonType 取消原因
     * @param cancelReasonExt  其他取消原因
     * @param id               预约记录id
     * @param modifier         修改人
     * @param allowCancel      是否允许强制取消
     * @return
     */
    ResponseDTO cancelReserve(Integer cancelReasonType, String cancelReasonExt, String id, String modifier, boolean allowCancel) throws AlipayApiException;

    /**
     * 关闭预约订单
     *
     * @param id
     */
    void closeReserve(String id);

    /**
     * 确认预约订单
     *
     * @param reserveInfoVO 预约信息
     * @param userCode      用户编码
     */
    void confirmReserve(CPReserveInfoVO reserveInfoVO, String userCode) throws AlipayApiException;
}



