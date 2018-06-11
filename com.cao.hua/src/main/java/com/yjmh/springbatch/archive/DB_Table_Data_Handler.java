package com.yjmh.springbatch.archive;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.yjmh.springbatch.beans.QueryCondition;
import com.yjmh.springbatch.data.PrepareData;
import com.yjmh.springbatch.utils.CSVUtils;

public class DB_Table_Data_Handler {
	public void archiveData(QueryCondition queryCondition) {
		System.out.println("Start to archive table: " + queryCondition.getTableName() 
			+ ", column: " + queryCondition.getConditionColumn() + ", filePath:" + queryCondition.getFilePath());	
		PrepareData prepareData = new PrepareData();
		//获取表中的所有列名
		LinkedHashMap<String, String> columnNameMap = new LinkedHashMap<String, String>();
		columnNameMap = prepareData.getTabelColumnsName(queryCondition.getTableName());
		
		//根据列查询表中数据
		List<LinkedHashMap<String, String>> exportData = new ArrayList<LinkedHashMap<String, String>>(); 
		//String sql = "select * from syslog where logID in ('61270', '61271', '61272', '61273', '61274', '64168')";
		//String sql = "select * from " + queryCondition.getTableName() 
			//+ " where (SYSDATE-" + queryCondition.getConditionColumn() + ") > 120";
		String sql = "select * from syslog where LOGID=102066"; 
		System.out.println("Query SQL: " + sql);
		exportData = prepareData.queryDataFromDBTable(columnNameMap, sql);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		
		String name = "ARCHIVE_" + queryCondition.getTableName() + "_TABLE_" + df.format(new Date());
		name = name.replace(" ", "-");
		name = name.replace(":", "-");
		CSVUtils.createCSVFile(exportData, columnNameMap, queryCondition.getFilePath(), name);
		System.out.println("Archive Table " + queryCondition.getTableName() + "Done");
	}
}
