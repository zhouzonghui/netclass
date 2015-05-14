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
			//��ʱ��ѧ���ͻ��˷�������������������������һ�����룬��д�����ݿ⣬�ٰ�����������ʼ���ʽ���͸�ѧ��
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			
			String passtmp = RandomNum.makeRandom();
			String md5pass = MD5utils.MD5(passtmp);
			//��ʱ�������д�����ݿ�
			boolean result = studentDao.resetPass(id, md5pass);
			
			//�����ʼ�
			new SendMail(email, passtmp).start();
			
			if (result) {
				response.getWriter().write("ok");
			}else {
				response.getWriter().write("no");
			}
		}else if("login".equals(type)) {
			//�������ǵ�¼����
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
