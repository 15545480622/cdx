package com.inspur.vista.labor.cp.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Title: AESUtil
 * @Description: AES加解密工具类
 * @Author: gengpeng
 * @CreateDate: 2019/5/23 17:50
 * @Version: 1.0
 */
public class AESUtil {

    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String KEY_AES = "AES";

//    private static final String KEY = "inspur_123456Aa?!";

    private static final String ID_NO_KEY = "c027ecf3726111ea";

    public static void main(String[] args) {
//        System.out.println(aesEncrypt("370602196312220739").toUpperCase());
        System.out.println(aesDecrypt("c027ecf3726111ea", "BD2F256B3EACC6F1250A5DF897CAB1702FFA89B7BC73447571BF812F96E0A8E0"));
    }

    /**
     * AES加密
     *
     * @param key     密钥
     * @param content 加密内容
     * @return
     */
    public static String aesEncrypt(String key, String content) {
        try {
            byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.US_ASCII), 16);
            SecretKey secKey = new SecretKeySpec(keyBytes, KEY_AES);
            Cipher cipher = Cipher.getInstance(KEY_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secKey);
            byte[] cleartext = content.getBytes(DEFAULT_CHARSET);
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(Hex.encodeHex(ciphertextBytes)).toUpperCase();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.warn("aesEncrypt error", e);
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param key     密钥
     * @param content 加密内容
     * @return
     */
    public static String aesDecrypt(String key, String content) {
        try {
            byte[] decodeContentBytes = Hex.decodeHex(content.toCharArray());
            byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.US_ASCII), 16);
            SecretKey secKey = new SecretKeySpec(keyBytes, KEY_AES);
            Cipher cipher = Cipher.getInstance(KEY_AES);
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            byte[] ciphertextBytes = cipher.doFinal(decodeContentBytes);
            return new String(ciphertextBytes, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | DecoderException e) {
            logger.warn("aesDecrypt error");
        }
        return content;
    }

}
