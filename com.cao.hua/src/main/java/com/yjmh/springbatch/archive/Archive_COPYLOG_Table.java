package com.yjmh.springbatch.archive;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.yjmh.springbatch.data.PrepareData;
import com.yjmh.springbatch.utils.CSVUtils;

public class Archive_COPYLOG_Table {
	public void archiveData() {
		PrepareData prepareData = new PrepareData();
		//Get Column Name
		LinkedHashMap<String, String> columnNameMap = new LinkedHashMap<String, String>();
		columnNameMap = prepareData.getTabelColumnsName("COPYLOG");
		
		//Get DB Data
		List<LinkedHashMap<String, String>> exportData = new ArrayList<LinkedHashMap<String, String>>(); 
		String sql = "select * from copyLog where (SYSDATE-OPTERIONTIME) >120";
		exportData = prepareData.queryDataFromDBTable(columnNameMap, sql);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		
		String name = "ARCHIVE_COPY_LOG_TABLE_" + df.format(new Date());
		name = name.replace(" ", "-");
		name = name.replace(":", "-");
		String filePath = "d:/export/";
		File file = CSVUtils.createCSVFile(exportData, columnNameMap, filePath, name);
		
	}
}
