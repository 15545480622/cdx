package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPLiabilitiesMonthMapper;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthEntity;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthListVO;
import com.inspur.vista.labor.cp.service.CPLiabilitiesMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPLiabilitiesMonthServiceImpl
 * @Description: 月度资产负债
 * @authur: wangxueying01
 * @CreatDate: 2020/1/9 15:58
 */
@Service
public class CPLiabilitiesMonthServiceImpl implements CPLiabilitiesMonthService {

    @Autowired
    private CPLiabilitiesMonthMapper cpLiabilitiesMonthMapper;

    /**
     * 获取月度资产负债
     *
     * @param id 月度资产负债id
     * @return
     */
    @Override
    public CPLiabilitiesMonthDetailVO getCPLiabilitiesMonth(Long id) {
        return cpLiabilitiesMonthMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询月度资产负债
     *
     * @param parameters 查询参数
     * @return
     */
    @Override
    public Page<CPLiabilitiesMonthListVO> listCPLiabilitiesMonth(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPLiabilitiesMonthListVO> p = new Page<>(page, pageSize);
        List<CPLiabilitiesMonthListVO> cpLiabilitiesMonthVOList = cpLiabilitiesMonthMapper.listCPLiabilitiesMonth(p, parameters);
        p.setRecords(cpLiabilitiesMonthVOList);
        return p;
    }

    /**
     * 保存月度资产负债
     *
     * @param cpLiabilitiesMonthEntity 月度资产负债
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCPLiabilitiesMonth(CPLiabilitiesMonthEntity cpLiabilitiesMonthEntity) {
        if (cpLiabilitiesMonthEntity.getId() == null) {
            cpLiabilitiesMonthMapper.insertSelective(cpLiabilitiesMonthEntity);
        } else {
            cpLiabilitiesMonthMapper.updateByPrimaryKeySelective(cpLiabilitiesMonthEntity);
        }
    }
}
