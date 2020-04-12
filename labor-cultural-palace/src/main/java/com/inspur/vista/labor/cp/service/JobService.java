package com.inspur.vista.labor.cp.service;


import com.inspur.vista.labor.cp.entity.QuartzJobAdd;

/**
 * @Title: JobService
 * @Description: //TODO
 * @Author: gengpeng
 * @CreateDate: 2020/1/6 11:29
 * @Version: 1.0
 */
public interface JobService {

    /**
     * 保存job
     * @param quartz
     */
    void saveJob(QuartzJobAdd quartz);

    /**
     * 触发job
     * @param jobName
     * @param jobGroup
     * @return
     */
    void triggerJob(String jobName, String jobGroup);

    /**
     * 暂停job
     * @param jobName
     * @param jobGroup
     * @return
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复job
     * @param jobName
     * @param jobGroup
     * @return
     */
    void resumeJob(String jobName, String jobGroup);

    /**
     * 移除job
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    void removeJob(String jobName, String jobGroup);

    /**
     * 中断
     *
     * @param jobName
     * @param jobGroup
     */
    void interrupt(String jobName, String jobGroup);

}
