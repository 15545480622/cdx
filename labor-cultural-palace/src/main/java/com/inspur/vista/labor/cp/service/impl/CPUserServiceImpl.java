package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.service.CPUserService;
import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: CPUserServiceImpl
 * @Description: 文化宫用户相关服务
 * @Author: gengpeng
 * @CreateDate: 2020/3/23 11:06
 * @Version: 1.0
 */
@Service
public class CPUserServiceImpl implements CPUserService {

    @Value("${bsp-gateway.url_verify_user}")
    private String verifyUserUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommonService commonService;

    /**
     * 校验用户密码是否正确
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyUser(String username, String password) {
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + commonService.getToken());
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        params.add("password", password);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(params,headers);
        ResponseDTO responseDTO = restTemplate.postForObject(verifyUserUrl, httpEntity, ResponseDTO.class);
        return null != responseDTO && ErrorCodeEnum.P_SUCCESS.getCode().equals(responseDTO.getCode());
    }
}
