package com.inspur.vista.labor.cp.service.activity.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.vista.labor.cp.dao.CPPlaceInfoMapper;
import com.inspur.vista.labor.cp.dao.OrganCantMapper;
import com.inspur.vista.labor.cp.dao.OrganMapper;
import com.inspur.vista.labor.cp.dao.activity.CPCultureCouponMapper;
import com.inspur.vista.labor.cp.dao.activity.CheckEntity;
import com.inspur.vista.labor.cp.dao.activity.CpActivityBaseDao;
import com.inspur.vista.labor.cp.dao.activity.CpActivityDrawRecordsDao;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.entity.activity.*;
import com.inspur.vista.labor.cp.exception.RRException;
import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.service.activity.*;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service("cpActivityBaseService")
public class CpActivityBaseServiceImpl extends ServiceImpl<CpActivityBaseDao, CpActivityBaseEntity> implements CpActivityBaseService {

    @Autowired
    private CPPlaceInfoMapper cpPlaceInfoMapper;
    @Autowired
    private OrganMapper organMapper;
    @Autowired
    private OrganCantMapper organCantMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CpActivityFormService cpActivityFormService;
    @Autowired
    private CpActivityJoinRecordsService cpActivityJoinRecordsService;
    @Autowired
    private CPCultureCouponMapper cpCultureCouponMapper;
    @Autowired
    private CpActivityCouponRecordsService cpActivityCouponRecordsService;
    @Autowired
    private CpActivityDrawRecordsService cpActivityDrawRecordsService;
    @Autowired
    private CPActivityVoteRecordsService cpActivityVoteRecordsService;
    @Autowired
    private CpActivityGoodsService cpActivityGoodsService;
    @Autowired
    private CpActivityDrawRecordsDao cpActivityDrawRecordsDao;
    @Autowired
    private PubFilesService filesService;
    @Value("${oss.download_host}")
    private String filePrefix;
    /* 默认替换标识 */
    private final String FLAG = "_address_flag_";


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${activity.url_white_cantcode}")
    private String whiteCantCode;
    @Value("${activity.url_create_activity}")
    private String createActivity;
    @Value("${activity.url_update_activity}")
    private String updateActivity;

