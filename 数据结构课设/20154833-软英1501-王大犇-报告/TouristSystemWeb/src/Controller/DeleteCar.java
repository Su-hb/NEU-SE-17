package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Tourist;

/*
 * @author �����
 * �����뿪�Ŀ�������
 */

@WebServlet("/deletePark")
public class DeleteCar extends HttpServlet {

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
		//��ǰ�˻�ȡ���ƺ�
		String number = request.getParameter("number");
		
		String results = Tourist.getPark().leavePark(number, new Date());
		
		//����json���
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        pw.write(results);
        pw.flush();
	}
}
