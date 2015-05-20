package org.graduate.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.FileDao;
import org.graduate.domain.File;

@SuppressWarnings("serial")
public class ListFileService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FileDao fileDao = new FileDao();
		List<File> files = fileDao.findAll();
		
		request.setAttribute("list", files);
		
		request.getRequestDispatcher("/jsp/downfilesservice.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
