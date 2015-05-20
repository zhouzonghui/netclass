package org.graduate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.graduate.domain.Notice;
import org.graduate.utils.JdbcUtils;

public class NoticeDao {
	/**
	 * 增加一条通知
	 * @param notice
	 */
	public void add(Notice notice) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into t_notice (n_info) values (?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getInfo());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	/**
	 * 删除一条通知
	 * @param id 通知id
	 */
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from t_notice where n_id=?";
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
	 * 从m开始取，一共查询出n条，注意是倒序的，即从新的往老的（android客户端可以返回固定6条通知数量）
	 * @param m 从哪个位置取
	 * @param n 取多少条
	 * @return 返回n条最新的通知，如果有n条的话
	 */
	public List<Notice> findSomeNotices(int m, int n) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_notice order by n_id desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, m);
			ps.setInt(2, n);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("n_id");
				String info = rs.getString("n_info");
				
				Notice notice = new Notice();
				notice.setId(id);
				notice.setInfo(info);
				
				list.add(notice);
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
	 * 获取总记录数
	 * @return 返回总记录数
	 */
	public int getTotalRecord() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select count(*) from t_notice";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int count = 0;
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	/**
	 * 列出所有通知
	 * @return 返回所有通知的集合
	 */
	public List<Notice> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_notice order by n_id desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("n_id");
				String info = rs.getString("n_info");
				
				Notice notice = new Notice();
				notice.setId(id);
				notice.setInfo(info);
				
				list.add(notice);
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
