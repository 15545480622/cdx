package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPCourtTalentEntity;
import com.inspur.vista.labor.cp.entity.CPCourtTalentListVO;

import java.util.Map;

/**
 * @ClassName: CPCourtTalentService
 * @Description: 场地专业人才关系服务类
 * @authur: wangxueying01
 * @CreatDate: 2020/4/2 17:33
 */
public interface CPCourtTalentService {

    /**
     * 关联专业人才
     *
     * @param cpCourtTalentEntity
     * @return
     */
    CPCourtTalentEntity saveCourtTalent(CPCourtTalentEntity cpCourtTalentEntity);

    /**
     * 取消关联专业人才
     *
     * @param paramMap
     * @return
     */
    int cancelCourtTalent(Map<String, Object> paramMap);

    /**
     * 查询专业人才及其关联状态
     *
     * @param paramMap
     * @return
     */
    Page<CPCourtTalentListVO> listCourtTalent(Map<String, Object> paramMap);
}