package com.inspur.vista.labor.cp.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * @Title: PubTaskHistoryEntity
 * @Description: 历史任务
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public class CPTaskHistoryEntity implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 代办任务类型
     */
    private Integer taskType;
    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 处理单位
     */
    private String handleOrgan;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBsnId() {
        return bsnId;
    }

    public void setBsnId(String bsnId) {
        this.bsnId = bsnId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getHandleOrgan() {
        return handleOrgan;
    }

    public void setHandleOrgan(String handleOrgan) {
        this.handleOrgan = handleOrgan;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CPTaskHistoryEntity{" +
                "id='" + id + '\'' +
                ", bsnId='" + bsnId + '\'' +
                ", taskType=" + taskType +
                ", taskName='" + taskName + '\'' +
                ", handleOrgan='" + handleOrgan + '\'' +
                ", handler='" + handler + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}