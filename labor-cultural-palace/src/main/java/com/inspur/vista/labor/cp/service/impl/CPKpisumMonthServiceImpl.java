package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPKpisumMonthMapper;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthEntity;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthListVO;
import com.inspur.vista.labor.cp.service.CPKpisumMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPKpisumMonthServiceImpl
 * @Description: 文化宫月度统计服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/8 11:34
 */
@Service
public class CPKpisumMonthServiceImpl implements CPKpisumMonthService {

    @Autowired
    private CPKpisumMonthMapper cpKpisumMonthMapper;

    /**
     * 获取月度统计
     * @param id
     * @return
     */
    @Override
    public CPKpisumMonthDetailVO getCPKpisumMonth(Long id) {
       CPKpisumMonthDetailVO cpKpisumMonthDetailVO = cpKpisumMonthMapper.selectByPrimaryKey(id);
       return cpKpisumMonthDetailVO;
    }

    /**
     * 查询月度统计
     * @param parameters
     * @return
     */
    @Override
    public Page<CPKpisumMonthListVO> listCPKpisumMonth(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPKpisumMonthListVO> p = new Page<>(page, pageSize);
        List<CPKpisumMonthListVO> cpKpisumMonthListVOS = cpKpisumMonthMapper.listCPKpisumMonth(p, parameters);
        p.setRecords(cpKpisumMonthListVOS);
        return p;
    }

    /**
     * 新增月度统计
     * @param cpKpisumMonthEntity
     */
    @Override
    public void saveKpisumMonth(CPKpisumMonthEntity cpKpisumMonthEntity) {
        cpKpisumMonthMapper.insertSelective(cpKpisumMonthEntity);
    }
}
