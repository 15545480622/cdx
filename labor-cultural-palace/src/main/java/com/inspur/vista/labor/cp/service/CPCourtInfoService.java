package com.inspur.vista.labor.cp.service;


import com.inspur.vista.labor.cp.entity.CPCourtInfoEntity;
import com.inspur.vista.labor.cp.entity.CPCourtInfoListVO;
import com.inspur.vista.labor.cp.entity.CPCourtInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPCourtInfoService
 * @Description: 场地信息服务类
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
public interface CPCourtInfoService {

    /**
     * 获取场地信息
     *
     * @param id
     * @return
     */
    CPCourtInfoVO getCPCourtInfo(String id);

    /**
     * 查询场地信息
     *
     * @param parameters
     * @return
     */
    List<CPCourtInfoListVO> listCPCourtInfo(Map<String, Object> parameters);

    /**
     * 查询允许预约的场地信息
     *
     * @param parameters
     * @return
     */
    List<CPCourtInfoListVO> listCanReserveCPCourtInfo(Map<String, Object> parameters);

    /**
     * 保存场地信息
     *
     * @param cpCourtInfo
     * @return
     */
    CPCourtInfoEntity saveCPCourtInfo(CPCourtInfoEntity cpCourtInfo);

    /**
     * 通过id删除场地信息
     *
     * @param ids
     * @return
     */
    int removeCPCourtInfoById(String[] ids, String modifier);

    /**
     * 修改场地启用状态
     *
     * @param map
     * @return
     */
    int enableCourt(Map map);

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param courtId
     * @return
     */
    List<String> uploadFile(MultipartFile[] file, String type, String courtId) throws Exception;
}



