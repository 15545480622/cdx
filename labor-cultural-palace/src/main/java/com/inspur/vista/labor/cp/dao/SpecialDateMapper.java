package com.inspur.vista.labor.cp.dao;

import java.util.List;

/**
 * @Title: SpecialDateMapper
 * @Description: 特殊日期
 * @Author: gengpeng
 * @CreateDate: 2019/10/12 13:51
 * @Version: 1.0
 */
public interface SpecialDateMapper {

    /**
     * 根据类型获取日期列表
     * @param dateType 日期类型
     * @return List
     */
    List<String> listByDateType(int dateType);
}
