package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPOrderEntity
 * @Description: 交易流水实体类
 * @authur: wangxueying01
 * @CreatDate: 2020/3/24 19:27
 */
@ApiModel(value = "交易流水", description = "交易流水")
public class CPOrderEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 交易编号
     */
    @ApiModelProperty(value = "交易编号", example = "1234", position = 2)
    private String tradeNo;

    /**
     * 支付方式，1.支付宝；2.微信；3.银行卡；4.现金
     */
    @ApiModelProperty(value = "支付方式，1.支付宝；2.微信；3.银行卡；4.现金", example = "1", position = 3)
    private Integer payType;

    /**
     * 收款账号
     */
    @ApiModelProperty(value = "收款账号", example = "1234", position = 4)
    private String receivingAccount;

    /**
     * 支付账号
     */
    @ApiModelProperty(value = "支付账号", example = "1234", position = 5)
    private String payAccount;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间", example = "2020-03-25", position = 6)
    private Date payTime;

    /**
     * 支付金额，元
     */
    @ApiModelProperty(value = "支付金额，元", example = "5.0", position = 7)
    private Double payMoney;

    /**
     * 退款时间
     */
    @ApiModelProperty(value = "退款时间", example = "2020-03-25", position = 8)
    private Date refundTime;

    /**
     * 退款金额，元
     */
    @ApiModelProperty(value = "退款金额，元", example = "5.0", position = 9)
    private Double refundMoney;

    /**
     * 支付状态，1.支付成功；2、支付失败；3、退款审核；4、退款中；5、已退款
     */
    @ApiModelProperty(value = "支付状态，1.支付成功；2、支付失败；3、退款审核；4、退款中；5、已退款", hidden = true)
    private Integer state;

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
     * 修改人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
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

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    @Override
    public String toString() {
        return "CPOrderEntity{" +
                "id='" + id + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", payType=" + payType +
                ", receivingAccount='" + receivingAccount + '\'' +
                ", payAccount='" + payAccount + '\'' +
                ", payTime=" + payTime +
                ", payMoney=" + payMoney +
                ", refundTime=" + refundTime +
                ", refundMoney=" + refundMoney +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
