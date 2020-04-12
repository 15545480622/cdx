package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.CPReserveUserMapper;
import com.inspur.vista.labor.cp.entity.CPReserveUserEntity;
import com.inspur.vista.labor.cp.entity.CPReserveUserInfoVO;
import com.inspur.vista.labor.cp.exception.CustomException;
import com.inspur.vista.labor.cp.service.CPReserveUserService;
import com.inspur.vista.labor.cp.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPReserveUserServiceImpl
 * @Description: 预约用户
 * @Author: gengpeng
 * @CreateDate: 2020/3/18 8:59
 * @Version: 1.0
 */
@Service
public class CPReserveUserServiceImpl implements CPReserveUserService {

    private static final Logger logger = LoggerFactory.getLogger(CPReserveUserServiceImpl.class);

    @Autowired
    private CPReserveUserMapper cpReserveUserMapper;

    @Autowired
    private CommonService commonService;


    /**
     * 通过手机号查询用户
     *
     * @param phone
     * @return
     */
    @Override
    public CPReserveUserInfoVO getCPReserveUserInfo(String phone) {
        CPReserveUserInfoVO cpReserveUserInfoVO = cpReserveUserMapper.selectByPhone(phone);
        return cpReserveUserInfoVO;
    }

    /**
     * 新增预约用户
     *
     * @param reserveUser
     * @param reserveId
     * @return
     */
    @Override
    public void saveCPReserveUser(Map<String, String> reserveUser, String reserveId) throws CustomException {

        List<CPReserveUserEntity> reserveUserList = new ArrayList<CPReserveUserEntity>();
        for (String phone : reserveUser.keySet()) {
            CPReserveUserEntity cpReserveUserEntity = new CPReserveUserEntity();
            cpReserveUserEntity.setReserveId(reserveId);
            cpReserveUserEntity.setUserCode(reserveUser.get(phone));
            cpReserveUserEntity.setUserPhone(phone);
            reserveUserList.add(cpReserveUserEntity);
        }
        cpReserveUserMapper.batchInsert(reserveUserList);
    }
}
