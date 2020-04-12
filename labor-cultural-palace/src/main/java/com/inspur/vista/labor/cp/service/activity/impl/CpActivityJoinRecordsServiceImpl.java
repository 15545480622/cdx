package com.inspur.vista.labor.cp.service.activity.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.labor.cp.dao.CPPlaceInfoMapper;
import com.inspur.vista.labor.cp.dao.activity.CheckEntity;
import com.inspur.vista.labor.cp.dao.activity.CpActivityJoinRecordsDao;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoVO;
import com.inspur.vista.labor.cp.entity.activity.CpActivityBaseEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityJoinRecordsEntity;
import com.inspur.vista.labor.cp.exception.RRException;
import com.inspur.vista.labor.cp.service.activity.CpActivityBaseService;
import com.inspur.vista.labor.cp.service.activity.CpActivityJoinRecordsService;
import com.inspur.vista.labor.cp.util.CPUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service("cpActivityJoinRecordsService")
public class CpActivityJoinRecordsServiceImpl extends ServiceImpl<CpActivityJoinRecordsDao, CpActivityJoinRecordsEntity> implements CpActivityJoinRecordsService {

    @Autowired
    private CpActivityBaseService cpActivityBaseService;
    @Autowired
    private CpActivityJoinRecordsService cpActivityJoinRecordsService;
    @Autowired
    private CPPlaceInfoMapper cpPlaceInfoMapper;

    /**
     * 活动报名
     * @param entity
     */
    @Override
    public void join(CpActivityJoinRecordsEntity entity) {
        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setActivityId(entity.getActivityId());
        checkEntity.setUserId(entity.getUserId());
        checkEntity.setLaborId(entity.getLaborId());
        //判断用户是否满足参与活动条件
        cpActivityBaseService.isMeet(checkEntity);
        EntityWrapper<CpActivityJoinRecordsEntity> recordsCount = new EntityWrapper<>();
        recordsCount.eq("user_id", entity.getUserId());
        recordsCount.eq("activity_id", entity.getActivityId());
        int joinCount = cpActivityJoinRecordsService.selectCount(recordsCount);
        if (joinCount > 0) {
            throw new RRException("不可重复参与");
        }
        //获取活动信息
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(entity.getActivityId());
        if (activity.getCountLimit() != null && activity.getCountLimit() > 0) {
            recordsCount = new EntityWrapper<>();
            recordsCount.eq("activity_id", activity.getId());
            int count = cpActivityJoinRecordsService.selectCount(recordsCount);
            if (count >= activity.getCountLimit()) {
                throw new RRException("活动参与人数已满");
            }
        }
        baseMapper.insert(entity);
    }

