package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPReserveInfoListVO
 * @Description: 场地预约记录
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@ApiModel(value = "预约记录列表", description = "预约记录列表")
public class CPReserveInfoListVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码",  position = 2)
    private String userCode;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名",  position = 2)
    private String userName;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户手机号",  position = 3)
    private String userPhone;

    /**
     * 所属工会id
     */
    @ApiModelProperty(value = "所属工会id，h5通过app获取，后台通过组织树获取",position = 4)
    private String laborId;

    /**
     * 所属工会名称
     */
    @ApiModelProperty(value = "所属工会名称",  position = 5)
    private String laborName;

    /**
     * 预约方式，1.App；2.现场
     */
    @ApiModelProperty(value = "预约方式，1.App；2.现场",  position = 6)
    private String type;

    /**
     * 使用人数
     */
    @ApiModelProperty(value = "使用人数", position = 7)
    private Integer personNum;

    /**
     * 预约状态，0.待支付；1.预约成功；2.预约失败；3.预约关闭；4.取消预约
     */
    @ApiModelProperty(value = "预约状态，0.待支付；1.预约成功；2.预约失败；3.预约关闭；4.取消预约；5.完成", position = 8)
    private Integer state;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间", position = 9)
    private String createTime;

    /**
     * 取消预约时间
     */
    @ApiModelProperty(value = "取消预约时间", position = 10)
    private String cancelTime;

    /**
     * 文化宫名称
     */
    @ApiModelProperty(value = "文化宫名称", position = 11)
    private String cpName;

    /**
     * 场地名称
     */
    @ApiModelProperty(value = "场地名称", position = 12)
    private String courtName;

    /**
     * 是否评价
     */
    @ApiModelProperty(value = "是否评价", position = 13)
    private Integer isEvaluate;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", position = 14)
    private String outTradeNo;


    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", position = 15)
    private String payMoney;

    /**
     * 生成二维码的加密参数
     */
    @ApiModelProperty(value = "生成二维码的加密参数", position = 16)
    private String qrCodeParam;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public Integer getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(Integer isEvaluate) {
        this.isEvaluate = isEvaluate;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getQrCodeParam() {
        return qrCodeParam;
    }

    public void setQrCodeParam(String qrCodeParam) {
        this.qrCodeParam = qrCodeParam;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CPReserveInfoListVO{" +
                "id='" + id + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", laborId='" + laborId + '\'' +
                ", laborName='" + laborName + '\'' +
                ", type='" + type + '\'' +
                ", personNum=" + personNum +
                ", state=" + state +
                ", createTime='" + createTime + '\'' +
                ", cancelTime='" + cancelTime + '\'' +
                ", cpName='" + cpName + '\'' +
                ", courtName='" + courtName + '\'' +
                ", isEvaluate=" + isEvaluate +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", payMoney='" + payMoney + '\'' +
                ", qrCodeParam='" + qrCodeParam + '\'' +
                '}';
    }
}