package org.graduate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Owner
 * ѧ��ʵ����
 */
public class Student {
	/**
	 * ѧ�ţ�Ψһ
	 */
	private String id;
	
	/**
	 * ѧ������
	 */
	private String name;
	
	/**
	 * ѧ����¼����
	 */
	private String password;
	
	/**
	 * ѧ���Ա�f��ʾŮ��m��ʾ��
	 */
	private char gender;
	
	/**
	 * ѧ���Ŀ��Է���
	 */
	private float score;
	
	/**
	 * ���õ�������student�������������Ŀ���
	 */
	private Class clazz; 
	
	/**
	 * ���õ�������student�õ����ѧ�����������s
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
