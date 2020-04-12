package com.inspur.vista.labor.cp.dao;


import com.inspur.vista.labor.cp.entity.CPReserveSceneEntity;
import com.inspur.vista.labor.cp.entity.CPReserveSceneListVO;
import com.inspur.vista.labor.cp.entity.CPReserveSceneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPReserveSceneMapper
 * @Description: 预约场次记录
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
public interface CPReserveSceneMapper {


    /**
     * 通过场次时间和场地id查询
     *
     * @param paramMap
     * @return
     */
    List<CPReserveSceneListVO> selectByTimeAndCourt(Map paramMap);

    /**
     * 通过场次和用户查询，判断用户是否预约过这个场次
     * @param paramMap
     * @return
     */
    CPReserveSceneVO selectByTimeAndUser(Map paramMap);

    /**
     * 根据预约记录id获取预约的场次基本信息
     *
     * @param reserveId
     * @return
     */
    List<CPReserveSceneEntity> listReserveSceneByReserveId(String reserveId);

    /**
     * 根据预约记录id获取预约的场次详细信息
     *
     * @param reserveId
     * @return
     */
    List<CPReserveSceneVO> listByReserveId(String reserveId);

    /**
     * 通过时间段获取预约场次记录
     *
     * @param paramMap
     * @return
     */
    List<CPReserveSceneListVO> listByBeginDateAndEndDate(Map paramMap);

    /**
     * 批量新增预约场次记录
     *
     * @param dataList
     * @return
     */
    int batchInsert(@Param("dataList") List<CPReserveSceneEntity> dataList);

}



