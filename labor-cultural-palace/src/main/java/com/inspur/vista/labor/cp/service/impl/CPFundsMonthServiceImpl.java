package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPFundsMonthMapper;
import com.inspur.vista.labor.cp.entity.CPFundsMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPFundsMonthEntity;
import com.inspur.vista.labor.cp.entity.CPFundsMonthListVO;
import com.inspur.vista.labor.cp.service.CPFundsMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPFundsMonthServiceImpl
 * @Description: 月度经费收支服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/13 8:50
 */
@Service
public class CPFundsMonthServiceImpl implements CPFundsMonthService {
    @Autowired
    private CPFundsMonthMapper cpFundsMonthMapper;

    /**
     * 获取月度经费收支
     *
     * @param id 月度经费收支id
     * @return
     */
    @Override
    public CPFundsMonthDetailVO getCPFundsMonth(Long id) {
        return cpFundsMonthMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询月度经费收支
     *
     * @param parameters 查询参数
     * @return
     */
    @Override
    public Page<CPFundsMonthListVO> listCPFundsMonth(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPFundsMonthListVO> p = new Page<>(page, pageSize);
        List<CPFundsMonthListVO> cpFundsMonthVOList = cpFundsMonthMapper.listCPFundsMonth(p, parameters);
        p.setRecords(cpFundsMonthVOList);
        return p;
    }

    /**
     * 保存月度经费收支
     *
     * @param cpFundsMonthEntity 月度经费收支
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCPFundsMonth(CPFundsMonthEntity cpFundsMonthEntity) {
        if (cpFundsMonthEntity.getId() == null) {
            cpFundsMonthMapper.insertSelective(cpFundsMonthEntity);
        } else {
            cpFundsMonthMapper.updateByPrimaryKeySelective(cpFundsMonthEntity);
        }
    }
}
