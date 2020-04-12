package com.inspur.vista.labor.cp.controller.activity;

import com.inspur.vista.labor.cp.config.ueditor.ActionEnter;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


/**
 * 用于处理关于ueditor插件相关的请求
 * @author Guoqing
 * @date 2017-11-29
 *
 */
@RestController
@CrossOrigin
@RequestMapping("api/cp/ueditor")
public class UeditorController {

	@Autowired
	private CpActivityBaseService cpActivityBaseService;

	@RequestMapping(value = "/exec")
	@ResponseBody
	public String exec(HttpServletRequest request) throws UnsupportedEncodingException{ 
		request.setCharacterEncoding("utf-8");


		String rootPath = request.getRealPath("/");
		return new ActionEnter(request, rootPath, cpActivityBaseService).exec();
	}
	
}
