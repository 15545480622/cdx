package com.inspur.vista.labor.cp.util;

import java.security.MessageDigest;

/**
 * MD5工具类 <br>
 *
 * @author zqx
 * @version 1.0
 * @createDate 2019/09/06 14:13
 */

public class MD5EncodingUtil {
    /**
     * 加密算法MD5
     *
     * @param text 明文
     * @return String 密文
     */
    public final static String encoding(String text) {
        char hexDigits[] =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                        'e', 'f'};
        String encodingStr = null;
        try {
            byte[] strTemp = text.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            encodingStr = new String(str);
        } catch (Exception e) {
        }
        return encodingStr;
    }

    public static void main(String[] areg) {
        String admin = MD5EncodingUtil.encoding("admin");
        System.out.println("admin............" + admin);
    }


}
