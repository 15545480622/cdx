package com.inspur.vista.labor.cp.controller;

import com.inspur.vista.labor.cp.entity.OrganEntity;
import com.inspur.vista.labor.cp.entity.UserAdminInfo;
import com.inspur.vista.labor.cp.service.OrganService;
import com.inspur.vista.labor.uac.common.security.UacUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.*;

/**
 * Controller公共组件
 *
 * @author administrator
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrganService organService;

    /**
     * 构建管理员信息
     *
     * @param userId
     * @param userMap
     * @param permissionOpers
     * @return
     */
    public UserAdminInfo buildAdminInfo(String userId, Map<String, String> userMap, Set<String> permissionOpers) {
        UserAdminInfo userAdminInfo = new UserAdminInfo();
        userAdminInfo.setUserId(userId);
        userAdminInfo.setUserName(userMap != null && userMap.get("userName") != null ? userMap.get("userName").toString() : null);
        userAdminInfo.setLaborId(userMap != null && userMap.get("departmentId") != null ? userMap.get("departmentId").toString() : null);
        userAdminInfo.setOrganIds(userMap != null && userMap.get("organIds") != null ? Arrays.asList(userMap.get("organIds").toString().split(",")) : null);
        OrganEntity organEntity = organService.getOrganByOrganId(userAdminInfo.getLaborId());
        if (null != organEntity) {
            userAdminInfo.setLaborType(organEntity.getType());
        }
        userAdminInfo.setOperations(permissionOpers);

        return userAdminInfo;
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public UserAdminInfo getUser() {
//        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//        UserAdminInfo adminInfo = (UserAdminInfo) session.getAttribute("userAdminInfo");
//        if (adminInfo != null) {
//            logger.info("登录用户信息:" + adminInfo.getUserName() + ", laborId:" + adminInfo.getLaborId() + ", sessionId:" + session.getId());
//            IUserService userService = VistaUserServiceFactory.getUserService();
//            Map<String, String> userMap = userService.getUserDetail(adminInfo.getUserId());
//            if (adminInfo.getOrganIds() == null || adminInfo.getOrganIds().size() == 0) {
//                adminInfo.setOrganIds(userMap != null && userMap.get("organIds") != null ? Arrays.asList(userMap.get("organIds").toString().split(",")) : null);
//                session.setAttribute("userAdminInfo", adminInfo);
//            }
//            if (adminInfo.getLaborId() == null || adminInfo.getLaborCode() == null) {
//                adminInfo.setLaborId(userMap != null && userMap.get("departmentId") != null ? userMap.get("departmentId").toString() : null);
//                session.setAttribute("userAdminInfo", adminInfo);
//            }
//        }

        UserAdminInfo adminInfo = new UserAdminInfo();
        adminInfo.setUserId("WHG37010200");
        adminInfo.setUserName("济南市历下区工人文化宫管理员");
        adminInfo.setLaborId("O0000000000000000837");
        adminInfo.setLaborCode("WHG37010200");
        adminInfo.setLaborName("济南市历下区工人文化宫");
        List<String> organIds = new ArrayList<>();
        organIds.add("O0000000000000000837");
        adminInfo.setOrganIds(organIds);
        adminInfo.setOperations(new HashSet<>());
        adminInfo.setDataPermissionList(new ArrayList<>());
        adminInfo.setLaborType("29");
        return adminInfo;
    }

    /**
     * 通过用户的token来得到当前的用户信息
     *
     * @param oauth2Authentication
     * @return
     */
    protected UacUserDetails getMyUserDetails(OAuth2Authentication oauth2Authentication) {
        UacUserDetails uacUserDetails = null;
        if (oauth2Authentication != null) {
            try {
                Object me = oauth2Authentication.getUserAuthentication().getPrincipal();
                uacUserDetails = (UacUserDetails) me;
            } catch (Exception e) {
                logger.error("无法获取登录用户信息");
            }
        }
        return uacUserDetails;
    }
}
