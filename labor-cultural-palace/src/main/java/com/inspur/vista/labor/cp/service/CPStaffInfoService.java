package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPStaffInfoEntity;
import com.inspur.vista.labor.cp.entity.CPStaffInfoListVO;
import com.inspur.vista.labor.cp.entity.CPStaffInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPStaffInfoService
 * @Description: 文化宫人员信息服务类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/5 19:42
 */
public interface CPStaffInfoService {

    /**
     * 获取人员信息
     *
     * @param id 人员id
     * @return
     */
    CPStaffInfoVO getCPStaffInfo(String id);

    /**
     * 管理员账号
     *
     * @param managerUsername
     * @return
     */
    CPStaffInfoVO getCPStaffInfoByManagerUsername(String managerUsername);

    /**
     * 通过身份证获取人员信息
     *
     * @param idcard
     * @return
     */
    CPStaffInfoVO getCPStaffInfoByIdcard(String idcard);

    /**
     * 查询人员信息
     *
     * @param parameters 查询参数
     * @return
     */
    Page<CPStaffInfoListVO> listCPStaffInfo(Map<String, Object> parameters);

    /**
     * 查询场地下的专业人才信息
     *
     * @param parameters
     * @return
     */
    Page<CPStaffInfoListVO> listCPTalentByCourtId(Map<String, Object> parameters);

    /**
     * 保存人员信息
     *
     * @param cpStaffInfoEntity 人员信息
     */
    void saveCPStaffInfo(CPStaffInfoEntity cpStaffInfoEntity);

    /**
     * 通过id删除人员信息
     *
     * @param idArr 人员id数组
     * @return
     */
    int removeCPStaffInfoById(String[] idArr);

    /**
     * 添加文化宫用户
     *
     * @param staffId
     * @param username    管理员账号
     * @param roleCodes
     */
    void addCPUser(String staffId, String username, String roleCodes) throws Exception;

    /**
     * 删除文化宫用户
     *
     * @param staffId
     */
    void removeCPUser(String staffId);

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param staffId
     * @return
     */
    List<String> uploadFile(MultipartFile[] file, String type, String staffId) throws Exception;
}