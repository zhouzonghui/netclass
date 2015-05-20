package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.QuestionDao;
import org.graduate.dao.StudentDao;
import org.graduate.domain.Question;

@SuppressWarnings("serial")
public class QuestionService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuestionDao questionDao = new QuestionDao();
		StudentDao studentDao = new StudentDao();
		
		String type = request.getParameter("type");
		
		if ("ask".equals(type)) {
			try {
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String studentid = request.getParameter("id");
				
				Question question = new Question();
				question.setTitle(title);
				question.setContent(content);
				question.setStudent(studentDao.findStudent(studentid));
				
				questionDao.add(question);
				response.getWriter().write("ok");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("no");
			}
			
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
