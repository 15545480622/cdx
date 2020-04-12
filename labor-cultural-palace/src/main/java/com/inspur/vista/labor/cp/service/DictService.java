package com.inspur.vista.labor.cp.service;

import com.inspur.vista.labor.cp.entity.DictItemVO;

import java.util.List;

/**
 * @Title: DictService
 * @Description: 字典服务类
 * @Author: gengpeng
 * @CreateDate: 2019/12/6 11:16
 * @Version: 1.0
 */
public interface DictService {

    /**
     * 查询字典项
     *
     * @param dictCodes 字典编码数组
     * @return
     */
    List<DictItemVO> listDictItem(List<String> dictCodes);

    /**
     * 获取字典项
     *
     * @param itemCode
     * @param dictCode
     * @return
     */
    DictItemVO getDictItem(String itemCode, String dictCode);
}
