package com.inspur.vista.labor.cp.config.ueditor.upload;


import com.inspur.vista.labor.cp.config.ueditor.define.State;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;
	private CpActivityBaseService cpActivityBaseService = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf, CpActivityBaseService cpActivityBaseService) {
		this.request = request;
		this.conf = conf;
		this.cpActivityBaseService = cpActivityBaseService;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else {
			state = BinaryUploader.save(this.request, this.conf, cpActivityBaseService);
		}

		return state;
	}
}
