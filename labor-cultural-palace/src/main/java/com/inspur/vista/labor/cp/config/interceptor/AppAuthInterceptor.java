package com.inspur.vista.labor.cp.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @Title: AppAuthInterceptor
 * @Description: app端授权拦截器
 * @Author: gengpeng
 * @CreateDate: 2020/3/30 18:25
 * @Version: 1.0
 */
public class AppAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AppAuthInterceptor.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 拦截器拦截请求跳转前台页面
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("authorization");
        if (StringUtils.isBlank(token)) {
            AppUserInfo appUserInfo = new AppUserInfo();
            request.setAttribute("userInfo", appUserInfo);
            return true;
        } else {
            try {
                if (redisTemplate.hasKey(CPConstants.TOKEN_KEY + token)) {
                    String userStr = Objects.toString(redisTemplate.opsForValue().get(CPConstants.TOKEN_KEY + token));
                    AppUserInfo appUserInfo = JSONObject.parseObject(userStr, AppUserInfo.class);
                    request.setAttribute("userInfo", appUserInfo);
                    return true;
                } else {
                    ResponseDTO responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_QLGH_TOKEN_CHECK_FAILED, "token验证失败");
                    sendJsonMessage(response, responseDTO);
                    logger.warn("token:" + token);
                }
            } catch (Exception e) {
                logger.error("生成用户信息失败", e);
                ResponseDTO responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_UNAUTHORIZED, "鉴权失败");
                sendJsonMessage(response, responseDTO);
            }
        }
        return false;
    }


    /**
     * 将某个对象转换成json格式并发送到客户端
     *
     * @param response
     * @param obj
     * @throws Exception
     */
    private void sendJsonMessage(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        OutputStream os = response.getOutputStream();
        os.write(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat).getBytes());
        os.close();
    }
}
