package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.NoticeDao;
import org.graduate.domain.Notice;

@SuppressWarnings("serial")
public class AddNotice extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDao noticeDao = new NoticeDao();
		
		if (request.getSession().getAttribute("username") != null) {
			String content = request.getParameter("content");
			Notice notice = new Notice();
			notice.setInfo(content);
			noticeDao.add(notice);
			request.setAttribute("msg", "�����ɹ���");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "����δ��¼������<a href='/netclass/login.jsp' target='_parent'>��¼</a>");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
