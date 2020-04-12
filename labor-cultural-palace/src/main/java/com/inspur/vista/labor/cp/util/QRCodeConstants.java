package com.inspur.vista.labor.cp.util;

/**
 * @ClassName: QRCodeConstants
 * @Description: 二维码常量类
 * @authur: wangxueying01
 * @CreatDate: 2020/3/7 14:17
 */
public class QRCodeConstants {

    /**
     * 二维码重定向地址键值前缀
     */
    public static String QR_CODE_REDIRECT_KEY = "social:security:qrcode:redirect:";

    /**
     * 生成二维码的参数的加密密钥
     */
    public static final String QR_CODE_PARAM_ENCRYPT_KEY = "labor_cp_Aa?";

    /**
     * 二维码类型-预约场地生成的二维码
     */
    public static final Integer QR_CODE_TYPE_RESERVE = 1;

    /**
     * 二维码类型-报名活动生成的二维码
     */
    public static final Integer QR_CODE_TYPE_SIGN_UP_ACTIVITY = 2;

    /**
     * 二维码类型-领券活动生成的二维码
     */
    public static final Integer QR_CODE_TYPE_COUPON_ACTIVITY = 3;

}

