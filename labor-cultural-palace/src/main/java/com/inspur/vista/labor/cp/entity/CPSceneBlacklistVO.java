package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Title: CPSceneBlacklistAdd
 * @Description: 场次黑名单
 * @Author: gengpeng
 * @CreateDate: 2020/3/17 11:07
 * @Version: 1.0
 */
@ApiModel(value = "黑名单新增", description = "黑名单新增")
public class CPSceneBlacklistVO {

    /**
     * 场地id
     */
    @ApiModelProperty(value = "场地id", required = true, position = 1)
    private String courtId;

    /**
     * 类型，1.场地黑名单；2.场次黑名单
     */
    @ApiModelProperty(value = "类型，1.场地黑名单；2.场次黑名单", required = true, position = 2)
    private Integer type;

    /**
     * 不可预约的开始时间
     */
    @ApiModelProperty(value = "不可预约的开始时间", required = true, position = 3)
    private String beginTime;

    /**
     * 不可预约的结束时间
     */
    @ApiModelProperty(value = "不可预约的结束时间", required = true, position = 4)
    private String endTime;
    /**
     * 取消预约原因
     */
    @ApiModelProperty(value = "取消预定的原因，如果设备损坏等", required = false, position = 6)
    private String cancelReasonExt;

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCancelReasonExt() {
        return cancelReasonExt;
    }

    public void setCancelReasonExt(String cancelReasonExt) {
        this.cancelReasonExt = cancelReasonExt;
    }

    @Override
    public String toString() {
        return "CPSceneBlacklistVO{" +
                "courtId='" + courtId + '\'' +
                ", type=" + type +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", cancelReasonExt='" + cancelReasonExt + '\'' +
                '}';
    }
}
