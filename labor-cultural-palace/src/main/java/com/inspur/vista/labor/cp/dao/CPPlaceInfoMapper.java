package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoEntity;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoListVO;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPPlaceInfoMapper
 * @Description: 场所信息
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
public interface CPPlaceInfoMapper {

    /**
     * 获取场所信息
     *
     * @param id
     * @return
     */
    CPPlaceInfoVO selectByPrimaryKey(String id);

    /**
     * 查询场所信息
     *
     * @param parameters
     * @return
     */
    List<CPPlaceInfoListVO> listCPPlaceInfo(Page page, Map<String, Object> parameters);

    /**
     * 通过文化宫id获取场所列表
     *
     * @param cpId
     * @return
     */
    List<CPPlaceInfoListVO> listCPPlaceInfoByCPId(String cpId);

    /**
     * 新增场所信息
     *
     * @param cpPlaceInfo
     * @return
     */
    int insertSelective(CPPlaceInfoEntity cpPlaceInfo);

    /**
     * 更新场所信息
     *
     * @param cpPlaceInfo
     * @return
     */
    int updateByPrimaryKeySelective(CPPlaceInfoEntity cpPlaceInfo);

    /**
     * 通过id删除场所信息
     *
     * @param paramMap modifier:修改人; id:场所信息id
     * @return
     */
    int deleteCPPlaceInfoById(Map paramMap);

    /**
     * 通过ids批量删除场所信息
     *
     * @param paramMap modifier:修改人; id:场所信息id的字符串数组
     * @return
     */
    int batchDeleteCPPlaceInfoById(Map paramMap);

}



