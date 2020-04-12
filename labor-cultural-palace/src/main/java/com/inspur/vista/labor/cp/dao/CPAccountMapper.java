package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAccountEntity;
import com.inspur.vista.labor.cp.entity.CPAccountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPAccountDao
 * @Description: 文化宫收款账号
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 18:38
 * @Version: 1.0
 */
public interface CPAccountMapper {

    /**
     * 通过文化宫id获取账号列表
     *
     * @return List
     */
    List<CPAccountVO> listAccount(Page page, Map<String, Object> parameters);

    /**
     * 获取文化宫可用的支付类型
     *
     * @param cpId
     * @return
     */
    String getAvailablePayWay(String cpId);

    /**
     * 通过账号类型获取文化宫收款账号
     *
     * @param cpId
     * @param type
     * @return
     */
    CPAccountEntity selectLastAccountByType(@Param("cpId") String cpId, @Param("type") int type);

    /**
     * 获取生效中的账号
     *
     * @param cpId
     * @param type
     * @return
     */
    CPAccountEntity selectEffectiveAccountByType(@Param("cpId") String cpId, @Param("type") int type);

    /**
     * 新增文化宫收款账号
     *
     * @param cpAccountEntity
     * @return
     */
    int insertSelective(CPAccountEntity cpAccountEntity);

    /**
     * 更新文化宫收款账号失效时间
     *
     * @param cpAccountEntity
     * @return
     */
    int updateFailureTime(CPAccountEntity cpAccountEntity);
}
