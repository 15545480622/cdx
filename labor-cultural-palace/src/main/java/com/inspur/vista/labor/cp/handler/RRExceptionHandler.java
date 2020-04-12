package com.inspur.vista.labor.cp.handler;

import com.inspur.vista.labor.cp.exception.RRException;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理器
 */
@RestControllerAdvice
public class RRExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResponseDTO handleRRException(RRException e){
		return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SYS_ERROR, e.getMessage(),null);
	}

}
