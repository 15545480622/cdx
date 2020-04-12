package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPSceneDefinitionDetailVO
 * @Description: 预约场次定义，根据预约计划自动生成
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@ApiModel(value = "场次详情", description = "场次详情")
public class CPSceneInfoVO implements Serializable {

    /**
     * 场地id
     */
    @ApiModelProperty(value = "场地id", position = 1)
    private String courtId;

    /**
     * 预定记录id
     */
    @ApiModelProperty(value = "预定记录id", position = 2)
    private String reserveId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", position = 4)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 该场次的预约费用
     */
    @ApiModelProperty(value = "该场次的预约费用", position = 5)
    private Double reserveFee;

    /**
     * 该场次的使用费用
     */
    @ApiModelProperty(value = "该场次的使用费用", position = 6)
    private Double useFee;

    /**
     * 剩余人数，如果为-1，则不限制，默认为-1,如果为-1则不需要展示剩余人数
     */
    @ApiModelProperty(value = "剩余预约人数，如果为-1，则不限制，默认为-1,如果为-1则不需要展示剩余人数", position = 7)
    private Integer remainingNumber;

    /**
     * 预约状态，1.可预约；0.不可预约；2.已预约，如果为已预约，则不允许更新此场次
     */
    @ApiModelProperty(value = "预约状态，1.可预约；0.不可预约；2.已预约，如果为已预约，则不允许更新此场次", position = 8)
    private Integer state;

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

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public Integer getRemainingNumber() {
        return remainingNumber;
    }

    public void setRemainingNumber(Integer remainingNumber) {
        this.remainingNumber = remainingNumber;
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

    @Override
    public String toString() {
        return "CPSceneInfoVO{" +
                "courtId='" + courtId + '\'' +
                ", reserveId='" + reserveId + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", reserveFee=" + reserveFee +
                ", useFee=" + useFee +
                ", remainingNumber=" + remainingNumber +
                ", state=" + state +
                '}';
    }
}