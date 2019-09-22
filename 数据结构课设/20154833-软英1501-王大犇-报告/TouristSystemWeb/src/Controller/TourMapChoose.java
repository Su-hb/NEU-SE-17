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
 * @author �����
 * ����յ㶼ѡ��ĵ���·��ͼ�Ŀ�����
 */

@WebServlet("/TourMapChoose")
public class TourMapChoose extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ָ����ʼ������
		String startName = request.getParameter("start1");
		String endName = request.getParameter("end1");
		
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        
        TourMap tourMap = new TourMap(Tourist.getGraph());
        
        if((tourMap.getPos(startName) != -1)&&(tourMap.getPos(endName) != -1)){
        	List<Integer> tourIndexList = tourMap.Prim(startName, endName);
        	pw.write("{\"tourList\":"+ JSON.toJSONString(tourIndexList)+ "}");
        }else{
			//�������ڣ����ؿ�����
			pw.write("{\"tourList\":[]}");
		}
        pw.flush();
	}
}
