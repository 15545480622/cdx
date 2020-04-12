package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPStaffInfoEntity;
import com.inspur.vista.labor.cp.entity.CPStaffInfoListVO;
import com.inspur.vista.labor.cp.entity.CPStaffInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPStaffInfoMapper
 * @Description: 文化宫人员信息
 * @authur: wangxueying01
 * @CreatDate: 2019/12/6 8:36
 */
public interface CPStaffInfoMapper {
    /**
     * 获取人员信息
     *
     * @param id
     * @return
     */
    CPStaffInfoVO selectByPrimaryKey(String id);

    /**
     * 通过管理员账号获取人员信息
     *
     * @param managerUsername
     * @return
     */
    CPStaffInfoVO selectByManagerUsername(String managerUsername);

    /**
     * 通过身份证获取人员信息
     *
     * @param idcard
     * @return
     */
    CPStaffInfoVO selectByIdcard(String idcard);

    /**
     * 查询人员信息
     *
     * @param page
     * @param parameters
     * @return
     */
    List<CPStaffInfoListVO> listCPStaffInfo(Page page, Map<String, Object> parameters);

    /**
     * 查询场地下的专业人才信息
     *
     * @param parameters
     * @return
     */
    List<CPStaffInfoListVO> listTalentByCourtId(Page page, Map<String, Object> parameters);

    /**
     * 新增人员信息
     *
     * @param cpStaffInfoEntity
     */
    int insertSelective(CPStaffInfoEntity cpStaffInfoEntity);

    /**
     * 更新人员信息
     *
     * @param cpStaffInfoEntity
     */
    int updateByPrimaryKeySelective(CPStaffInfoEntity cpStaffInfoEntity);

    /**
     * 通过id删除人员信息
     *
     * @param paramMap modifier:修改人; id:人员信息id
     * @return
     */
    int deleteCPStaffInfoById(Map<String, Object> paramMap);

    /**
     * 通过ids批量删除人员信息
     *
     * @param paramMap modifier:修改人; id:人员信息id的字符串数组
     * @return
     */
    int batchDeleteCPStaffInfoById(Map<String, Object> paramMap);
}