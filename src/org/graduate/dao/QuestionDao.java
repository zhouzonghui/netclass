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
	 * ����һ������
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
	 * ��ҳȡ���ݣ�ȥ��������ѧ�������⣬������ĳһ�ض�ѧ��������
	 * @param start ��ʼλ��
	 * @param offset ��Ҫȡ�ļ�¼����
	 * @return ������һҳ���ݵļ���
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
				//����Ҫ֪������������ĸ�ѧ����ģ�������q_student_id��
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
	 * ��ҳȡquestion������ѧ����ȡ����Ҫ��ѧ���Լ��鿴�Լ���������
	 * @param id ѧ��ѧ��
	 * @param start ���Ŀ�ʼȡ
	 * @param offset ȡ��������¼
	 * @return �������ѧ����һЩquestion�ļ���
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
				boolean isanswer = rs.getInt("q_isanswer") == 1;
				
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
}













