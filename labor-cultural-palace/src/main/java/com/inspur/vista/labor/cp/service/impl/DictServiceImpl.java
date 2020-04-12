package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.DictMapper;
import com.inspur.vista.labor.cp.entity.DictItemVO;
import com.inspur.vista.labor.cp.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: DictServiceImpl
 * @Description: 字典服务类
 * @Author: gengpeng
 * @CreateDate: 2019/12/6 11:16
 * @Version: 1.0
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    /**
     * 查询字典项
     *
     * @param dictCodes 字典编码列表
     * @return
     */
    @Override
    public List<DictItemVO> listDictItem(List<String> dictCodes) {
        List<DictItemVO> allDictItemList = new ArrayList<>();
        for (String dictCode : dictCodes) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dictCode", dictCode);
            List<DictItemVO> dictItemList = dictMapper.listDictItem(parameters);
            allDictItemList.addAll(dictItemList);
        }
        return allDictItemList;
    }

    /**
     * 获取字典项
     *
     * @param itemCode
     * @param dictCode
     * @return
     */
    @Override
    public DictItemVO getDictItem(String itemCode, String dictCode) {
        return dictMapper.getDictItem(itemCode, dictCode);
    }
}
