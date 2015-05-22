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
			ps.setInt(1, offset);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				//不需要知道这个问题是哪个学生提的，不设置q_student_id了
				int id = rs.getInt("q_id");
				String title = rs.getString("q_title");
				String content = rs.getString("q_content");
				String answer = rs.getString("q_answer");
				boolean isanswer = rs.getInt("q_isanswer") == 1;
				
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













