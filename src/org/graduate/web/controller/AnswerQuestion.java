package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.QuestionDao;

@SuppressWarnings("serial")
public class AnswerQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String qid = request.getParameter("qid");
		String qans = request.getParameter("qans");
		
		QuestionDao questionDao = new QuestionDao();
		questionDao.update(Integer.parseInt(qid), qans);
		request.setAttribute("success", "replysuccess");
		request.getRequestDispatcher("/servlet/ReplyQuestion").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
