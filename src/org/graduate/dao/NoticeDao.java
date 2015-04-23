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
	 * 从最新发布的通知往前，一共查询出6条（固定6条通知数量）
	 * @return 返回6条最新的通知，如果有六条的话
	 */
	public List<Notice> findSomeNotices() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_notice order by n_id desc limit 0,6";
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
