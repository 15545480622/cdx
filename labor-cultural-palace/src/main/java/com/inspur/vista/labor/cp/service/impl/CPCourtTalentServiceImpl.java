package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPCourtTalentMapper;
import com.inspur.vista.labor.cp.entity.CPCourtTalentEntity;
import com.inspur.vista.labor.cp.entity.CPCourtTalentListVO;
import com.inspur.vista.labor.cp.service.CPCourtTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPCourtTalentServiceImpl
 * @Description: 场地专业人才关系服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/4/2 17:33
 */
@Service
public class CPCourtTalentServiceImpl implements CPCourtTalentService {

    @Autowired
    private CPCourtTalentMapper cpCourtTalentMapper;

    /**
     * 关联专业人才
     *
     * @param cpCourtTalentEntity
     * @return
     */
    @Override
    public CPCourtTalentEntity saveCourtTalent(CPCourtTalentEntity cpCourtTalentEntity) {
        cpCourtTalentEntity.setId(IdUtil.fastSimpleUUID());
        cpCourtTalentMapper.insertCourtTalent(cpCourtTalentEntity);
        return cpCourtTalentEntity;
    }

    /**
     * 取消关联专业人才
     *
     * @param paramMap
     * @return
     */
    @Override
    public int cancelCourtTalent(Map<String, Object> paramMap) {
        return cpCourtTalentMapper.deleteCourtTalent(paramMap);
    }

    /**
     * 查询专业人才及其关联状态
     *
     * @param paramMap
     * @return
     */
    @Override
    public Page<CPCourtTalentListVO> listCourtTalent(Map<String, Object> paramMap) {
        int page = Convert.toInt(paramMap.get("page"));
        int pageSize = Convert.toInt(paramMap.get("pageSize"));
        Page<CPCourtTalentListVO> p = new Page<>(page, pageSize);
        List<CPCourtTalentListVO> cpCourtTalentListVOS = cpCourtTalentMapper.listCourtTalent(p, paramMap);
        p.setRecords(cpCourtTalentListVOS);
        return p;
    }
}
