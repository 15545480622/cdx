package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.entity.QuartzJobAdd;
import com.inspur.vista.labor.cp.service.JobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Title: JobServiceImpl
 * @Description: //TODO
 * @Author: gengpeng
 * @CreateDate: 2020/1/6 11:30
 * @Version: 1.0
 */
@Service
public class JobServiceImpl implements JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    private static final String TRIGGER_IDENTITY = "trigger";

    private static final String PARAM_NAME = "paramName";

    private static final String PARAM_VALUE = "paramValue";

    private static final String SCHEDULER_INSTANCE_NAME = "schedulerInstanceName";

    @Value("${spring.quartz.properties.org.quartz.scheduler.instanceName}")
    private String schedulerInstanceName;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void saveJob(QuartzJobAdd quartz) {
        try {
            //如果是修改  展示旧的 任务
            if (quartz.getOldJobGroup() != null && !"".equals(quartz.getOldJobGroup())) {
                JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
                scheduler.deleteJob(key);
            }

            //构建job信息
            Class cls = Class.forName(quartz.getJobClassName());
            cls.newInstance();
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup())
                    .withDescription(quartz.getDescription()).build();
            putDataMap(job, quartz);

            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression().trim());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_IDENTITY + quartz.getJobName(), quartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            logger.error("保存任务失败", e);
        }
    }


    private void putDataMap(JobDetail job, QuartzJobAdd quartz) {

        // 将scheduler instanceName存入jobDataMap，方便业务job中进行数据库操作
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put(SCHEDULER_INSTANCE_NAME, schedulerInstanceName);

        List<Map<String, Object>> jobDataParam = quartz.getJobDataParam();
        if (null != jobDataParam && !jobDataParam.isEmpty()) {
            for (Map<String, Object> map : jobDataParam) {
                for (String key : map.keySet()) {
                    jobDataMap.put(key, map.get(key));
                }
            }
        }
    }

    @Override
    public void triggerJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(key);
            System.out.println("触发：" + jobName);
        } catch (SchedulerException e) {
            logger.error("触发任务失败", e);
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            logger.error("暂停任务失败", e);
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            logger.error("恢复任务失败", e);
        }
    }

    @Override
    public void removeJob(String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_IDENTITY + jobName, jobGroup);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            System.out.println("removeJob:" + JobKey.jobKey(jobName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 中断
     *
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void interrupt(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.interrupt(key);
        } catch (SchedulerException e) {
            logger.error("中断任务失败", e);
        }
    }
}
