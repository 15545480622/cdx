package com.inspur.vista.labor.cp.controller.app;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.inspur.vista.labor.cp.config.pay.AlipayProperties;
import com.inspur.vista.labor.cp.entity.CPOrderEntity;
import com.inspur.vista.labor.cp.entity.CPReserveInfoVO;
import com.inspur.vista.labor.cp.service.CPOrderService;
import com.inspur.vista.labor.cp.service.CPPayServce;
import com.inspur.vista.labor.cp.service.CPReserveInfoService;
import com.inspur.vista.labor.cp.util.CPConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Title: CPPayAppController
 * @Description: 支付控制器
 * @Author: gengpeng
 * @CreateDate: 2020/3/24 13:16
 * @Version: 1.0
 */
@Api(value = "app端支付控制器", tags = {"app端支付接口"})
@RestController
@RequestMapping("/api/app/cp/pay")
public class CPPayAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPPayAppController.class);

    @Autowired
    private CPReserveInfoService reserveInfoService;

    @Autowired
    private CPPayServce payServce;

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private CPOrderService orderService;


    /**
     * 支付
     *
     * @return ResponseDTO
     */
    @ApiOperation(value = "支付", notes = "")
    @GetMapping(value = "")
    public void addReserveInfo(@ApiParam(name = "reserveId", value = "预约记录id", required = true) @RequestParam String reserveId,
                               @ApiParam(name = "payType", value = "支付方式", required = true) @RequestParam Integer payType,
                               HttpServletResponse response) {
        try {
            CPReserveInfoVO cpReserveInfoVO = reserveInfoService.getCPReserveInfo(reserveId);
            if (null != cpReserveInfoVO) {
                if(CPConstants.RESERVE_STATE_NOT_PAY.equals(cpReserveInfoVO.getState())){
                    if (CPConstants.RECEIVE_ACCOUNT_ALI == payType) {
                        payServce.payByAlipay(cpReserveInfoVO, response);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("支付失败", ex);
        }
    }

    /**
     * 服务器异步通知页面路径
     *
     * @return ResponseDTO
     */
    @ApiIgnore
    @ApiOperation(value = "服务器异步通知页面路径", notes = "")
    @PostMapping(value = "/notify")
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        logger.debug("调用异步通知");
        request.setCharacterEncoding("utf-8");//乱码解决，这段代码在出现乱码时使用
        PrintWriter out = response.getWriter();
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        logger.debug("支付异步返回值：{}", JSONObject.toJSONString(params));
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        logger.debug("商户订单号:{}", outTradeNo);
        //支付宝交易号
        String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        logger.debug("支付宝交易号:{}", tradeNo);
        // 付款金额
        String totalAmountt = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        logger.debug("付款金额:{}", totalAmountt);
        // 商户id
        String sellerId = new String(request.getParameter("seller_id").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        logger.debug("商户id:{}", sellerId);
        // 支付账号
        String buyerLogonId = new String(request.getParameter("buyer_logon_id").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        logger.debug("支付账号:{}", buyerLogonId);
        //交易状态
        String tradeStatus = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        logger.debug("交易状态:{}", tradeStatus);
        // 买家付款时间
        String payTime = new String(request.getParameter("gmt_create").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        logger.debug("买家付款时间:{}", payTime);

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verifyResult = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(), "UTF-8", "RSA2");

        if (verifyResult) {//验证成功
            logger.debug("异步通知验证成功");
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if ("TRADE_FINISHED".equals(tradeStatus)) {
                //普通版不支持支付完成后的退款操作，即用户充值完成后，该交易就算是完成了，这笔交易就不能再做任何操作了。
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if ("TRADE_SUCCESS".equals(tradeStatus)) {
                // 这个版本在用户充值完成后，卖家可以执行退款操作进行退款，即该交易还没有彻底完成，卖家还可以修改这笔交易。
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
                CPOrderEntity orderEntity = new CPOrderEntity();

                orderEntity.setTradeNo(tradeNo);
                orderEntity.setPayType(CPConstants.RECEIVE_ACCOUNT_ALI);
                orderEntity.setReceivingAccount(sellerId);
                orderEntity.setPayAccount(buyerLogonId);
                orderEntity.setPayTime(DateUtil.parse(payTime, "yyyy-MM-dd HH:mm:ss"));
                orderEntity.setPayMoney(Double.valueOf(totalAmountt));
                orderEntity.setState(CPConstants.PAY_STATE_SUCCESS);

                orderService.saveCPOrderInfo(orderEntity);
                reserveInfoService.saveOrder(outTradeNo, orderEntity.getId());
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            out.println("success");    //请不要修改或删除

            //////////////////////////////////////////////////////////////////////////////////////////
        } else {//验证失败
            logger.debug("异步通知验证失败");
            out.println("fail");

        }
    }

    /**
     * 页面跳转同步通知页面路径
     *
     * @return ResponseDTO
     */
    @ApiIgnore
    @ApiOperation(value = "页面跳转同步通知页面路径", notes = "")
    @GetMapping(value = "/return")
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("调用同步通知");
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号

            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            logger.debug("商户订单号:{}", outTradeNo);
            //支付宝交易号

            String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            logger.debug("支付宝交易号:{}", tradeNo);
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean verifyResult = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(), "UTF-8", "RSA2");

            if (verifyResult) {//验证成功
                //////////////////////////////////////////////////////////////////////////////////////////
                //请在这里加上商户的业务逻辑程序代码
                //该页面可做页面美工编辑
                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
                logger.debug("验证同步通知成功");
                response.sendRedirect("http://117.73.3.171:88/cp-h5/pages/order/success");
                //////////////////////////////////////////////////////////////////////////////////////////
            } else {
                logger.debug("验证同步通知失败");
                //该页面可做页面美工编辑
            }
        } catch (Exception ex) {
            logger.error("同步通知", ex);
        }


    }

}
