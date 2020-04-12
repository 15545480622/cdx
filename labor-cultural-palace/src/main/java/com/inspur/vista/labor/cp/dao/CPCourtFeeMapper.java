package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPCourtFeeEntity;
import com.inspur.vista.labor.cp.entity.CPCourtFeeListVO;
import com.inspur.vista.labor.cp.entity.CPCourtFeeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPArenaFeeMapper
 * @Description: 场地费用标准
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
public interface CPCourtFeeMapper {

    /**
     * 获取场地费用标准
     *
     * @param id
     * @return
     */
    CPCourtFeeVO selectByPrimaryKey(String id);


    /**
     * 根据场地id和日期获取场地费用标准
     *
     * @param courtId
     * @param date
     * @return
     */
    CPCourtFeeVO selectByCourtIdAndDate(@Param("courtId") String courtId,@Param("date") String date);

    /**
     * 查询场地费用标准
     *
     * @param parameters
     * @return
     */
    List<CPCourtFeeListVO> listCPArenaFee(Page page, Map<String, Object> parameters);

    /**
     * 新增场地费用标准
     *
     * @param cpArenaFee
     * @return
     */
    int insertSelective(CPCourtFeeEntity cpArenaFee);

    /**
     * 更新场地费用标准
     *
     * @param cpArenaFee
     * @return
     */
    int updateByPrimaryKeySelective(CPCourtFeeEntity cpArenaFee);

    /**
     * 通过id删除场地费用标准
     *
     * @param paramMap modifier:修改人; id:场地费用标准id
     * @return
     */
    int deleteCPArenaFeeById(Map paramMap);

    /**
     * 获取场地最后的标准
     *
     * @param courtId
     * @return
     */
    CPCourtFeeEntity selectLast(String courtId);

    /**
     * 获取场地生效中的费用
     *
     * @param courtId
     * @return
     */
    CPCourtFeeEntity selectEffective(String courtId);

    /**
     * 获取场地待生效的费用
     *
     * @param courtId
     * @return
     */
    CPCourtFeeEntity selectToBeEffective(String courtId);

    /**
     * 清空失效时间
     *
     * @param paramMap
     * @return
     */
    int removeEndTime(Map paramMap);
}



