package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inspur.vista.labor.cp.config.DateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @Title: CPCourtInfoEntity
 * @Description: 场地信息
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@ApiModel(value = "场地信息", description = "场地信息")
public class CPCourtInfoEntity implements Serializable {

    /**
     *
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 场所id
     */
    @ApiModelProperty(value = "场所id", required = true, example = "123", position = 2)
    private String placeId;

    /**
     * 场地的名称
     */
    @ApiModelProperty(value = "场地的名称", required = true, example = "会议室", position = 3)
    private String name;

    /**
     * 场地的编码
     */
    @ApiModelProperty(value = "场地的编码", example = "1123", position = 4)
    private String courtCode;

    /**
     * 场地的项目类别，如羽毛球、乒乓球等
     */
    @ApiModelProperty(value = "场地的项目类别，如羽毛球、乒乓球等", required = true, example = "1", position = 5)
    private String type;

    /**
     * 是否允许预约，1.允许；0.不允许
     */
    @ApiModelProperty(value = "是否允许预约，1.允许；0.不允许", required = true, example = "1", position = 5)
    private Integer canReserve;

    /**
     * 是否包含法定节假日
     */
    @ApiModelProperty(value = "是否包含法定节假日", required = true, example = "1", position = 7)
    private Integer includeHolidays;

    /**
     * 是否包含周末
     */
    @ApiModelProperty(value = "是否包含周末", required = true, example = "1", position = 8)
    private Integer includeWeekend;

    /**
     * 最小起租小时，精确的小数点后一位
     */
    @ApiModelProperty(value = "最小起租小时，精确的小数点后一位", required = true, example = "2.0", position = 9)
    private Double minimumHireHour;

    /**
     * 上午开始时间
     */
    @ApiModelProperty(value = "上午开始时间", dataType = "java.lang.String", example = "08:30:00", position = 10)
    private Time morningStartTime;

    /**
     * 上午结束时间
     */
    @ApiModelProperty(value = "上午结束时间", dataType = "java.lang.String", example = "11:30:00", position = 11)
    private Time morningEndTime;

    /**
     * 下午开始时间
     */
    @ApiModelProperty(value = "下午开始时间", dataType = "java.lang.String", example = "13:00:00", position = 12)
    private Time afternoonStartTime;

    /**
     * 下午结束时间
     */
    @ApiModelProperty(value = "下午结束时间", dataType = "java.lang.String", example = "17:00:00", position = 13)
    private Time afternoonEndTime;

    /**
     * 晚上开始时间
     */
    @ApiModelProperty(value = "晚上开始时间", dataType = "java.lang.String", example = "19:00:00", position = 14)
    private Time eveningStartTime;

    /**
     * 晚上结束时间
     */
    @ApiModelProperty(value = "晚上结束时间", dataType = "java.lang.String", example = "20:00:00", position = 15)
    private Time eveningEndTime;

    /**
     * 预约类型
     */
    @ApiModelProperty(value = "预约类型，1：按照场次预约；2：按照时间预约",example = "1", required = true, position = 16)
    private Integer reserveType;

    /**
     * 每个时间段预约人数上限
     */
    @ApiModelProperty(value = "每个时间段预约人数上限，当预约类型为2时填写，默认为0，不限制", example = "100", position = 17)
    private Integer reserveCapacity;


    /**
     * 是否启用，1.启用；0.未启用
     */
    @ApiModelProperty(value = "是否启用，1.启用；0.未启用(新增时不需传此字段，默认未启用，更新时如果需要变更状态则需要传)", hidden = true)
    private Integer isEnable;

    /**
     * 状态，1：有效；0：无效
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String remark;


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

    public Integer getIncludeHolidays() {
        return includeHolidays;
    }

    public void setIncludeHolidays(Integer includeHolidays) {
        this.includeHolidays = includeHolidays;
    }

    public Integer getIncludeWeekend() {
        return includeWeekend;
    }

    public void setIncludeWeekend(Integer includeWeekend) {
        this.includeWeekend = includeWeekend;
    }

    public Double getMinimumHireHour() {
        return minimumHireHour;
    }

    public void setMinimumHireHour(Double minimumHireHour) {
        this.minimumHireHour = minimumHireHour;
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

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getReserveCapacity() {
        return reserveCapacity;
    }

    public void setReserveCapacity(Integer reserveCapacity) {
        this.reserveCapacity = reserveCapacity;
    }

    public Integer getReserveType() {
        return reserveType;
    }

    public void setReserveType(Integer reserveType) {
        this.reserveType = reserveType;
    }

    public Integer getCanReserve() {
        return canReserve;
    }

    public void setCanReserve(Integer canReserve) {
        this.canReserve = canReserve;
    }

    @Override
    public String toString() {
        return "CPCourtInfoEntity{" +
                "id='" + id + '\'' +
                ", placeId='" + placeId + '\'' +
                ", name='" + name + '\'' +
                ", courtCode='" + courtCode + '\'' +
                ", type='" + type + '\'' +
                ", canReserve=" + canReserve +
                ", includeHolidays=" + includeHolidays +
                ", includeWeekend=" + includeWeekend +
                ", minimumHireHour=" + minimumHireHour +
                ", morningStartTime=" + morningStartTime +
                ", morningEndTime=" + morningEndTime +
                ", afternoonStartTime=" + afternoonStartTime +
                ", afternoonEndTime=" + afternoonEndTime +
                ", eveningStartTime=" + eveningStartTime +
                ", eveningEndTime=" + eveningEndTime +
                ", reserveType=" + reserveType +
                ", reserveCapacity=" + reserveCapacity +
                ", isEnable=" + isEnable +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}