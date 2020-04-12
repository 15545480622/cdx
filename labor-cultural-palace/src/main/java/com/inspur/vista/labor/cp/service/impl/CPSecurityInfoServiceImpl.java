package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPSecurityInfoMapper;
import com.inspur.vista.labor.cp.dao.PubFilesMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPSecurityInfoService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPSecurityInfoServiceImpl
 * @Description: 安全管理服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/4/7 19:54
 */
@Service
public class CPSecurityInfoServiceImpl implements CPSecurityInfoService {

    @Autowired
    private CPSecurityInfoMapper cpSecurityInfoMapper;

    @Autowired
    private PubFilesService filesService;

    @Autowired
    private PubFilesMapper filesMapper;

    /**
     * 获取安全管理
     *
     * @param id
     * @return
     */
    @Override
    public CPSecurityInfoVO getCPSecurityInfo(String id) {
        return cpSecurityInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询安全管理
     *
     * @param paramMap
     * @return
     */
    @Override
    public Page<CPSecurityInfoListVO> listCPSecurityInfo(Map<String, Object> paramMap) {
        int page = Convert.toInt(paramMap.get("page"));
        int pageSize = Convert.toInt(paramMap.get("pageSize"));
        Page<CPSecurityInfoListVO> p = new Page<>(page, pageSize);
        List<CPSecurityInfoListVO> cpSecurityInfoListVOS = cpSecurityInfoMapper.listCPSecurityInfo(p, paramMap);
        p.setRecords(cpSecurityInfoListVOS);
        return p;
    }

    /**
     * 保存安全管理
     *
     * @param cpSecurityInfoEntity
     * @return
     */
    @Override
    public CPSecurityInfoEntity saveCPSecurityInfo(CPSecurityInfoEntity cpSecurityInfoEntity) {
        if (StringUtils.isBlank(cpSecurityInfoEntity.getId())) {
            cpSecurityInfoEntity.setId(IdUtil.fastSimpleUUID());
            cpSecurityInfoMapper.insertSelective(cpSecurityInfoEntity);
        } else {
            cpSecurityInfoMapper.updateByPrimaryKeySelective(cpSecurityInfoEntity);
        }
        return cpSecurityInfoEntity;
    }

    /**
     * 删除安全管理
     *
     * @param paramMap
     * @return
     */
    @Override
    public int removeCPSecurityInfo(Map<String, Object> paramMap) {
        String[] ids = (String[]) paramMap.get("ids");
        int result;
        if (ids.length == 1) {
            paramMap.put("id", ids[0]);
            result = cpSecurityInfoMapper.deleteCPSecurityInfo(paramMap);
        } else {
            result = cpSecurityInfoMapper.batchDeleteCPSecurityInfo(paramMap);
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param securityId
     * @return
     */
    @Override
    public List<String> uploadFile(MultipartFile[] file, String type, String securityId) throws Exception {
        FileBsnInfo bsnInfo = createFileBsnInfo(type, securityId);
        List<UploadFileResponse> uploadFileResponseList = filesService.updateFile(bsnInfo, file);
        // 2.新增附件信息
        List<PubFilesEntity> fileList = new ArrayList<>();
        List<String> fileIdList = new ArrayList<>();
        for (UploadFileResponse fileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setType(type);
            filesEntity.setBsnId(securityId);
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
     * @param securityId
     * @return
     */
    private FileBsnInfo createFileBsnInfo(String type, String securityId) {
        FileBsnInfo bsnInfo = new FileBsnInfo();
        //安全管理附件
        if (CPConstants.FILE_TYPE_SECURITY_FACILITIES_PHOTO.equals(type)) {
            bsnInfo.setBsnId(securityId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_SECURITY_FACILITIES_PHOTO);
            bsnInfo.setBsnDesc("安全管理的安全设施照片");
        } else if (CPConstants.FILE_TYPE_DRILL_PHOTO.equals(type)) {
            bsnInfo.setBsnId(securityId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_DRILL_PHOTO);
            bsnInfo.setBsnDesc("安全管理的组织演练照片");
        } else if (CPConstants.FILE_TYPE_EMERGENCY_PLAN.equals(type)) {
            bsnInfo.setBsnId(securityId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_EMERGENCY_PLAN);
            bsnInfo.setBsnDesc("安全管理的应急预案文件");
        } else if (CPConstants.FILE_TYPE_SECURITY_EDUCATION_PHOTO.equals(type)) {
            bsnInfo.setBsnId(securityId);
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_SECURITY_EDUCATION_PHOTO);
            bsnInfo.setBsnDesc("安全管理的安全教育照片");
        }
        return bsnInfo;
    }
}
