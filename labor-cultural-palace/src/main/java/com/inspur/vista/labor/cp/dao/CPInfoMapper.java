package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPInfoEntity;
import com.inspur.vista.labor.cp.entity.CPInfoListVO;
import com.inspur.vista.labor.cp.entity.CPInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPInfoDao
 * @Description: 文化宫基本信息
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 17:02
 * @Version: 1.0
 */
public interface CPInfoMapper {

    /**
     * 获取文化宫基本信息信息
     *
     * @param id 文化宫id
     * @return CPInfoVO
     */
    CPInfoVO selectByPrimaryKey(String id);

    /**
     * 新增文化宫基本信息
     *
     * @param cpInfoEntity
     * @return
     */
    int insertSelective(CPInfoEntity cpInfoEntity);

    /**
     * 更新文化宫信息
     *
     * @param cpInfoEntity
     * @return
     */
    int updateByPrimaryKeySelective(CPInfoEntity cpInfoEntity);

    /**
     * 查询文化宫信息
     *
     * @param parameters
     * @return
     */
    List<CPInfoListVO> listCPInfo(Page page, Map<String, Object> parameters);

    /**
     * 根据区划或项目类型查询文化宫
     *
     * @param parameters
     * @return
     */
    List<CPInfoListVO> listCPInfoByCantOrType(Map<String, Object> parameters);

    /**
     * 根据文化宫id查询已有的项目类型
     *
     * @param cpId
     * @return
     */
    List<Map<String, String>> listTypeByCpId(String cpId);

    /**
     * 通过id删除文化宫
     *
     * @param paramMap
     * @return
     */
    int deleteCPInfoById(Map<String, Object> paramMap);
}
