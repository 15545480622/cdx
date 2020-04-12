package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectEntity;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectListVO;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPWelfareProjectMapper
 * @Description: 公益性服务项目清单
 * @authur: wangxueying01
 * @CreatDate: 2020/4/8 11:00
 */
public interface CPWelfareProjectMapper {

    /**
     * 获取公益性服务项目清单
     * @param id
     * @return
     */
    CPWelfareProjectVO selectByPrimaryKey(String id);

    /**
     * 查询公益性服务项目清单
     * @param p
     * @param parameters
     * @return
     */
    List<CPWelfareProjectListVO> listCPWelfareProject(Page<CPWelfareProjectListVO> p, Map<String, Object> parameters);

    /**
     * 新增公益性服务项目清单
     * @param cpWelfareProjectEntity
     */
    void insertSelective(CPWelfareProjectEntity cpWelfareProjectEntity);

    /**
     * 更新公益性服务项目清单
     * @param cpWelfareProjectEntity
     */
    void updateByPrimaryKeySelective(CPWelfareProjectEntity cpWelfareProjectEntity);

    /**
     * 单个删除公益性服务项目清单
     * @param paramMap
     * @return
     */
    int deleteCPWelfareProjectById(Map<String, Object> paramMap);

    /**
     * 批量删除公益性服务项目清单
     * @param paramMap
     * @return
     */
    int batchDeleteCPWelfareProjectById(Map<String, Object> paramMap);
}