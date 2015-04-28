package org.graduate.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.ClassDao;
import org.graduate.dao.StudentDao;
import org.graduate.domain.Class;
import org.graduate.domain.Student;

@SuppressWarnings("serial")
public class ScoreMrgUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDao studentDao = new StudentDao();
		ClassDao classDao = new ClassDao();
		
		if (request.getSession().getAttribute("username") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Student> list = studentDao.findStudentsByClass(id);
			Class clazz = classDao.find(id);
			request.setAttribute("clazz", clazz);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/jsp/student.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "ÄúÉÐÎ´µÇÂ¼£¬ÇëÏÈ<a href='/netclass/login.jsp' target='_parent'>µÇÂ¼</a>");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
