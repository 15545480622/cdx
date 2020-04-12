package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.util.IdUtil;
import com.inspur.vista.labor.cp.dao.CPCourtInfoMapper;
import com.inspur.vista.labor.cp.dao.PubFilesMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPCourtInfoService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.CPConstants;
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
 * @Title: CPCourtInfoServiceImpl
 * @Description: 场地信息服务类
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@Service
public class CPCourtInfoServiceImpl implements CPCourtInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPCourtInfoServiceImpl.class);

    @Autowired
    private CPCourtInfoMapper cpCourtInfoMapper;

    @Autowired
    private PubFilesService pubFilesService;

    @Autowired
    private PubFilesMapper filesMapper;

    /**
     * 获取场地信息
     *
     * @param id 场地信息id
     * @return
     */
    @Override
    public CPCourtInfoVO getCPCourtInfo(String id) {
        CPCourtInfoVO cpCourtInfoVO = cpCourtInfoMapper.selectByPrimaryKey(id);
        return cpCourtInfoVO;
    }

    /**
     * 查询场地信息
     *
     * @param parameters
     * @return
     */
    @Override
    public List<CPCourtInfoListVO> listCPCourtInfo(Map<String, Object> parameters) {
        return cpCourtInfoMapper.listCPCourtInfo(parameters);
    }

    /**
     * 查询允许预约的场地信息
     *
     * @param parameters
     * @return
     */
    @Override
    public List<CPCourtInfoListVO> listCanReserveCPCourtInfo(Map<String, Object> parameters) {
        return cpCourtInfoMapper.listCanReserveCPCourtInfo(parameters);
    }

    /**
     * 保存场地信息
     *
     * @param cpCourtInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPCourtInfoEntity saveCPCourtInfo(CPCourtInfoEntity cpCourtInfo) {
        if (null == cpCourtInfo.getId() || "".equals(cpCourtInfo.getId())) {
            cpCourtInfo.setId(IdUtil.fastSimpleUUID());
            cpCourtInfo.setIsEnable(CPConstants.NOT_ENABLED);
            cpCourtInfo.setState(CPConstants.INFO_VALID);
            cpCourtInfoMapper.insertSelective(cpCourtInfo);
        } else {
            cpCourtInfoMapper.updateByPrimaryKeySelective(cpCourtInfo);
        }
        return cpCourtInfo;
    }

    /**
     * 通过id删除场地信息
     *
     * @param ids 场地信息id的字符串数组
     * @return
     */
    @Override
    public int removeCPCourtInfoById(String[] ids, String modifier) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", modifier);
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpCourtInfoMapper.deleteCPCourtInfoById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpCourtInfoMapper.batchDeleteCPCourtInfoById(paramMap);
        }
        return result;
    }

    /**
     * 修改场地启用状态
     *
     * @param map
     * @return
     */
    @Override
    public int enableCourt(Map map) {
        return cpCourtInfoMapper.enableCourt(map);
    }

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param courtId
     * @return
     */
    @Override
    public List<String> uploadFile(MultipartFile[] file, String type, String courtId) throws Exception {
        FileBsnInfo bsnInfo = new FileBsnInfo();
        bsnInfo.setBsnId(courtId);
        bsnInfo.setBsnType(type);
        bsnInfo.setBsnDesc("文化宫场地照片");
        List<UploadFileResponse> uploadFileResponseList = pubFilesService.updateFile(bsnInfo, file);
        // 2.新增附件信息
        List<PubFilesEntity> fileList = new ArrayList<>();
        List<String> fileIdList = new ArrayList<>();
        for (UploadFileResponse fileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setType(type);
            filesEntity.setBsnId(courtId);
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
