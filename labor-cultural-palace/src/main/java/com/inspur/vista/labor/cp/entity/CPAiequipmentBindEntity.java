package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inspur.vista.labor.cp.config.DateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPAiequipmentBindEntity
 * @Description: 智能设备绑定信息
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:54
 * @Version: 1.0
 */
@ApiModel(value = "智能设备绑定信息", description = "智能设备绑定信息")
public class CPAiequipmentBindEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", example = "", position = 1)
    private String id;

    /**
     * 智能设备id
     */
    @ApiModelProperty(value = "智能设备id", required = true, example = "", position = 2)
    private String aiequipmentId;

    /**
     * 场所id
     */
    @ApiModelProperty(value = "场所id", required = true, example = "", position = 3)
    private String placeId;

    /**
     * 位置
     */
    @ApiModelProperty(value = "位置", required = true, example = "", position = 4)
    private String location;

    /**
     * 状态，1：有效；0：无效
     */
    @ApiModelProperty(value = "状态，1：有效；0：无效", hidden = true)
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
     * 最后更新人
     */
    @ApiModelProperty(value = "最后更新人", hidden = true)
    private String modifier;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
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

    public String getAiequipmentId() {
        return aiequipmentId;
    }

    public void setAiequipmentId(String aiequipmentId) {
        this.aiequipmentId = aiequipmentId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "CPAiequipmentBindEntity{" +
                ", id='" + id + '\'' +
                ", aiequipmentId='" + aiequipmentId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", location='" + location + '\'' +
                ", state='" + state + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifier='" + modifier + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}