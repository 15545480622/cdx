package com.inspur.vista.labor.cp.exception;

import com.inspur.vista.labor.cp.util.ErrorCodeEnum;

/**
 * 自定义异常
 * 
 * @date 2018年10月18日
 */
public class RRException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private String code = ErrorCodeEnum.P_SYS_ERROR.getCode();
    
    public RRException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public RRException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public RRException(String msg, String code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public RRException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum.getMsg());
		this.msg = errorCodeEnum.getMsg();
		this.code = errorCodeEnum.getCode();
	}
	
	public RRException(String msg, String code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
