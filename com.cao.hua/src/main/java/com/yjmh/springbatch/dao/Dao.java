package com.yjmh.springbatch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yjmh.springbatch.utils.ConfigManagerUtils;

public class Dao {
	
	public Connection getConnection() {
		 Connection conn = null;
		 try {
			 Class.forName(ConfigManagerUtils.getInstance().getProperties("jdbc.driver_class"));
			 String url = ConfigManagerUtils.getInstance().getProperties("jdbc.connection.url");
			 String user = ConfigManagerUtils.getInstance().getProperties("jdbc.connection.username");
			 String pwd = ConfigManagerUtils.getInstance().getProperties("jdbc.connection.password");
			 conn=(Connection)DriverManager.getConnection(url,user,pwd);
		 } catch(Exception e) { 
			 e.printStackTrace();
       }
		 return conn;
	}
	
	public ResultSet query(String sql) {
		Connection conn = getConnection();
		Statement state = null;
		ResultSet resultSet = null;
		try {
			state = conn.createStatement();
			resultSet = state.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resultSet;
	}
	
	public void close(Statement pst, Connection conn) {
		if (pst != null || conn != null) {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
