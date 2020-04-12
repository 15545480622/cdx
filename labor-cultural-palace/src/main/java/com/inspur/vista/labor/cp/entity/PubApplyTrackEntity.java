package com.inspur.vista.labor.cp.entity;

import java.util.Date;

/**
 * @Title: PubApplyTrackEntity
 * @Description: 申请轨迹
 * @Author: gengpeng
 * @CreateDate: 2020/2/26 18:20
 * @Version: 1.0
 */
public class PubApplyTrackEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 申请单id
     */
    private Long applyId;

    /**
     * 申请单类型
     */
    private int applyType;

    /**
     * 处理阶段
     */
    private int handlerState;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理人所属工会id
     */
    private String handleLaborId;

    /**
     * 驳回原因
     */
    private String reason;

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

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }

    public int getHandlerState() {
        return handlerState;
    }

    public void setHandlerState(int handlerState) {
        this.handlerState = handlerState;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        return "PubApplyTrackEntity{" +
                "id=" + id +
                ", applyId=" + applyId +
                ", applyType=" + applyType +
                ", handlerState=" + handlerState +
                ", handler='" + handler + '\'' +
                ", handleLaborId='" + handleLaborId + '\'' +
                ", reason='" + reason + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
