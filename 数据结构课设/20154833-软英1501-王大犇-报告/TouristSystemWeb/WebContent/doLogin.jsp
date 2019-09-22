<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.BufferedWriter"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileReader"%>
<%@ page import="java.util.ArrayList"%>
<%
    String username = "";
    String password = "";
    
    boolean isCorrect=false;
    
    request.setCharacterEncoding("utf-8");
    
    username = request.getParameter("username");
    password = request.getParameter("password");
    
    
	      BufferedReader bufferReader = new BufferedReader(new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Code.dat"));
	      String content = null;
	      content= bufferReader.readLine();
	      
	      String single[]=content.split("&");//将字符串按+分割得到账号和密码的组合;
	      
	     
	      
	      for(int i=0;i<single.length;i++){
	    	 
	    	  String a[]=single[i].split("@");//分割每个账户
	    	  String name=a[0];
	    	  String code=a[1];
	    	  if(name.equals(username) && code.equals(password)){
	    	        session.setAttribute("loginUser",username);
	    	        response.sendRedirect("Administrator.jsp"); 
	    	        isCorrect=true;
	    	  }
	      }
	      if(isCorrect==false){
	    	  response.sendRedirect("Index.html");
	      }
	      
    
    
    
%>
