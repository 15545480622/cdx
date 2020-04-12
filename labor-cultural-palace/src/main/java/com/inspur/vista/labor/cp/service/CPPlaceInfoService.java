package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoEntity;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoListVO;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @Title: CPPlaceInfoService
 * @Description: 场所信息服务类
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
public interface CPPlaceInfoService {

    /**
     * 获取场所信息
     *
     * @param id
     * @return
     */
    CPPlaceInfoVO getCPPlaceInfo(String id);

    /**
     * 查询场所信息
     *
     * @param parameters
     * @return
     */
    Page<CPPlaceInfoListVO> listCPPlaceInfo(Map<String, Object> parameters);

    /**
     * 保存场所信息
     *
     * @param cpPlaceInfo
     * @return
     */
    CPPlaceInfoEntity saveCPPlaceInfo(CPPlaceInfoEntity cpPlaceInfo);

    /**
     * 通过id删除场所信息
     *
     * @param ids
     * @return
     */
    int removeCPPlaceInfoById(String[] ids, String modifier);

    /**
     * 生成场所二维码
     *
     * @param placeId 场所id
     */
    void generateQRCode(String placeId, HttpServletResponse response) throws IOException;

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param placeId
     * @return
     */
    List<String> uploadFile(MultipartFile[] file, String type, String placeId) throws Exception;
}



