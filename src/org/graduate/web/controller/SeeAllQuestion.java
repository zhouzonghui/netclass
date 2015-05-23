package org.graduate.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.QuestionDao;
import org.graduate.domain.Page;
import org.graduate.domain.Question;

@SuppressWarnings("serial")
public class SeeAllQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") != null) {
			QuestionDao questionDao = new QuestionDao();
			//�û��뿴��ҳ��
			String pagenum = request.getParameter("pagenum");
			int totalrecord = questionDao.getTotalRecord(true);
			
			//û��pagenum��ʾ��ʼʱ�ĵ���������ǵ�һҳ
			if (pagenum == null) {
				Page page = new Page(totalrecord, 1);
				List<Question> list = questionDao.getPageData(page.getStartindex(), page.getPagesize(), true);
				page.setList(list);
				page.setUrl(this.getServletContext().getContextPath() + "/servlet/SeeAllQuestion");
				request.setAttribute("page", page);
			}else {
				Page page = new Page(totalrecord, Integer.parseInt(pagenum));
				List<Question> list = questionDao.getPageData(page.getStartindex(), page.getPagesize(), true);
				page.setList(list);
				page.setUrl(this.getServletContext().getContextPath() + "/servlet/SeeAllQuestion");
				request.setAttribute("page", page);
			}
			
			
			
			
			request.getRequestDispatcher("/jsp/seequestion.jsp").forward(request, response);
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
