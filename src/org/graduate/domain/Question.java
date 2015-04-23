package org.graduate.domain;
/**
 * @author Owner
 * 问题实体类
 */
public class Question {
	/**
	 * 问题的id
	 */
	private int id;
	
	/**
	 * 问题的标题
	 */
	private String title;
	
	/**
	 * 问题的内容
	 */
	private String content;
	
	/**
	 * 问题的回复内容
	 */
	private String answer;
	
	/**
	 * 问题是否被回复
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
	 * 由question导航到student，即这个question是哪个student提出的
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
