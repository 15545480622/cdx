package com.inspur.vista.labor.cp.entity;

import java.util.Date;

/**
 * @Title: CPApplyInfoEntity
 * @Description: 申请表信息
 * @Author: gengpeng
 * @CreateDate: 2020/3/31 15:56
 * @Version: 1.0
 */
public class CPApplyInfoEntity {

    /**
     * 申请表id
     */
    private String applyId;

    /**
     * 申请表类型
     */
    private Integer applyType;

    /**
     * 申请原因
     */
    private String applyReason;

    /**
     * 申请表所处阶段
     */
    private int step;


    /**
     * 创建人
     */
    private String creator;

    /**
     * 最后更新人
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

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
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

    @Override
    public String toString() {
        return "CPApplyInfoEntity{" +
                "applyId='" + applyId + '\'' +
                ", applyType=" + applyType +
                ", applyReason='" + applyReason + '\'' +
                ", step=" + step +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
