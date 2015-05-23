package org.graduate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.graduate.domain.Question;
import org.graduate.utils.JdbcUtils;

public class QuestionDao {
	/**
	 * 增加一条问题
	 * @param question
	 */
	public void add(Question question) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into t_question (q_title,q_content,q_student_id) values (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, question.getTitle());
			ps.setString(2, question.getContent());
			ps.setString(3, question.getStudent().getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * 老师回复学生问题时的更新
	 * @param id 问题的id
	 * @param ans 老师的回复
	 */
	public void update(int id, String ans) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update t_question set q_answer=?,q_isanswered=true where q_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ans);
			ps.setInt(2, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * 取出某一页数据，此方法在有无条件下都有效，关键在于参数b
	 * @param startindex 从数据哪个位置开始取
	 * @param pagesize 取多少个
	 * @param b 用于过滤出未被答复的问题
	 * @return 返回这一页面数据的集合
	 */
	public List<Question> getPageData(int startindex, int pagesize, Boolean b) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Question> questions = new ArrayList<Question>();
		
		try {
			conn = JdbcUtils.getConnection();
			
			//表示无条件
			if (b) {
				String sql = "select * from t_question order by q_id desc limit ?,?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, startindex);
				ps.setInt(2, pagesize);
				
				rs = ps.executeQuery();
				
			}else {
				//表示有条件，只取出未被回复的集合
				String sql = "select * from t_question where q_isanswered=? order by q_id desc limit ?,?";
				ps = conn.prepareStatement(sql);
				ps.setBoolean(1, b);
				ps.setInt(2, startindex);
				ps.setInt(3, pagesize);
				
				rs = ps.executeQuery();
			}
			while (rs.next()) {
				Question question = new Question();
				
				question.setId(rs.getInt("q_id"));
				question.setTitle(rs.getString("q_title"));
				question.setContent(rs.getString("q_content"));
				question.setAnswer(rs.getString("q_answer"));
				question.setIsanswer(rs.getBoolean("q_isanswered"));
				
				questions.add(question);
			}
			return questions;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return 删除成功返回true
	 */
	public boolean delete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from t_question where q_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			int result = ps.executeUpdate();
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	/**
	 * 获得question的记录数，对于有没有“已被回复”条件均适用
	 * @param b 问题是否被回复
	 * @return 返回记录数目
	 */
	public int getTotalRecord(Boolean b) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			//表示要获取所有记录的数目
			if (b) {
				String sql = "select count(*) from t_question";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
			}else {
				//要获取未被答复的问题的数目
				String sql = "select count(*) from t_question where q_isanswered=?";
				ps = conn.prepareStatement(sql);
				ps.setBoolean(1, b);
				rs = ps.executeQuery();
			}
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	/**
	 * 分页取数据，取的是所有学生的问题，而不是某一特定学生的问题
	 * @param start 开始位置
	 * @param offset 需要取的记录条数
	 * @return 返回这一页数据的集合
	 */
	public List<Question> findSomeQuestions(int start, int offset) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Question> list = new ArrayList<Question>();
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_question order by q_id desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, offset);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				//不需要知道这个问题是哪个学生提的，不设置q_student_id了
				int id = rs.getInt("q_id");
				String title = rs.getString("q_title");
				String content = rs.getString("q_content");
				String answer = rs.getString("q_answer");
				boolean isanswer = rs.getInt("q_isanswered") == 1;
				
				Question question = new Question();
				question.setId(id);
				question.setTitle(title);
				question.setContent(content);
				question.setAnswer(answer);
				question.setIsanswer(isanswer);
				
				list.add(question);
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
	 * 分页取question，根据学号来取，主要是学生自己查看自己所有提问
	 * @param id 学生学号
	 * @param start 从哪开始取
	 * @param offset 取多少条记录
	 * @return 返回这个学生的一些question的集合
	 */
	public List<Question> findQuestionsByStudentId(String id, int start, int offset) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Question> list = new ArrayList<Question>();
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_question where q_student_id=? order by q_id desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, start);
			ps.setInt(3, offset);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int q_id = rs.getInt("q_id");
				String title = rs.getString("q_title");
				String content = rs.getString("q_content");
				String answer = rs.getString("q_answer");
				boolean isanswer = rs.getInt("q_isanswered") == 1;
				
				Question question = new Question();
				question.setId(q_id);
				question.setTitle(title);
				question.setContent(content);
				question.setAnswer(answer);
				question.setIsanswer(isanswer);
				
				list.add(question);
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
	 * 获取某学生的提问的总记录数
	 * @param id 学号
	 * @return 返回总记录数
	 */
	public int getTotalRecord(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select count(*) from t_question where q_student_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
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
}













