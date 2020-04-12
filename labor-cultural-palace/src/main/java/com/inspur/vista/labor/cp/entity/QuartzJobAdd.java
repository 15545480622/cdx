package com.inspur.vista.labor.cp.entity;

import java.util.List;
import java.util.Map;

/**
 * @Title: QuartzJob
 * @Description: //TODO
 * @Author: gengpeng
 * @CreateDate: 2020/1/6 11:35
 * @Version: 1.0
 */
public class QuartzJobAdd {

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 执行类包名类名
     */
    private String jobClassName;

    /**
     * 执行时间的cron表达式
     */
    private String cronExpression;

    /**
     * 任务名称 用于修改
     */
    private String oldJobName;

    /**
     * 任务分组 用于修改
     */
    private String oldJobGroup;

    private List<Map<String, Object>> jobDataParam;

    public QuartzJobAdd() {
        super();
    }

    public QuartzJobAdd(String jobName, String jobGroup, String description, String jobClassName, String cronExpression) {
        super();
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.description = description;
        this.jobClassName = jobClassName;
        this.cronExpression = cronExpression;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getOldJobName() {
        return oldJobName;
    }

    public void setOldJobName(String oldJobName) {
        this.oldJobName = oldJobName;
    }

    public String getOldJobGroup() {
        return oldJobGroup;
    }

    public void setOldJobGroup(String oldJobGroup) {
        this.oldJobGroup = oldJobGroup;
    }

    public List<Map<String, Object>> getJobDataParam() {
        return jobDataParam;
    }

    public void setJobDataParam(List<Map<String, Object>> jobDataParam) {
        this.jobDataParam = jobDataParam;
    }
}
