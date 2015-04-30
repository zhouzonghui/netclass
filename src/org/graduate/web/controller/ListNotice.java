package org.graduate.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.NoticeDao;
import org.graduate.domain.Notice;

/**
 * 后台通知查看，也可删除，不用分页了，感觉太多时删除一些比较早的通知就行了
 * @author t-zhouzonghui
 */
@SuppressWarnings("serial")
public class ListNotice extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDao noticeDao = new NoticeDao();
		
		if (request.getSession().getAttribute("username") != null) {
			List<Notice> list = noticeDao.findAll();
			
			request.setAttribute("notices", list);
			request.getRequestDispatcher("/jsp/notices.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "您尚未登录，请先<a href='/netclass/login.jsp' target='_parent'>登录</a>");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
