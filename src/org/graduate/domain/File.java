package org.graduate.domain;

import java.sql.Timestamp;
/**
 * �ļ�ʵ���࣬��װ�ļ��ϴ����ļ�
 * @author t-zhouzonghui
 */
public class File {
	/**
	 * �ļ���id
	 */
	private int id;
	
	/**
	 * �ļ���
	 */
	private String name;
	
	/**
	 * �ļ�������·��
	 */
	private String url;
	
	/**
	 * �ļ��ϴ���ʱ��
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
