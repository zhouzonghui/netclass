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
			//用户想看的页号
			String pagenum = request.getParameter("pagenum");
			int totalrecord = questionDao.getTotalRecord(false);
			
			//没带pagenum表示初始时的点击，看的是第一页
			if (pagenum == null) {
				Page page = new Page(totalrecord, 1);
				List<Question> list = questionDao.getPageData(page.getStartindex(), page.getPagesize(), false);
				page.setList(list);
				page.setUrl(this.getServletContext().getContextPath() + "/servlet/ReplyQuestion");
				
				/*victory是为了带一个参数给jsp，jsp依据这个参数是否为空来决定是否弹出“已回复”的提示框。
				 * 如果是直接从左侧功能区发来的请求，则没经过AnswerQuestion，request.getAttribute("success")就为空，这样在jsp就不弹窗*/
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
			request.setAttribute("msg", "您尚未登录，请先<a href='/netclass/login.jsp' target='_parent'>登录</a>");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
