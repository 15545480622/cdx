package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPApplyFileEntity;
import com.inspur.vista.labor.cp.entity.CPApplyFileVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: CPApplyInfoMapper
 * @Description: 申请表相关的文件
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 14:56
 * @Version: 1.0
 */
public interface CPApplyFileMapper {

    /**
     * 批量新增申请表附带的附件
     *
     * @param dataList
     * @return
     */
    int batchInsert(@Param("dataList") List<CPApplyFileEntity> dataList);

    /**
     * 批量更新附件
     *
     * @param ids
     * @return
     */
    int batchUpdate(@Param("applyId") String applyId, @Param("ids") String... ids);

    /**
     * 获取申请表的附件
     *
     * @param applyId
     * @return
     */
    List<CPApplyFileVO> listApplyFile(String applyId);

    /**
     * 通过id删除附件
     *
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 通过申请表id删除附件
     *
     * @param applyId
     * @return
     */
    int deleteByApplyId(String applyId);
}
