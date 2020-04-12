package com.inspur.vista.labor.cp.service;

import com.inspur.vista.labor.cp.entity.CPReserveUserInfoVO;
import com.inspur.vista.labor.cp.exception.CustomException;

import java.util.Map;

/**
 * @Title: CPReserveUserService
 * @Description: 预约用户
 * @Author: gengpeng
 * @CreateDate: 2020/3/18 8:57
 * @Version: 1.0
 */
public interface CPReserveUserService {

    /**
     * 通过手机号查询用户
     *
     * @param phone
     * @return
     */
    CPReserveUserInfoVO getCPReserveUserInfo(String phone);

    /**
     * 新增预约用户
     *
     * @param reserveUser
     * @param reserveId
     * @return
     */
    void saveCPReserveUser(Map<String,String>  reserveUser, String reserveId) throws CustomException;


}
