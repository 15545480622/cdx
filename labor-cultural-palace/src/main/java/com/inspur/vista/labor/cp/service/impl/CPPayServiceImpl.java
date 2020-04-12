package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.inspur.vista.labor.cp.config.pay.AlipayProperties;
import com.inspur.vista.labor.cp.dao.*;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPPayServce;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: CPPayServiceImpl
 * @Description: 支付服务
 * @Author: gengpeng
 * @CreateDate: 2020/3/24 13:12
 * @Version: 1.0
 */
@Service
public class CPPayServiceImpl implements CPPayServce {

    private static final Logger logger = LoggerFactory.getLogger(CPPayServiceImpl.class);

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private CPCourtInfoMapper courtInfoMapper;

    @Autowired
    private CPAccountMapper accountMapper;

    @Autowired
    private CPReserveSceneMapper reserveSceneMapper;

    @Autowired
    private CPReserveInfoMapper reserveInfoMapper;

    @Autowired
    private CPOrderMapper orderMapper;

    @Autowired
    private CPInfoMapper infoMapper;

    /**
     * 支付宝发起支付
     *
     * @param cpReserveInfoVO 预约记录
     */
    @Override
    public void payByAlipay(CPReserveInfoVO cpReserveInfoVO, HttpServletResponse response) throws IOException, AlipayApiException {
        String cpId = courtInfoMapper.getCpIdByCourtId(cpReserveInfoVO.getCourtId());
        CPInfoVO cpInfoVO = infoMapper.selectByPrimaryKey(cpId);
        CPCourtInfoVO courtInfoVO = courtInfoMapper.selectByPrimaryKey(cpReserveInfoVO.getCourtId());
        CPAccountEntity accountEntity = accountMapper.selectEffectiveAccountByType(cpId, CPConstants.RECEIVE_ACCOUNT_ALI);
        String court = courtInfoVO.getName() + "(编号：" + courtInfoVO.getCourtCode() + ");";
        List<CPReserveSceneVO> reserveSceneList = reserveSceneMapper.listByReserveId(cpReserveInfoVO.getId());
        List<String> sceneList = new ArrayList<>();
        for (CPReserveSceneVO reserveScene : reserveSceneList) {
            sceneList.add(DateUtil.format(reserveScene.getBeginTime(), "yyyy-MM-dd HH:mm:ss") + "-" + DateUtil.format(reserveScene.getEndTime(), "HH:mm:ss"));
        }

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String outTradeNo = cpReserveInfoVO.getOutTradeNo();
        // 订单名称，必填
        String subject = cpInfoVO.getName() + "场地预约";
        // 付款金额，必填
        String totalAmount = String.valueOf(cpReserveInfoVO.getReserveMoney() + cpReserveInfoVO.getUseMoney());
        // 商品描述，可空
        String body = court + ArrayUtil.join(sceneList.toArray(), ",");
        // 超时时间 可空
        String timeoutExpress = "5m";
        // 销售产品码 必填
        String productCode = "QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(), alipayProperties.getAppId(), alipayProperties.getRsaPrivateKey(),
                alipayProperties.getFormat(), alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(), alipayProperties.getSigntype());
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setBody(body);
        model.setTimeoutExpress(timeoutExpress);
        model.setProductCode(productCode);
        alipayRequest.setBizModel(model);
        // 设置异步通知地址
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyUrl());
        // 设置同步地址
        alipayRequest.setReturnUrl(alipayProperties.getReturnUrl());
        alipayRequest.putOtherTextParam("app_auth_token", accountEntity.getAlipayAuthToken());

        // form表单生产
        String form = "";
        // 调用SDK生成表单
        logger.debug("支付参数：{}", JSONObject.toJSONString(alipayRequest));
        form = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 全额退款
     *
     * @param reserveId
     */
    @Override
    public void refundAll(String reserveId) throws AlipayApiException {
        CPReserveInfoVO reserveInfoVO = reserveInfoMapper.selectByPrimaryKey(reserveId);
        CPOrderVO orderVO = orderMapper.selectByPrimaryKey(reserveInfoVO.getOrderId());
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(), alipayProperties.getAppId(), alipayProperties.getRsaPrivateKey(),
                alipayProperties.getFormat(), alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(), alipayProperties.getSigntype());
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(reserveInfoVO.getOutTradeNo());
        model.setRefundAmount(String.valueOf(orderVO.getPayMoney()));
        request.setBizModel(model);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            CPOrderEntity orderEntity = new CPOrderEntity();
            orderEntity.setId(reserveInfoVO.getOrderId());
            orderEntity.setRefundTime(new Date());
            orderEntity.setRefundMoney(orderVO.getPayMoney());
            orderEntity.setState(CPConstants.PAY_STATE_REFUND_SUCCESS);
            orderMapper.updateByPrimaryKeySelective(orderEntity);
            System.out.println("调用成功");
        } else {
            CPOrderEntity orderEntity = new CPOrderEntity();
            orderEntity.setId(reserveInfoVO.getOrderId());
            orderEntity.setRefundTime(new Date());
            orderEntity.setRefundMoney(orderVO.getPayMoney());
            orderEntity.setState(CPConstants.PAY_STATE_REFUND_FAILURE);
            orderMapper.updateByPrimaryKeySelective(orderEntity);
            System.out.println("调用失败");
        }
    }

    /**
     * 部分退款
     *
     * @param reserveId
     */
    @Override
    public void refundPart(String reserveId, Double money) throws AlipayApiException {

        CPReserveInfoVO reserveInfoVO = reserveInfoMapper.selectByPrimaryKey(reserveId);
        CPOrderVO orderVO = orderMapper.selectByPrimaryKey(reserveInfoVO.getOrderId());
        if (null != orderVO) {
            AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(), alipayProperties.getAppId(), alipayProperties.getRsaPrivateKey(),
                    alipayProperties.getFormat(), alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(), alipayProperties.getSigntype());
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(reserveInfoVO.getOutTradeNo());
            model.setRefundAmount(String.valueOf(money));
            //随机数  不是全额退款，部分退款必须使用该参数，同一个订单，不同的out_request_no代表部分退款
            String outRequestNo = RandomStringUtils.randomAlphanumeric(13);
            model.setOutRequestNo(outRequestNo);
            request.setBizModel(model);
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                CPOrderEntity orderEntity = new CPOrderEntity();
                orderEntity.setId(reserveInfoVO.getOrderId());
                orderEntity.setRefundTime(new Date());
                orderEntity.setRefundMoney(orderVO.getPayMoney());
                orderEntity.setState(CPConstants.PAY_STATE_PART_REFUND_SUCCESS);
                orderMapper.updateByPrimaryKeySelective(orderEntity);
                System.out.println("调用成功");
            } else {
                CPOrderEntity orderEntity = new CPOrderEntity();
                orderEntity.setId(reserveInfoVO.getOrderId());
                orderEntity.setRefundTime(new Date());
                orderEntity.setRefundMoney(orderVO.getPayMoney());
                orderEntity.setState(CPConstants.PAY_STATE_REFUND_FAILURE);
                orderMapper.updateByPrimaryKeySelective(orderEntity);
                System.out.println("调用失败");
            }
        }
    }
}
