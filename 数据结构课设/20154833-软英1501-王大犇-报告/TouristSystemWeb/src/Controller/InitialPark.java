package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Tourist;

/*
 * @author �����
 * ��ʼ��ͣ������С������
 */

@WebServlet("/initialPark")
public class InitialPark extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//ͣ������С
		String parkSize = request.getParameter("parkSize");
		
		Tourist.setPark(Integer.parseInt(parkSize));
		
		//����json���
		response.setContentType("text/json" + ";charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
		PrintWriter pw = response.getWriter();
		pw.write("{}");
		pw.flush();
	}
}
