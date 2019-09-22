package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Tour.TourMap;
import Controller.Tourist;

/*
 * @author 王大犇
 * 旅游路线图的控制器类
 */

@WebServlet("/output")
public class OutputGraph extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//指定起始点名称
		String startName = request.getParameter("start");
		
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        
        TourMap tourMap = new TourMap(Tourist.getGraph());
		//判断是否存在指定的起始点
        if(tourMap.getPos(startName) != -1){
        	//若存在，生成旅游路线图和是否有回路
			List<Integer> tourIndexList = tourMap.DFS(startName);
	        pw.write("{\"tourList\":"+ JSON.toJSONString(tourIndexList)+ "}");
		}else{
			//若不存在，返回空数组
			pw.write("{\"tourList\":[]}");
		}
        pw.flush();
	}
}
