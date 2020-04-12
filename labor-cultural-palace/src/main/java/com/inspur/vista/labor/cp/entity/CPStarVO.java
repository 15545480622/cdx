package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPStarVO
 * @Description: 文化宫评价星级
 * @authur: wangxueying01
 * @CreatDate: 2020/3/19 19:03
 */
@ApiModel(value = "文化宫评价星级", description = "文化宫评价星级")
public class CPStarVO implements Serializable {

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", position = 1)
    private String cpId;

    /**
     * 整体星级，1到5，表示1星到5星
     */
    @ApiModelProperty(value = "整体星级，1到5，表示1星到5星", position = 2)
    private Double star;

    /**
     * 配套设施星级
     */
    @ApiModelProperty(value = "配套设施星级", position = 3)
    private Double supportingFacilityStar;

    /**
     * 服务态度星级
     */
    @ApiModelProperty(value = "服务态度星级", position = 4)
    private Double serviceAttitudeStar;

    /**
     * 卫生情况星级
     */
    @ApiModelProperty(value = "卫生情况星级", position = 5)
    private Integer hygienicConditionStar;

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
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

    public Integer getHygienicConditionStar() {
        return hygienicConditionStar;
    }

    public void setHygienicConditionStar(Integer hygienicConditionStar) {
        this.hygienicConditionStar = hygienicConditionStar;
    }

    @Override
    public String toString() {
        return "CPStarVO{" +
                "cpId='" + cpId + '\'' +
                ", star=" + star +
                ", supportingFacilityStar=" + supportingFacilityStar +
                ", serviceAttitudeStar=" + serviceAttitudeStar +
                ", hygienicConditionStar=" + hygienicConditionStar +
                '}';
    }
}
