package org.graduate.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.graduate.dao.FileDao;
import org.graduate.domain.File;

@SuppressWarnings("serial")
public class UploadFile extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") != null) {
			FileDao fileDao = new FileDao();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			String path = this.getServletContext().getRealPath("/files");
			
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			
			try {
				List<FileItem> list = upload.parseRequest(request);
				for(FileItem item : list) {
					if (!item.isFormField()) {
						String filename = item.getName();
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
						
						InputStream in = item.getInputStream();
						OutputStream out = new FileOutputStream(path + "\\" + filename);
						
						byte[] buffer = new byte[1024];
						
						int len = 0;
						while ((len = in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
						out.close();
						in.close();
						File file = new File();
						file.setName(filename);
						file.setUrl(path + "\\" + filename);
						fileDao.add(file);
					}
					
				}
				request.setAttribute("msg", "上传成功！");
				request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			} catch (FileUploadException e) {
				e.printStackTrace();
				request.setAttribute("msg", "上传失败！");
				request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			}
			
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
