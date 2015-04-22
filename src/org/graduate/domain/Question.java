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
