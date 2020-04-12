package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Title: CulturePalaceService
 * @Description: 文化宫基本信息服务类
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 16:01
 * @Version: 1.0
 */
public interface CPInfoService {

    /**
     * 获取文化宫信息
     *
     * @param id 驿站id
     * @return CPInfoVO
     */
    CPInfoVO getCPInfo(String id);

    /**
     * app端获取文化宫信息
     *
     * @param id
     * @return
     */
    CPInfoAppVO getAppCPInfo(String id);

    /**
     * 获取文化宫申请信息
     *
     * @param applyId 申请表id
     * @return CPInfoVO
     */
    CPInfoApplyVO getCPInfoApply(String applyId);

    /**
     * 保存文化宫信息
     *
     * @param cpInfoEntity
     */
    ResponseDTO save(CPInfoEntity cpInfoEntity) throws Exception;

    /**
     * 申请审核
     *
     * @param applyAdd
     */
    void apply(CPInfoApplyAdd applyAdd);

    /**
     * 审核
     *
     * @param applyCheck
     */
    void check(CPApplyCheck applyCheck);

    /**
     * 查询文化宫信息
     *
     * @param parameters
     * @return
     */
    Page<CPInfoListVO> listCPInfo(Map<String, Object> parameters);

    /**
     * 根据区划或项目类型查询文化宫
     *
     * @param parameters
     * @return
     */
    List<CPInfoListVO> listCPInfoByCantOrType(Map<String, Object> parameters);


    /**
     * 查询文化宫的附件
     *
     * @param cpId
     * @return
     */
    List<PubFilesVO> getCPFiles(Long cpId);

    /**
     * 根据文化宫id查询已有的项目类型
     *
     * @param cpId
     * @return
     */
    List<Map<String, String>> listTypeByCpId(String cpId);

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param cpId
     * @return
     */
    List<String> uploadFile(MultipartFile[] file, String type, String cpId) throws Exception;

    /**
     * 根据id删除文化宫
     *
     * @param id
     * @return
     */
    int removeCPInfoById(String id);
}
