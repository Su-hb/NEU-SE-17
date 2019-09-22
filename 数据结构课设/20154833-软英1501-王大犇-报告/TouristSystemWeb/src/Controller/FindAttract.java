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

import Controller.Tourist;
import Tour.Find;

/*
 * @author 王大犇
 * 查找景点的控制器类
 */

@WebServlet("/findAttract")
public class FindAttract extends HttpServlet {

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
		//搜索关键字
		String keyWord = request.getParameter("keyWord");
		
		Find find = new Find(Tourist.getGraph());
		//根据关键字搜索
		List<Integer> searchNodes = find.findArc(keyWord);
		
		//返回json结果
		response.setContentType("text/json" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(searchNodes));
        pw.flush();
	}
}
