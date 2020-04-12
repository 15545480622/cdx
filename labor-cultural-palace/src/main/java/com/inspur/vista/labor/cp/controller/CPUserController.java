package com.inspur.vista.labor.cp.controller;

import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: CPLoginController
 * @Description: 登录及用户接口
 * @Author: gengpeng
 * @CreateDate: 2020/3/11 9:55
 * @Version: 1.0
 */
@Api(value = "登录用户控制类", tags = {"登录用户接口"})
@RestController
@RequestMapping("/api/user")
public class CPUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPUserController.class);

    @ApiOperation(value = "获取登录用户的信息", notes = "")
    @GetMapping(value = "/userinfo")
    public ResponseDTO getSession() {
        System.err.println("=====获取session====");
        logger.info("userAdminInfo{}", getUser());
        return WebUtils.createSuccessResponse(getUser());
    }
}
