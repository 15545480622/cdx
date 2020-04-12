package com.inspur.vista.labor.cp.entity;

import java.util.Date;

/**
 * @Title: CPApplyTrackEntiry
 * @Description: 申请轨迹
 * @Author: gengpeng
 * @CreateDate: 2020/3/31 15:47
 * @Version: 1.0
 */
public class CPApplyTrackEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 申请单id
     */
    private String applyId;

    /**
     * 阶段
     */
    private Integer step;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理人所属工会
     */
    private String handleLaborId;

    /**
     * 处理结果，1.通过；0.未通过
     */
    private int handleResult;

    /**
     * 处理意见
     */
    private String handleOpinions;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandleLaborId() {
        return handleLaborId;
    }

    public void setHandleLaborId(String handleLaborId) {
        this.handleLaborId = handleLaborId;
    }

    public int getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(int handleResult) {
        this.handleResult = handleResult;
    }

    public String getHandleOpinions() {
        return handleOpinions;
    }

    public void setHandleOpinions(String handleOpinions) {
        this.handleOpinions = handleOpinions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CPApplyTrackEntiry{" +
                "id=" + id +
                ", applyId='" + applyId + '\'' +
                ", step=" + step +
                ", handler='" + handler + '\'' +
                ", handleLaborId='" + handleLaborId + '\'' +
                ", handleResult=" + handleResult +
                ", handleOpinions='" + handleOpinions + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
