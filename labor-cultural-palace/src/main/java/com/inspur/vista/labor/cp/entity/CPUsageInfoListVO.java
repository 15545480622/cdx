package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPUsageInfoListVO
 * @Description: 场所使用记录列表信息
 * @authur: wangxueying01
 * @CreatDate: 2020/3/13 10:18
 */
@ApiModel(value = "场所使用记录列表信息", description = "场所使用记录列表信息")
public class CPUsageInfoListVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 场所id
     */
    @ApiModelProperty(value = "场所id", position = 2)
    private String placeId;

    /**
     * 预约记录id
     */
    @ApiModelProperty(value = "预约记录id", position = 3)
    private String reserveId;

    /**
     * 用户编码，现场预约不需要填
     */
    @ApiModelProperty(value = "用户编码，现场预约不需要填", position = 4)
    private String userCode;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", position = 5)
    private String userName;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话", position = 6)
    private String userPhone;

    /**
     * 所属工会id
     */
    @ApiModelProperty(value = "所属工会id", position = 7)
    private String laborId;

    /**
     * 工会名称
     */
    @ApiModelProperty(value = "所属工会名称", position = 8)
    private String laborName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getLaborId() {
        return laborId;
    }

    public void setLaborId(String laborId) {
        this.laborId = laborId;
    }

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CPUsageInfoListVO{" +
                "id='" + id + '\'' +
                ", placeId='" + placeId + '\'' +
                ", reserveId='" + reserveId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", laborId='" + laborId + '\'' +
                ", laborName='" + laborName + '\'' +
                '}';
    }
}
