package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPWelfareProjectMapper;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectEntity;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectListVO;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectVO;
import com.inspur.vista.labor.cp.service.CPWelfareProjectService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPWelfareProjectServiceImpl
 * @Description: 公益性服务项目清单服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/4/8 11:01
 */
@Service
public class CPWelfareProjectServiceImpl implements CPWelfareProjectService {


    @Autowired
    private CPWelfareProjectMapper cpWelfareProjectMapper;

    /**
     * 获取公益性服务项目清单
     *
     * @param id
     * @return
     */
    @Override
    public CPWelfareProjectVO getCPWelfareProject(String id) {
        return cpWelfareProjectMapper.selectByPrimaryKey(id);
    }

    /**
     * 保存公益性服务项目清单
     *
     * @param cpWelfareProjectEntity
     * @return
     */
    @Override
    public void saveCPWelfareProject(CPWelfareProjectEntity cpWelfareProjectEntity) {
        if (StringUtils.isBlank(cpWelfareProjectEntity.getId())) {
            cpWelfareProjectEntity.setId(IdUtil.fastSimpleUUID());
            cpWelfareProjectEntity.setState(CPConstants.INFO_VALID);
            cpWelfareProjectMapper.insertSelective(cpWelfareProjectEntity);
        } else {
            cpWelfareProjectMapper.updateByPrimaryKeySelective(cpWelfareProjectEntity);
        }
    }

    /**
     * 查询公益性服务项目清单
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPWelfareProjectListVO> listCPWelfareProject(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPWelfareProjectListVO> p = new Page<>(page, pageSize);
        List<CPWelfareProjectListVO> cpWelfareProjectListVOS = cpWelfareProjectMapper.listCPWelfareProject(p, parameters);
        p.setRecords(cpWelfareProjectListVOS);
        return p;
    }

    /**
     * 删除公益性服务项目清单
     *
     * @param ids
     * @return
     */
    @Override
    public int removeCPWelfareProjectById(String[] ids) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpWelfareProjectMapper.deleteCPWelfareProjectById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpWelfareProjectMapper.batchDeleteCPWelfareProjectById(paramMap);
        }
        return result;
    }
}
