package com.inspur.vista.labor.cp.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPCourtFeeEntity;
import com.inspur.vista.labor.cp.entity.CPCourtFeeListVO;
import com.inspur.vista.labor.cp.entity.CPCourtFeeVO;
import com.inspur.vista.labor.cp.util.ResponseDTO;

import java.util.Map;


/**
 * @Title: CPArenaFeeService
 * @Description: 场地费用标准服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
public interface CPCourtFeeService {

    /**
     * 获取场地费用标准
     *
     * @param id
     * @return
     */
    CPCourtFeeVO getCPCourtFee(String id);

    /**
     * 获取生效中的费用标准
     *
     * @return
     */
    CPCourtFeeEntity getEffectiveFee(String arenaId);

    /**
     * 查询场地费用标准
     *
     * @param parameters
     * @return
     */
    Page<CPCourtFeeListVO> listCPCourtFee(Map<String, Object> parameters);

    /**
     * 保存场地费用标准
     *
     * @param cpArenaFee
     * @return
     */
    ResponseDTO saveCPCourtFee(CPCourtFeeEntity cpArenaFee);
    /**
     * 通过id删除场地费用标准
     *
     * @param id
     * @return
     */
    int removeCPCourtFeeById(String id, String modifier);
}



