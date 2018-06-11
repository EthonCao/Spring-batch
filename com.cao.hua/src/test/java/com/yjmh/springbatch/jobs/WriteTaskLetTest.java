package com.yjmh.springbatch.jobs;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WriteTaskLetTest {

	@Test
	public void executeJob() {
		ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("batch.xml");
        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("ArchiveDBTableData");
        JobExecution execute;
		try {
			execute = launcher.run(job, new JobParameters());
			System.out.println("Job Execute Done: " + execute.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		context.close();
	}

}
