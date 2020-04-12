package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Title: CPSceneBlacklistEntity
 * @Description: 预约场次黑名单
 * @Author: gengpeng
 * @CreateDate: 2020/3/16 15:46
 * @Version: 1.0
 */
@ApiModel(value = "黑名单详情", description = "黑名单详情")
public class CPSceneBlacklistEntity {

    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private String id;

    /**
     * 场地id
     */
    private String courtId;

    /**
     * 不可预约的开始时间
     */
    private Date beginTime;

    /**
     * 不可预约的开始时间
     */
    private Date endTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CPSceneBlacklistEntity{" +
                "id='" + id + '\'' +
                ", courtId='" + courtId + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
