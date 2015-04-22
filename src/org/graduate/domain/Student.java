package org.graduate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Owner
 * 学生实体类
 */
public class Student {
	/**
	 * 学号，唯一
	 */
	private String id;
	
	/**
	 * 学生姓名
	 */
	private String name;
	
	/**
	 * 学生登录密码
	 */
	private String password;
	
	/**
	 * 学生性别，f表示女，m表示男
	 */
	private char gender;
	
	/**
	 * 学生的考试分数
	 */
	private float score;
	
	/**
	 * 设置导航，由student导航到他所属的课堂
	 */
	private Class clazz; 
	
	/**
	 * 设置导航，由student得到这个学生所提的问题s
	 */
	private Set<Question> questions = new HashSet<Question>();
	
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	
}
