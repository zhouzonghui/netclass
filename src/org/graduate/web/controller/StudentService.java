package org.graduate.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graduate.dao.StudentDao;
import org.graduate.domain.Student;
import org.graduate.utils.MD5utils;
import org.graduate.utils.RandomNum;
import org.graduate.utils.SendMail;

@SuppressWarnings("serial")
public class StudentService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDao studentDao = new StudentDao();
		
		String type = request.getParameter("type");
		System.out.println(type);
		
		if ("reset".equals(type)) {
			//此时是学生客户端发来的重置密码的请求，随记生成一个密码，先写入数据库，再把这个密码以邮件方式发送给学生
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			
			String passtmp = RandomNum.makeRandom();
			String md5pass = MD5utils.MD5(passtmp);
			//临时随机密码写入数据库
			boolean result = studentDao.resetPass(id, md5pass);
			
			//发送邮件
			new SendMail(email, passtmp).start();
			
			if (result) {
				response.getWriter().write("ok");
			}else {
				response.getWriter().write("no");
			}
		}else if("login".equals(type)) {
			//发来的是登录请求
			String pwd = request.getParameter("pwd");
			String id = request.getParameter("id");
			Student student = studentDao.findStudent(id);
			if (student.getPassword().equals(MD5utils.MD5(pwd))) {
				response.getWriter().write("ok");
			}else {
				response.getWriter().write("no");
			}
			
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
