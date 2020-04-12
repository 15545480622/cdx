package com.inspur.vista.labor.cp.entity;


import java.io.Serializable;

/**
 * @Title: PubTaskHistoryDetailVO
 * @Description: 历史任务
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
public class PubTaskHistoryDetailVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 业务id
     */
    private Long bsnId;

    /**
     * 业务类型
     */
    private Integer bsnType;

    /**
     * 任务名称
     */
    private String taskTitle;

    /**
     * 处理单位
     */
    private String handleOrgan;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getBsnId() {
        return bsnId;
    }

    public void setBsnId(Long bsnId) {
        this.bsnId = bsnId;
    }

    public Integer getBsnType() {
        return bsnType;
    }

    public void setBsnType(Integer bsnType) {
        this.bsnType = bsnType;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getHandleOrgan() {
        return handleOrgan;
    }

    public void setHandleOrgan(String handleOrgan) {
        this.handleOrgan = handleOrgan;
    }

    @Override
    public String toString() {
        return "PubTaskHistoryEntity{" +
                ", id='" + id + '\'' +
                ", taskId='" + taskId + '\'' +
                ", bsnId='" + bsnId + '\'' +
                ", bsnType='" + bsnType + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", handleOrgan='" + handleOrgan + '\'' +
                '}';
    }

}