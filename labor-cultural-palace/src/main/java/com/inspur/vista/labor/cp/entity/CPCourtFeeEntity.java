package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inspur.vista.labor.cp.config.DateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPArenaFeeEntity
 * @Description: 场地费用标准
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@ApiModel(value = "场地费用标准详情", description = "场地费用标准详情")
public class CPCourtFeeEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 场地id
     */
    @ApiModelProperty(value = "场地id",required = true,position = 2)
    private String courtId;

    /**
     * 预约费用
     */
    @ApiModelProperty(value = "预约费用",required = true,position = 3)
    private Double reserveFee;

    /**
     * 使用费用
     */
    @ApiModelProperty(value = "使用费用",required = true,position = 4)
    private Double useFee;

    /**
     * 标准生效日期
     */
    @ApiModelProperty(value = "标准生效日期",required = true,position = 5)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date beginTime;

    /**
     * 标准到期日期 不填则永久有效
     */
    @ApiModelProperty(value = "标准到期日期,自动计算", hidden = true)
    private Date endTime;

    /**
     * 状态 1：有效；0：无效
     */
    @ApiModelProperty(value = "状态 1：有效；0：无效", hidden = true)
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
    private Date createTime;

    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "最后更新人", hidden = true)
    private String modifier;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
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

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public Double getReserveFee() {
        return reserveFee;
    }

    public void setReserveFee(Double reserveFee) {
        this.reserveFee = reserveFee;
    }

    public Double getUseFee() {
        return useFee;
    }

    public void setUseFee(Double useFee) {
        this.useFee = useFee;
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

    @Override
    public String toString() {
        return "CPArenaFeeEntity{" +
                "id='" + id + '\'' +
                ", courtId='" + courtId + '\'' +
                ", reserveFee=" + reserveFee +
                ", useFee=" + useFee +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}