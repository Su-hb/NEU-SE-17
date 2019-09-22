package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Tour.GiveRecommend;
import Tour.AttractNode;
import Tour.TourGraph;
import Tour.VNode;
import Controller.Tourist;
import Tour.VInfo;

@WebServlet("/recommendRoad")
public class RecommendRoad  extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GiveRecommend buildRoad = new GiveRecommend(Tourist.getGraph());
		//克鲁斯卡尔算法生成道路修建图
		List<VInfo> results = buildRoad.kruskal();
		Map<String, List<VInfo>> map = new HashMap<String, List<VInfo>>();
		map.put("results", results);
		
		
		//返回json结果
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(map));
        pw.flush();
	}
}
