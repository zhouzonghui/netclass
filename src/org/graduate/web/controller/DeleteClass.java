package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.ClassDao;

@SuppressWarnings("serial")
public class DeleteClass extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassDao classDao = new ClassDao();
		
		int id = Integer.parseInt(request.getParameter("id"));
		classDao.delete(id);
		
		request.getRequestDispatcher("/servlet/DeleteClassUI").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
