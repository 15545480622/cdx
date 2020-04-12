package com.inspur.vista.labor.cp.dao;


import com.inspur.vista.labor.cp.entity.CPCourtInfoEntity;
import com.inspur.vista.labor.cp.entity.CPCourtInfoListVO;
import com.inspur.vista.labor.cp.entity.CPCourtInfoVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPCourtInfoMapper
 * @Description: 场地信息
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
public interface CPCourtInfoMapper {

    /**
     * 获取场地信息
     *
     * @param id
     * @return
     */
    CPCourtInfoVO selectByPrimaryKey(String id);

    /**
     * 查询场地信息
     *
     * @param parameters
     * @return
     */
    List<CPCourtInfoListVO> listCPCourtInfo(Map<String, Object> parameters);

    /**
     * app端查询允许预约的场地信息
     *
     * @param parameters
     * @return
     */
    List<CPCourtInfoListVO> listCanReserveCPCourtInfo(Map<String, Object> parameters);

    /**
     * 新增场地信息
     *
     * @param cpCourtInfo
     * @return
     */
    int insertSelective(CPCourtInfoEntity cpCourtInfo);

    /**
     * 更新场地信息
     *
     * @param cpCourtInfo
     * @return
     */
    int updateByPrimaryKeySelective(CPCourtInfoEntity cpCourtInfo);

    /**
     * 通过id删除场地信息
     *
     * @param paramMap modifier:修改人; id:场地信息id
     * @return
     */
    int deleteCPCourtInfoById(Map paramMap);

    /**
     * 通过ids批量删除场地信息
     *
     * @param paramMap modifier:修改人; id:场地信息id的字符串数组
     * @return
     */
    int batchDeleteCPCourtInfoById(Map paramMap);

    /**
     * 修改场地启用状态
     *
     * @param paramMap
     * @return
     */
    int enableCourt(Map paramMap);

    /**
     * 通过场地id获取所在文化宫id
     *
     * @param courtId
     * @return
     */
    String getCpIdByCourtId(String courtId);
}



