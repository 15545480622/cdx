package com.inspur.vista.labor.cp.controller.app;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.service.UacUserService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.CPUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: CPLoginAppController
 * @Description: App端登录
 * @Author: gengpeng
 * @CreateDate: 2020/3/30 14:04
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/app/login")
public class CPLoginAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPLoginAppController.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UacUserService uacUserService;

    /**
     * 互联网前端页面地址
     */
    @Value("${cp.app-page}")
    private String cpAppPath;

    /**
     * 跳转到h5
     *
     * @param param 参数
     * @return ResponseDTO
     */
    @GetMapping(value = "/router")
    public ModelAndView router(@RequestParam String userCode, @RequestParam(required = false) String param) {

        AppUserInfo userInfo = new AppUserInfo();
        Map userMap = uacUserService.getUserInfoByUserCode(userCode);
        if (null != userMap) {
            userInfo.setPhone(Convert.toStr(userMap.get("phone")));
            if (StringUtils.isNotBlank(Convert.toStr(userMap.get("memberId")))) {
                Map memberMap = uacUserService.getMemberInfo(Convert.toStr(userMap.get("memberId")));
                if (null != memberMap) {
                    userInfo.setLaborId(Convert.toStr(memberMap.get("laborId")));
                    userInfo.setLaborName(Convert.toStr(memberMap.get("laborName")));
                    userInfo.setUserName(Convert.toStr(memberMap.get("fullName")));
                    userInfo.setDistrict(Convert.toStr(memberMap.get("districtCode")));
                    userInfo.setMaskingIdNo(Convert.toStr(memberMap.get("maskingIdNo")));
                    String memberState = Convert.toStr(memberMap.get("state"), "");
                    if ("1".equals(memberState) || "2".equals(memberState) || "3".equals(memberState)) {
                        userInfo.setAuth(true);
                    } else {
                        userInfo.setAuth(false);
                    }
                }
            } else {
                userInfo.setAuth(false);
            }
        }
        userInfo.setUserCode(userCode);
        String userJson = JSONObject.toJSONString(userInfo);
        String token = IdUtil.fastSimpleUUID();
        redisTemplate.opsForValue().set(CPConstants.TOKEN_KEY + token, userJson, 1, TimeUnit.DAYS);
        ModelAndView mv = new ModelAndView();

        StringBuilder url = new StringBuilder(cpAppPath + "?token=" + token);
        if (StrUtil.isNotBlank(param) && !"null".equals(param)) {
            logger.debug("param参数：{}", param);
            Map<String, Object> map = CPUtils.decryptQRCodeParam(param);
            for (String key : map.keySet()) {
                url.append("&").append(key).append("=").append(map.get(key));
            }
        }
        mv.setView(new RedirectView(url.toString()));
        return mv;
    }
}
