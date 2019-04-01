package com.feri.admin.quartz;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 *@Author feri
 *@Date Created in 2019/3/28 15:22
 */
public class Quartz_Main {
    public static void main(String[] args) {
        try {
            Scheduler scheduler=new StdSchedulerFactory().getScheduler();
            Trigger trigger=TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();
            JobDetail jobDetail=JobBuilder.newJob(EsJob.class).build();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
