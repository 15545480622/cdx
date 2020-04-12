package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPUsageInfoEntity
 * @Description: 场所使用记录
 * @authur: wangxueying01
 * @CreatDate: 2020/3/13 10:18
 */
@ApiModel(value = "场所使用记录信息", description = "场所使用记录信息")
public class CPUsageInfoEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 场所id
     */
    @ApiModelProperty(value = "场所id", required = true, example = "123", position = 2)
    private String placeId;

    /**
     * 预约记录id
     */
    @ApiModelProperty(value = "预约记录id", example = "123", position = 3)
    private String reserveId;

    /**
     * 用户编码，现场预约不需要填
     */
    @ApiModelProperty(value = "用户编码，现场预约不需要填", example = "123", position = 4)
    private String userCode;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话", example = "13211111111", position = 6)
    private String userPhone;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CPUsageInfoEntity{" +
                "id='" + id + '\'' +
                ", placeId='" + placeId + '\'' +
                ", reserveId='" + reserveId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
