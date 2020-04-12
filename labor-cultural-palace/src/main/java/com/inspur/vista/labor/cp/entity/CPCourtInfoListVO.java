package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Time;

/**
 * @Title: CPCourtInfoListVO
 * @Description: 场地信息
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@ApiModel(value = "场地列表信息", description = "场地列表信息")
public class CPCourtInfoListVO implements Serializable {

    /**
     *
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 场所id
     */
    @ApiModelProperty(value = "场所id", position = 2)
    private String placeId;

    /**
     * 场地的名称
     */
    @ApiModelProperty(value = "场地的名称", position = 3)
    private String name;

    /**
     * 场地的编码
     */
    @ApiModelProperty(value = "场地的编码", position = 4)
    private String courtCode;

    /**
     * 场地的项目类别，如羽毛球、乒乓球等
     */
    @ApiModelProperty(value = "场地的项目类别，如羽毛球、乒乓球等", position = 5)
    private String type;

    /**
     * 是否允许预约，1.允许；0.不允许
     */
    @ApiModelProperty(value = "是否允许预约，1.允许；0.不允许", position = 5)
    private Integer canReserve;

    /**
     * 场地的项目名称，如羽毛球、乒乓球等
     */
    @ApiModelProperty(value = "场地的项目名称，如羽毛球、乒乓球等", position = 6)
    private String projectName;

    /**
     * 上午开始时间
     */
    @ApiModelProperty(value = "上午开始时间", dataType = "java.lang.String", position = 10)
    private Time morningStartTime;

    /**
     * 上午结束时间
     */
    @ApiModelProperty(value = "上午结束时间", dataType = "java.lang.String", position = 11)
    private Time morningEndTime;

    /**
     * 下午开始时间
     */
    @ApiModelProperty(value = "下午开始时间", dataType = "java.lang.String", position = 12)
    private Time afternoonStartTime;

    /**
     * 下午结束时间
     */
    @ApiModelProperty(value = "下午结束时间", dataType = "java.lang.String", position = 13)
    private Time afternoonEndTime;

    /**
     * 晚上开始时间
     */
    @ApiModelProperty(value = "晚上开始时间", dataType = "java.lang.String", position = 14)
    private Time eveningStartTime;

    /**
     * 晚上结束时间
     */
    @ApiModelProperty(value = "晚上结束时间", dataType = "java.lang.String", position = 15)
    private Time eveningEndTime;

    /**
     * 已预订次数
     */
    @ApiModelProperty(value = "已预订次数", position = 7)
    private String reservedNumber;

    /**
     * 是否启用，1.启用；0.未启用
     */
    @ApiModelProperty(value = "是否启用，1.启用；0.未启用", position = 8)
    private Integer isEnable;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getReservedNumber() {
        return reservedNumber;
    }

    public void setReservedNumber(String reservedNumber) {
        this.reservedNumber = reservedNumber;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Time getMorningStartTime() {
        return morningStartTime;
    }

    public void setMorningStartTime(Time morningStartTime) {
        this.morningStartTime = morningStartTime;
    }

    public Time getMorningEndTime() {
        return morningEndTime;
    }

    public void setMorningEndTime(Time morningEndTime) {
        this.morningEndTime = morningEndTime;
    }

    public Time getAfternoonStartTime() {
        return afternoonStartTime;
    }

    public void setAfternoonStartTime(Time afternoonStartTime) {
        this.afternoonStartTime = afternoonStartTime;
    }

    public Time getAfternoonEndTime() {
        return afternoonEndTime;
    }

    public void setAfternoonEndTime(Time afternoonEndTime) {
        this.afternoonEndTime = afternoonEndTime;
    }

    public Time getEveningStartTime() {
        return eveningStartTime;
    }

    public void setEveningStartTime(Time eveningStartTime) {
        this.eveningStartTime = eveningStartTime;
    }

    public Time getEveningEndTime() {
        return eveningEndTime;
    }

    public void setEveningEndTime(Time eveningEndTime) {
        this.eveningEndTime = eveningEndTime;
    }

    public Integer getCanReserve() {
        return canReserve;
    }

    public void setCanReserve(Integer canReserve) {
        this.canReserve = canReserve;
    }

    @Override
    public String toString() {
        return "CPCourtInfoListVO{" +
                "id='" + id + '\'' +
                ", placeId='" + placeId + '\'' +
                ", name='" + name + '\'' +
                ", courtCode='" + courtCode + '\'' +
                ", type='" + type + '\'' +
                ", canReserve=" + canReserve +
                ", projectName='" + projectName + '\'' +
                ", morningStartTime=" + morningStartTime +
                ", morningEndTime=" + morningEndTime +
                ", afternoonStartTime=" + afternoonStartTime +
                ", afternoonEndTime=" + afternoonEndTime +
                ", eveningStartTime=" + eveningStartTime +
                ", eveningEndTime=" + eveningEndTime +
                ", reservedNumber='" + reservedNumber + '\'' +
                ", isEnable=" + isEnable +
                '}';
    }
}