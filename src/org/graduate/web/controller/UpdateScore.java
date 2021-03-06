package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.StudentDao;

@SuppressWarnings("serial")
public class UpdateScore extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDao studentDao = new StudentDao();
		
		String[] ids = request.getParameterValues("id");
		String[] scores = request.getParameterValues("score");
		double[] real_scores = null;
		
		try {
			
			if (ids.length == scores.length) {
				real_scores = new double[ids.length];
				for (int i = 0; i < scores.length; i++) {
					real_scores[i] = Double.parseDouble(scores[i]);
				}
			}
			
			studentDao.updateBatch(ids, real_scores);
			request.setAttribute("msg", "成绩更新成功！");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "成绩更新失败！");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