    @Override
    public Page queryPage(Map<String, Object> params) {

        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.queryPage(p, params);
        //图片url还原
        list.forEach(l -> {
            if (StringUtils.isNotBlank((String) l.get("coverImg"))) {
                l.put("coverImg", activityUrlReduction((String) l.get("coverImg")));
            }
            if (StringUtils.isNotBlank((String) l.get("content"))) {
                l.put("content", activityUrlReduction((String) l.get("content")));
            }
        });
        p.setRecords(list);

        return p;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertActivity(CpActivityBaseEntity activity) {

        String laborId = activity.getLaborId();
        OrganEntity organEntity = organMapper.selectByOrganId(laborId);

        //如果未指定工会，获取工会区划信息，顶级工会path
        if (activity.getSpecifyList() == null || activity.getSpecifyList().size() == 0) {
            if (StringUtils.isBlank(activity.getLevel())) {
                throw new RRException("未指定工会且未设置活动范围");
            }
            if (organEntity == null) {
                throw new RRException("未获取到当前工会信息");
            }
            if (activity.getLevel().equals(organEntity.getType())) {
                activity.setPath(organEntity.getStruPath());
            }
            if (!activity.getLevel().equals(organEntity.getType())) {
                String struPath = organEntity.getStruPath();
                struPath = struPath.replace("rootId#", "");
                String[] pathArray = struPath.split("#");
                for (String struId : pathArray) {
                    organEntity = organMapper.selectByStruId(struId);
                    if (null != organEntity && activity.getLevel().equals(organEntity.getType())) {
                        activity.setPath(organEntity.getStruPath());
                        break;
                    }
                }
            }
            OrganCantEntity organCantEntity = organCantMapper.selectByOrganId(organEntity.getOrganId());
            if (organCantEntity == null) {
                throw new RRException("未获取到顶级工会区划信息");
            }
            activity.setUseRange(organCantEntity.getCantCode());
        }
        //url替换
        if (StringUtils.isNotBlank(activity.getCoverImg())) {
            activity.setCoverImg(activityUrlReplace(activity.getCoverImg()));
        }
        if (StringUtils.isNotBlank(activity.getContent())) {
            activity.setContent(activityUrlReplace(activity.getContent()));
        }
        baseMapper.insert(activity);

        //如果指定了工会
        if (activity.getSpecifyList() != null && activity.getSpecifyList().size() > 0) {
            activity.getSpecifyList().forEach(s -> {
                s.setActivityId(activity.getId());
                //获取工会path
                OrganEntity organ = organMapper.selectByOrganId(s.getSpecifyLaborId());
                if (organ != null) {
                    s.setSpecifyLaborPath(organ.getStruPath());
                }
            });
            baseMapper.insertSpecifyOrgan(activity.getSpecifyList());
        }
        this.createLinkActivity(activity);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateActivityById(CpActivityBaseEntity activity) {

        //如果指定了工会
        if (activity.getSpecifyList() != null && activity.getSpecifyList().size() > 0) {
            activity.getSpecifyList().forEach(s -> {
                s.setActivityId(activity.getId());
                //获取工会path
                OrganEntity organ = organMapper.selectByOrganId(s.getSpecifyLaborId());
                if (organ != null) {
                    s.setSpecifyLaborPath(organ.getStruPath());
                }
            });
            //删除之前指定的工会
            baseMapper.deleteSpecifyByActivityId(activity.getId());
            baseMapper.insertSpecifyOrgan(activity.getSpecifyList());
        }
        //替换文件url
        if (StringUtils.isNotBlank(activity.getCoverImg())) {
            activity.setCoverImg(activityUrlReplace(activity.getCoverImg()));
        }
        if (StringUtils.isNotBlank(activity.getContent())) {
            activity.setContent(activityUrlReplace(activity.getContent()));
        }
        baseMapper.updateById(activity);
        //获取活动link
        CpActivityBaseEntity cpActivityBaseEntity = baseMapper.selectById(activity.getId());
        activity.setLink(cpActivityBaseEntity.getLink());
        this.updateLinkActivity(activity);
    }

    @Override
    public void updateStatus(int id, int status) {
        CpActivityBaseEntity cpActivityBaseEntity = new CpActivityBaseEntity();
        cpActivityBaseEntity.setId(id);
        cpActivityBaseEntity.setStatus(status);
        if (status == 0) {
            //如果关闭活动，取消置顶
            cpActivityBaseEntity.setIsTop(0);
        }
        baseMapper.updateById(cpActivityBaseEntity);
        //获取活动link
        CpActivityBaseEntity activity = baseMapper.selectById(id);
        cpActivityBaseEntity.setLink(activity.getLink());
        this.updateLinkActivity(cpActivityBaseEntity);
    }

    @Override
    public void deleteActivityById(int activityId) {
        CpActivityBaseEntity cpActivityBaseEntity = baseMapper.selectById(activityId);
        if(cpActivityBaseEntity != null) {
            Integer status = 1;
            if (status.equals(cpActivityBaseEntity.getStatus())) {
                throw new RRException("开启中的活动不可删除");
            }
            baseMapper.deleteById(activityId);
        }
    }

    @Override
    public List<CPPlaceInfoListVO> getPlaceListByCpId(String cpId) {
        return cpPlaceInfoMapper.listCPPlaceInfoByCPId(cpId);
    }

    /**
     * 根据laborId获取白名单区划编码
     */
    @Override
    public String getWhiteByLaborId(String laborId) {

        Map params = new HashMap<>();
        Map header = new HashMap<>();
        params.put("laborId", laborId);
        header.put("Authorization", "bearer " + commonService.getToken());
        Map map = HttpUtil.get(whiteCantCode, params, header);
        if (map == null) {
            return "";
        }
        return (String) map.get("data");
    }

    /**
     * app会员获取活动列表
     * @return
     */
    @Override
    public Page<Map> getListForApp(Map params) {

        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.selectListForApp(p, params);
        //图片url还原
        list.forEach(l -> {
            if (StringUtils.isNotBlank((String) l.get("coverImg"))) {
                l.put("coverImg", activityUrlReduction((String) l.get("coverImg")));
            }
            //去掉content内容
            l.put("content", null);
        });
        p.setRecords(list);
        return p;
    }

    /**
     * app获取我参与的活动列表
     *
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public Page<Map> getMyList(Map params) {
        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.selectMyListForApp(p, params);
        //图片url还原
        list.forEach(l -> {
            if (StringUtils.isNotBlank((String) l.get("coverImg"))) {
                l.put("coverImg", activityUrlReduction((String) l.get("coverImg")));
            }
            //去掉content内容
            l.put("content", null);
        });
        p.setRecords(list);
        return p;
    }

    /**
     * app获取活动详情
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public Map getInfoForApp(Map params) {

        Map<String, Object> result = new HashMap<>();
        if (params.get("id") == null) {
            throw new RRException("活动id不可为空");
        }
        String id = String.valueOf(params.get("id"));
        //获取活动信息
        Map cpActivityBaseMap = baseMapper.selectByActivityId(id);
        if (cpActivityBaseMap == null) {
            throw new RRException("未获取到活动信息");
        }
        //url还原
        if (StringUtils.isNotBlank((String) cpActivityBaseMap.get("coverImg"))) {
            cpActivityBaseMap.put("coverImg", activityUrlReduction((String) cpActivityBaseMap.get("coverImg")));
        }
        if (StringUtils.isNotBlank((String) cpActivityBaseMap.get("content"))) {
            cpActivityBaseMap.put("content", activityUrlReduction((String) cpActivityBaseMap.get("content")));
        }
        //报名活动
        if ("join".equals(cpActivityBaseMap.get("type"))) {
            //获取活动表单
            EntityWrapper<CpActivityFormEntity> formWrapper = new EntityWrapper<>();
            formWrapper.eq("activity_id", id);
            CpActivityFormEntity cpActivityFormEntity = cpActivityFormService.selectOne(formWrapper);
            result.put("form", cpActivityFormEntity);
            //如果用户id不为空获取用户参与记录
            if(params.get("userId") != null) {
                EntityWrapper<CpActivityJoinRecordsEntity> recordWrapper = new EntityWrapper<>();
                recordWrapper.eq("activity_id", id);
                recordWrapper.eq("user_id", String.valueOf(params.get("userId")));
                CpActivityJoinRecordsEntity record = cpActivityJoinRecordsService.selectOne(recordWrapper);
                result.put("userRecord", record);
            }
        }
        //领券
        if ("tickets".equals(cpActivityBaseMap.get("type")) && cpActivityBaseMap.get("couponId") != null) {
            //获取优惠券
            CPCultureCouponEntity cpCultureCouponEntity = cpCultureCouponMapper.selectById(String.valueOf(cpActivityBaseMap.get("couponId")));
            result.put("coupon", cpCultureCouponEntity);
        }
        //抽奖
        if ("draw".equals(cpActivityBaseMap.get("type"))) {
            //获取奖品
            EntityWrapper<CpActivityGoodsEntity> goodsWrapper = new EntityWrapper<>();
            goodsWrapper.eq("activity_id", cpActivityBaseMap.get("id"));
            List<CpActivityGoodsEntity> goods = cpActivityGoodsService.selectList(goodsWrapper);
            result.put("goods", goods);
            //获取中将记录
            List<Map> winRecords = cpActivityDrawRecordsService.selectWinRecords((Integer)cpActivityBaseMap.get("id"));
            result.put("winRecords", winRecords);
            int totalNum = 1;
            if(cpActivityBaseMap.get("takeCount") != null) {
                totalNum = (Integer)cpActivityBaseMap.get("takeCount");
            }
            cpActivityBaseMap.put("totalNum", totalNum);
            cpActivityBaseMap.put("num", 1);
            if(params.get("userId") != null) {
                if ("onlyOne".equals(cpActivityBaseMap.get("condition"))) {
                    //只能一次
                    EntityWrapper<CpActivityDrawRecordsEntity> drawRecordWrapper = new EntityWrapper<>();
                    drawRecordWrapper.eq("activity_id", id);
                    drawRecordWrapper.eq("user_id", String.valueOf(params.get("userId")));
                    int count = cpActivityDrawRecordsService.selectCount(drawRecordWrapper);
                    if (count > 0) {
                        cpActivityBaseMap.put("num", 0);
                    }
                }
                if ("everyDay".equals(cpActivityBaseMap.get("condition"))) {
                    //每天多次，获取当日抽奖次数
                    CpActivityDrawRecordsEntity cpActivityDrawRecordsEntity = new CpActivityDrawRecordsEntity();
                    cpActivityDrawRecordsEntity.setActivityId(Integer.valueOf(id));
                    cpActivityDrawRecordsEntity.setUserId((String) params.get("userId"));
                    int currDayCount = cpActivityDrawRecordsDao.selectCurrentDayCount(cpActivityDrawRecordsEntity);
                    if (cpActivityBaseMap.get("takeCount") != null) {
                        cpActivityBaseMap.put("num", totalNum - currDayCount);
                    }
                }
            }
        }
        result.put("activity", cpActivityBaseMap);
        return result;
    }

    /**
     * 活动详情
     *
     * @param id
     * @return
     */
    @Override
    public Map detils(Integer id) {
        Map<String, Object> result = new HashMap<>();
        if (id == null) {
            throw new RRException("活动id不可为空");
        }
        //获取活动信息
        CpActivityBaseEntity cpActivityBaseEntity = baseMapper.selectById(id);
        //url还原
        if (StringUtils.isNotBlank(cpActivityBaseEntity.getCoverImg())) {
            cpActivityBaseEntity.setCoverImg(activityUrlReduction(cpActivityBaseEntity.getCoverImg()));
        }
        if (StringUtils.isNotBlank(cpActivityBaseEntity.getContent())) {
            cpActivityBaseEntity.setContent(activityUrlReduction(cpActivityBaseEntity.getContent()));
        }
        if (cpActivityBaseEntity == null) {
            throw new RRException("未获取到活动信息");
        }
        result.put("activity", cpActivityBaseEntity);
        //报名活动
        if ("join".equals(cpActivityBaseEntity.getType())) {
            //获取活动表单
            EntityWrapper<CpActivityFormEntity> formWrapper = new EntityWrapper<>();
            formWrapper.eq("activity_id", id);
            CpActivityFormEntity cpActivityFormEntity = cpActivityFormService.selectOne(formWrapper);
            result.put("form", cpActivityFormEntity);
        }
        //领券
        if ("tickets".equals(cpActivityBaseEntity.getType()) && cpActivityBaseEntity.getCouponCount() != null) {
            //获取优惠券
            CPCultureCouponEntity cpCultureCouponEntity = cpCultureCouponMapper.selectById(cpActivityBaseEntity.getCouponCount());
            result.put("coupon", cpCultureCouponEntity);
        }
        //抽奖
        if ("draw".equals(cpActivityBaseEntity.getType())) {
            //获取奖品
            EntityWrapper<CpActivityGoodsEntity> goodsWrapper = new EntityWrapper<>();
            goodsWrapper.eq("activity_id", cpActivityBaseEntity.getId());
            List<CpActivityGoodsEntity> goods = cpActivityGoodsService.selectList(goodsWrapper);
            result.put("goods", goods);
            //获取中将记录
            List<Map> winRecords = cpActivityDrawRecordsService.selectWinRecords(cpActivityBaseEntity.getId());
            result.put("winRecords", winRecords);

        }
        return result;
    }

    /**
     * 判断用户是否满足活动参与条件
     */
    @Override
    public void isMeet(CheckEntity entity) {

        if (entity.getLaborId() == null) {
            throw new RRException("未获取到用户工会id");
        }
        //获取活动信息
        CpActivityBaseEntity activity = baseMapper.selectById(entity.getActivityId());
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        OrganEntity organEntity = organMapper.selectByOrganId(entity.getLaborId());
        if (entity.getLaborId() == null) {
            throw new RRException("未获取到用户所属工会信息");
        }
        //获取指定工会
        List<String> specifyOrganIdList = baseMapper.selectSpecifyOrganIdList(activity.getId());
        //判断活动是否指定工会
        if (specifyOrganIdList != null && specifyOrganIdList.size() > 0 && !specifyOrganIdList.contains(entity.getLaborId())) {
            throw new RRException("非活动指定工会用户");
        }

        //如果未指定工会
        if (specifyOrganIdList == null || specifyOrganIdList.size() == 0) {
            //不同，判断是否直属工会
            List<String> directList = new ArrayList<>();
            directList.add("21991");
            directList.add("21992");
            directList.add("21993");
            directList.add("22991");
            directList.add("22992");
            directList.add("22993");
            directList.add("24991");
            directList.add("24992");
            directList.add("24993");
            //是直属工会
            if (directList.contains(organEntity.getType())) {
                //用户所属工会与创建活动工会是否一致
                boolean isCreate = activity.getLaborId().equals(organEntity.getOrganId());
                //获取白名单区划
                String cantCode = getWhiteByLaborId(entity.getLaborId());
                //如果不是创建者且白名单区划编码为空，不满足参与条件
                if (!isCreate && StringUtils.isBlank(cantCode)) {
                    throw new RRException("所属工会不在本活动设置范围");
                }
                //判断区划编码是否符合useRange
                if (!cantCode.equals(activity.getUseRange()) &&
                    !(cantCode.substring(0,2) + "0000").equals(activity.getUseRange()) &&
                    !(cantCode.substring(0,4) + "00").equals(activity.getUseRange())) {
                    throw new RRException("所属工会不在本活动设置范围");
                }
            }
            //如果不是直属工会，判断工会path
            if (organEntity.getStruPath().indexOf(activity.getPath()) == -1) {
                throw new RRException("所属工会不在本活动设置范围");
            }
        }

        //校验活动时间
        Date date = new Date();
        if (date.after(activity.getEndTime())) {
            throw new RRException("活动已结束");
        }
        if (date.before(activity.getStartTime())) {
            throw new RRException("活动未开始");
        }

    }

    /**
     * 获取小程序扫码活动列表
     *
     * @param params
     * @return
     */
    @Override
    public Page<Map> scanJoinActivityList(Map params) {

        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.scanJoinActivityList(p, params);
        p.setRecords(list);
        return p;
    }

    /**
     * 团体报名分页列表
     * @param params
     * @return
     */
    @Override
    public Page groupJoinList(Map params) {

        int page = Integer.parseInt(Objects.toString(params.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize")));
        Page<Map> p = new Page<>(page, pageSize);
        List<Map> list = baseMapper.groupJoinList(p, params);
        p.setRecords(list);

        return p;
    }

    /**
     * 获取活动指定机构id list
     *
     * @param id
     * @return
     */
    @Override
    public List<String> selectSpecifyOrganIdList(Integer id) {
        return baseMapper.selectSpecifyOrganIdList(id);
    }

    /**
     * 创建外链活动
     */
    @Override
    public void createLinkActivity(CpActivityBaseEntity entity) {

        try {
            //没有链接直接返回
            if (StringUtils.isBlank(entity.getLink())) {
                return;
            }
            //url处理
            if (StringUtils.isNotBlank(entity.getCoverImg())) {
                entity.setCoverImg(activityUrlReduction(entity.getCoverImg()));
            }
            if (StringUtils.isNotBlank(entity.getContent())) {
                entity.setContent(activityUrlReduction(entity.getContent()));
            }
            List list = new ArrayList<>();
            //查询活动是否指定了工会
            List<CpActivitySpecifyOrganEntity> organs = baseMapper.selectSpecifyOrganList(entity.getId());
            if(organs != null && organs.size() > 0) {
                organs.forEach(o -> {
                    Map map = new HashMap<>();
                    map.put("title", entity.getTitle());
                    map.put("type", "extlinks");
                    map.put("coverImg", entity.getCoverImg());
                    map.put("startTime", entity.getStartTime());
                    map.put("endTime", entity.getEndTime());
                    map.put("link", entity.getLink());
                    map.put("status", entity.getStatus());
                    map.put("releaseStatus", "1");
                    map.put("useRange", entity.getUseRange());
                    map.put("level", entity.getLevel());
                    map.put("laborCode", o.getSpecifyLaborCode());
                    map.put("laborId", o.getSpecifyLaborId());
                    map.put("laborName", o.getSpecifyLaborName());
                    map.put("path", o.getSpecifyLaborPath());
                    list.add(map);
                });
            } else {
                Map map = new HashMap<>();
                map.put("title", entity.getTitle());
                map.put("type", "extlinks");
                map.put("coverImg", entity.getCoverImg());
                map.put("startTime", entity.getStartTime());
                map.put("endTime", entity.getEndTime());
                map.put("link", entity.getLink());
                map.put("status", entity.getStatus());
                map.put("releaseStatus", entity.getReleaseStatus());
                map.put("useRange", entity.getUseRange());
                map.put("level", entity.getLevel());
                list.add(map);
            }
            //调用工会添加外链活动接口
            Map header = new HashMap<>();
            header.put("Authorization", "bearer " + commonService.getToken());
            Map post = HttpUtil.post(createActivity, JSON.toJSONString(list), header);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 编辑外链活动
     */
    @Override
    public void updateLinkActivity(CpActivityBaseEntity entity) {
        try {
            //没有链接直接返回
            if (StringUtils.isBlank(entity.getLink())) {
                return;
            }
            //url处理
            if (StringUtils.isNotBlank(entity.getCoverImg())) {
                entity.setCoverImg(activityUrlReduction(entity.getCoverImg()));
            }
            if (StringUtils.isNotBlank(entity.getContent())) {
                entity.setContent(activityUrlReduction(entity.getContent()));
            }
            Map map = new HashMap<>();
            map.put("title", entity.getTitle());
            map.put("type", "extlinks");
            map.put("coverImg", entity.getCoverImg());
            map.put("startTime", entity.getStartTime());
            map.put("endTime", entity.getEndTime());
            map.put("link", entity.getLink());
            map.put("status", entity.getStatus());
            map.put("releaseStatus", "1");
            map.put("useRange", entity.getUseRange());
            map.put("level", entity.getLevel());
            //调用工会修改外链活动接口
            Map header = new HashMap<>();
            header.put("Authorization", "bearer " + commonService.getToken());
            Map post = HttpUtil.post(updateActivity, JSON.toJSONString(map), header);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 领券活动添加优惠券
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCoupon(Map params) {

        Integer activityId = Integer.valueOf(Objects.toString(params.get("id")));
        Integer couponId = Integer.valueOf(Objects.toString(params.get("couponId")));
        Integer count = Integer.valueOf(Objects.toString(params.get("count")));
        //获取优惠券
        CPCultureCouponEntity cpCultureCouponEntity = cpCultureCouponMapper.selectById(couponId);
        if (cpCultureCouponEntity == null) {
            throw new RRException("未获取到优惠券信息");
        }
        if (count > cpCultureCouponEntity.getCount()) {
            throw new RRException("优惠券余量不足");
        }
        CpActivityBaseEntity cpActivityBaseEntity = baseMapper.selectById(activityId);
        if (cpActivityBaseEntity == null) {
            throw new RRException("未获取到活动信息");
        }
        if (cpCultureCouponEntity.getEndTime().before(cpActivityBaseEntity.getEndTime())){
            throw new RRException("优惠券截止时间不得早于活动结束时间");
        }
        cpActivityBaseEntity = new CpActivityBaseEntity();
        cpActivityBaseEntity.setId(activityId);
        cpActivityBaseEntity.setCouponId(couponId);
        cpActivityBaseEntity.setCouponCount(count);
        baseMapper.updateById(cpActivityBaseEntity);
        cpCultureCouponMapper.updateCount(couponId, count);
    }

    /**
     * 根据活动id获取指定机构列表
     *
     * @param id
     */
    @Override
    public List<CpActivitySpecifyOrganEntity> querySpecifyOrgansByActivityId(Integer id) {
        return baseMapper.querySpecifyOrgansByActivityId(id);
    }

    /**
     * 富文本上传文件
     */
    @Override
    public String ueditorUploadFile(MultipartFile file) {
        MultipartFile[] multipartFiles = new MultipartFile[1];
        multipartFiles[0] = file;
        try {
            FileBsnInfo bsnInfo = new FileBsnInfo();
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_ACTIVITY);
            bsnInfo.setBsnDesc("活动富文本上传文件");
            List<UploadFileResponse> uploadFileResponseList = filesService.updateFile(bsnInfo, multipartFiles);
            UploadFileResponse uploadFileResponse = uploadFileResponseList.get(0);
            if (uploadFileResponse != null) {
                return filePrefix + uploadFileResponse.getFilePath();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * 用户是否还可再参与活动
     */
    @Override
    public boolean getMyActivityStatus(String activityId, String userCode) {

        //获取活动信息
        CpActivityBaseEntity activity = baseMapper.selectById(activityId);
        if (activity == null) {
            throw new RRException("未获取到活动信息");
        }
        //根据活动类型判断用户是否可继续参与
        //报名
        if("join".equals(activity.getType())) {
            EntityWrapper<CpActivityJoinRecordsEntity> joinRecordWrapper = new EntityWrapper<>();
            joinRecordWrapper.eq("activity_id", activityId);
            joinRecordWrapper.eq("user_id", userCode);
            int count = cpActivityJoinRecordsService.selectCount(joinRecordWrapper);
            if (count == 0) {
                return true;
            }
        }
        //领券
        if ("tickets".equals(activity.getType())) {
            EntityWrapper<CpActivityCouponRecordsEntity> couponRecordWrapper = new EntityWrapper<>();
            couponRecordWrapper.eq("activity_id", activityId);
            couponRecordWrapper.eq("user_id", userCode);
            int count = cpActivityCouponRecordsService.selectCount(couponRecordWrapper);
            //每天一次
            if ("onlyOne".equals(activity.getCondition()) && count == 0) {
                return true;
            }
            //每天多次
            if ("onlyOne".equals(activity.getCondition()) && count < activity.getTakeCount()) {
                return true;
            }
        }
        //抽奖
        if ("draw".equals(activity.getType())) {
            EntityWrapper<CpActivityDrawRecordsEntity> drawRecordWrapper = new EntityWrapper<>();
            drawRecordWrapper.eq("activity_id", activityId);
            drawRecordWrapper.eq("user_id", userCode);
            int count = cpActivityDrawRecordsService.selectCount(drawRecordWrapper);
            //每天一次
            if ("onlyOne".equals(activity.getCondition()) && count == 0) {
                return true;
            }
            //每天多次
            if ("onlyOne".equals(activity.getCondition()) && count < activity.getTakeCount()) {
                return true;
            }
        }
        //投票
        if ("vote".equals(activity.getType())) {
            EntityWrapper<CPActivityVoteRecordsEntity> voteRecordWrapper = new EntityWrapper<>();
            voteRecordWrapper.eq("activity_id", activityId);
            voteRecordWrapper.eq("user_id", userCode);
            int count = cpActivityVoteRecordsService.selectCount(voteRecordWrapper);
            //每天一次
            if ("onlyOne".equals(activity.getCondition()) && count == 0) {
                return true;
            }
            //每天多次
            if ("onlyOne".equals(activity.getCondition()) && count < activity.getTakeCount()) {
                return true;
            }
        }

        return false;
    }

    /**
     * 活动文件url前缀替换为指定标识
     */
    @Override
    public String activityUrlReplace(String content) {
        //替换图片url前缀
        if (StringUtils.isNotBlank(content)) {
            content = content.replace(filePrefix, FLAG);
        }
        return content;
    }

    /**
     * 活动文件url还原
     */
    @Override
    public String activityUrlReduction(String content) {
        //还原图片url前缀
        if (StringUtils.isNotBlank(content)) {
            content = content.replace(FLAG, filePrefix);
        }
        return content;
    }
}
