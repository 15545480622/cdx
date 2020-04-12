package com.inspur.vista.labor.cp.service;

import java.util.Map;

/**
 * @Title: UacUserService
 * @Description: uac用户信息服务
 * @Author: gengpeng
 * @CreateDate: 2020/3/30 14:21
 * @Version: 1.0
 */
public interface UacUserService {

    /**
     * 从uac获取用户信息
     *
     * @param phone
     * @return
     */
    Map getUserInfoByPhone(String phone);

    /**
     * 从uac获取用户信息
     *
     * @param userCode
     * @return
     */
    Map getUserInfoByUserCode(String userCode);

    /**
     * 从uac获取会员信息
     *
     * @param memberId
     * @return
     */
    Map getMemberInfo(String memberId);
}
