package com.yjmh.springbatch.beans;

public class QueryCondition {

	/**
	 * 需要进行备份的表的表名
	 * */
	String tableName;
	
	/**查询条件
	 * 
	 * 以时间为查询条件, 本字段用于保存表中的一个表示时间的列,如SYSLOG表中的OPTERIONTIME
	 * */
	String conditionColumn;
	
	/**
	 * 文件路径
	 * 
	 * 用户保存成备份生成的CSV文件*/
	String filePath;
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getConditionColumn() {
		return conditionColumn;
	}
	
	public void setConditionColumn(String conditionColumn) {
		this.conditionColumn = conditionColumn;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
