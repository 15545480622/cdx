package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPInfoMapper;
import com.inspur.vista.labor.cp.dao.CPStaffInfoMapper;
import com.inspur.vista.labor.cp.dao.PubFilesMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPStaffInfoService;
import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPStaffInfoServiceImpl
 * @Description: 文化宫人员信息服务类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/6 16:16
 */
@Service
public class CPStaffInfoServiceImpl implements CPStaffInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPStaffInfoServiceImpl.class);

    @Autowired
    private CPStaffInfoMapper cpStaffInfoMapper;

    @Autowired
    private CPInfoMapper infoMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private PubFilesService filesService;

    @Autowired
    private PubFilesMapper filesMapper;

    @Value("${bsp-gateway.url_change_user_name}")
    private String changeUserNameUrl;

    @Value("${bsp-gateway.url_add_user}")
    private String addUserUrl;

    @Value("${bsp-gateway.url_del_user}")
    private String delUserUrl;

    /**
     * 获取人员信息
     *
     * @param id 人员id
     * @return
     */
    @Override
    public CPStaffInfoVO getCPStaffInfo(String id) {
        return cpStaffInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 管理员账号
     *
     * @param managerUsername
     * @return
     */
    @Override
    public CPStaffInfoVO getCPStaffInfoByManagerUsername(String managerUsername) {
        return cpStaffInfoMapper.selectByManagerUsername(managerUsername);
    }

    /**
     * 通过身份证获取人员信息
     *
     * @param idcard
     * @return
     */
    @Override
    public CPStaffInfoVO getCPStaffInfoByIdcard(String idcard) {
        return cpStaffInfoMapper.selectByIdcard(idcard);
    }

    /**
     * 查询人员信息
     *
     * @param parameters 查询参数
     * @return
     */
    @Override
    public Page<CPStaffInfoListVO> listCPStaffInfo(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPStaffInfoListVO> p = new Page<>(page, pageSize);
        List<CPStaffInfoListVO> cpStaffInfoVOList = cpStaffInfoMapper.listCPStaffInfo(p, parameters);
        p.setRecords(cpStaffInfoVOList);
        return p;
    }

    /**
     * 查询场地下的专业人才信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPStaffInfoListVO> listCPTalentByCourtId(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPStaffInfoListVO> p = new Page<>(page, pageSize);
        List<CPStaffInfoListVO> cpStaffInfoVOList = cpStaffInfoMapper.listTalentByCourtId(p, parameters);
        for (CPStaffInfoListVO talentVO : cpStaffInfoVOList) {
            Map<String, Object> fileParam = new HashMap<>();
            fileParam.put("bsnId", talentVO.getId());
            fileParam.put("type", CPConstants.FILE_TYPE_STAFF_PHOTO);
            List<PubFilesVO> filesVOList = filesMapper.listPubFiles(fileParam);
            if (!filesVOList.isEmpty()) {
                talentVO.setHeadAvatar(filesVOList.get(0).getDownloadUrl());
            }
        }
        p.setRecords(cpStaffInfoVOList);
        return p;
    }

    /**
     * 保存人员信息
     *
     * @param cpStaffInfoEntity 人员信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCPStaffInfo(CPStaffInfoEntity cpStaffInfoEntity) {
        if (StringUtils.isBlank(cpStaffInfoEntity.getId())) {
            cpStaffInfoEntity.setId(IdUtil.fastSimpleUUID());
            cpStaffInfoEntity.setState(CPConstants.INFO_VALID);
            cpStaffInfoMapper.insertSelective(cpStaffInfoEntity);
        } else {
            cpStaffInfoMapper.updateByPrimaryKeySelective(cpStaffInfoEntity);

        }
    }

    /**
     * 通过id删除人员信息
     *
     * @param ids 人员id数组
     * @return
     */
    @Override
    public int removeCPStaffInfoById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpStaffInfoMapper.deleteCPStaffInfoById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpStaffInfoMapper.batchDeleteCPStaffInfoById(paramMap);
        }
        return result;
    }

    @Override
    public void addCPUser(String staffId, String username, String roleCodes) throws Exception {
        CPStaffInfoVO staffInfo = cpStaffInfoMapper.selectByPrimaryKey(staffId);
        CPInfoVO infoVO = infoMapper.selectByPrimaryKey(staffInfo.getCpId());
        boolean addResult = addUser(username, staffInfo.getName(), infoVO.getOrganId(), roleCodes);
        if (addResult) {
            CPStaffInfoEntity cpStaffInfoEntity = new CPStaffInfoEntity();
            cpStaffInfoEntity.setId(staffId);
            cpStaffInfoEntity.setManagerUsername(username);
            cpStaffInfoMapper.updateByPrimaryKeySelective(cpStaffInfoEntity);
        }
    }

    @Override
    public void removeCPUser(String staffId) {
        CPStaffInfoVO staffInfo = cpStaffInfoMapper.selectByPrimaryKey(staffId);
        boolean delResult = delUser(staffInfo.getManagerUsername());
        if (delResult) {
            CPStaffInfoEntity cpStaffInfoEntity = new CPStaffInfoEntity();
            cpStaffInfoEntity.setId(staffId);
            cpStaffInfoEntity.setManagerUsername("");
            cpStaffInfoMapper.updateByPrimaryKeySelective(cpStaffInfoEntity);
        }
    }

    /**
     * 用户新增
     *
     * @param userId
     * @param userName
     */
    private boolean addUser(String userId, String userName, String organId, String roleCodes) throws Exception {
        boolean addResult;
        Map resultMap;
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        Map param = new HashMap();
        param.put("userId", userId);
        param.put("userName", userName);
        param.put("organId", organId);
        param.put("roleCodes", roleCodes);
        resultMap = HttpUtil.post(addUserUrl, param, headerMap);
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("新增用户失败：{}", resultMap.get("msg"));
                addResult = false;
            } else {
                addResult = true;
            }
        } else {
            addResult = false;
        }

        return addResult;
    }

    /**
     * 驿站用户删除
     *
     * @param userId
     */
    private boolean delUser(String userId) {
        boolean delResult;
        Map resultMap;
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        Map param = new HashMap();
        param.put("userId", userId);
        resultMap = HttpUtil.post(delUserUrl, param, headerMap);
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("删除用户失败：{}", resultMap.get("msg"));
                delResult = false;
            } else {
                delResult = true;
            }
        } else {
            delResult = false;
        }

        return delResult;
    }


    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param staffId
     * @return
     */
    @Override
    public List<String> uploadFile(MultipartFile[] file, String type, String staffId) throws Exception {
        FileBsnInfo bsnInfo = createFileBsnInfo(type, staffId);
        List<UploadFileResponse> uploadFileResponseList = filesService.updateFile(bsnInfo, file);
        List<PubFilesEntity> fileList = new ArrayList<>();
        List<String> fileIdList = new ArrayList<>();
        for (UploadFileResponse fileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setType(type);
            filesEntity.setBsnId(staffId);
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
}
