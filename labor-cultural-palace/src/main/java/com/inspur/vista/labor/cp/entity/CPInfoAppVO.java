package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPInfoEntity
 * @Description: 文化宫基本信息VO
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 16:15
 * @Version: 1.0
 */
@ApiModel(value = "app端文化宫信息", description = "app端文化宫信息")
public class CPInfoAppVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫名称
     */
    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    /**
     * 服务设施，逗号分隔
     */
    @ApiModelProperty(value = "服务设施，逗号分隔", position = 3)
    private String serviceFacilities;

    /**
     * 负责人联系方式
     */
    @ApiModelProperty(value = "负责人联系方式", position = 4)
    private String officialsPhone;

    /**
     * 营业时间
     */
    @ApiModelProperty(value = "营业时间", position = 5)
    private String businessTime;

    /**
     * 联系人联系方式
     */
    @ApiModelProperty(value = "联系人的联系方式", position = 6)
    private String contactPhone;

    /**
     * 停车场车位数
     */
    @ApiModelProperty(value = "停车场车位数", position = 7)
    private int parkNum;

    /**
     * 所在区划编码
     */
    @ApiModelProperty(value = "所在区划编码", position = 8)
    private String district;

    /**
     * 所在地址
     */
    @ApiModelProperty(value = "所在地址", position = 9)
    private String address;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", position = 10)
    private String introduction;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", position = 11)
    private Double lng;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", position = 12)
    private Double lat;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式", position = 13)
    private String payWay;

    /**
     * 是否全天
     */
    @ApiModelProperty(value = "是否全天", position = 14)
    private Integer isAllDay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceFacilities() {
        return serviceFacilities;
    }

    public void setServiceFacilities(String serviceFacilities) {
        this.serviceFacilities = serviceFacilities;
    }

    public String getOfficialsPhone() {
        return officialsPhone;
    }

    public void setOfficialsPhone(String officialsPhone) {
        this.officialsPhone = officialsPhone;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getParkNum() {
        return parkNum;
    }

    public void setParkNum(int parkNum) {
        this.parkNum = parkNum;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Integer getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(Integer isAllDay) {
        this.isAllDay = isAllDay;
    }

    @Override
    public String toString() {
        return "CPInfoAppVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceFacilities='" + serviceFacilities + '\'' +
                ", officialsPhone='" + officialsPhone + '\'' +
                ", businessTime='" + businessTime + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", parkNum=" + parkNum +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", introduction='" + introduction + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", payWay='" + payWay + '\'' +
                ", isAllDay=" + isAllDay +
                '}';
    }
}
