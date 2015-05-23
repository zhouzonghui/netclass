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
	 * ��ʦ�ظ�ѧ������ʱ�ĸ���
	 * @param id �����id
	 * @param ans ��ʦ�Ļظ�
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
	 * ȡ��ĳһҳ���ݣ��˷��������������¶���Ч���ؼ����ڲ���b
	 * @param startindex �������ĸ�λ�ÿ�ʼȡ
	 * @param pagesize ȡ���ٸ�
	 * @param b ���ڹ��˳�δ���𸴵�����
	 * @return ������һҳ�����ݵļ���
	 */
	public List<Question> getPageData(int startindex, int pagesize, Boolean b) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Question> questions = new ArrayList<Question>();
		
		try {
			conn = JdbcUtils.getConnection();
			
			//��ʾ������
			if (b) {
				String sql = "select * from t_question order by q_id desc limit ?,?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, startindex);
				ps.setInt(2, pagesize);
				
				rs = ps.executeQuery();
				
			}else {
				//��ʾ��������ֻȡ��δ���ظ��ļ���
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
	 * ɾ��һ����¼
	 * @param id
	 * @return ɾ���ɹ�����true
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
	 * ���question�ļ�¼����������û�С��ѱ��ظ�������������
	 * @param b �����Ƿ񱻻ظ�
	 * @return ���ؼ�¼��Ŀ
	 */
	public int getTotalRecord(Boolean b) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			//��ʾҪ��ȡ���м�¼����Ŀ
			if (b) {
				String sql = "select count(*) from t_question";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
			}else {
				//Ҫ��ȡδ���𸴵��������Ŀ
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
	 * ��ҳȡ���ݣ�ȡ��������ѧ�������⣬������ĳһ�ض�ѧ��������
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
			ps.setInt(2, offset);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				//����Ҫ֪������������ĸ�ѧ����ģ�������q_student_id��
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
	 * ��ȡĳѧ�������ʵ��ܼ�¼��
	 * @param id ѧ��
	 * @return �����ܼ�¼��
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













