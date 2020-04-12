package com.inspur.vista.labor.cp.service.activity.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.labor.cp.dao.activity.CpActivityFormDao;
import com.inspur.vista.labor.cp.entity.activity.CpActivityFormEntity;
import com.inspur.vista.labor.cp.service.activity.CpActivityFormService;
import org.springframework.stereotype.Service;



@Service("cpActivityFormService")
public class CpActivityFormServiceImpl extends ServiceImpl<CpActivityFormDao, CpActivityFormEntity> implements CpActivityFormService {

    /**
     * 根据活动id查询表单信息
     *
     * @param id
     * @return
     */
    @Override
    public CpActivityFormEntity selectByActivityid(Integer id) {

        //获取活动表单
        CpActivityFormEntity cpActivityFormEntity = new CpActivityFormEntity();
        cpActivityFormEntity.setActivityId(id);
        return baseMapper.selectOne(cpActivityFormEntity);
    }
}
