package com.inspur.vista.labor.cp.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayOpenAuthTokenAppModel;
import com.alipay.api.domain.AlipayOpenAuthTokenAppQueryModel;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.config.pay.AlipayProperties;
import com.inspur.vista.labor.cp.entity.CPAccountAdd;
import com.inspur.vista.labor.cp.entity.CPAccountEntity;
import com.inspur.vista.labor.cp.entity.CPAccountVO;
import com.inspur.vista.labor.cp.entity.CPApplyAccountEntity;
import com.inspur.vista.labor.cp.service.CPAccountService;
import com.inspur.vista.labor.cp.service.CPApplyAccountService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPAccountController
 * @Description: 文化宫收款账号管理
 * @Author: gengpeng
 * @CreateDate: 2019/12/6 16:47
 * @Version: 1.0
 */
@Api(value = "文化宫收款账号控制类", tags = {"文化宫收款账号接口"})
@RequestMapping("/api/cp/account")
@RestController
public class CPAccountController {

    private static final Logger logger = LoggerFactory.getLogger(CPInfoController.class);

    @Autowired
    private CPApplyAccountService applyAccountService;

    @Autowired
    private CPAccountService cpAccountService;

    @Autowired
    private AlipayProperties alipayProperties;


    /**
     * 加载账号列表
     *
     * @param parameters
     * @return
     */
    @ApiOperation(value = "加载账号列表", notes = "")
    @PostMapping("/list")
    public ResponseDTO listAccount(@ApiParam(value = "Map类型，参数包括：cpId、page、pageSize") @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        Page<CPAccountVO> accountVOPage = cpAccountService.listAccount(parameters);
        responseDTO = WebUtils.createSuccessResponse(accountVOPage);
        return responseDTO;
    }

    /**
     * 申请启用新账号
     *
     * @param cpId
     * @param account
     * @param accountType
     * @param activeTime
     * @return
     */
    @ApiOperation(value = "申请启用新账号", notes = "")
    @GetMapping("/authorization")
    public ModelAndView authorization(@RequestParam String cpId, @RequestParam String account, @RequestParam int accountType,
                                      @RequestParam String activeTime) {
        ModelAndView mv = new ModelAndView();
        CPAccountAdd accountAdd = new CPAccountAdd();
        accountAdd.setAccount(account);
        accountAdd.setAccountType(accountType);
        accountAdd.setActiveTime(DateUtil.parseDate(activeTime));
        accountAdd.setCpId(cpId);
        String state = JSONObject.toJSONString(accountAdd);
        byte[] keyBytes = Arrays.copyOf("inspur".getBytes(StandardCharsets.US_ASCII), 16);
        String stateAes = SecureUtil.aes(keyBytes).encryptHex(state);
        String redirectUri = alipayProperties.getRedirectUrl() + "&state=" + stateAes;

        mv.setView(new RedirectView(alipayProperties.getAuthUrl() + "app_id=" + alipayProperties.getAppId() + "&redirect_uri=" + URLUtil.encode(redirectUri)));
        return mv;
    }

    /**
     * 保存账号
     *
     * @param
     * @return
     */
    @ApiIgnore
    @GetMapping("/save")
    public ResponseDTO save(@RequestParam(value = "app_auth_code") String appAuthCode, @RequestParam(value = "state") String state) {
        ResponseDTO responseDTO;
        try {
            byte[] keyBytes = Arrays.copyOf("inspur".getBytes(StandardCharsets.US_ASCII), 16);
            state = SecureUtil.aes(keyBytes).decryptStr(state);
            CPAccountAdd accountAdd = JSONObject.parseObject(URLUtil.decode(state), CPAccountAdd.class);
            CPAccountEntity cpAccountEntity = new CPAccountEntity();
            cpAccountEntity.setCpId(accountAdd.getCpId());
            cpAccountEntity.setType(accountAdd.getAccountType());
            cpAccountEntity.setAccount(accountAdd.getAccount());
            cpAccountEntity.setActiveTime(accountAdd.getActiveTime());
            if (CPConstants.RECEIVE_ACCOUNT_ALI == accountAdd.getAccountType()) {
                String authToken = getAlipayAuthToken(appAuthCode);
                cpAccountEntity.setAlipayAuthToken(authToken);
                String sellerId = getSellerId(authToken);
                cpAccountEntity.setSellerId(sellerId);
            }
            cpAccountService.save(cpAccountEntity);
            responseDTO = WebUtils.createSuccessResponse("");
        } catch (AlipayApiException ex) {
            logger.error("支付宝获取token失败");
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "保存失败");
        }

        return responseDTO;
    }

    /**
     * 获取授权token
     *
     * @param authCode
     * @return
     * @throws AlipayApiException
     */
    private String getAlipayAuthToken(String authCode) throws AlipayApiException {
        String authToken = "";
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(), alipayProperties.getAppId(), alipayProperties.getRsaPrivateKey(),
                alipayProperties.getFormat(), alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(), alipayProperties.getSigntype());
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        AlipayOpenAuthTokenAppModel model = new AlipayOpenAuthTokenAppModel();
        model.setCode(authCode);
        model.setGrantType("authorization_code");
        request.setBizModel(model);
        AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            authToken = response.getAppAuthToken();
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

        return authToken;
    }

    /**
     * 获取商户信息
     *
     * @param authToken
     * @return
     * @throws AlipayApiException
     */
    private String getSellerId(String authToken) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(), alipayProperties.getAppId(), alipayProperties.getRsaPrivateKey(),
                alipayProperties.getFormat(), alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(), alipayProperties.getSigntype());
        AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
        AlipayOpenAuthTokenAppQueryModel model = new AlipayOpenAuthTokenAppQueryModel();
        model.setAppAuthToken(authToken);
        request.setBizModel(model);
        AlipayOpenAuthTokenAppQueryResponse response = alipayClient.execute(request);
        return response.getUserId();
    }

    /**
     * 申请新增收款账号
     *
     * @param applyAccountEntity 新账号信息
     * @return ResponseDTO
     */
    @ApiIgnore
    @PostMapping(value = "/apply")
    public ResponseDTO saveAccount(@RequestBody CPApplyAccountEntity applyAccountEntity) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");

            if (applyAccountEntity.getAccountType() != 1
                    && applyAccountEntity.getAccountType() != 2
                    && applyAccountEntity.getAccountType() != 3) {
                msg.set(msg.get() + "账号类型不合法" + ",");
            }
            if (StringUtils.isBlank(applyAccountEntity.getNewAccount())) {
                msg.set(msg.get() + "新账号不能为空" + ",");
            }
            if (applyAccountEntity.getActiveTime().before(new Date())) {
                msg.set(msg.get() + "新账号启用时间应晚于今天" + ",");
            }
            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                applyAccountService.createApply(applyAccountEntity);
                responseDTO = WebUtils.createSuccessResponse("");
            }
        } catch (Exception e) {
            logger.error("申请新增收款账号失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

}
