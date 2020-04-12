package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPHonorMapper;
import com.inspur.vista.labor.cp.dao.PubFilesMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPHonorService;
import com.inspur.vista.labor.cp.service.PubFilesService;
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
 * @ClassName: CPHonorServiceImpl
 * @Description: 文化宫荣誉信息服务类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/23 8:49
 */
@Service
public class CPHonorServiceImpl implements CPHonorService {

    private static final Logger logger = LoggerFactory.getLogger(CPHonorServiceImpl.class);

    @Autowired
    private CPHonorMapper cpHonorMapper;

    @Autowired
    private PubFilesService pubFilesService;

    @Autowired
    private PubFilesMapper filesMapper;

    /**
     * 获取荣誉信息
     *
     * @param id
     * @return
     */
    @Override
    public CPHonorInfoVO getCPHonorInfo(String id) {
        return cpHonorMapper.selectByPrimaryKey(id);
    }

    /**
     * 保存荣誉信息
     *
     * @param cpHonorEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPHonorEntity saveHonorInfo(CPHonorEntity cpHonorEntity) throws Exception {
        if (StringUtils.isBlank(cpHonorEntity.getId())) {
            cpHonorEntity.setId(IdUtil.fastSimpleUUID());
            cpHonorMapper.insertSelective(cpHonorEntity);
        } else {
            cpHonorEntity.setId(cpHonorEntity.getId());
            cpHonorMapper.updateByPrimaryKeySelective(cpHonorEntity);
        }
        return cpHonorEntity;
    }

    /**
     * 查询荣誉信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPHonorListVO> listCPHonorInfo(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPHonorListVO> p = new Page<>(page, pageSize);
        List<CPHonorListVO> cpHonorListVOS = cpHonorMapper.listCPHonorInfo(p, parameters);
        p.setRecords(cpHonorListVOS);
        return p;
    }

    /**
     * 根据id删除荣誉信息
     *
     * @param ids
     * @return
     */
    @Override
    public int removeCPHonorInfoById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpHonorMapper.deleteCPHonorInfoById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpHonorMapper.batchDeleteCPHonorInfoById(paramMap);
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param honorId
     * @return
     */
    @Override
    public List<String> uploadFile(MultipartFile[] file, String type,String honorId) throws Exception {
        FileBsnInfo bsnInfo = new FileBsnInfo();
        bsnInfo.setBsnId(honorId);
        bsnInfo.setBsnType(type);
        bsnInfo.setBsnDesc("文化宫荣誉证明");
        List<UploadFileResponse> uploadFileResponseList = pubFilesService.updateFile(bsnInfo, file);
        // 2.新增附件信息
        List<PubFilesEntity> fileList = new ArrayList<>();
        List<String> fileIdList = new ArrayList<>();
        for (UploadFileResponse fileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setType(type);
            filesEntity.setBsnId(honorId);
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
}
