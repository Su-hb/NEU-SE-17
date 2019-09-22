package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Controller.Tourist;
import Tour.ShortestPath;

/*
 * @author �����
 * ���·��servlet������
 */

@WebServlet("/Shortestpath")
public class Shortestpath extends HttpServlet  {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ʼ������
		String startName = request.getParameter("startName");
		//�յ�����
		String endName = request.getParameter("endName");
		
		ShortestPath shortestPath = new ShortestPath(Tourist.getGraph());
		
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        //���㲻�ɴ�
		if(shortestPath.getPos(startName)==-1 || shortestPath.getPos(endName)==-1){
			pw.write("{\"pathDis\":-1}");
		}else{//����ɴ�
			shortestPath.Dijkstra(startName, endName);
			List<Integer> result = shortestPath.outputShortestPath();
			
			List<Integer> path = new ArrayList<Integer>();
			for(int i=result.size()-1; i>0; i--){
				path.add(result.get(i));
			}
			
			pw.write("{\"pathDis\":" + result.get(0) + ",\"nodesIndex\":" + JSON.toJSONString(path) + "}");
		}
		pw.flush();
	}
}
