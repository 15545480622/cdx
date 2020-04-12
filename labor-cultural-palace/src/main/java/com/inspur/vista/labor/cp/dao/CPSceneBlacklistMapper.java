package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPSceneBlacklistEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPSceneBlacklistMapper
 * @Description: 场次黑名单
 * @Author: gengpeng
 * @CreateDate: 2020/3/16 15:44
 * @Version: 1.0
 */
public interface CPSceneBlacklistMapper {

    /**
     * 查询黑名单记录
     *
     * @param parameters
     * @return
     */
    List<CPSceneBlacklistEntity> listSceneBlacklist(Map<String, Object> parameters);

    /**
     * 批量插入黑名单记录
     *
     * @param dataList
     * @return
     */
    int batchInsert(@Param("dataList") List<CPSceneBlacklistEntity> dataList);


    /**
     * 单个插入
     *
     * @param sceneBlacklistEntity
     * @return
     */
    int insertSelective(CPSceneBlacklistEntity sceneBlacklistEntity);

    /**
     * 删除场次黑名单记录
     *
     * @return
     */
    int deleteSceneBlacklist(Map paramMap);

    /**
     * 删除场地黑名单记录
     * @param paramMap
     * @return
     */
    int batchDeleteSceneBlacklist(Map paramMap);
}
