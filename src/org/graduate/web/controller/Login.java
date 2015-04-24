package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//���ӹ�������ɾ��
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		
		if ("zzh".equals(username) && "123".equals(password)) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect("/netclass/jsp/index.jsp");
			return;
		}else {
			request.setAttribute("msg", "�û�����������������µ�¼!");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
