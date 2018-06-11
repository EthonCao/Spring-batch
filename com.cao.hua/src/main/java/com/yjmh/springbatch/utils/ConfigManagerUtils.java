package com.yjmh.springbatch.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManagerUtils {

	private static ConfigManagerUtils configManagerUtils; 
	
	private static Properties properties;
	
	private String jdbcConfigFile = "jdbc.properties";
	
	private ConfigManagerUtils() {
		properties = new Properties();
		InputStream in = ConfigManagerUtils.class.getClassLoader().getResourceAsStream(jdbcConfigFile);
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Single
	public static ConfigManagerUtils getInstance() {
		if (configManagerUtils == null) {
			configManagerUtils = new ConfigManagerUtils();
			return configManagerUtils;
		}
		return configManagerUtils;
	}
	
	public String getProperties(String key) {
		return properties.getProperty(key);
	}

}
