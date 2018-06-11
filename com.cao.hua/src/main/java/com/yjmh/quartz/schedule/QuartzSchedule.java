package com.yjmh.quartz.schedule;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.yjmh.quartz.jobs.WriteTaskLetJob;

public class QuartzSchedule {

	public void shecduleJob(Object o) {
		try {
			//创建调度器工厂
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			//创建调度器实例
			Scheduler scheduler = schedulerFactory.getScheduler();
			
			//创建JobDetail
			JobDetail jobDetail = JobBuilder.newJob(WriteTaskLetJob.class).build();
			
			//任务运行的时间，SimpleSchedle类型触发器有效
	        long time=  System.currentTimeMillis() + 3*1000L; //3秒后启动任务
	        Date startTime = new Date(time);
	        
	        //任务结束时间
	        long eTime=  System.currentTimeMillis() + 300*1000L; //5分钟后结束
	        Date endTime = new Date(eTime);
	        
	        //4.创建Trigger使用SimpleScheduleBuilder或者CronScheduleBuilder
	        Trigger trigger = TriggerBuilder.newTrigger()
	        		.startAt(startTime)
	        		.endAt(endTime).withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 * * * ?")) //3分钟一次, "0/3* * * * ?"3秒一次
	        		.build();
			
	        //注册定时任务和定时器
	        scheduler.scheduleJob(jobDetail, trigger);
	        //启动调度器
	        scheduler.start();
	        System.out.println("Job启动时间: " + new Date());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
