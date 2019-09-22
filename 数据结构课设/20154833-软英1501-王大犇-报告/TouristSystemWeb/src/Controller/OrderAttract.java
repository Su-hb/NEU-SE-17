package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Tour.Order;
import Controller.Tourist;

/*
 * @author �����
 * ���������
 */

@WebServlet("/orderAttract")
public class OrderAttract extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��������
		String type = request.getParameter("type");
		
		Order order = new Order(Tourist.getGraph());
		Map<String, Integer> orderResults = null;
		//����ӭ������
		if(type.equals("popular")){
			orderResults = order.orderByPop();
		}else{//������·��������
			orderResults = order.orderByPathnum();
		}
		
		//����json���
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        pw.write(toJSONString(orderResults));
        pw.flush();
	}
	
	private String toJSONString(Map<String, Integer> orderResults){
		String json = "[";
		for(Map.Entry<String, Integer> entry : orderResults.entrySet()){
			json  += "{\"name\":\"" + entry.getKey() + "\",\"value\":" + entry.getValue() + "},";
		}
		json = json.substring(0, json.length()-1);
		json += "]";
		
		return json;
	}
}
