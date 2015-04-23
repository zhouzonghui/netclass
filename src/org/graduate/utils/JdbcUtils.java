package org.graduate.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {
	public static final String DRIVER = PropertyMgr.getProperty("driver");
	public static final String URL = PropertyMgr.getProperty("dburl");
	public static final String USERNAME = PropertyMgr.getProperty("username");
	public static final String PASSWORD = PropertyMgr.getProperty("password");
	
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try{
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		if(ps != null) {
			try{
				ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		if(conn != null) {
			try{
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
