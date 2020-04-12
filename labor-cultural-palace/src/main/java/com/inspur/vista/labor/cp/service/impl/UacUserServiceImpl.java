package com.inspur.vista.labor.cp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.service.UacUserService;
import com.inspur.vista.labor.cp.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: UacUserServiceImpl
 * @Description: uac用户信息服务类
 * @Author: gengpeng
 * @CreateDate: 2020/3/30 14:21
 * @Version: 1.0
 */
@Service
public class UacUserServiceImpl implements UacUserService {

    private static final Logger logger = LoggerFactory.getLogger(UacUserServiceImpl.class);

    @Autowired
    private CommonService commonService;

    @Value("${uac.url_get_user_with_phone}")
    private String getUserByPhone;

    @Value("${uac.url_get_user_with_user_code}")
    private String getUserByUserCode;

    @Value("${uac.url_get_member_with_member_id}")
    private String getMemberByMemberId;

    /**
     * 从uac获取用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public Map getUserInfoByPhone(String phone) {
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        String url = String.format(getUserByPhone, phone);
        logger.debug("通过手机号获取用户信息URL:{}", url);
        Map resultMap = HttpUtil.get(url, null, headerMap);
        logger.debug("通过手机号获取用户信息:{}", JSONObject.toJSONString(resultMap));
        Map userMap = null;
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("获取用户失败：{}", resultMap.get("msg"));
            } else {
                userMap = (Map) resultMap.get("data");
            }
        }
        return userMap;
    }

    @Override
    public Map getUserInfoByUserCode(String userCode) {
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        String url = String.format(getUserByUserCode, userCode);
        Map resultMap = HttpUtil.get(url, null, headerMap);
        Map userMap = null;
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("获取用户失败：{}", resultMap.get("msg"));
            } else {
                userMap = (Map) resultMap.get("data");
            }
        }
        return userMap;
    }

    /**
     * 从uac获取会员信息
     *
     * @param memberId
     * @return
     */
    @Override
    public Map getMemberInfo(String memberId) {
        Map<String, Object> memberMap = null;
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        String url = String.format(getMemberByMemberId, memberId);
        Map resultMap = HttpUtil.get(url, null, headerMap);
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("获取用户失败：{}", resultMap.get("msg"));
            } else {
                memberMap = (Map) resultMap.get("data");
            }
        }
        return memberMap;
    }
}
