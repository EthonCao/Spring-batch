package com.yjmh.springbatch.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.yjmh.springbatch.utils.CSVUtils;

public class CSVUtilsTest {

	@Test
	public void createCSVFileTest() {
		 List exportData = new ArrayList<Map>();  
		 Map row1 = new LinkedHashMap<String, String>();  
		 row1.put("1", "11");  
		 row1.put("2", "12");  
		 row1.put("3", "13");  
		 row1.put("4", "14");  
		 exportData.add(row1);  
		 
		 row1 = new LinkedHashMap<String, String>();  
		 row1.put("1", "21");  
		 row1.put("2", "22");  
		 row1.put("3", "23");  
		 row1.put("4", "24");  
		 exportData.add(row1);  
		 
		 //鏂囦欢妯欓
		 LinkedHashMap map = new LinkedHashMap();  
		 map.put("1", "UserName");  
		 map.put("2", "UserID");  
		 map.put("3", "StaffID");  
		 map.put("4", "Comments");  
		   
		 String path = "d:/export/";  
		 String fileName = "测试导出";  
		 File file = CSVUtils.createCSVFile(exportData, map, path, fileName);  
		 String fileName2 = file.getName();  
		 System.out.println("测试导出" + fileName2);  
	}
	
	//@Test
	public void createCSVFileTest1() {
		//file name
		String name = "閾惰閫�娆炬暟鎹�";
		//the path where file be creaetd
		String filePath = "d:/export/"; 
		
		//Construct table content
		List exportData = new ArrayList();  
		LinkedHashMap datamMap = null;  
		for (int i = 0; i < 10; i++) {  
		   datamMap = new LinkedHashMap();  
		   datamMap.put("1", "ID " + i);  
		   datamMap.put("2", "Date: " + i);  
		   datamMap.put("3", 100 + i);  
		   datamMap.put("4", "Reason: " + i); 
		   datamMap.put("5", "Reason: " + i);
		   datamMap.put("6", "Reason: " + i);
		   datamMap.put("7", "Reason: " + i);
		   datamMap.put("8", "Reason: " + i);
		   datamMap.put("9", "Reason: " + i);
		   datamMap.put("10", "Reason: " + i);
		   exportData.add(datamMap);  
		}  
		
		//Construct table title
		 LinkedHashMap map = new LinkedHashMap();  
		 map.put("1", "Order No.");  
		 map.put("2", "Pay Date");  
		 map.put("3", "Return No.");  
		 map.put("4", "Return Reason");  
		 map.put("5", "Return Reason"); 
		 map.put("6", "Return Reason"); 
		 map.put("7", "Return Reason"); 
		 map.put("8", "Return Reason"); 
		 map.put("9", "Return Reason"); 
		 map.put("10", "Return Reason"); 
		  
		 File file = CSVUtils.createCSVFile(exportData, map, filePath, name);//鐢熸垚CSV鏂囦欢  
		 String fileName = file.getName();  
		 HttpServletResponse response = null;
		 try {
			CSVUtils.exportFile(response, filePath + fileName, fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//涓嬭浇鐢熸垚鐨凜SV鏂囦欢
	}

}
