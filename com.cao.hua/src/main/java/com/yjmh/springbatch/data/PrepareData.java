package com.yjmh.springbatch.data;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.yjmh.springbatch.dao.Dao;

public class PrepareData {
	Dao dao = new Dao();
	
	public List<LinkedHashMap<String, String>> queryDataFromDBTable(LinkedHashMap<String, String> columnNameMap, String sql) {
		
		ResultSet rs = null;
		List<LinkedHashMap<String, String>> exportData = new ArrayList<LinkedHashMap<String, String>>();  
		LinkedHashMap<String, String> datamMap = null; 
		try {
			rs = dao.query(sql);
			while (rs.next()) {
				datamMap = new LinkedHashMap<String, String>(); 
				for (String culumnName : columnNameMap.values()) {
					datamMap.put(culumnName, getValue(rs, culumnName)); 
				}
				exportData.add(datamMap); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exportData;
	}
	
	public String getValue(ResultSet rs, String columnName) {
		ResultSetMetaData rsmd;
		String value = "";
		try {
			rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String tempName = rsmd.getColumnName(i);
				if (tempName.equals(columnName)) {
					String dataType = rsmd.getColumnTypeName(i);
					switch (dataType) {
						case "BLOB":
							try {
								/*Blob blob = rs.getBlob(columnName);
								long l = 1L;
								InputStream is;
								if (blob != null) {
									is = blob.getBinaryStream();
								}
								byte[] bs = blob.getBytes(l, (int)blob.length());
								value = new String(bs, "UTF-8");*/
								value = "暂时注释掉代码,待异常解决后放开,注释代码为: L49 - L56";
								/*
								 InputStream is = rs.getBinaryStream(columnName);
								 InputStreamReader isr = new InputStreamReader(is);
								 char cc[] = new char[2000];
								 isr.read(cc, 0, 2000);
								 String str = new String(new String(cc).getBytes(), "GBK");
								 System.out.println("BLOB Values: " + str);
								 
								 String test = new String(rs.getBytes(columnName),"GBK");
								 System.out.println("-----" + test);
								 isr.close();
								 is.close();*/
							} catch (Exception e) {
								e.printStackTrace();
							}
						    break;
						default:
							value = rs.getString(columnName);
							break;
					}
				}
				if (value != "") {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public LinkedHashMap<String, String> getTabelColumnsName(String tableName) {
		String sql = "select * from " + tableName;
		LinkedHashMap<String, String> columnNameMap =  new LinkedHashMap<String, String>();  
		try {
			ResultSet rs = dao.query(sql);
			ResultSetMetaData rsmd = rs.getMetaData() ;
			for(int i = 1; i <= rsmd.getColumnCount(); i++){
				columnNameMap.put(rsmd.getColumnName(i), rsmd.getColumnName(i));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnNameMap;
	}
	
}
