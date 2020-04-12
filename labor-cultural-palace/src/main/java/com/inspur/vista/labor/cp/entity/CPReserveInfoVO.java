package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: CPReserveInfoDetailVO
 * @Description: 场地预约记录
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@ApiModel(value = "预约记录详情", description = "预约记录详情")
public class CPReserveInfoVO implements Serializable {

    /**
     * id
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
    @ApiModelProperty(value = "文化宫名称", position = 2)
    private String cpName;

    /**
     * 场地id
     */
    @ApiModelProperty(value = "场地id", position = 3)
    private String courtId;

    /**
     * 场地id
     */
    @ApiModelProperty(value = "场地名称", position = 4)
    private String courtName;


    /**
     * 用户编码，现场预约不需要填
     */
    @ApiModelProperty(value = "用户编码", position = 5)
    private String userCode;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", position = 6)
    private String userName;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户手机号", position = 7)
    private String userPhone;

    /**
     * 同伴电话
     */
    @ApiModelProperty(value = "同伴手机号(多个手机号用逗号分隔)", position = 8)
    private String companionPhone;

    /**
     * 所属工会id
     */
    @ApiModelProperty(value = "所属工会id", position = 9)
    private String laborId;

    /**
     * 所属工会名称
     */
    @ApiModelProperty(value = "所属工会名称", position = 10)
    private String laborName;

    /**
     * 预约方式，1.App；2.现场
     */
    @ApiModelProperty(value = "预约方式，1.App；2.现场", position = 11)
    private String type;

    /**
     * 使用人数
     */
    @ApiModelProperty(value = "使用人数", position = 12)
    private Integer personNum;

    /**
     * 应支付金额，元
     */
    @ApiModelProperty(value = "预约金额", position = 13)
    private Double reserveMoney;

    /**
     * 应支付金额，元
     */
    @ApiModelProperty(value = "使用金额", position = 14)
    private Double useMoney;

    /**
     * 预约状态，0.待支付；1.预约成功；2.预约失败；3.预约关闭；4.取消预约
     */
    @ApiModelProperty(value = "预约状态，0.待支付；1.预约成功；2.预约失败；3.预约关闭；4.取消预约；5.完成", position = 15)
    private Integer state;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间", position = 16)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 支付剩余秒数
     */
    @ApiModelProperty(value = "支付剩余秒数", position = 17)
    private Long endPaySecond;

    /**
     * 取消预约时间
     */
    @ApiModelProperty(value = "取消预约时间", position = 18)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;

    /**
     * 取消预约原因类型
     */
    @ApiModelProperty(value = "取消预约原因类型", position = 19)
    private Integer cancelReasonType;

    /**
     * 取消预约原因
     */
    @ApiModelProperty(value = "取消预约原因内容，如果类型为其他时填写", position = 20)
    private String cancelReasonExt;

    /**
     * 交易流水id
     */
    @ApiModelProperty(value = "交易流水id", position = 21)
    private String orderId;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态，1.支付成功；2、支付失败；3、退款中；4、已退款；5、部分退款；6、退款失败", position = 22)
    private Integer payState;


    /**
     * 预约的场次列表
     */
    @ApiModelProperty(value = "预约的场次列表", position = 23)
    private List<CPReserveSceneVO> reserveSceneList;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", position = 24)
    private String outTradeNo;


    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式，1.支付宝；2.微信", position = 25)
    private int payType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCompanionPhone() {
        return companionPhone;
    }

    public void setCompanionPhone(String companionPhone) {
        this.companionPhone = companionPhone;
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

    public Double getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(Double useMoney) {
        this.useMoney = useMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public List<CPReserveSceneVO> getReserveSceneList() {
        return reserveSceneList;
    }

    public void setReserveSceneList(List<CPReserveSceneVO> reserveSceneList) {
        this.reserveSceneList = reserveSceneList;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public Long getEndPaySecond() {
        return endPaySecond;
    }

    public void setEndPaySecond(Long endPaySecond) {
        this.endPaySecond = endPaySecond;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CPReserveInfoVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", cpName='" + cpName + '\'' +
                ", courtId='" + courtId + '\'' +
                ", courtName='" + courtName + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", companionPhone='" + companionPhone + '\'' +
                ", laborId='" + laborId + '\'' +
                ", laborName='" + laborName + '\'' +
                ", type='" + type + '\'' +
                ", personNum=" + personNum +
                ", reserveMoney=" + reserveMoney +
                ", useMoney=" + useMoney +
                ", state=" + state +
                ", createTime=" + createTime +
                ", endPaySecond=" + endPaySecond +
                ", cancelTime=" + cancelTime +
                ", cancelReasonType=" + cancelReasonType +
                ", cancelReasonExt='" + cancelReasonExt + '\'' +
                ", orderId='" + orderId + '\'' +
                ", payState=" + payState +
                ", reserveSceneList=" + reserveSceneList +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", payType=" + payType +
                '}';
    }
}