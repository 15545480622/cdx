package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPPlaceInfoDetailVO
 * @Description: 场所信息
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@ApiModel(value = "场所详细信息", description = "场所详细信息")
public class CPPlaceInfoVO implements Serializable {

    /**
     *
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", position = 2)
    private String cpId;

    /**
     * 文化宫名称
     */
    @ApiModelProperty(value = "文化宫名称，针对app端", position = 2)
    private String cpName;

    /**
     * 场所名称
     */
    @ApiModelProperty(value = "场所名称", position = 3)
    private String name;

    /**
     * 场所编码
     */
    @ApiModelProperty(value = "场所编码", position = 4)
    private String placeCode;

    /**
     * 场所类型，字典
     */
    @ApiModelProperty(value = "场所类型，字典", position = 5)
    private String type;

    /**
     * 场所类型，字典
     */
    @ApiModelProperty(value = "场所类型，针对app端", position = 5)
    private String typeName;

    /**
     * 可容纳人数
     */
    @ApiModelProperty(value = "可容纳人数", position = 6)
    private Integer capacity;

    /**
     * 面积(平方米)
     */
    @ApiModelProperty(value = "面积(平方米)", position = 7)
    private Double area;

    /**
     * 类别，1.自有；2.第三方
     */
    @ApiModelProperty(value = "类别，1.自有；2.第三方", position = 8)
    private String category;

    /**
     * 位置
     */
    @ApiModelProperty(value = "位置", position = 9)
    private String addr;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", position = 10)
    private String introduction;


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

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "CPPlaceInfoVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", cpName='" + cpName + '\'' +
                ", name='" + name + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", capacity=" + capacity +
                ", area=" + area +
                ", category='" + category + '\'' +
                ", addr='" + addr + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}