package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.ClassDao;
import org.graduate.domain.Class;

@SuppressWarnings("serial")
public class AddClass extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassDao classDao = new ClassDao();
		
		if (request.getSession().getAttribute("username") != null) {
			String className = request.getParameter("classname");
			int time = Integer.parseInt(request.getParameter("time"));
			
			Class clazz = new Class();
			clazz.setName(className);
			clazz.setTime(time);
			
			classDao.add(clazz);
			
			request.setAttribute("msg", "���ӳɹ���");
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
