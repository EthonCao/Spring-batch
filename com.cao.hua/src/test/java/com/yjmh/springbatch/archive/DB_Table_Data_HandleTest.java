package com.yjmh.springbatch.archive;

import org.junit.Test;

import com.yjmh.springbatch.beans.QueryCondition;

public class DB_Table_Data_HandleTest {

	@Test
	public void testArchiveData() {
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.setTableName("SYSLOG");
		queryCondition.setConditionColumn("OPTERIONTIME");
		queryCondition.setFilePath("d:/export/syslog");
		DB_Table_Data_Handler handle = new DB_Table_Data_Handler();
		handle.archiveData(queryCondition);
	}

}
