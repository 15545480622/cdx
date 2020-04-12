package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPProfessionalTalentMapper;
import com.inspur.vista.labor.cp.dao.PubFilesMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPProfessionalTalentService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPProfessionalTalentServiceImpl
 * @Description: 专业人才库服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
@Service
public class CPProfessionalTalentServiceImpl implements CPProfessionalTalentService {

    private static final Logger logger = LoggerFactory.getLogger(CPProfessionalTalentServiceImpl.class);

    @Autowired
    private CPProfessionalTalentMapper cpProfessionalTalentMapper;

    @Autowired
    private PubFilesService filesService;

    @Autowired
    private PubFilesMapper filesMapper;

    /**
     * 获取专业人才信息
     *
     * @param id 专业人才id
     * @return
     */
    @Override
    public CPProfessionalTalentVO getCPProfessionalTalent(String id) {
        return cpProfessionalTalentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询专业人才信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPProfessionalTalentListVO> listCPProfessionalTalent(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPProfessionalTalentListVO> p = new Page<>(page, pageSize);
        List<CPProfessionalTalentListVO> cpProfessionalTalentList = cpProfessionalTalentMapper.listCPProfessionalTalent(p, parameters);
        for (CPProfessionalTalentListVO talentVO : cpProfessionalTalentList) {
            Map<String, Object> fileParam = new HashMap<>();
            fileParam.put("bsnId", talentVO.getId());
            List<PubFilesVO> filesVOList = filesService.listPubFiles(fileParam);
            List<String> downloadUrlList = new ArrayList<>();
            for (PubFilesVO pubFilesVO : filesVOList) {
                if (CPConstants.FILE_TYPE_STAFF_PHOTO.equals(pubFilesVO.getType())) {
                    talentVO.setHeadAvatar(pubFilesVO.getDownloadUrl());
                } else {
                    downloadUrlList.add(pubFilesVO.getDownloadUrl());
                }
            }
            talentVO.setOtherFile(downloadUrlList);
        }
        p.setRecords(cpProfessionalTalentList);
        return p;
    }

    /**
     * 保存专业人才信息
     *
     * @param professionalTalentAdd
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCPProfessionalTalent(CPProfessionalTalentAdd professionalTalentAdd) throws Exception {

        if (StringUtils.isBlank(professionalTalentAdd.getId())) {
            // 保存专业人才信息
            CPProfessionalTalentEntity professionalTalentEntity = new CPProfessionalTalentEntity();
            professionalTalentEntity.setId(IdUtil.fastSimpleUUID());
            professionalTalentEntity.setCpId(professionalTalentAdd.getCpId());
            professionalTalentEntity.setName(professionalTalentAdd.getName());
            professionalTalentEntity.setIdcard(professionalTalentAdd.getIdcard());
            professionalTalentEntity.setSex(professionalTalentAdd.getSex());
            professionalTalentEntity.setNation(professionalTalentAdd.getNation());
            professionalTalentEntity.setEducation(professionalTalentAdd.getEducation());
            professionalTalentEntity.setPoliticalOutlook(professionalTalentAdd.getPoliticalOutlook());
            professionalTalentEntity.setPhone(professionalTalentAdd.getPhone());
            professionalTalentEntity.setEmploymentType(professionalTalentAdd.getEmploymentType());
            professionalTalentEntity.setType(professionalTalentAdd.getType());
            professionalTalentEntity.setIntroduction(professionalTalentAdd.getIntroduction());
            professionalTalentEntity.setIsStaffing(professionalTalentAdd.getIsStaffing());
            cpProfessionalTalentMapper.insertSelective(professionalTalentEntity);
            // 附件添加专业人才id
            String[] fileIdArray = professionalTalentAdd.getFileIds().split(",");
            filesMapper.batchUpdate(professionalTalentEntity.getId(), fileIdArray);
        } else {
            CPProfessionalTalentEntity professionalTalentEntity = new CPProfessionalTalentEntity();
            professionalTalentEntity.setId(professionalTalentAdd.getId());
            professionalTalentEntity.setCpId(professionalTalentAdd.getCpId());
            professionalTalentEntity.setName(professionalTalentAdd.getName());
            professionalTalentEntity.setIdcard(professionalTalentAdd.getIdcard());
            professionalTalentEntity.setSex(professionalTalentAdd.getSex());
            professionalTalentEntity.setNation(professionalTalentAdd.getNation());
            professionalTalentEntity.setEducation(professionalTalentAdd.getEducation());
            professionalTalentEntity.setPoliticalOutlook(professionalTalentAdd.getPoliticalOutlook());
            professionalTalentEntity.setPhone(professionalTalentAdd.getPhone());
            professionalTalentEntity.setEmploymentType(professionalTalentAdd.getEmploymentType());
            professionalTalentEntity.setType(professionalTalentAdd.getType());
            professionalTalentEntity.setIntroduction(professionalTalentAdd.getIntroduction());
            professionalTalentEntity.setIsStaffing(professionalTalentAdd.getIsStaffing());
            cpProfessionalTalentMapper.updateByPrimaryKeySelective(professionalTalentEntity);
            // 附件添加专业人才id
            String[] fileIdArray = professionalTalentAdd.getFileIds().split(",");
            filesMapper.batchUpdate(professionalTalentEntity.getId(), fileIdArray);
        }

    }

    /**
     * 通过id删除专业人才信息
     *
     * @param ids 专业人才id的字符串数组
     * @return
     */
    @Override
    public int removeCPProfessionalTalentById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpProfessionalTalentMapper.deleteCPProfessionalTalentById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpProfessionalTalentMapper.batchDeleteCPProfessionalTalentById(paramMap);
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param cpId
     * @return
     */
    @Override
    public List<String> uploadFile(MultipartFile[] file, String type, String cpId) throws Exception {
        FileBsnInfo bsnInfo = createFileBsnInfo(type, cpId);
        List<UploadFileResponse> uploadFileResponseList = filesService.updateFile(bsnInfo, file);
        // 2.新增附件信息
        List<PubFilesEntity> fileList = new ArrayList<>();
        List<String> fileIdList = new ArrayList<>();
        for (UploadFileResponse fileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setType(type);
            filesEntity.setFileName(fileResponse.getFileName());
            filesEntity.setFileExt(fileResponse.getFileExt());
            filesEntity.setFileSize(fileResponse.getFileSize());
            filesEntity.setFilePath(fileResponse.getFilePath());
            fileList.add(filesEntity);
            fileIdList.add(filesEntity.getId());
        }
        filesMapper.batchInsert(fileList);
        return fileIdList;
    }

    /**
     * 创建文件上传业务信息
     *
     * @param type
     * @param staffId
     * @return
     */
    private FileBsnInfo createFileBsnInfo(String type, String staffId) {
        FileBsnInfo bsnInfo = new FileBsnInfo();
        // 专业人才照片
        if (CPConstants.FILE_TYPE_STAFF_PHOTO.equals(type)) {
            bsnInfo.setBsnId(staffId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_STAFF_PHOTO);
            bsnInfo.setBsnDesc("文化宫专业人才的照片");
        } else if (CPConstants.FILE_TYPE_STAFF_CERTIFICATE.equals(type)) {
            bsnInfo.setBsnId(staffId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_STAFF_CERTIFICATE);
            bsnInfo.setBsnDesc("文化宫专业人才的证书");
        } else if (CPConstants.FILE_TYPE_STAFF_AWARD_CERTIFICATE.equals(type)) {
            bsnInfo.setBsnId(staffId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_STAFF_AWARD_CERTIFICATE);
            bsnInfo.setBsnDesc("文化宫专业人才的获奖证明");
        } else if (CPConstants.FILE_TYPE_STAFF_QUALIFICATION.equals(type)) {
            bsnInfo.setBsnId(staffId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_STAFF_QUALIFICATION);
            bsnInfo.setBsnDesc("文化宫专业人才的资质照片");
        }

        return bsnInfo;
    }

    /**
     * 查询场地下的专业人才信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPProfessionalTalentListVO> listCPProfessionalTalentByCourtId(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPProfessionalTalentListVO> p = new Page<>(page, pageSize);
        List<CPProfessionalTalentListVO> cpProfessionalTalentList = cpProfessionalTalentMapper.listCPProfessionalTalentByCourtId(p, parameters);
        for (CPProfessionalTalentListVO talentVO : cpProfessionalTalentList) {
            Map<String, Object> fileParam = new HashMap<>();
            fileParam.put("bsnId", talentVO.getId());
            fileParam.put("type", CPConstants.FILE_TYPE_STAFF_PHOTO);
            List<PubFilesVO> filesVOList = filesMapper.listPubFiles(fileParam);
            if (!filesVOList.isEmpty()) {
                talentVO.setHeadAvatar(filesVOList.get(0).getDownloadUrl());
            }
        }
        p.setRecords(cpProfessionalTalentList);
        return p;
    }
}
