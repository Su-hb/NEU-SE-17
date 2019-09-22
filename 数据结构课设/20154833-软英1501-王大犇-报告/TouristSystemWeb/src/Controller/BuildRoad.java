package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

/*
 * @author 王大犇
 * 添加道路节点控制器
 * form表单提交
 */

@WebServlet("/buildRoad")
public class BuildRoad extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("GB2312");
		
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		String dist=request.getParameter("dist");
		
		String path="F:/JavaWorkspaceEE/TouristSystemWeb/Map.txt";
		
		File f=new File(path);
		OutputStreamWriter write=new OutputStreamWriter(new FileOutputStream(f,true));
		BufferedWriter writer=new BufferedWriter(write);
		
		FileWriter fw=new FileWriter(path,true);
		String str="\r\n"+start+"-"+end+"-"+dist;
		//System.out.println(str);
		writer.write(str);
		writer.close();
		
		response.sendRedirect("Administrator.jsp");
	}
}
