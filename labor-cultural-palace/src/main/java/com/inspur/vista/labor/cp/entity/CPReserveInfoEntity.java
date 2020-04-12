package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPReserveInfoEntity
 * @Description: 场地预约记录
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@ApiModel(value = "预约记录信息", description = "预约记录信息")
public class CPReserveInfoEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 场地id
     */
    @ApiModelProperty(value = "场地id", position = 2)
    private String courtId;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", position = 3)
    private String cpId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",hidden = true)
    private String outTradeNo;

    /**
     * 用户编码，现场预约不需要填
     */
    @ApiModelProperty(value = "用户编码", position = 4)
    private String userCode;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户id", required = true, example = "张三", position = 5)
    private String userName;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户手机号", required = true, example = "18200000033", position = 6)
    private String userPhone;

    /**
     * 所属工会id
     */
    @ApiModelProperty(value = "所属工会id，h5通过app获取，后台通过组织树获取", example = "O0000000000000000514", position = 7)
    private String laborId;

    /**
     * 所属工会名称
     */
    @ApiModelProperty(value = "所属工会名称", example = "PA工会", position = 8)
    private String laborName;

    /**
     * 预约方式，1.App；2.现场
     */
    @ApiModelProperty(value = "预约方式，1.App；2.现场", example = "1", position = 9)
    private String type;

    /**
     * 使用人数
     */
    @ApiModelProperty(value = "使用人数", example = "5", position = 10)
    private Integer personNum;

    /**
     * 应支付金额，元
     */
    @ApiModelProperty(value = "应支付预约金额，定金", hidden = true)
    private Double reserveMoney;

    /**
     * 应支付金额，元
     */
    @ApiModelProperty(value = "应支付使用金额", hidden = true)
    private Double userMoney;

    /**
     * 预约状态，0.待支付；1.预约成功；2.预约失败；3.预约关闭；4.取消预约
     */
    @ApiModelProperty(value = "预约状态，0.待支付；1.预约成功；2.预约失败；3.预约关闭；4.取消预约；5.完成", hidden = true)
    private Integer state;

    /**
     * 取消预约时间
     */
    @ApiModelProperty(value = "取消预约时间", hidden = true)
    private Date cancelTime;

    /**
     * 取消预约原因类型
     */
    @ApiModelProperty(value = "取消预约原因类型", position = 11)
    private Integer cancelReasonType;

    /**
     * 取消预约原因
     */
    @ApiModelProperty(value = "取消预约原因内容，如果类型为其他时填写", position = 12)
    private String cancelReasonExt;

    /**
     * 交易流水id
     */
    @ApiModelProperty(value = "交易流水id", hidden = true)
    private String orderId;

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
     * 最后更新人
     */
    @ApiModelProperty(value = "最后更新人", hidden = true)
    private String modifier;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Double getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Double reserveMoney) {
        this.reserveMoney = reserveMoney;
    }

    public Double getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Double userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Integer getCancelReasonType() {
        return cancelReasonType;
    }

    public void setCancelReasonType(Integer cancelReasonType) {
        this.cancelReasonType = cancelReasonType;
    }

    public String getCancelReasonExt() {
        return cancelReasonExt;
    }

    public void setCancelReasonExt(String cancelReasonExt) {
        this.cancelReasonExt = cancelReasonExt;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "CPReserveInfoEntity{" +
                "id='" + id + '\'' +
                ", courtId='" + courtId + '\'' +
                ", cpId='" + cpId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", laborId='" + laborId + '\'' +
                ", laborName='" + laborName + '\'' +
                ", type='" + type + '\'' +
                ", personNum=" + personNum +
                ", reserveMoney=" + reserveMoney +
                ", userMoney=" + userMoney +
                ", state=" + state +
                ", cancelTime=" + cancelTime +
                ", cancelReasonType=" + cancelReasonType +
                ", cancelReasonExt='" + cancelReasonExt + '\'' +
                ", orderId='" + orderId + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}