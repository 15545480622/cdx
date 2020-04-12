package com.inspur.vista.labor.cp.service;


import com.inspur.vista.labor.cp.entity.FileBsnInfo;
import com.inspur.vista.labor.cp.entity.PubFilesVO;
import com.inspur.vista.labor.cp.entity.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Title: PubFilesService
 * @Description: 附件服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/11 15:04
 * @Version: 1.0
 */
public interface PubFilesService {

    /**
     * 获取附件
     *
     * @param id
     * @return
     */
    PubFilesVO getPubFiles(String id);

    /**
     * 查询附件
     *
     * @param parameters
     * @return
     */
    List<PubFilesVO> listPubFiles(Map<String, Object> parameters);

    /**
     * 保存附件
     *
     * @param bsnInfo
     * @param files
     * @return
     */
    void savePubFiles(FileBsnInfo bsnInfo, MultipartFile[] files, String creator) throws Exception;


    /**
     * 文件上传，需相同的业务类型
     *
     * @param bsnInfo
     * @param multipartFiles
     * @return
     */
    List<UploadFileResponse> updateFile(FileBsnInfo bsnInfo, MultipartFile[] multipartFiles) throws Exception;

    /**
     * 通过业务id及业务类型删除附件
     *
     * @param bsnId
     * @param type
     * @return
     */
    int removeByBsnIdAndType(String bsnId, String type, String modifier);

    /**
     * 通过id删除附件
     *
     * @param ids
     * @return
     */
    int removePubFilesById(String[] ids);


}



