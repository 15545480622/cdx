package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPReserveUserInfoVO
 * @Description: 预约用户VO
 * @authur: wangxueying01
 * @CreatDate: 2020/3/18 9:54
 */
@ApiModel(value = "预约用户详情", description = "预约用户详情")
public class CPReserveUserInfoVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 预约记录id
     */
    @ApiModelProperty(value = "预约记录id", position = 2)
    private String reserveId;

    /**
     * 用户编码，现场预约不需要填
     */
    @ApiModelProperty(value = "用户编码", position = 3)
    private String userCode;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户手机号", position = 4)
    private String userPhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CPReserveUserInfoVO{" +
                "id='" + id + '\'' +
                ", reserveId='" + reserveId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
