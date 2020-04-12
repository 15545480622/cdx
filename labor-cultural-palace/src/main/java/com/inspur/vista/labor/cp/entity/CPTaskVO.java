package com.inspur.vista.labor.cp.entity;


import java.io.Serializable;

/**
 * @Title: PubTaskTodoDetailVO
 * @Description: 待办任务
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public class CPTaskVO implements Serializable {
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

    @Override
    public String toString() {
        return "CPTaskVO{" +
                "bsnId='" + bsnId + '\'' +
                ", taskType=" + taskType +
                ", taskName='" + taskName + '\'' +
                ", handleOrgan='" + handleOrgan + '\'' +
                ", handler='" + handler + '\'' +
                '}';
    }
}