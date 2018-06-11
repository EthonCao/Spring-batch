package com.yjmh.springbatch.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 操作CSV文件的工具类
 * */
public class CSVUtils {  
   
  @SuppressWarnings("rawtypes")  
  public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath,  
                   String fileName) {
	  File csvFile = null;  
	  BufferedWriter csvFileOutputStream = null;  
	  try {  
		  File file = new File(outPutPath);  
		  if (!file.exists()) {  
			  file.mkdir();  
		  }  
		  csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));  
		  csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);  
		  for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {  
			  java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();  
			  csvFileOutputStream.write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "");  
			  if (propertyIterator.hasNext()) {  
				  csvFileOutputStream.write(",");  
			  }  
		  }  
		  csvFileOutputStream.newLine();  
		  for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {  
			  Object row = (Object) iterator.next();  
			  for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {  
				  java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();  
				  if (propertyEntry != null && propertyEntry.getKey() != null) {
					  System.out.println("Key: " + (String) propertyEntry.getKey());
					  System.out.println("Row: " + row);
					  System.out.println("Value: " + (String) BeanUtils.getProperty(row,  (String) propertyEntry.getKey()));
					  System.out.println("Is Null? " + ((String) BeanUtils.getProperty(row,  (String) propertyEntry.getKey()) == null));
					  if ((String) BeanUtils.getProperty(row,  (String) propertyEntry.getKey()) != null) {
						  csvFileOutputStream.write((String) BeanUtils.getProperty(row,  (String) propertyEntry.getKey()));  
					  } else {
						  csvFileOutputStream.write("");
					  }
				  } else {
					  csvFileOutputStream.write("");
				  }
				  if (propertyIterator.hasNext()) {  
					  csvFileOutputStream.write(",");  
				  }  
			  }  
			  if (iterator.hasNext()) {  
				  csvFileOutputStream.newLine();  
			  }  
		  }  
		  csvFileOutputStream.flush();  
	  } catch (Exception e) {  
		  e.printStackTrace();  
	  } finally {  
		  try {  
			  csvFileOutputStream.close();  
		  } catch (IOException e) {  
			  e.printStackTrace();  
		  }  
	  }  
	  return csvFile;  
  }  
   
   
  public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName)  
                                                  throws IOException {  
    response.setContentType("application/csv;charset=GBK");  
    response.setHeader("Content-Disposition",  
      "attachment; filename=" + URLEncoder.encode(fileName, "GBK"));  
   
    InputStream in = null;  
    try {  
      in = new FileInputStream(csvFilePath);  
      int len = 0;  
      byte[] buffer = new byte[1024];  
      response.setCharacterEncoding("UTF-8");  
      OutputStream out = response.getOutputStream();  
      while ((len = in.read(buffer)) > 0) {  
        out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });  
        out.write(buffer, 0, len);  
      }  
    } catch (FileNotFoundException e) {  
      e.printStackTrace();
    } finally {  
      if (in != null) {  
        try {  
          in.close();  
        } catch (Exception e) {  
          throw new RuntimeException(e);  
        }  
      }  
    }  
  }  
   
  
  public static void deleteFiles(String filePath) {  
    File file = new File(filePath);  
    if (file.exists()) {  
      File[] files = file.listFiles();  
      for (int i = 0; i < files.length; i++) {  
        if (files[i].isFile()) {  
          files[i].delete();  
        }  
      }  
    }  
  }  
   
  public static void deleteFile(String filePath, String fileName) {  
    File file = new File(filePath);  
    if (file.exists()) {  
      File[] files = file.listFiles();  
      for (int i = 0; i < files.length; i++) {  
        if (files[i].isFile()) {  
          if (files[i].getName().equals(fileName)) {  
            files[i].delete();  
            return;  
          }  
        }  
      }  
    }  
  }  
   
}