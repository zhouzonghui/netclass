package org.graduate.test;


import org.graduate.dao.ClassDao;
import org.graduate.dao.StudentDao;
import org.graduate.domain.Class;
import org.graduate.domain.Student;
import org.junit.Test;

public class DbTest {
	@Test
	public void testAddStudent() {
		StudentDao studentDao = new StudentDao();
		Student student = new Student();
		student.setName("xxxxx");
		student.setId("12345");
		student.setGender("f");
		Class clazz = new Class();
		clazz.setId(11);
		clazz.setName("利大");
		clazz.setTime(31);
		student.setClazz(clazz);
		studentDao.add(student);
	}
	
	@Test
	public void testDeleteStudent() {
		StudentDao studentDao = new StudentDao();
		studentDao.delete("12345");
	}
	
	@Test
	public void testAddClass() {
		ClassDao classDao = new ClassDao();
		Class clazz = new Class();
		clazz.setName("柴麻字利大");
		clazz.setTime(34);
		classDao.add(clazz);
		
	}
	
	@Test
	public void testDeleteClass() {
		ClassDao classDao = new ClassDao();
		classDao.delete(12);
	}
}




















