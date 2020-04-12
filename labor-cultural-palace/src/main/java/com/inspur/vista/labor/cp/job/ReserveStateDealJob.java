package com.inspur.vista.labor.cp.job;

import com.inspur.vista.labor.cp.service.CPReserveInfoService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @Title: ReserveStateDealJob
 * @Description: 订单状态处理任务
 * @Author: gengpeng
 * @CreateDate: 2020/3/24 18:12
 * @Version: 1.0
 */
@Component
public class ReserveStateDealJob extends QuartzJobBean {

    @Autowired
    private CPReserveInfoService reserveInfoService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String reserveId = context.getJobDetail().getJobDataMap().getString("reserveId");
        // 此处是否需要加锁
        reserveInfoService.closeReserve(reserveId);
    }
}
