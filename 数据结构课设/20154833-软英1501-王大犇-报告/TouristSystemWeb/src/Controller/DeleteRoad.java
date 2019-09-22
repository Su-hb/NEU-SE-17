package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

/*
 * @author 王大犇
 * 删除道路节点的控制器类
 */

@WebServlet("/deleteRoad")
public class DeleteRoad extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String mapPath="F:/JavaWorkspaceEE/TouristSystemWeb/Map.txt";
		
		request.setCharacterEncoding("GB2312");
		  String delRoad_start = request.getParameter("deleteStart");
		  String delRoad_end = request.getParameter("deleteEnd");
		  
		  String del1=delRoad_start + "-" + delRoad_end;
		  String del2=delRoad_end + "-" + delRoad_start;
		  
		  String line_map = null;
	      String str_1_map = null;
	      String str_map = null;
	      int count_map = 0;
	      
	      File file_map = new File(mapPath);
		  if(!file_map.exists()){
	             file_map.createNewFile();
		  }
		  
		  BufferedReader readInfo_map = new BufferedReader(new FileReader(mapPath));
		  while((line_map = readInfo_map.readLine())!=null)
	      {
			  if(line_map.contains(del1)||line_map.contains(del2)){
				  continue;
			  }else{
			  if(count_map == 0) {str_1_map = line_map + "&";}
			  else{str_1_map = str_1_map + line_map + "&";}
			  count_map++;
			  }
	      }
		  readInfo_map.close();
	      str_map = str_1_map;
	      
	      //擦除文件中内容
	      FileWriter fileWriter_map =new FileWriter(file_map);
	      fileWriter_map.write("");
	      fileWriter_map.flush();
	      fileWriter_map.close();
	      
	      //将未删除部分重新写入文件
	      
	      int offset_map = 0;
	      String line_1_map = null;
		  
		  FileWriter writer_map = new FileWriter(mapPath, true);
		  String s=null;
		  while((offset_map = str_map.indexOf("&", offset_map)) != -1){
			  
			  line_1_map = str_1_map.substring(0, str_1_map.indexOf("&"));
			  str_1_map = str_1_map.substring(str_1_map.indexOf("&") + 1);
			  
			  s=s+line_1_map+"\r\n";
			  
			  
			  offset_map = offset_map + "&".length();
	          count_map++;
		  }
		  int h=s.lastIndexOf("\r\n");
		  s=s.substring(4,h);
		  writer_map.write(s);
		  writer_map.close();
		  
		  response.sendRedirect("Administrator.jsp");
	}
}
