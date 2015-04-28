package org.graduate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.graduate.domain.Class;
import org.graduate.utils.JdbcUtils;

public class ClassDao {
	/**
	 * ���ӿ���
	 * @param clazz
	 */
	public void add(Class clazz) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into t_class (c_name,c_time) values (?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, clazz.getName());
			ps.setInt(2, clazz.getTime());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	/**
	 * ɾ������
	 * @param id
	 */
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from t_class where c_id=?";
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
	 * �г����п�����Ϣ
	 * @return �������п��õļ���
	 */
	public List<Class> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Class> list = new ArrayList<Class>();
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_class";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_name = rs.getString("c_name");
				int c_time = rs.getInt("c_time");
				Class clazz = new Class();
				clazz.setId(c_id);
				clazz.setName(c_name);
				clazz.setTime(c_time);
				list.add(clazz);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * ����id��ѯ��һ������
	 * @param id
	 * @return ����class
	 */
	public Class find(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_class where c_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_name = rs.getString("c_name");
				int c_time = rs.getInt("c_time");
				
				Class clazz = new Class();
				clazz.setId(c_id);
				clazz.setName(c_name);
				clazz.setTime(c_time);
				return clazz;
			}
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
}















