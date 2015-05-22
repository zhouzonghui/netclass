package org.graduate.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
			
		}else if("getcount".equals(type)) {
			try {
				String id = request.getParameter("id");
				int totalrecord = questionDao.getTotalRecord(id);
				response.getWriter().write(totalrecord + "");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write(0);
			}
		}else if("getdata".equals(type)){
			String startindex = request.getParameter("startindex");
			String count = request.getParameter("count");
			String id = request.getParameter("id");
			
			List<Question> questions = questionDao.findQuestionsByStudentId(id, Integer.parseInt(startindex), Integer.parseInt(count));
			JSONArray array = new JSONArray();
			
			for(Question question : questions) {
				JSONObject object = new JSONObject();
				object.put("id", question.getId());
				object.put("title", question.getTitle());
				object.put("content", question.getContent());
				object.put("answer", question.getAnswer());
				object.put("isanswered", question.isIsanswer());
				
				array.add(object);
			}
			
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(array.toString());
			pw.flush();
		}else if("delete".equals(type)) {
			String id = request.getParameter("id");
			boolean result = questionDao.delete(Integer.parseInt(id));
			
			if (result) {
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
