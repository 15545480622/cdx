package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectEntity;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectListVO;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectVO;

import java.util.Map;

/**
 * @ClassName: CPWelfareProjectService
 * @Description: 公益性服务项目清单服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/4/8 11:01
 */
public interface CPWelfareProjectService {

    /**
     * 获取公益性服务项目清单
     *
     * @param id
     * @return
     */
    CPWelfareProjectVO getCPWelfareProject(String id);

    /**
     * 保存公益性服务项目清单
     *
     * @param cpWelfareProjectEntity
     * @return
     */
    void saveCPWelfareProject(CPWelfareProjectEntity cpWelfareProjectEntity);

    /**
     * 查询公益性服务项目清单
     *
     * @param parameters
     * @return
     */
    Page<CPWelfareProjectListVO> listCPWelfareProject(Map<String, Object> parameters);

    /**
     * 删除公益性服务项目清单
     *
     * @param idArr
     * @return
     */
    int removeCPWelfareProjectById(String[] idArr);
}