package com.inspur.vista.labor.cp.dao.activity;

/**
 * @version 1.0
 * @description 判断是否具备参与活动资格实体
 * @date 2020/03/27 16/01
 **/
public class CheckEntity {

    /**
     * 会员id
     */
    private String userId;

    /**
     * 工会id
     */
    private String laborId;

    /**
     * 活动Id
     */
    private Integer activityId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLaborId() {
        return laborId;
    }

    public void setLaborId(String laborId) {
        this.laborId = laborId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
