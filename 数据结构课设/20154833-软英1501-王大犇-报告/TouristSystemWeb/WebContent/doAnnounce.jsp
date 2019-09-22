<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*" %>
<%
String area = "";
String select = "";

boolean isCorrect=false;

request.setCharacterEncoding("utf-8");
//获取前端公告内容
area = request.getParameter("area");
select = request.getParameter("select");



java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("<yyyy/MM/dd>");

java.util.Date currentTime = new java.util.Date();//得到当前系统时间

String str_date = formatter.format(currentTime); //将日期时间格式化 
System.out.println(str_date);

String announcement=str_date+" "+area+" @"+select;

  String filepath="F:/JavaWorkspaceEE/TouristSystemWeb/Note.txt";
  try{
	  FileWriter fw=new FileWriter(filepath,true);
	  fw.write("\r\n");
	  fw.write(announcement);
	  fw.close();
	  response.sendRedirect("Administrator.jsp");
  }catch(IOException e){
	  e.printStackTrace();
  }
%>