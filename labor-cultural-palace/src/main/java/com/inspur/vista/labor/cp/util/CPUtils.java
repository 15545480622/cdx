package com.inspur.vista.labor.cp.util;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: CPUtils
 * @Description: 文化宫工具类
 * @Author: gengpeng
 * @CreateDate: 2020/3/10 11:23
 * @Version: 1.0
 */
public class CPUtils {


    /**
     * 校验两个时间区间是否有交集
     *
     * @param leftStartDate
     * @param leftEndDate
     * @param rightStartDate
     * @param rightEndDate
     * @return
     */
    public static boolean isOverlap(Date leftStartDate, Date leftEndDate, Date rightStartDate, Date rightEndDate) {
        if ((leftStartDate.getTime() >= rightStartDate.getTime()
                && leftStartDate.getTime() < rightEndDate.getTime())
                || (rightStartDate.getTime() >= leftStartDate.getTime()
                && rightStartDate.getTime() < leftEndDate.getTime())) {
            return true;
        }
        return false;
    }

    /**
     * 加密生成二维码的参数
     *
     * @param plainParamMap 待加密的参数
     * @return 加密的字符串
     */
    public static String encryptQRCodeParam(Map<String, Object> plainParamMap) {
        // 生成加密参数
        byte[] keyBytes = Arrays.copyOf(QRCodeConstants.QR_CODE_PARAM_ENCRYPT_KEY.getBytes(StandardCharsets.US_ASCII), 16);
        StringBuilder sb = new StringBuilder();
        for (String key : plainParamMap.keySet()) {
            sb.append(key).append("=").append(plainParamMap.get(key));
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return SecureUtil.aes(keyBytes).encryptBase64(s);
    }

    /**
     * 解密生成二维码的参数
     *
     * @param secParamStr 加密的字符串
     * @return 未加密的参数
     */
    public static Map<String, Object> decryptQRCodeParam(String secParamStr) {
        Map<String, Object> plainParamMap = new LinkedHashMap<>();
        byte[] keyBytes = Arrays.copyOf(QRCodeConstants.QR_CODE_PARAM_ENCRYPT_KEY.getBytes(StandardCharsets.US_ASCII), 16);
        String plainParamStr = SecureUtil.aes(keyBytes).decryptStr(secParamStr);
        String[] params = plainParamStr.split("&");
        for (String param : params) {
            String[] p = param.split("=");
            if (p.length == 2) {
                plainParamMap.put(p[0], p[1]);
            }
        }
        return plainParamMap;
    }
}
