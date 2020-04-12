package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Title: CPApplyAccountEntity
 * @Description: 账号变更实体类
 * @Author: gengpeng
 * @CreateDate: 2019/12/14 14:38
 * @Version: 1.0
 */
@ApiModel(value = "账号新增", description = "账号新增")
public class CPAccountAdd {

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id",required = true,position = 1)
    private String cpId;

    /**
     * 账号类型，1.支付宝；2.微信；3.银行卡
     */
    @ApiModelProperty(value = "账号类型，1.支付宝；2.微信；3.银行卡",required = true,position = 2)
    private Integer accountType;

    /**
     * 账号，邮箱等
     */
    @ApiModelProperty(value = "账号，邮箱等",required = true,position = 3)
    private String account;

    /**
     * 支付宝的授权码
     */
    @ApiModelProperty(value = "支付宝的授权码",required = false,position = 4)
    private String alipayAuthoCde;

    /**
     * 启用时间
     */
    @ApiModelProperty(value = "启用时间",required = false,position = 5)
    private Date activeTime;

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlipayAuthoCde() {
        return alipayAuthoCde;
    }

    public void setAlipayAuthoCde(String alipayAuthoCde) {
        this.alipayAuthoCde = alipayAuthoCde;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    @Override
    public String toString() {
        return "CPApplyAccountAdd{" +
                "cpId=" + cpId +
                ", accountType=" + accountType +
                ", account='" + account + '\'' +
                ", alipayAuthoCde='" + alipayAuthoCde + '\'' +
                ", activeTime=" + activeTime +
                '}';
    }
}
