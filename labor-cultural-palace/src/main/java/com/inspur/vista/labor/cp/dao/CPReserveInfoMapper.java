package com.inspur.vista.labor.cp.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPReserveInfoEntity;
import com.inspur.vista.labor.cp.entity.CPReserveInfoListVO;
import com.inspur.vista.labor.cp.entity.CPReserveInfoVO;

import java.util.List;
import java.util.Map;


/**
 * @Title: CPReserveInfoMapper
 * @Description: 场地预约记录
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
public interface CPReserveInfoMapper {

    /**
     * 获取场地预约记录
     *
     * @param id
     * @return
     */
    CPReserveInfoVO selectByPrimaryKey(String id);

    /**
     * 查询场地预约记录
     *
     * @param parameters
     * @return
     */
    List<CPReserveInfoListVO> listCPReserveInfo(Page page, Map<String, Object> parameters);

    /**
     * 查询某一场次预约记录
     *
     * @param parameters
     * @return
     */
    List<CPReserveInfoListVO> listCPReserveInfoByScene(Page page, Map<String, Object> parameters);

    /**
     * 新增场地预约记录
     *
     * @param cpReserveInfo
     * @return
     */
    int insertSelective(CPReserveInfoEntity cpReserveInfo);

    /**
     * 更新场地预约记录
     *
     * @param cpReserveInfo
     * @return
     */
    int updateByPrimaryKeySelective(CPReserveInfoEntity cpReserveInfo);

    /**
     * 通过订单编号查询id
     *
     * @param outTradeNo
     * @return
     */
    String selectIdByOutTradeNo(String outTradeNo);

    /**
     * 通过id删除场地预约记录
     *
     * @param paramMap modifier:修改人; id:场地预约记录id
     * @return
     */
    int deleteCPReserveInfoById(Map paramMap);

    /**
     * 通过ids批量删除场地预约记录
     *
     * @param paramMap modifier:修改人; id:场地预约记录id的字符串数组
     * @return
     */
    int batchDeleteCPReserveInfoById(Map paramMap);



}



