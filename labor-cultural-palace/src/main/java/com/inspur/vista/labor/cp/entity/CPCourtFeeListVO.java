package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPArenaFeeListVO
 * @Description: 场地费用标准
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@ApiModel(value = "场地费用标准列表", description = "场地费用标准列表")
public class CPCourtFeeListVO implements Serializable {

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
    @ApiModelProperty(value = "标准生效日期",position = 5)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private java.util.Date beginTime;

    /**
     * 标准到期日期 不填则永久有效
     */
    @ApiModelProperty(value = "标准到期日期,自动计算", position = 6)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private java.util.Date endTime;

    /**
     * 是否生效,-1.已失效，0.生效中，1.待生效
     */
    @ApiModelProperty(value = "是否生效,-1.已失效，0.生效中，1.待生效",position =7)
    private Integer effectived;


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

    public Integer getEffectived() {
        return effectived;
    }

    public void setEffectived(Integer effectived) {
        this.effectived = effectived;
    }

    @Override
    public String toString() {
        return "CPCourtFeeListVO{" +
                "id='" + id + '\'' +
                ", courtId='" + courtId + '\'' +
                ", reserveFee=" + reserveFee +
                ", useFee=" + useFee +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", effectived=" + effectived +
                '}';
    }
}