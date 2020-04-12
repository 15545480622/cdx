package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: CommonServiceImpl
 * @Description: 公共服务类
 * @Author: gengpeng
 * @CreateDate: 2019/10/19 12:54
 * @Version: 1.0
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Value("${uac.url_getClientToken}")
    private String getClientTokenUrl;

    @Override
    public String getToken() {
        String accessToken = "";
        Map headerMap = new HashMap();
        headerMap.put("Authorization", CPConstants.BASIC_AUTH);
        Map paramMap = new HashMap();
        paramMap.put("grant_type", "client_credentials");
        Map resultMap = HttpUtil.post(getClientTokenUrl, paramMap, headerMap);
        if (resultMap != null && resultMap.get("access_token") != null) {
            accessToken = resultMap.get("access_token").toString();
        }
        return accessToken;
    }
}
