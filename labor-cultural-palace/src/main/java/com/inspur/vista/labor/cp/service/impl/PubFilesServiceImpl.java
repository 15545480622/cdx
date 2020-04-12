package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.inspur.vista.labor.cp.dao.PubFilesMapper;
import com.inspur.vista.labor.cp.entity.FileBsnInfo;
import com.inspur.vista.labor.cp.entity.PubFilesEntity;
import com.inspur.vista.labor.cp.entity.PubFilesVO;
import com.inspur.vista.labor.cp.entity.UploadFileResponse;
import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * @Title: PubFilesServiceImpl
 * @Description: 附件服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/11 15:04
 * @Version: 1.0
 */
@Service
public class PubFilesServiceImpl implements PubFilesService {

    private static final Logger logger = LoggerFactory.getLogger(PubFilesServiceImpl.class);

    @Autowired
    private PubFilesMapper pubFilesMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommonService commonService;

    @Value("${oss.upload_file}")
    private String uploadFileUrl;

    @Value("${oss.referer}")
    private String referer;

    /**
     * 获取附件
     *
     * @param id 附件id
     * @return
     */
    @Override
    public PubFilesVO getPubFiles(String id) {
        PubFilesVO pubFilesVO = pubFilesMapper.selectByPrimaryKey(id);
        return pubFilesVO;
    }

    /**
     * 查询附件
     *
     * @param parameters
     * @return
     */
    @Override
    public List<PubFilesVO> listPubFiles(Map<String, Object> parameters) {

        List<PubFilesVO> filesVOList = pubFilesMapper.listPubFiles(parameters);
        for (PubFilesVO filesVO : filesVOList) {
            filesVO.setDownloadUrl(uploadFileUrl + filesVO.getFilePath());
        }
        return filesVOList;
    }

    /**
     * 保存附件
     *
     * @param bsnInfo
     * @param creator
     * @param files
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePubFiles(FileBsnInfo bsnInfo, MultipartFile[] files, String creator) throws Exception {
        List<UploadFileResponse> uploadFileResponseList = updateFile(bsnInfo, files);
        List<PubFilesEntity> filesEntityList = new ArrayList<>();
        for (UploadFileResponse uploadFileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setBsnId(bsnInfo.getBsnId());
            filesEntity.setType(bsnInfo.getBsnType());
            filesEntity.setFileExt(uploadFileResponse.getFileExt());
            filesEntity.setFileSize(uploadFileResponse.getFileSize());
            filesEntity.setFileName(uploadFileResponse.getFileName());
            filesEntity.setFilePath(uploadFileResponse.getFilePath());
            filesEntity.setCreator(creator);
            pubFilesMapper.insertSelective(filesEntity);
        }
    }


    /**
     * 上传附件
     *
     * @param multipartFiles
     * @param bsnInfo        文件的业务信息
     * @throws Exception
     */
    @Override
    public List<UploadFileResponse> updateFile(FileBsnInfo bsnInfo, MultipartFile[] multipartFiles) throws Exception {

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        headers.set("Authorization", "Bearer " + commonService.getToken());
        headers.set("Referer", referer);
        File[] files = new File[multipartFiles.length];
        List<UploadFileResponse> responseList = new ArrayList<>();
        //遍历文件数组
        for (int i = 0; i < multipartFiles.length; i++) {
            UploadFileResponse response = new UploadFileResponse();
            // 转为FileSystemResource 用于发送文件
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            File localFile = File.createTempFile(Objects.requireNonNull(multipartFiles[i].getOriginalFilename()), "." + FileUtil.extName(multipartFiles[i].getOriginalFilename()));
            multipartFiles[i].transferTo(localFile);
            files[i] = localFile;
            response.setFileName(FileUtil.getName((multipartFiles[i].getOriginalFilename())));
            response.setFileExt(FileUtil.extName(localFile));
            response.setFileSize(FileUtils.sizeOf(localFile));
            param.add("group", CPConstants.FILE_UPLOAD_GROUP);
            param.add("bsnType", bsnInfo.getBsnType());
            param.add("bsnId", bsnInfo.getBsnId());
            param.add("bsnDesc", bsnInfo.getBsnDesc());
            param.add("file", new FileSystemResource(localFile));

            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
            ResponseDTO result = restTemplate.postForObject(uploadFileUrl, httpEntity, ResponseDTO.class);

            if (files[i].exists()) {
                logger.debug("删除临时文件" + files[i].getName());
                files[i].delete();
            }
            if (null != result && ErrorCodeEnum.P_SUCCESS.getCode().equals(result.getCode())) {
                List<String> pathList = Convert.toList(String.class, result.getData());
                if (!pathList.isEmpty()) {
                    response.setFilePath(pathList.get(0));
                    responseList.add(response);
                }
            }
        }
        return responseList;
    }

    /**
     * 通过业务id及业务类型删除附件
     *
     * @param bsnId
     * @param type
     * @return
     */
    @Override
    public int removeByBsnIdAndType(String bsnId, String type, String modifier) {
        return pubFilesMapper.deleteFilesByBsnIdAndType(bsnId, type, modifier);
    }

    /**
     * 通过id删除附件
     *
     * @param ids 附件id的字符串数组
     * @return
     */
    @Override
    public int removePubFilesById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = pubFilesMapper.deletePubFilesById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = pubFilesMapper.batchDeletePubFilesById(paramMap);
        }
        return result;
    }
}
