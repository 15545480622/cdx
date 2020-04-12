package com.inspur.vista.labor.cp.controller;

import com.inspur.vista.bsp.client.VistaUserServiceFactory;
import com.inspur.vista.labor.cp.entity.UserAdminInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loushang.bsp.api.role.RoleServiceFactory;
import org.loushang.bsp.api.role.service.IRoleService;
import org.loushang.bsp.api.user.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "agent-sp")
public class AgentSpController extends BaseController {


    Log logger = LogFactory.getLog(AgentSpController.class);

    @RequestMapping("/login")
    public void doAgent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.warn("用户单点登陆");
        String userId = (String) request.getAttribute("saml.userid");
        logger.warn("单点登录用户userId:" + userId);
        // 用户信息
        IUserService userService = VistaUserServiceFactory.getUserService();
        Map<String, String> userMap = userService.getUserDetail(userId);
        // 用户权限信息
        IRoleService roleService = RoleServiceFactory.getRoleService();
        Set<String> permissionOpers = roleService.getPermitOperationsList(userId);
        // 构建管理员信息
        UserAdminInfo userAdminInfo = buildAdminInfo(userId, userMap, permissionOpers);

        HttpSession session = request.getSession(false);
        session.setAttribute("userAdminInfo", userAdminInfo);

        if (logger.isDebugEnabled()) {
            logger.debug(userId + "[" + userAdminInfo.getUserName() + "] has already stored in the session.");
        }

        String path = (String) request.getAttribute("RelayState");
        response.sendRedirect(path);
    }

    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.err.println("=====回掉成功====");
        HttpSession session = request.getSession(false);
        UserAdminInfo userAdminInfo = (UserAdminInfo) session.getAttribute("userAdminInfo");
        logger.info("userAdminInfo{}" + userAdminInfo);
    }
}
