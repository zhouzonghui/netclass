package org.graduate.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	static Properties props = new Properties();
	
	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("db_info.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("应用初始化错误！");
		}
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}