    /**
     * 报名参与记录（分页)
     * @param params
     * @return
     */
    @Override
    public Page selectPageByActivityId(Map params) {
        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));

        Page<CpActivityJoinRecordsEntity> p = new Page<>(page, pageSize);
        List<CpActivityJoinRecordsEntity> list = baseMapper.selectPageByActivityId(p, params);
        p.setRecords(list);

        return p;
    }

    /**
     * 团体报名
     * @param list
     * @return 重复报名者userName
     */
    @Override
    public List<String> groupJoin(List<CpActivityJoinRecordsEntity> list) {

        List<String> alreadyList = new ArrayList<>();
        if (list == null || list.size()==0) {
            return alreadyList;
        }
        CpActivityJoinRecordsEntity record = list.get(0);
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(record.getActivityId());
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        //校验活动条件是否满足
        if (!Integer.valueOf(1).equals(activity.getIsGroup())) {
            throw new RRException("该活动非团体活动");
        }
        //查看是否有操作者id
        String operatorId = activity.getOperatorId();
        if (StringUtils.isBlank(operatorId)) {
            throw new RRException("未获取到操作者id");
        }
        //查看工会是否匹配
        if (record.getLaborId() == null) {
            throw new RRException("未获取到会员工会id");
        }
        //获取指定工会
        List<String> specifyOrganIdList = cpActivityBaseService.selectSpecifyOrganIdList(activity.getId());
        //判断活动是否指定工会
        if (specifyOrganIdList == null ||
            specifyOrganIdList.size() == 0 ||
            !specifyOrganIdList.contains(record.getLaborId())) {

            throw new RRException("团体活动工会不匹配");
        }

        //校验活动时间
        Date date = new Date();
        if (date.after(activity.getEndTime())) {
            throw new RRException("活动已结束");
        }
        if (date.before(activity.getStartTime())) {
            throw new RRException("活动未开始");
        }
        //判断人数限制
        if (!Integer.valueOf(0).equals(activity.getCountLimit())) {
            //获取当前活动已参与人数
            EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("activity_id", activity.getId());
            int recordCount = cpActivityJoinRecordsService.selectCount(recordWrapper);
            if (recordCount + list.size() > activity.getCountLimit()) {
                throw new RRException("参与人数超过活动限制人数");
            }
        }
        //检查会员是否已报名
        List<CpActivityJoinRecordsEntity> insertList = new ArrayList<>();

        list.forEach(l -> {
            EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("user_id", l.getUserId());
            recordWrapper.eq("activity_id", l.getActivityId());
            Integer count = baseMapper.selectCount(recordWrapper);
            if (count != null && count > 0) {
                alreadyList.add(l.getUserName());
            } else {
                insertList.add(l);
            }

        });
        if (insertList.size() > 0) {
            this.insertBatch(insertList);
        }
        return alreadyList;
    }

    /**
     * 小程序扫码
     *
     * @param params
     */
    @Override
    public Map<String, Object> scan(Map params) {

        Map<String, Object> result = new HashMap<>();

        String userId = params.get("userId") == null ? "" : Objects.toString(params.get("userId"));
        String activityId = params.get("activityId") == null ? "" : Objects.toString(params.get("activityId"));
        String cpId = params.get("cpId") == null ? "" : Objects.toString(params.get("cpId"));

        //获取活动
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(activityId);
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = activity.getStartTime() == null ? "" : format.format(activity.getStartTime());
        String endTime = activity.getEndTime() == null ? "" : format.format(activity.getEndTime());

        result.put("activityTitle", activity.getTitle());
        result.put("activityStartTime", startTime);
        result.put("activityEndTime", endTime);
        //获取场所信息
        CPPlaceInfoVO cpPlaceInfoVO = cpPlaceInfoMapper.selectByPrimaryKey(activity.getPlaceId());
        if (cpPlaceInfoVO != null) {
            result.put("placeName", cpPlaceInfoVO.getName());
        }
        //判断文化宫是否匹配
        if (!cpId.equals(activity.getCpId())) {
            throw new RRException("扫码者文化宫id与活动文化宫id不匹配");
        }
        //报名活动
        if ("join".equals(activity.getType())) {
            //获取活动参与记录
            EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("user_id", userId);
            recordWrapper.eq("activity_id", activityId);
            CpActivityJoinRecordsEntity recordEntity = cpActivityJoinRecordsService.selectOne(recordWrapper);
            if (recordEntity == null) {
                throw new RRException("未获取到参与记录");
            }
            if (Integer.valueOf(1).equals(recordEntity.getIsUse())) {
                throw new RRException("二维码已被使用");
            }
            result.put("userName", recordEntity.getUserName());
            result.put("userLaborName", recordEntity.getLaborName());
            //返回用户信息让操作者确认
            return result;
        }
        throw new RRException("不支持的活动类型");
    }

    /**
     * 确认扫码
     *
     * @param params
     */
    @Override
    public void confirmScan(Map params) {
        String userId = params.get("userId") == null ? "" : Objects.toString(params.get("userId"));
        String activityId = params.get("activityId") == null ? "" : Objects.toString(params.get("activityId"));
        String scanUserId = params.get("scanUserId") == null ? "" : Objects.toString(params.get("scanUserId"));
        String cpId = params.get("cpId") == null ? "" : Objects.toString(params.get("cpId"));

        //获取活动
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(activityId);
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        //判断文化宫是否匹配
        if (!cpId.equals(activity.getCpId())) {
            throw new RRException("扫码者文化宫id与活动文化宫id不匹配");
        }
        //报名活动
        if ("join".equals(activity.getType())) {
            //获取活动参与记录
            EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("user_id", userId);
            recordWrapper.eq("activity_id", activityId);
            CpActivityJoinRecordsEntity recordEntity = cpActivityJoinRecordsService.selectOne(recordWrapper);
            if (recordEntity == null) {
                throw new RRException("未获取到参与记录");
            }
            if (Integer.valueOf(1).equals(recordEntity.getIsUse())) {
                throw new RRException("二维码已被使用");
            }
            Integer recordId = recordEntity.getId();
            //执行修改操作
            recordEntity = new CpActivityJoinRecordsEntity();
            recordEntity.setId(recordId);
            recordEntity.setIsUse(1);
            recordEntity.setScanTime(new Date());
            recordEntity.setScanUserId(scanUserId);
            baseMapper.updateById(recordEntity);
            return;
        }
        throw new RRException("不支持的活动类型");
    }

    /**
     * 获取小程序扫码报名活动详情列表
     *
     * @param params
     * @return
     */
    @Override
    public Page<Map> scanJoinDetailsActivityList(Map params) {
        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.scanJoinDetailsActivityList(p, params);
        p.setRecords(list);
        return p;
    }

    /**
     * 检查二维码是否已被使用
     *
     * @param params
     * @return
     */
    @Override
    public Integer isUse(Map params) {
        String userId = params.get("userId") == null ? "" : Objects.toString(params.get("userId"));
        String activityId = params.get("activityId") == null ? "" : Objects.toString(params.get("activityId"));

        CpActivityBaseEntity activity = cpActivityBaseService.selectById(activityId);
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        if ("join".equals(activity.getType())) {
            //获取参与记录
            EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
            recordWrapper.eq("user_id", userId);
            recordWrapper.eq("activity_id", activityId);
            CpActivityJoinRecordsEntity recordEntity = cpActivityJoinRecordsService.selectOne(recordWrapper);
            if (recordEntity == null) {
                throw new RRException("未获取到参与记录");
            }
            return recordEntity.getIsUse();
        }
        throw new RRException("不支持的活动类型");
    }

    /**
     * 报名活动app扫码到场信息确认
     */
    @Override
    public Map appScan(Map params) {
        String userCode = (String) params.get("userCode");
        String param = (String) params.get("param");
        Map<String, Object> map = CPUtils.decryptQRCodeParam(param);
        if (map.get("activityId") == null) {
            throw new RRException("未解密到活动id");
        }
        String activityId = String.valueOf(map.get("activityId"));
        //获取活动信息
        CpActivityBaseEntity cpActivityBaseEntity = cpActivityBaseService.selectById(activityId);
        if (cpActivityBaseEntity == null) {
            throw new RRException("未获取到活动信息，活动可能已被删除");
        }
        //获取参与记录
        EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
        recordWrapper.eq("activity_id", activityId);
        recordWrapper.eq("user_id", userCode);
        CpActivityJoinRecordsEntity entity = cpActivityJoinRecordsService.selectOne(recordWrapper);
        if (entity == null) {
            throw new RRException("未获取到活动参与记录");
        }
        Map info = new HashMap<>();
        info.put("title", cpActivityBaseEntity.getTitle());
        info.put("userName", entity.getUserName());
        info.put("userPhone", entity.getUserPhone());
        info.put("recordId", entity.getId());
        return info;
    }

    /**
     * 报名活动app扫码到场
     *
     */
    @Override
    public void appConfirmScan(Map params) {
        String userCode = (String) params.get("userCode");
        String param = (String) params.get("param");
        Map<String, Object> map = CPUtils.decryptQRCodeParam(param);
        if (map.get("activityId") == null) {
            throw new RRException("未解密到活动id");
        }
        String activityId = String.valueOf(map.get("activityId"));
        //获取活动信息
        CpActivityBaseEntity activity = cpActivityBaseService.selectById(activityId);
        if (activity == null) {
            throw new RRException("未获取到活动信息，活动可能已被删除");
        }
        //校验活动时间
        Date date = new Date();
        if (date.after(activity.getEndTime())) {
            throw new RRException("活动已结束");
        }
        if (date.before(activity.getStartTime())) {
            throw new RRException("活动未开始");
        }
        //获取参与记录
        EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
        recordWrapper.eq("activity_id", activityId);
        recordWrapper.eq("user_id", userCode);
        CpActivityJoinRecordsEntity entity = cpActivityJoinRecordsService.selectOne(recordWrapper);
        if (entity == null) {
            throw new RRException("未获取到活动参与记录");
        }
        if (entity.getIsUse() == 1) {
            throw new RRException("无需重复确认");
        }
        entity.setIsUse(1);
        entity.setScanTime(new Date());
        baseMapper.updateById(entity);
    }
}
