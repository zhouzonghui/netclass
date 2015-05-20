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

import org.graduate.dao.NoticeDao;
import org.graduate.domain.Notice;

@SuppressWarnings("serial")
public class NoticeService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDao noticeDao = new NoticeDao();
		
		String type = request.getParameter("type");
		
		//获取记录总数的请求
		if ("getcount".equals(type)) {
			int total = noticeDao.getTotalRecord();
			
			response.setCharacterEncoding("UTF-8");
			response.getOutputStream().write(String.valueOf(total).getBytes());
			
		}else if("getdata".equals(type)) {
			//获取特定条数记录的请求
			String startindex = request.getParameter("startindex");
			String count = request.getParameter("count");
			
			List<Notice> notices = noticeDao.findSomeNotices(Integer.parseInt(startindex), Integer.parseInt(count));
			JSONArray array = new JSONArray();
			
			for(Notice notice : notices) {
				JSONObject object = new JSONObject();
				object.put("id", notice.getId());
				object.put("info", notice.getInfo());
				array.add(object);
			}
			
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(array.toString());
			pw.flush();
			
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
