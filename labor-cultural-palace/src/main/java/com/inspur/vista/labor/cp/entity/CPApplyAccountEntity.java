package com.inspur.vista.labor.cp.entity;

import java.util.Date;

/**
 * @Title: CPApplyAccountEntity
 * @Description: 账号变更实体类
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 14:38
 * @Version: 1.0
 */
public class CPApplyAccountEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 原因
     */
    private String reason;

    /**
     * 状态，0. 暂存；1.审核中；2.完成
     */
    private int state;

    /**
     * 文化宫id
     */
    private Long cpId;

    /**
     * 账号类型
     */
    private Integer accountType;

    /**
     * 新账号
     */
    private String newAccount;

    /**
     * 支付宝的授权token
     */
    private String alipayAuthToken;

    /**
     * 启用时间
     */
    private Date activeTime;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(String newAccount) {
        this.newAccount = newAccount;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
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

    public String getAlipayAuthToken() {
        return alipayAuthToken;
    }

    public void setAlipayAuthToken(String alipayAuthToken) {
        this.alipayAuthToken = alipayAuthToken;
    }

    @Override
    public String toString() {
        return "CPApplyAccountChangeEntity{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", state=" + state +
                ", cpId=" + cpId +
                ", accountType=" + accountType +
                ", newAccount='" + newAccount + '\'' +
                ", alipayAuthToken='" + alipayAuthToken + '\'' +
                ", activeTime=" + activeTime +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
