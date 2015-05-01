package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.FileDao;
import org.graduate.domain.File;

@SuppressWarnings("serial")
public class DeleteFile extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FileDao fileDao = new FileDao();
		int id = Integer.parseInt(request.getParameter("id"));
		
		//��Ӳ����ɾ���ļ�
		File file = fileDao.find(id);
		String url = file.getUrl();
		java.io.File realFile = new java.io.File(url);
		realFile.delete();
		
		//�����ݿ���ɾ����¼
		fileDao.delete(id);
		request.getRequestDispatcher("/servlet/ListFile").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
