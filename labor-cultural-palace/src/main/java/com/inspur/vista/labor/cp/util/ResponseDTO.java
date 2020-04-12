package com.inspur.vista.labor.cp.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: ResponseDTO
 * @Description: 统一返回
 * @Author: gengpeng
 * @CreateDate: 2020/3/5 16:09
 * @Version: 1.0
 */
@ApiModel
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回状态码", position = 1)
    private String code;

    @ApiModelProperty(value = "描述信息", position = 2)
    private String msg;

    @ApiModelProperty(position = 3)
    private T data;

    public ResponseDTO() {
    }

    public ResponseDTO(ErrorCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
