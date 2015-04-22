package org.graduate.domain;
/**
 * @author Owner
 * 通知实体类
 */
public class Notice {
	/**
	 * 通知的id
	 */
	private int id;
	
	/**
	 * 通知信息内容
	 */
	private String info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
