package com.inspur.vista.labor.cp.service;

/**
 * @Title: CPUserService
 * @Description: 文化宫用户相关服务
 * @Author: gengpeng
 * @CreateDate: 2020/3/23 11:06
 * @Version: 1.0
 */
public interface CPUserService {

    /**
     * 校验用户密码是否正确
     * @param username
     * @param password
     * @return
     */
    boolean verifyUser(String username,String password);
}
