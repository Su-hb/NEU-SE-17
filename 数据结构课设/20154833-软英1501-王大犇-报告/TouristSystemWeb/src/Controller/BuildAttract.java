package Controller;

import java.io.IOException;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author 王大犇
 * 添加景点节点
 * form表单提交
 */

@WebServlet("/buildAttract")
public class BuildAttract extends HttpServlet {

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
		
        request.setCharacterEncoding("GB2312");
		//从前端获取组件的值
		String attractName=request.getParameter("name");
		//String name=new String(attractName.getBytes("8859_1"),"GBK");
		String des=request.getParameter("des");
		String hasRest=request.getParameter("hasRest");
		String hasToilet=request.getParameter("hasToilet");
		
		String path="F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt";
		
		File f=new File(path);
		OutputStreamWriter write=new OutputStreamWriter(new FileOutputStream(f,true));
		BufferedWriter writer=new BufferedWriter(write);
		
		//读入文件，以在后面追加的形式
		//FileWriter fw=new FileWriter(path,true);
		String str="\r\n"+attractName+" "+des+" "+"0"+" "+hasRest+" "+hasToilet;
		System.out.println(str);
		writer.write(str);
		writer.close();
		
		response.sendRedirect("Administrator.jsp");
	}
	
}
