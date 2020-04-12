package com.inspur.vista.labor.cp.service.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.inspur.vista.labor.cp.dao.activity.CheckEntity;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoListVO;
import com.inspur.vista.labor.cp.entity.activity.CpActivityBaseEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivitySpecifyOrganEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 投票报名活动
 * 
 * @author 
 * @email 
 * @date 2020-03-18 14:28:24
 */
public interface CpActivityBaseService extends IService<CpActivityBaseEntity> {

    Page queryPage(Map<String, Object> params);

    void insertActivity(CpActivityBaseEntity cpActivityBaseEntity);

    void updateActivityById(CpActivityBaseEntity cpActivityBaseEntity);

    void updateStatus(int id, int status);

    void deleteActivityById(int activityId);

    List<CPPlaceInfoListVO> getPlaceListByCpId(String cpId);

    /**
     * 根据laborId获取白名单区划编码
     */
    String getWhiteByLaborId(String laborId);

    /**
     * app会员获取活动列表
     * @return
     */
    Page<Map> getListForApp(Map params);

    /**
     * app获取我参与的活动列表
     * @param params
     * @return
     * @throws Exception
     */
    Page<Map> getMyList(Map params);

    /**
     * app获取活动详情
     * @param params
     * @return
     * @throws Exception
     */
    Map getInfoForApp(Map params);

    /**
     * 活动详情
     * @param id
     * @return
     */
    Map detils(Integer id);


    /**
     * 判断用户是否满足活动参与条件
     * @param entity
     */
    void isMeet(CheckEntity entity);

    /**
     * 获取小程序扫码报名活动列表
     * @param params
     * @return
     */
    Page<Map> scanJoinActivityList(Map params);


    /**
     * 团体报名分页列表
     * @param params
     * @return
     */
    Page groupJoinList(Map params);

    /**
     * 获取活动指定机构id list
     * @param id
     * @return
     */
    List<String> selectSpecifyOrganIdList(Integer id);

    /**
     * 创建外链活动
     */
    void createLinkActivity(CpActivityBaseEntity entity);

    /**
     * 修改外链活动
     */
    void updateLinkActivity(CpActivityBaseEntity entity);

    /**
     * 领券活动添加优惠券
     */
    void addCoupon(Map params);

    /**
     * 根据活动id获取指定机构列表
     */
    List<CpActivitySpecifyOrganEntity> querySpecifyOrgansByActivityId(Integer id);

    /**
     * 富文本上传文件
     * @param file
     * @return
     */
    String ueditorUploadFile(MultipartFile file);

    /**
     * 用户是否还可再参与活动
     */
    boolean getMyActivityStatus(String activityId, String userCode);

    /**
     * 活动文件url前缀替换为指定标识
     */
    String activityUrlReplace(String content);

    /**
     * 活动文件url还原
     */
    String activityUrlReduction(String content);
}
