package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Title: CPInfoListVO
 * @Description: 列表
 * @Author: gengpeng
 * @CreateDate: 2020/3/5 15:51
 * @Version: 1.0
 */
@ApiModel(value = "文化宫列表信息", description = "文化宫列表信息")
public class CPInfoListVO {

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
     * 标签
     */
    @ApiModelProperty(value = "标签",  position = 3)
    private String tag;

    /**
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名", position = 4)
    private String officialsName;

    /**
     * 负责人联系方式
     */
    @ApiModelProperty(value = "负责人联系方式", position = 5)
    private String officialsPhone;

    /**
     * 成立时间
     */
    @ApiModelProperty(value = "成立时间", position = 6)
    private String establishDate;

    /**
     * 工会名称
     */
    @ApiModelProperty(value = "所属工会", position = 7)
    private String laborName;

    /**
     * 停车场车位数
     */
    @ApiModelProperty(value = "停车场车位数", position = 8)
    private int parkNum;

    /**
     * 营业时间
     */
    @ApiModelProperty(value = "营业时间", position = 9)
    private String businessTime;

    /**
     * 是否全天
     */
    @ApiModelProperty(value = "是否全天", position = 10)
    private Integer isAllDay;

    /**
     * 营业星期，逗号分隔
     */
    @ApiModelProperty(value = "营业星期，逗号分隔", position = 11)
    private String businessWeek;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", position = 12)
    private String contactName;

    /**
     * 联系人联系方式
     */
    @ApiModelProperty(value = "联系人的联系方式", position = 13)
    private String contactPhone;

    /**
     * 所在地址
     */
    @ApiModelProperty(value = "所在地址", position = 14)
    private String address;

    /**
     * 距离
     */
    @ApiModelProperty(value = "距离", position = 15)
    private String distance;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", position = 16)
    private Double lng;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", position = 17)
    private Double lat;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOfficialsName() {
        return officialsName;
    }

    public void setOfficialsName(String officialsName) {
        this.officialsName = officialsName;
    }

    public String getOfficialsPhone() {
        return officialsPhone;
    }

    public void setOfficialsPhone(String officialsPhone) {
        this.officialsPhone = officialsPhone;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public int getParkNum() {
        return parkNum;
    }

    public void setParkNum(int parkNum) {
        this.parkNum = parkNum;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public Integer getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(Integer isAllDay) {
        this.isAllDay = isAllDay;
    }

    public String getBusinessWeek() {
        return businessWeek;
    }

    public void setBusinessWeek(String businessWeek) {
        this.businessWeek = businessWeek;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    @Override
    public String toString() {
        return "CPInfoListVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", officialsName='" + officialsName + '\'' +
                ", officialsPhone='" + officialsPhone + '\'' +
                ", establishDate='" + establishDate + '\'' +
                ", laborName='" + laborName + '\'' +
                ", parkNum=" + parkNum +
                ", businessTime='" + businessTime + '\'' +
                ", isAllDay=" + isAllDay +
                ", businessWeek='" + businessWeek + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", address='" + address + '\'' +
                ", distance='" + distance + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
