package com.inspur.vista.labor.cp.entity;

/**
 * @Title: CPApplyInfoEntity
 * @Description: 申请表信息
 * @Author: gengpeng
 * @CreateDate: 2020/3/31 15:56
 * @Version: 1.0
 */
public class CPApplyInfoVO {

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

    @Override
    public String toString() {
        return "CPApplyInfoVO{" +
                "applyId='" + applyId + '\'' +
                ", applyType=" + applyType +
                ", applyReason='" + applyReason + '\'' +
                ", step=" + step +
                '}';
    }
}
