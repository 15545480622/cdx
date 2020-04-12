package com.inspur.vista.labor.cp.dao.activity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.activity.CpActivityBaseEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivitySpecifyOrganEntity;

import java.util.List;
import java.util.Map;

/**
 * 投票报名活动
 *
 * @author
 * @email
 * @date 2020-03-18 14:28:24
 */
public interface CpActivityBaseDao extends BaseMapper<CpActivityBaseEntity> {

    List<Map> queryPage(Page<Map> p, Map<String, Object> params);

    List<Map> selectListForApp(Page<Map> p, Map params);

    List<Map> selectMyListForApp(Page<Map> p, Map params);

    List<Map> scanJoinActivityList(Page<Map> p, Map params);

    List<Map> groupJoinList(Page<Map> p, Map params);

    void insertSpecifyOrgan(List<CpActivitySpecifyOrganEntity> list);

    void deleteSpecifyByActivityId(Integer id);

    List<String> selectSpecifyOrganIdList(Integer id);

    List<CpActivitySpecifyOrganEntity> selectSpecifyOrganList(Integer activityId);

    List<CpActivitySpecifyOrganEntity> querySpecifyOrgansByActivityId(Integer id);

    Map selectByActivityId(String id);
}
