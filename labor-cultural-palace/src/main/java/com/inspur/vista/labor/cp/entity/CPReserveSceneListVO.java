package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPReserveSceneListVO
 * @Description: 预约场次记录
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@ApiModel(value = "预约记录与场次对应关系列表", description = "预约记录与场次对应关系列表")
public class CPReserveSceneListVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    /**
     * 预约记录
     */
    @ApiModelProperty(value = "预约记录的id", position = 3)
    private String reserveId;

    /**
     * 场次开始时间
     */
    @ApiModelProperty(value = "场次开始时间", position = 4)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    /**
     * 场次结束时间
     */
    @ApiModelProperty(value = "场次结束时间", position = 5)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 预约参加的人数
     */
    @ApiModelProperty(value = "此时间段预约的人数", position = 6)
    private Integer userNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
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

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    @Override
    public String toString() {
        return "CPReserveSceneListVO{" +
                "id=" + id +
                ", reserveId='" + reserveId + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", userNum=" + userNum +
                '}';
    }
}