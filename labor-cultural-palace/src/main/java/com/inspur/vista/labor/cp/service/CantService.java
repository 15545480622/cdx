package com.inspur.vista.labor.cp.service;


import com.inspur.vista.labor.cp.entity.CantVO;

import java.util.List;

/**
 * @Title: CantService
 * @Description: 行政区划服务类
 * @Author: gengpeng
 * @CreateDate: 2019/9/20 18:48
 * @Version: 1.0
 */
public interface CantService {

    /**
     * 获取下级区划
     *
     * @param superCode
     * @return
     */
    List<CantVO> selectDirectCant(String superCode);

    /**
     * 根据区划名称获取区划编码
     *
     * @param cantName
     * @return
     */
    String selectCantCodeByCodeName(String cantName);
}
