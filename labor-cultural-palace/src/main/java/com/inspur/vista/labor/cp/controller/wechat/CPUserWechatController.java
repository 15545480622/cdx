package com.inspur.vista.labor.cp.controller.wechat;

import com.inspur.vista.labor.cp.entity.CPInfoVO;
import com.inspur.vista.labor.cp.entity.CPStaffInfoVO;
import com.inspur.vista.labor.cp.service.CPInfoService;
import com.inspur.vista.labor.cp.service.CPStaffInfoService;
import com.inspur.vista.labor.cp.service.CPUserService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: CPUserAppController
 * @Description: app端的用户接口
 * @Author: gengpeng
 * @CreateDate: 2020/3/23 11:02
 * @Version: 1.0
 */
@Api(value = "微信小程序的用户接口", tags = {"微信小程序用户接口"})
@RestController
@RequestMapping("/api/wechat/cp/user")
public class CPUserWechatController {

    private static final Logger logger = LoggerFactory.getLogger(CPUserWechatController.class);

    @Autowired
    private CPUserService userService;

    @Autowired
    private CPStaffInfoService staffInfoService;

    @Autowired
    private CPInfoService cpInfoService;

    /**
     * 微信小程序登录
     *
     * @param username 用户名
     * @param password 密码
     * @return ResponseDTO
     */
    @ApiOperation(value = "登录", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/login")
    public ResponseDTO listCPInfo(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
                                  @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password) {
        ResponseDTO responseDTO;
        // 用户名存大写
        CPStaffInfoVO cpStaffInfoVO = staffInfoService.getCPStaffInfoByManagerUsername(username.toUpperCase());
        if (null == cpStaffInfoVO) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_UAC_USER_INVALID, "用户无效");
        } else {
            boolean checkResult = userService.verifyUser(username, password);
            if (checkResult) {
                CPInfoVO cpInfoVO = cpInfoService.getCPInfo(cpStaffInfoVO.getCpId());
                Map<String, String> cpInfo = new LinkedHashMap<>();
                cpInfo.put("userId", cpStaffInfoVO.getId());
                cpInfo.put("nickname", cpStaffInfoVO.getManagerNickname());
                cpInfo.put("cpName", cpInfoVO.getName());
                cpInfo.put("cpId", cpStaffInfoVO.getCpId());
                responseDTO = WebUtils.createSuccessResponse(cpInfo);
            }else{
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_NAME_OR_PWD_ERROR, "用户名或密码错误");
            }
        }
        return responseDTO;
    }
}
