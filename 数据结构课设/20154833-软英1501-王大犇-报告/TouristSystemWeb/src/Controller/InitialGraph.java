package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tour.BuildGraph;
import Tour.AttractNode;
import Tour.TourGraph;
import Tour.VNode;

/*
 * @author 王大犇
 * 初始化旅游景点图
 */

@WebServlet("/createGraph")
public class InitialGraph extends HttpServlet{

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int i=0,j=0;
		//读取两个文件，一个以读取景点信息，一个读取边节点的信息
		FileReader infofile=new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt");
		BufferedReader infobf=new BufferedReader(infofile);
		FileReader file=new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Map.txt");
		BufferedReader bf=new BufferedReader(file);
		
		//循环以获取景点个数和边个数，给后面创建图计数
		while(infobf.readLine()!=null){
			i++;
		}
		while(bf.readLine()!=null){
			j++;
		}
		
		//创建图
		BuildGraph graph = new BuildGraph(i, j);
		graph.createGraph();
		
		//生成图的json格式字符串
		String results = toJSONString(graph.getGraph());
		
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        pw.write(results);
        pw.flush();
	}
	

	
	private String toJSONString(TourGraph graph){
		String jsonString = "{\"attractNum\":" + graph.getAttractNum() + ",\"RNum\":" + graph.getRNum()
				+ ",\"nodes\":[";
		int flag = 1;
		for(AttractNode node : graph.getNodes()){
			if(flag == 1){
				jsonString += "{\"name\":\"" + node.getName() + "\",\"des\":\"" + node.getDes() + "\",\"pop\":"
						+ node.getPop() + ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
						+ ",\"edges\":[";
				flag = 0;
			}else{
				jsonString += ",{\"name\":\"" + node.getName() + "\",\"des\":\"" + node.getDes() + "\",\"pop\":"
						+ node.getPop() + ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
						+ ",\"edges\":[";
			}
			
			
			VNode tmp = node.getFirst();
			jsonString += "{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist() + "}";
			tmp = tmp.getNext();
			while(tmp != null){
				jsonString += ",{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist() +  "}";
				tmp = tmp.getNext();
			}
			jsonString += "]}";
		}
		jsonString += "]}";
		
		
		return jsonString;
		
	}

}
