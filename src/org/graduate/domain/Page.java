package org.graduate.domain;

import java.util.List;
/**
 * ��ҳ��ҳ������������κ����ͣ���һ���������
 * @author Owner
 */
public class Page {
	private List list;
	private int totalpage;		//��ס��ҳ��
	private int totalrecord;	
	private int pagesize = 5;	//ҳ���СΪ5����ÿҳ��ʾ5����¼
	
	private int pagenum;		//�����û��뿴��ҳ
	private int startindex;	//�����û��뿴��ҳ�����ݴ����ݿ��ĸ��ط���ʼȡ
	
	private int startPage;		//��סjspҳ����ʾ����ʼҳ��
	private int endPage;		//��סjspҳ����ʾ�Ľ���ҳ��
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page(int totalrecord, int pagenum) {
		this.totalrecord = totalrecord;
		
		if (this.totalrecord % this.pagesize == 0) {
			this.totalpage = this.totalrecord / this.pagesize;
		}else {
			this.totalpage = this.totalrecord / this.pagesize + 1;
		}
		
		this.pagenum = pagenum;
		this.startindex = (this.pagenum - 1) * this.pagesize;
		
		//�����û��뿴��ҳpagenum�����jspҳ�����ʼ�ͽ���ҳ��
		//������ʾ5��ҳ��
		
		if (this.totalpage <= 5) {
			this.startPage = 1;
			this.endPage = this.totalpage;
		}else{
			this.startPage = this.pagenum - 4;
			this.endPage = this.pagenum + 5;
			
			if (this.startPage < 1) {
				this.startPage = 1;
				this.endPage = 5;
			}
			
			if (this.endPage > this.totalpage) {
				this.endPage = this.totalpage;
				this.startPage = this.totalpage - 4;
			}
		}
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
