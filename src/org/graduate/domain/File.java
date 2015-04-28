package org.graduate.domain;

import java.sql.Timestamp;
/**
 * 文件实体类，封装文件上传的文件
 * @author t-zhouzonghui
 */
public class File {
	/**
	 * 文件的id
	 */
	private int id;
	
	/**
	 * 文件名
	 */
	private String name;
	
	/**
	 * 文件的完整路径
	 */
	private String url;
	
	/**
	 * 文件上传的时间
	 */
	private Timestamp time;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
