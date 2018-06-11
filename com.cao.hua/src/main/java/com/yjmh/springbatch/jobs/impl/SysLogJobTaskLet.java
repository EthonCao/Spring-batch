package com.yjmh.springbatch.jobs.impl;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.yjmh.springbatch.archive.DB_Table_Data_Handler;
import com.yjmh.springbatch.beans.QueryCondition;
import com.yjmh.springbatch.jobs.taskLet.WriteTaskLet;

public class SysLogJobTaskLet implements WriteTaskLet {
	/**
	 * execute方法，是由Tasklet接口继承而来的，是Tasklet实现业务逻辑的地方。
	 * */
	String filePath;
	String tableName;
	
	/**查询条件*/
	QueryCondition queryCondition;
	
	/**
	 * 处理数据
	 * */
	private DB_Table_Data_Handler dbTableDataHandler;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		queryCondition.setTableName(tableName);
		queryCondition.setConditionColumn("OPTERIONTIME");
		queryCondition.setFilePath(filePath);
		dbTableDataHandler.archiveData(queryCondition);
		System.out.println("------------ARCHIVE-------------" + tableName + "------------DONE-------------");
		return RepeatStatus.FINISHED;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public QueryCondition getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(QueryCondition queryCondition) {
		this.queryCondition = queryCondition;
	}

	public DB_Table_Data_Handler getDbTableDataHandler() {
		return dbTableDataHandler;
	}

	public void setDbTableDataHandler(DB_Table_Data_Handler dbTableDataHandler) {
		this.dbTableDataHandler = dbTableDataHandler;
	}
	
	

}
