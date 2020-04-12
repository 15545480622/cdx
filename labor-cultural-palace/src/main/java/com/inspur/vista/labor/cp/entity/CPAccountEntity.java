package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPAccountEntity
 * @Description: 文化宫收款账号
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 18:35
 * @Version: 1.0
 */
public class CPAccountEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 文化宫id
     */
    private String cpId;

    /**
     * 账号类型，1.支付宝；2.微信；3.银行卡
     */
    private Integer type;

    /**
     * 账号
     */
    private String account;

    /**
     * 商家id
     */
    private String sellerId;

    /**
     * 支付宝的授权token
     */
    private String alipayAuthToken;

    /**
     * 启用时间
     */
    private Date activeTime;

    /**
     * 失效时间
     */
    private Date failureTime;


    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    public String getAlipayAuthToken() {
        return alipayAuthToken;
    }

    public void setAlipayAuthToken(String alipayAuthToken) {
        this.alipayAuthToken = alipayAuthToken;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "CPAccountEntity{" +
                "id=" + id +
                ", cpId='" + cpId + '\'' +
                ", type=" + type +
                ", account='" + account + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", alipayAuthToken='" + alipayAuthToken + '\'' +
                ", activeTime=" + activeTime +
                ", failureTime=" + failureTime +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
