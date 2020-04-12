package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPAccountVO
 * @Description: 文化宫收款账号VO
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 18:41
 * @Version: 1.0
 */
public class CPAccountVO implements Serializable {

    /**
     * 账号类型，1.支付宝；2.微信；3.银行卡
     */
    private int type;

    /**
     * 账号
     */
    private String account;

    /**
     * 启用时间
     */
    private Date activeTime;

    /**
     * 失效时间
     */
    private Date failureTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    @Override
    public String toString() {
        return "CPAccountVO{" +
                "type=" + type +
                ", account='" + account + '\'' +
                ", activeTime=" + activeTime +
                ", failureTime=" + failureTime +
                '}';
    }
}
