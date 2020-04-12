package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAccountEntity;
import com.inspur.vista.labor.cp.entity.CPAccountVO;

import java.util.Map;

/**
 * @Title: CPAccountService
 * @Description: 文化宫收款账号服务类
 * @Author: gengpeng
 * @CreateDate: 2019/12/6 16:59
 * @Version: 1.0
 */
public interface CPAccountService {

    /**
     * 获取有效的账号
     *
     * @param cpId
     * @param type
     * @return
     */
    CPAccountVO getActiveAccount(String cpId, int type);

    /**
     * 加载账号列表
     *
     * @return
     */
    Page<CPAccountVO> listAccount(Map<String, Object> param);

    /**
     * 保存文化宫收款账号
     *
     * @param cpAccountEntity
     */
    void save(CPAccountEntity cpAccountEntity);

}
