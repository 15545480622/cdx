package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.inspur.vista.labor.cp.entity.activity.CpActivityJoinRecordsEntity;

import java.util.List;
import java.util.Map;

/**
 * 报名参与记录表
 *
 * @author
 * @email
 * @date 2020-03-18 14:28:24
 */
public interface CpActivityJoinRecordsService extends IService<CpActivityJoinRecordsEntity> {

    /**
     * 活动报名
     * @param entity
     */
    void join(CpActivityJoinRecordsEntity entity);

    /**
     * 报名参与记录（分页)
     * @param params
     * @return
     */
    Page selectPageByActivityId(Map params);

    /**
     * 团体报名
     * @param list
     */
    List<String> groupJoin(List<CpActivityJoinRecordsEntity> list);

    /**
     * 小程序扫码
     * @param params
     */
    Map<String, Object> scan(Map params);

    /**
     * 确认扫码
     * @param params
     */
    void confirmScan(Map params);

    /**
     * 获取小程序扫码报名活动详情列表
     * @param params
     * @return
     */
    Page<Map> scanJoinDetailsActivityList(Map params);

    /**
     * 检查二维码是否已被使用
     * @param params
     * @return
     */
    Integer isUse(Map params);

    /**
     * 报名活动app扫码到场信息确认
     * @param params
     * @return
     */
    Map appScan(Map params);

    /**
     * 报名活动app扫码到场
     */
    void appConfirmScan(Map params);
}
