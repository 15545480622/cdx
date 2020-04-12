package com.inspur.vista.labor.cp.service;

import com.alipay.api.AlipayApiException;
import com.inspur.vista.labor.cp.entity.CPReserveInfoVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: CPPayServce
 * @Description: 支付服务
 * @Author: gengpeng
 * @CreateDate: 2020/3/24 13:12
 * @Version: 1.0
 */
public interface CPPayServce {

    /**
     * 支付宝发起支付
     *
     * @param cpReserveInfoVO 预约记录
     */
    void payByAlipay(CPReserveInfoVO cpReserveInfoVO, HttpServletResponse response) throws IOException, AlipayApiException;

    /**
     * 全额退款
     *
     * @param reserveId
     */
    void refundAll(String reserveId) throws AlipayApiException;


    /**
     * 部分退款
     *
     * @param reserveId
     */
    void refundPart(String reserveId,Double money) throws AlipayApiException;
}
