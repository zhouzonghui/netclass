package org.graduate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.graduate.domain.File;
import org.graduate.utils.JdbcUtils;

public class FileDao {
	/**
	 * 增加一条文件记录
	 * @param file
	 */
	public void add(File file) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into t_file (f_name,f_url) values (?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, file.getName());
			ps.setString(2, file.getUrl());
			
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * 删除一条文件记录
	 * @param file
	 */
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from t_file where f_id=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * 根据id查出一条文件记录
	 * @param id 文件id
	 * @return 返回file对象
	 */
	public File find(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_file where f_id=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				int f_id = rs.getInt("f_id");
				String f_name = rs.getString("f_name");
				String f_url = rs.getString("f_url");
				Timestamp f_time = rs.getTimestamp("f_time");
				
				File file = new File();
				file.setId(f_id);
				file.setName(f_name);
				file.setUrl(f_url);
				file.setTime(f_time);
				
				return file;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * 列出所有文件
	 * @return 返回文件的集合
	 */
	public List<File> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<File> list = new ArrayList<File>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_file";
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				File file = new File();
				
				int id = rs.getInt("f_id");
				String name = rs.getString("f_name");
				String url = rs.getString("f_url");
				Timestamp time = rs.getTimestamp("f_time");
				
				file.setId(id);
				file.setName(name);
				file.setUrl(url);
				file.setTime(time);
				
				list.add(file);
			}
			return list;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	
}
