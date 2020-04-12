package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentEntity;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentListVO;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPProfessionalTalentMapper
 * @Description: 专业人才库
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
public interface CPProfessionalTalentMapper {

    /**
     * 获取专业人才信息
     *
     * @param id
     * @return
     */
    CPProfessionalTalentVO selectByPrimaryKey(String id);

    /**
     * 查询专业人才信息
     *
     * @param parameters
     * @return
     */
    List<CPProfessionalTalentListVO> listCPProfessionalTalent(Page page, Map<String, Object> parameters);

    /**
     * 新增专业人才信息
     *
     * @param cpProfessionalTalent
     * @return
     */
    int insertSelective(CPProfessionalTalentEntity cpProfessionalTalent);

    /**
     * 更新专业人才信息
     *
     * @param cpProfessionalTalent
     * @return
     */
    int updateByPrimaryKeySelective(CPProfessionalTalentEntity cpProfessionalTalent);

    /**
     * 通过id删除专业人才信息
     *
     * @param paramMap modifier:修改人; id:专业人才id
     * @return
     */
    int deleteCPProfessionalTalentById(Map paramMap);

    /**
     * 通过ids批量删除专业人才信息
     *
     * @param paramMap modifier:修改人; id:专业人才id的字符串数组
     * @return
     */
    int batchDeleteCPProfessionalTalentById(Map paramMap);

    /**
     * 查询场地下的专业人才信息
     *
     * @param parameters
     * @return
     */
    List<CPProfessionalTalentListVO> listCPProfessionalTalentByCourtId(Page page, Map<String, Object> parameters);

}



