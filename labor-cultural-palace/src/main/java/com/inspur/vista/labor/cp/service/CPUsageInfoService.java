package com.inspur.vista.labor.cp.service;

import com.inspur.vista.labor.cp.entity.CPUsageInfoEntity;
import com.inspur.vista.labor.cp.entity.CPUsageInfoListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPUsageInfoService
 * @Description: 场所使用记录服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/3/13 10:15
 */
public interface CPUsageInfoService {

    /**
     * 查询场所使用记录
     * @param parameters
     * @return
     */
    List<CPUsageInfoListVO> listCPUsageInfo(Map<String, Object> parameters);

    /**
     * 新增场所使用记录
     *
     * @param cpUsageInfoEntity
     * @return
     */
    CPUsageInfoEntity saveCPUsageInfo(CPUsageInfoEntity cpUsageInfoEntity);

    /**
     * 使用场地
     *
     * @param userCode
     * @param reserveId
     */
    void useCourt(String userCode, String... reserveId);
}