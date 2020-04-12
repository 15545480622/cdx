package com.inspur.vista.labor.socialsecurity.api;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.QRCodeConstants;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Title: QRCodeApi
 * @Description: 二维码接口类
 * @Author: gengpeng
 * @CreateDate: 2019/9/11 19:48
 * @Version: 1.0
 */
@Controller
@RequestMapping("/qrcode")
public class QRCodeApi {

    private static final Logger logger = LoggerFactory.getLogger(QRCodeApi.class);

    @Autowired
    @Qualifier("cacheRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 二维码路由接口
     *
     * @param type     二维码类型
     * @param param    携带加密参数
     * @param response
     */
    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public void qrCodeRouter(@RequestParam("type") String type,
                             @RequestParam("param") String param,
                             HttpServletResponse response) {

        String key = QRCodeConstants.QR_CODE_REDIRECT_KEY + type;

        if (redisTemplate.hasKey(key)) {
            String urlPrefix = Objects.toString(redisTemplate.opsForValue().get(key));
            try {
                byte[] keyBytes = Arrays.copyOf(QRCodeConstants.QR_CODE_PARAM_ENCRYPT_KEY.getBytes(StandardCharsets.US_ASCII), 16);
                String plainParam = SecureUtil.aes(keyBytes).decryptStr(param);
                String url = urlPrefix + "?" + plainParam + "&uuid=" + IdUtil.fastSimpleUUID();
                logger.debug("重定向url:{}", url);
                response.sendRedirect(url);
            } catch (IOException e) {
                logger.error("重定向失败，请检查配置是否正确", e);
            }
        } else {
            logger.error("无法从redis中取出{}对应的URL", type);
        }
    }

    /**
     * 二维码路由接口地址修改
     *
     * @param key
     * @param value
     */
    @RequestMapping(value = "/redirect_url", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO saveRedirectUrl(@RequestParam(value = "key") String key,
                                       @RequestParam(value = "value") String value) {

        redisTemplate.opsForValue().set(QRCodeConstants.QR_CODE_REDIRECT_KEY + key, value);
        return WebUtils.createSuccessResponse("");
    }

    /**
     * 二维码路由接口地址删除
     *
     * @param key
     */
    @RequestMapping(value = "/redirect_url", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDTO deleteRedirectUrl(@RequestParam(value = "key") String key) {
        ResponseDTO responseDTO;
        boolean isDelete = redisTemplate.delete(QRCodeConstants.QR_CODE_REDIRECT_KEY + key);
        if (isDelete) {
            responseDTO = WebUtils.createSuccessResponse("");
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "删除失败");
        }
        return responseDTO;
    }
}
