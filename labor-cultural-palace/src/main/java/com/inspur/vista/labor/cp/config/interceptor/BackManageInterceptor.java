package com.inspur.vista.labor.cp.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 拦截前端页面的请求，调转到对应的页面
 *
 * @author zhangqixia
 * @Date 2019/06/13
 */
public class BackManageInterceptor extends HandlerInterceptorAdapter {

    public Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 互联网前端页面地址
     */
    @Value("${cp.manage-page}")
    private String cpFrontPath;


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
        StringBuffer requestURL = request.getRequestURL();
        logger.info("拦截器拦截前台请求:{}", requestURL.toString());

        String prePath = requestURL.substring(requestURL.lastIndexOf("/"), requestURL.length());
        String url = "";
        // 跳转
        if ("/home".contains(prePath)) {
            logger.debug("跳转到" + cpFrontPath);
            response.sendRedirect(cpFrontPath);
            logger.debug("session失效时间{}", request.getSession().getMaxInactiveInterval());
            Object userAdminInfo = request.getSession().getAttribute("userAdminInfo");
            logger.debug("登录用户信息1:{}", JSONObject.toJSONString(userAdminInfo));
            return false;
        } else {
            logger.debug("session失效时间{}", request.getSession().getMaxInactiveInterval());
            Object userAdminInfo = request.getSession().getAttribute("userAdminInfo");
            logger.debug("登录用户信息2:{}", JSONObject.toJSONString(userAdminInfo));
            return true;
//            if (null == userAdminInfo) {
//                if (requestURL.indexOf("api/") > -1) {
//                    return true;
//                }
//                ResponseDTO responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_INVALID, "会话失效，请重新登录");
//                sendJsonMessage(response, responseDTO);
//                return false;
//            } else {
//                return true;
//            }
        }
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
