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
	 * 添加一个学生
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
	 * 根据学号删除一个学生
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
	 * 更新学生信息
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
	 * 根据课堂号检索出该课堂上的所有学生
	 * @param class_id 课堂号
	 * @return 返回这个课堂上的学生
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
				String gender = g.equals("f") ? "女" : "男";
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
	 * 批处理，更新某个课堂的学生的成绩
	 * @param ids 学号
	 * @param scores 分数
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
				throw new RuntimeException("更新出错！");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	/**
	 * 批量初始化某个课堂上的学生
	 * @param id 课堂id
	 * @param students 代表学生信息的数组，格式：{"201109020229-张三-男", "201109020212-李四-男", "201109020210-王武-男"}
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
					ps.setString(3, (students[i].split("\\-")[2]).equals("男") ? "m" : "f");
					ps.setInt(4, id);
					
					ps.addBatch();
				}
				ps.executeBatch();
			}else {
				throw new RuntimeException("添加失败！");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtils.release(conn, ps, rs);
		}
		
		
		
		
		
	}
}











