package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentAdd;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentListVO;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPProfessionalTalentService
 * @Description: 专业人才库服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
public interface CPProfessionalTalentService {

    /**
     * 获取专业人才信息
     *
     * @param id
     * @return
     */
    CPProfessionalTalentVO getCPProfessionalTalent(String id);

    /**
     * 查询专业人才信息
     *
     * @param parameters
     * @return
     */
    Page<CPProfessionalTalentListVO> listCPProfessionalTalent(Map<String, Object> parameters);

    /**
     * 保存专业人才信息
     *
     * @param professionalTalentAdd
     * @return
     */
    void saveCPProfessionalTalent(CPProfessionalTalentAdd professionalTalentAdd) throws Exception;

    /**
     * 通过id删除专业人才信息
     *
     * @param ids
     * @return
     */
    int removeCPProfessionalTalentById(String[] ids);

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param cpId
     * @return
     */
    List<String> uploadFile(MultipartFile[] file, String type, String cpId) throws Exception;

    /**
     * 查询场地下的专业人才信息
     *
     * @param parameters
     * @return
     */
    Page<CPProfessionalTalentListVO> listCPProfessionalTalentByCourtId(Map<String, Object> parameters);
}



