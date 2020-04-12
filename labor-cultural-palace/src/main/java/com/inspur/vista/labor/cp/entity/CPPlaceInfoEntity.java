package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inspur.vista.labor.cp.config.DateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPPlaceInfoEntity
 * @Description: 场所信息
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@ApiModel(value = "场所信息", description = "场所信息")
public class CPPlaceInfoEntity implements Serializable {

    /**
     *
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, example = "1234", position = 2)
    private String cpId;

    /**
     * 场所名称
     */
    @ApiModelProperty(value = "场所名称", required = true, example = "会议室", position = 3)
    private String name;

    /**
     * 场所编码
     */
    @ApiModelProperty(value = "场所编码", example = "会议室", position = 4)
    private String placeCode;

    /**
     * 场所类型，字典
     */
    @ApiModelProperty(value = "场所类型，字典", example = "1", position = 5)
    private String type;

    /**
     * 可容纳人数
     */
    @ApiModelProperty(value = "可容纳人数", example = "123", position = 6)
    private Integer capacity;

    /**
     * 面积(平方米)
     */
    @ApiModelProperty(value = "面积(平方米)", example = "123.12", position = 7)
    private Double area;

    /**
     * 类别，1.自有；2.第三方
     */
    @ApiModelProperty(value = "类别，1.自有；2.第三方", example = "1", position = 8)
    private String category;

    /**
     * 位置
     */
    @ApiModelProperty(value = "位置", example = "浪潮路", position = 9)
    private String addr;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", example = "无", position = 10)
    private String introduction;

    /**
     * 状态 1：有效；0：无效
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
     * 最后更新人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 修改时间
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

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        return "CPPlaceInfoEntity{" +
                ", id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", name='" + name + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", type='" + type + '\'' +
                ", capacity='" + capacity + '\'' +
                ", area='" + area + '\'' +
                ", category='" + category + '\'' +
                ", addr='" + addr + '\'' +
                ", introduction='" + introduction + '\'' +
                ", state='" + state + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifier='" + modifier + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}