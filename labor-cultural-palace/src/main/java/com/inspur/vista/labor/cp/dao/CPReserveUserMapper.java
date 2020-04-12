package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPReserveUserEntity;
import com.inspur.vista.labor.cp.entity.CPReserveUserInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: CPReserveUserMapper
 * @Description: 预约用户
 * @authur: wangxueying01
 * @CreatDate: 2020/3/18 9:32
 */
public interface CPReserveUserMapper {

    /**
     * 通过手机号查询用户
     *
     * @param phone
     * @return
     */
    CPReserveUserInfoVO selectByPhone(String phone);

    /**
     * 批量新增预约用户
     *
     * @param dataList
     * @return
     */
    int batchInsert(@Param("dataList") List<CPReserveUserEntity> dataList);

    /**
     * 通过预约记录id查询预约用户
     *
     * @param reserveId
     * @return
     */
    List<String> selectByReserveId(String reserveId);

}