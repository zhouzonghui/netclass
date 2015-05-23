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
public class ReplyQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") != null) {
			QuestionDao questionDao = new QuestionDao();
			//�û��뿴��ҳ��
			String pagenum = request.getParameter("pagenum");
			int totalrecord = questionDao.getTotalRecord(false);
			
			//û��pagenum��ʾ��ʼʱ�ĵ���������ǵ�һҳ
			if (pagenum == null) {
				Page page = new Page(totalrecord, 1);
				List<Question> list = questionDao.getPageData(page.getStartindex(), page.getPagesize(), false);
				page.setList(list);
				page.setUrl(this.getServletContext().getContextPath() + "/servlet/ReplyQuestion");
				
				/*victory��Ϊ�˴�һ��������jsp��jsp������������Ƿ�Ϊ���������Ƿ񵯳����ѻظ�������ʾ��
				 * �����ֱ�Ӵ���๦����������������û����AnswerQuestion��request.getAttribute("success")��Ϊ�գ�������jsp�Ͳ�����*/
				request.setAttribute("victory", request.getAttribute("success"));
				
				request.setAttribute("page", page);
			}else {
				Page page = new Page(totalrecord, Integer.parseInt(pagenum));
				List<Question> list = questionDao.getPageData(page.getStartindex(), page.getPagesize(), false);
				page.setList(list);
				page.setUrl(this.getServletContext().getContextPath() + "/servlet/ReplyQuestion");
				request.setAttribute("page", page);
			}
			
			request.getRequestDispatcher("/jsp/replyquestion.jsp").forward(request, response);
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
