package com.inspur.vista.labor.cp.dao;

import com.inspur.vista.labor.cp.entity.CPCodeRuleEntity;

/**
 * @Title: CPCodeRuleMapper
 * @Description: 文化宫编码生成规则
 * @Author: gengpeng
 * @CreateDate: 2020/4/7 13:38
 * @Version: 1.0
 */
public interface CPCodeRuleMapper {

    /**
     * 通过区划编码获取规则
     *
     * @param cantCode
     * @return
     */
    CPCodeRuleEntity selectByPrimaryKey(String cantCode);

    /**
     * 更新当前值
     *
     * @param cantCode
     * @return
     */
    int updateCurrentValue(String cantCode);
}
