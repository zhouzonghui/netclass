package org.graduate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.graduate.domain.Student;
import org.graduate.utils.JdbcUtils;

public class StudentDao {
	
	/**
	 * ���һ��ѧ��
	 * @param student
	 */
	public void add(Student student) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into t_student (s_id,s_name,s_gender,s_class_id) values (?,?,?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getGender());
			ps.setInt(4, student.getClazz().getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * ����ѧ��ɾ��һ��ѧ��
	 * @param id
	 */
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from t_student where s_id=?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	/**
	 * ����ѧ����Ϣ
	 * @param student
	 */
	public void update(Student student) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update t_student set s_name=?,s_password=?,s_gender=?,s_score=?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getGender());
			ps.setFloat(3, student.getScore());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
	}
	
	/**
	 * ���ݿ��úż������ÿ����ϵ�����ѧ��
	 * @param class_id ���ú�
	 * @return ������������ϵ�ѧ��
	 */
	public List<Student> findStudentsByClass(int class_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Student> list = new ArrayList<Student>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_student where s_class_id=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, class_id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("s_id");
				String name = rs.getString("s_name");
				String g = rs.getString("s_gender");
				String gender = g.equals("f") ? "Ů" : "��";
				float score = rs.getFloat("s_score");
				
				Student student = new Student();
				student.setId(id);
				student.setName(name);
				student.setGender(gender);
				student.setScore(score);
				
				list.add(student);
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
	 * ����������ĳ�����õ�ѧ���ĳɼ�
	 * @param ids ѧ��
	 * @param scores ����
	 */
	public void updateBatch(String[] ids, double[] scores) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update t_student set s_score=? where s_id=?";
			ps = conn.prepareStatement(sql);
			
			if (ids.length == scores.length) {
				for (int i = 0; i < scores.length; i++) {
					ps.setDouble(1, scores[i]);
					ps.setString(2, ids[i]);
					
					ps.addBatch();
				}
				ps.executeBatch();
			}else {
				throw new RuntimeException("���³���");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	/**
	 * ������ʼ��ĳ�������ϵ�ѧ��
	 * @param id ����id
	 * @param students ����ѧ����Ϣ�����飬��ʽ��{"201109020229-����-��", "201109020212-����-��", "201109020210-����-��"}
	 */
	public void addStudentOfClass(int id, String[] students) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into t_student (s_id,s_name,s_gender,s_class_id) values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			if (students != null && students.length > 0) {
				for (int i = 0; i < students.length; i++) {
					ps.setString(1, students[i].split("\\-")[0]);
					ps.setString(2, students[i].split("\\-")[1]);
					ps.setString(3, (students[i].split("\\-")[2]).equals("��") ? "m" : "f");
					ps.setInt(4, id);
					
					ps.addBatch();
				}
				ps.executeBatch();
			}else {
				throw new RuntimeException("���ʧ�ܣ�");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
		
		
		
		
	}
}











