package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPPlaceStarListVO
 * @Description: 场所评价星级
 * @authur: wangxueying01
 * @CreatDate: 2020/3/11 14:09
 */
@ApiModel(value = "场所评价星级列表信息", description = "场所评价星级列表信息")
public class CPPlaceStarListVO  implements Serializable {

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

    /**
     * 整体星级
     */
    @ApiModelProperty(value = "整体星级", position = 11)
    private Double star;

    /**
     * 配套设施星级
     */
    @ApiModelProperty(value = "配套设施星级", position = 12)
    private Double supportingFacilityStar;

    /**
     * 服务态度星级
     */
    @ApiModelProperty(value = "服务态度星级", position = 13)
    private Double serviceAttitudeStar;

    /**
     * 卫生情况星级
     */
    @ApiModelProperty(value = "卫生情况星级", position = 14)
    private Double hygienicConditionStar;

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

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Double getSupportingFacilityStar() {
        return supportingFacilityStar;
    }

    public void setSupportingFacilityStar(Double supportingFacilityStar) {
        this.supportingFacilityStar = supportingFacilityStar;
    }

    public Double getServiceAttitudeStar() {
        return serviceAttitudeStar;
    }

    public void setServiceAttitudeStar(Double serviceAttitudeStar) {
        this.serviceAttitudeStar = serviceAttitudeStar;
    }

    public Double getHygienicConditionStar() {
        return hygienicConditionStar;
    }

    public void setHygienicConditionStar(Double hygienicConditionStar) {
        this.hygienicConditionStar = hygienicConditionStar;
    }

    @Override
    public String toString() {
        return "CPPlaceStarListVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", name='" + name + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", area=" + area +
                ", category='" + category + '\'' +
                ", addr='" + addr + '\'' +
                ", introduction='" + introduction + '\'' +
                ", star=" + star +
                ", supportingFacilityStar=" + supportingFacilityStar +
                ", serviceAttitudeStar=" + serviceAttitudeStar +
                ", hygienicConditionStar=" + hygienicConditionStar +
                '}';
    }
}


