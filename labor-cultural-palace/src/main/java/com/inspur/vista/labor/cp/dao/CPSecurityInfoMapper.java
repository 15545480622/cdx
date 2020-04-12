package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoEntity;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoListVO;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPSecurityInfoMapper
 * @Description: 安全管理mapper
 * @authur: wangxueying01
 * @CreatDate: 2020/4/7 19:53
 */
public interface CPSecurityInfoMapper {

    /**
     * 获取安全管理
     *
     * @param id
     * @return
     */
    CPSecurityInfoVO selectByPrimaryKey(String id);

    /**
     * 新增安全管理
     *
     * @param cpSecurityInfoEntity
     */
    void insertSelective(CPSecurityInfoEntity cpSecurityInfoEntity);

    /**
     * 更新安全管理
     *
     * @param cpSecurityInfoEntity
     */
    void updateByPrimaryKeySelective(CPSecurityInfoEntity cpSecurityInfoEntity);

    /**
     * 查询安全管理
     *
     * @param paramMap
     * @return
     */
    List<CPSecurityInfoListVO> listCPSecurityInfo(Page page, Map<String, Object> paramMap);

    /**
     * 单个删除安全管理
     *
     * @param paramMap
     * @return
     */
    int deleteCPSecurityInfo(Map<String, Object> paramMap);

    /**
     * 批量删除安全管理
     *
     * @param paramMap
     * @return
     */
    int batchDeleteCPSecurityInfo(Map<String, Object> paramMap);
}