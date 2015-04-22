package org.graduate.domain;

/**
 * @author Owner
 * 课堂实体类
 */
public class Class {
	/**
	 * 课堂号
	 */
	private int id;
	
	/**
	 * 课堂名称
	 */
	private String name;
	
	/**
	 * 上课时间，如：31表示周三第一节
	 */
	private int time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
}
