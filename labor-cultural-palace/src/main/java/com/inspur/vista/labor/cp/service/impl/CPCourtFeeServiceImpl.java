package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPCourtFeeMapper;
import com.inspur.vista.labor.cp.entity.CPCourtFeeEntity;
import com.inspur.vista.labor.cp.entity.CPCourtFeeListVO;
import com.inspur.vista.labor.cp.entity.CPCourtFeeVO;
import com.inspur.vista.labor.cp.service.CPCourtFeeService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPArenaFeeServiceImpl
 * @Description: 场地费用标准服务类
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@Service
public class CPCourtFeeServiceImpl implements CPCourtFeeService {

    private static final Logger logger = LoggerFactory.getLogger(CPCourtFeeServiceImpl.class);

    @Autowired
    private CPCourtFeeMapper courtFeeMapper;

    /**
     * 获取场地费用标准
     *
     * @param id 场地费用标准id
     * @return
     */
    @Override
    public CPCourtFeeVO getCPCourtFee(String id) {
        return courtFeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public CPCourtFeeEntity getEffectiveFee(String arenaId) {
        return courtFeeMapper.selectEffective(arenaId);
    }

    /**
     * 查询场地费用标准
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPCourtFeeListVO> listCPCourtFee(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPCourtFeeListVO> p = new Page<>(page, pageSize);
        List<CPCourtFeeListVO> cpCourtFeeList = courtFeeMapper.listCPArenaFee(p, parameters);
        for (CPCourtFeeListVO courtFeeListVO : cpCourtFeeList) {
            if (courtFeeListVO.getEndTime() != null && courtFeeListVO.getEndTime().before(new Date())) {
                courtFeeListVO.setEffectived(CPConstants.EXPIRED);
            } else if ((courtFeeListVO.getBeginTime().before(new Date())
                    || courtFeeListVO.getBeginTime().equals(new Date()))
                    && (courtFeeListVO.getEndTime() == null || courtFeeListVO.getEndTime().after(new Date()))) {
                courtFeeListVO.setEffectived(CPConstants.EFFECTIVING);
            } else if (courtFeeListVO.getBeginTime().after(new Date())) {
                courtFeeListVO.setEffectived(CPConstants.TO_BE_EFFECTIVED);
            }
        }
        p.setRecords(cpCourtFeeList);
        return p;
    }

    /**
     * 保存场地费用标准
     *
     * @param courtFee
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public ResponseDTO saveCPCourtFee(CPCourtFeeEntity courtFee) {
        ResponseDTO responseDTO;
        if (StringUtils.isBlank(courtFee.getId())) {
            courtFee.setId(IdUtil.fastSimpleUUID());
            CPCourtFeeEntity cpArenaFeeInDb = courtFeeMapper.selectLast(courtFee.getCourtId());
            if (null == cpArenaFeeInDb) {
                courtFee.setState(CPConstants.INFO_VALID);
                courtFeeMapper.insertSelective(courtFee);
                responseDTO = WebUtils.createSuccessResponse("");
            } else {
                if (cpArenaFeeInDb.getBeginTime().after(courtFee.getBeginTime()) || cpArenaFeeInDb.getBeginTime() == courtFee.getBeginTime()) {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "新标准生效时间应晚于旧生效的启用时间");
                } else {
                    // 设置旧标准的失效时间
                    cpArenaFeeInDb.setEndTime(DateUtil.offsetDay(courtFee.getBeginTime(), -1));
                    courtFeeMapper.updateByPrimaryKeySelective(cpArenaFeeInDb);
                    // 保存新标准
                    courtFee.setState(CPConstants.INFO_VALID);
                    courtFeeMapper.insertSelective(courtFee);
                    responseDTO = WebUtils.createSuccessResponse("");
                }
            }
        } else {
            courtFeeMapper.updateByPrimaryKeySelective(courtFee);
            responseDTO = WebUtils.createSuccessResponse("");
        }
        return responseDTO;
    }

    /**
     * 通过id删除场地费用标准
     *
     * @param id 场地费用标准id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeCPCourtFeeById(String id, String modifier) {
        Map<String, Object> paramMap = new HashMap<>();
        CPCourtFeeVO cpCourtFeeVO = courtFeeMapper.selectByPrimaryKey(id);
        int result;
        paramMap.put("modifier", modifier);
        paramMap.put("id", id);
        result = courtFeeMapper.deleteCPArenaFeeById(paramMap);
        if (result > 0) {
            CPCourtFeeEntity courtFeeLast = courtFeeMapper.selectLast(cpCourtFeeVO.getCourtId());
            if (null != courtFeeLast) {
                paramMap.put("id", courtFeeLast.getId());
                courtFeeMapper.removeEndTime(paramMap);
            }
        }
        return result;
    }

}
