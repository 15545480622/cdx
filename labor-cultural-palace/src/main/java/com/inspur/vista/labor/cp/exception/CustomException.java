package com.inspur.vista.labor.cp.exception;

import com.inspur.vista.labor.cp.util.ErrorCodeEnum;

/**
 * @Title: CustomException
 * @Description: //TODO
 * @Author: gengpeng
 * @CreateDate: 2019/11/12 12:38
 * @Version: 1.0
 */
public class CustomException extends RuntimeException {
    private String code;
    private String message;

    public CustomException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
