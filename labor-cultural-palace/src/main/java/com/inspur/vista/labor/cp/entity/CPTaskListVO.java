package com.inspur.vista.labor.cp.entity;


import java.io.Serializable;

/**
 * @Title: PubTaskTodoListVO
 * @Description: 待办任务
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public class CPTaskListVO implements Serializable {

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 业务类型
     */
    private String bsnType;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 提交人所属文化宫
     */
    private String cpName;

    public String getBsnId() {
        return bsnId;
    }

    public void setBsnId(String bsnId) {
        this.bsnId = bsnId;
    }

    public String getBsnType() {
        return bsnType;
    }

    public void setBsnType(String bsnType) {
        this.bsnType = bsnType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    @Override
    public String toString() {
        return "CPTaskListVO{" +
                "bsnId='" + bsnId + '\'' +
                ", bsnType=" + bsnType +
                ", taskName='" + taskName + '\'' +
                ", submitter='" + submitter + '\'' +
                ", cpName='" + cpName + '\'' +
                '}';
    }
}