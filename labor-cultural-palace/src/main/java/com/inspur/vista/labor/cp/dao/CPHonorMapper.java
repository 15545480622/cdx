package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPHonorEntity;
import com.inspur.vista.labor.cp.entity.CPHonorInfoVO;
import com.inspur.vista.labor.cp.entity.CPHonorListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPHonorMapper
 * @Description: 文化宫荣誉信息
 * @authur: wangxueying01
 * @CreatDate: 2019/12/23 8:50
 */
public interface CPHonorMapper {

    /**
     * 获取荣誉信息
     *
     * @param id
     * @return
     */
    CPHonorInfoVO selectByPrimaryKey(String id);

    /**
     * 新增荣誉信息
     *
     * @param cpHonorInfo
     */
    void insertSelective(CPHonorEntity cpHonorInfo);

    /**
     * 更新荣誉信息
     *
     * @param cpHonorInfo
     */
    void updateByPrimaryKeySelective(CPHonorEntity cpHonorInfo);

    /**
     * 查询荣誉信息
     *
     * @param p
     * @param parameters
     * @return
     */
    List<CPHonorListVO> listCPHonorInfo(Page<CPHonorListVO> p, Map<String, Object> parameters);

    /**
     * 通过id删除荣誉信息
     *
     * @param paramMap
     * @return
     */
    int deleteCPHonorInfoById(Map<String, Object> paramMap);

    /**
     * 通过ids批量删除荣誉信息
     *
     * @param paramMap
     * @return
     */
    int batchDeleteCPHonorInfoById(Map<String, Object> paramMap);

}