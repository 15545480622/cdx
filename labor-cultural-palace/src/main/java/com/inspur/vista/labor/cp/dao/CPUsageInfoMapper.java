package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPUsageInfoEntity;
import com.inspur.vista.labor.cp.entity.CPUsageInfoListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPUsageInfoServiceMapper
 * @Description: 场所使用记录
 * @authur: wangxueying01
 * @CreatDate: 2020/3/13 10:16
 */
public interface CPUsageInfoMapper {

    /**
     * 新增场所使用记录
     * @param cpUsageInfoEntity
     * @return
     */
    int insertSelective(CPUsageInfoEntity cpUsageInfoEntity);

    /**
     * 查询场所使用记录
     * @param parameters
     * @return
     */
    List<CPUsageInfoListVO> listCPUsageInfo(Map<String,Object> parameters);
}