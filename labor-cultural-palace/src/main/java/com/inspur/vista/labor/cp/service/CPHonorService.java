package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPHonorEntity;
import com.inspur.vista.labor.cp.entity.CPHonorInfoVO;
import com.inspur.vista.labor.cp.entity.CPHonorListVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPHonorService
 * @Description: 文化宫荣誉信息服务类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/23 8:49
 */
public interface CPHonorService {

    /**
     * 获取荣誉信息
     *
     * @param id
     * @return
     */
    CPHonorInfoVO getCPHonorInfo(String id);

    /**
     * 保存荣誉信息
     *
     * @param cpHonorEntity
     * @return
     */
    CPHonorEntity saveHonorInfo(CPHonorEntity cpHonorEntity) throws Exception;

    /**
     * 查询荣誉信息
     *
     * @param parameters
     * @return
     */
    Page<CPHonorListVO> listCPHonorInfo(Map<String, Object> parameters);

    /**
     * 根据id删除荣誉信息
     *
     * @param idArr
     * @return
     */
    int removeCPHonorInfoById(String[] idArr);

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param honorId
     * @return
     */
    List<String> uploadFile(MultipartFile[] file, String type, String honorId) throws Exception;

}