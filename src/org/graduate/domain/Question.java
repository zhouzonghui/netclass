package org.graduate.domain;
/**
 * @author Owner
 * ����ʵ����
 */
public class Question {
	/**
	 * �����id
	 */
	private int id;
	
	/**
	 * ����ı���
	 */
	private String title;
	
	/**
	 * ���������
	 */
	private String content;
	
	/**
	 * ����Ļظ�����
	 */
	private String answer;
	
	/**
	 * �����Ƿ񱻻ظ�
	 */
	private boolean isanswer;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isIsanswer() {
		return isanswer;
	}
	public void setIsanswer(boolean isanswer) {
		this.isanswer = isanswer;
	}
	/**
	 * ��question������student�������question���ĸ�student�����
	 */
	private Student student;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
