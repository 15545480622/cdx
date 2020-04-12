package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.vista.labor.cp.entity.activity.CpActivityFormEntity;

/**
 * 报名活动表单
 * 
 * @author 
 * @email 
 * @date 2020-03-18 14:28:24
 */
public interface CpActivityFormService extends IService<CpActivityFormEntity> {

    /**
     * 根据活动id查询表单信息
     * @param id
     * @return
     */
    CpActivityFormEntity selectByActivityid(Integer id);
}
