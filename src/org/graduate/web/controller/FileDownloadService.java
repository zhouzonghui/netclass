package org.graduate.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FileDownloadService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String file = request.getParameter("filename");
		String path = this.getServletContext().getRealPath("/files/" + file);
		String filename = path.substring(path.lastIndexOf("\\") + 1);
		
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
		InputStream in = null;
		OutputStream out = null;
		
		try {
			in = new FileInputStream(path);
			int len = 0;
			byte[] buffer = new byte[1024];
			out = response.getOutputStream();
			
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			
			
		} catch (Exception e) {
			
		}finally{
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
					
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e2) {
					
				}
			}
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
