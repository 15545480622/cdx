package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPCourtTalentEntity;
import com.inspur.vista.labor.cp.entity.CPCourtTalentListVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPCourtTalentMapper
 * @Description: 场地专业人才关系
 * @authur: wangxueying01
 * @CreatDate: 2020/4/2 17:34
 */
public interface CPCourtTalentMapper {

    /**
     * 关联专业人才
     *
     * @param cpCourtTalentEntity
     * @return
     */
    int insertCourtTalent(CPCourtTalentEntity cpCourtTalentEntity);

    /**
     * 取消关联专业人才
     *
     * @param paramMap
     * @return
     */
    int deleteCourtTalent(Map<String, Object> paramMap);

    /**
     * 查询专业人才及其关联状态
     *
     * @param paramMap
     * @return
     */
    List<CPCourtTalentListVO> listCourtTalent(Page page, Map<String, Object> paramMap);

}