package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoEntity;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoListVO;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPSecurityInfoService
 * @Description: 安全管理服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/4/7 19:54
 */
public interface CPSecurityInfoService {

    /**
     * 获取安全管理
     *
     * @param id
     * @return
     */
    CPSecurityInfoVO getCPSecurityInfo(String id);

    /**
     * 查询安全管理
     *
     * @param paramMap
     * @return
     */
    Page<CPSecurityInfoListVO> listCPSecurityInfo(Map<String, Object> paramMap);

    /**
     * 保存安全管理
     *
     * @param cpSecurityInfoEntity
     * @return
     */
    CPSecurityInfoEntity saveCPSecurityInfo(CPSecurityInfoEntity cpSecurityInfoEntity);

    /**
     * 删除安全管理
     *
     * @param paramMap
     * @return
     */
    int removeCPSecurityInfo(Map<String, Object> paramMap);

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param securityId
     * @return
     */
    List<String> uploadFile(MultipartFile[] file, String type, String securityId) throws Exception;
}