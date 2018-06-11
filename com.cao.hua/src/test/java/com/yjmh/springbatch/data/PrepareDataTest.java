package com.yjmh.springbatch.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

public class PrepareDataTest {

	@Test
	public void testQueryDataFromDBTable() {
		PrepareData data = new PrepareData();
		
		LinkedHashMap<String, String> datamMap = new LinkedHashMap<String, String>();
		datamMap = (LinkedHashMap<String, String>) data.getTabelColumnsName("SYSLOG");;
		
		List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		String sql = "select * from syslog where logID in ('106803', '106804', '106805', '106806', '106807', '106808')";
		list = data.queryDataFromDBTable(datamMap, sql);
		for (int i = 0; i < list.size(); i++) {
			for (String value : list.get(i).values()) {
				System.out.println(value);
			}
		}
	}

	@Test
	public void testGetTabelColumnName() {
		PrepareData data = new PrepareData();
		data.getTabelColumnsName("COPYLOG");
	}

}
