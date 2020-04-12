package com.inspur.vista.labor.cp.dao;


import com.inspur.vista.labor.cp.entity.PubFilesEntity;
import com.inspur.vista.labor.cp.entity.PubFilesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Title: PubFilesMapper
 * @Description: 附件
 * @Author: liuzq
 * @CreateDate: 2019/12/11 15:04
 * @Version: 1.0
 */
public interface PubFilesMapper {

    /**
     * 获取附件
     *
     * @param id
     * @return
     */
    PubFilesVO selectByPrimaryKey(String id);

    /**
     * 查询附件
     *
     * @param parameters
     * @return
     */
    List<PubFilesVO> listPubFiles(Map<String, Object> parameters);

    /**
     * 查询附件
     *
     * @param ids
     * @return
     */
    List<PubFilesVO> listByIds(@Param("ids") String... ids);

    /**
     * 新增附件
     *
     * @param pubFiles
     * @return
     */
    int insertSelective(PubFilesEntity pubFiles);

    /**
     * 通过业务id及业务类型删除附件
     *
     * @param bsnId
     * @param type
     * @return
     */
    int deleteFilesByBsnIdAndType(@Param("bsnId") String bsnId, @Param("type") String type, @Param("modifier") String modifier);

    /**
     * 通过业务id删除附件
     *
     * @param bsnId
     * @return
     */
    int deleteFilesByBsnId(@Param("bsnId") String bsnId, @Param("modifier") String modifier);

    /**
     * 通过id删除附件
     *
     * @param paramMap modifier:修改人; id:附件id
     * @return
     */
    int deletePubFilesById(Map paramMap);

    /**
     * 通过ids批量删除附件
     *
     * @param paramMap modifier:修改人; id:附件id的字符串数组
     * @return
     */
    int batchDeletePubFilesById(Map paramMap);

    /**
     * 批量新增附件信息
     *
     * @param dataList
     * @return
     */
    int batchInsert(@Param("dataList") List<PubFilesEntity> dataList);

    /**
     * 批量更新附件
     *
     * @param ids
     * @return
     */
    int batchUpdate(@Param("bsnId") String bsnId, @Param("ids") String... ids);
}



