package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.DictItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Title: DictMapper
 * @Description: 字典
 * @Author: gengpeng
 * @CreateDate: 2019/12/6 10:34
 * @Version: 1.0
 */
public interface DictMapper {

    /**
     * 查询字典项
     *
     * @param parameters
     * @return
     */
    List<DictItemVO> listDictItem(Map<String, Object> parameters);

    /**
     * 获取字典项
     *
     * @param itemCode
     * @param dictCode
     * @return
     */
    DictItemVO getDictItem(@Param("itemCode") String itemCode, @Param("dict_code") String dictCode);
}
