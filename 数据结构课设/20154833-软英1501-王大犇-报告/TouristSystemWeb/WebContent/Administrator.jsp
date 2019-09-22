 <%@ page language="java" import="java.util.*"  pageEncoding="GB2312" %>
    <%@ page import="java.io.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin (<%=session.getAttribute("loginUser") %>)</title>
     <link href="css/Admin.css" rel="stylesheet">
     <link href="css/bootstrap.css" rel="stylesheet">
     <link rel="stylesheet" type="text/css" href="css/tabstyle.css" />
     <script src="js/jquery-1.12.0.min.js"></script>
     <script type="text/javascript" src="js/toursystem.js"></script>
     <script type="text/javascript" src="js/new_ajax.js"></script>
     <style type="text/css">
        
        .select,.selectNext{cursor:pointer;position:absolute;top:50px; left:530px;width: 120px;height: 40px;text-align: center; border: 2px solid #CCCCCC;border-radius: 6px;line-height: 45px;font-size: 15px;color: #666666;padding-left: 0px;}
        .xl-icon,.xl-iconNext{position: absolute;width: 20px;height: 20px;
            background-image: url("image/xl-icon_03.png");
            background-position: 4px 0px;
            background-repeat: no-repeat;
            right: 0;
            top:14px;
        }
        .select:hover,.selectNext:hover{background-color: #f7f7f7;}
        .sq-icon{background-position: 4px -33px;}
        /*--下拉选择列表样式--*/
        .selectList,.selectListNext{z-index:999;display:none;width: 130px;border: 3px solid #CCCCCC;border-radius: 6px;position: absolute;top:37px;left: -1px;}
        .listA,.listANext{width: 100%;height: 40px;line-height: 40px;font-size: 14px;color: #545454;text-align: center;display: block;background-color: #FFFFFF;}
        .listA:hover,.listANext:hover{background-color: #f7f7f7;color: #b83b43;}
        .listA:first-child,.listANext:first-child{border-radius: 6px 6px 0 0;}
        .listA:last-child,.listANext:last-child{border-radius: 0 0 6px 6px;}
        
        .dropDown{position: relative;}
        .selectListNext{top:58px;}
    </style>
    <script type="text/javascript">
    $(document).ready(function(){
    	ajax('coordX.txt',function(str){
    		var x=str.split(",");
    		for(var i=0;i<x.length;i++){
    			var a=x[i];
    			
    			xPosition.push(Number(a));
    		}
    	});
    	ajax('coordY.txt',function(str){
    		var x=str.split(",");
    		for(var i=0;i<x.length;i++){
    			var a=x[i];
    			
    			yPosition.push(Number(a));
    		}
    	});
    })
    </script>
</head>
<body onmousemove="" ontouchstart="" onload="showGraph()">



   <!-- 导航栏 -->
       <nav id="mainNav" class="navbar navbar-default navbar-fixed-top hc-top-up">
			<div class="container-fluid" style="background-color:#000000">
				<a href="javascript:void(0)" class="hc-logobox">
					<img src="image/icon.png" />
				</a>

				<div class="navbar-right hc-contact p768">
					<a href="#"><%=session.getAttribute("loginUser") %></a>
				</div>
				<div id="oiBtn" class="hc-oi p1100">
					<em></em>
				</div>

				<ul id="navBox" class="nav navbar-nav navbar-right hc-navbox">
					<li>
						<a class="nav-on" href="Index.html" >Home</a>
						<a class="nav-off" href="Index.html">Main Menu</a>
					</li>
					<li>
						<a class="nav-on" href="#">Tour</a>
						<a class="nav-off" href="Tourist.jsp">Tourist Menu</a>
					</li>
					<li>
						<a class="nav-on" href="#">About Us</a>
						<a class="nav-off" href="#">Contact us</a>
					</li>
					<li>
						<a class="nav-on" href="#">Support</a>
						<a class="nav-off" href="#">Give technical help</a>
					</li>
				</ul>
			</div>
		</nav>
		
		<div style="position:absolute; margin-top:20px;left:250px;">
		  <div class="Icon">
		      <img alt="" src="image/icon2.png" height="150px;" margin="2px;">
		  </div>
		  <div>
		   <font style="position:relative;font-size:30px;"><%=session.getAttribute("loginUser") %></font>
		  </div>
		</div>
		
		<div class="jq22-container">
		   <div class="tabs">
		  <input type="radio" id="tab1" name="tab-control" checked>
		  <input type="radio" id="tab2" name="tab-control">
		  <input type="radio" id="tab3" name="tab-control">  
		  <input type="radio" id="tab4" name="tab-control">
		  <ul class="sticky">
		    <li title="Manage Attraction"><label for="tab1" role="button">
					<br><span>Manage Attract</span></label></li>
		    <li title="Manage Road"><label for="tab2" role="button">
					<br><span>Manage Road</span></label></li>
		    <li title="Announcement"><label for="tab3" role="button">
					<br><span>Announcement</span></label></li>   
		    <li title="Parking Plot"><label for="tab4" role="button">
						<br><span>Recommend Construct</span></label></li>
		  </ul>
		  
		  
		  <HR style="border:1px solid; color:#919191;position:absolute;left:20px;top:76px;width:720px;">
		  <div class="slider"><div class="indicator"></div></div>
		  
		  <div class="content">
		  
		  <!-- 景点管理 -->
		    <section>
		      <h2>Manage Attract</h2>
		        <div id="CanvasParent" style="position:relative; top:20px;height:100%; left:-15px;border-radius:10px;box-shadow:inset 5px 5px 5px rgba(0, 0, 0, 0.1),inset 0 -5px 5px rgba(0, 0, 0, 0.1),inset -5px 0px 5px rgba(0, 0, 0, 0.1);">
	               <!-- 控制背景组件的交互 -->
		           <canvas id="CanvasAdmin" style="border-radius:10px; border:1px solid #000000; width:100%" width="700px" height="420px">
		           </canvas>
	            </div>
	            <!-- 添加一个景点 -->
	            <% String coord;%>
	            <!-- 选取一个点 -->
	            <button class="cBtn" onClick="chooseAttract()" style="position:raletive;margin-top:30px;">Choose</button>
	            <button class="cBtn"  onClick="Add()" style="position:raletive;top:50px;">Sure</button>
	            <%
	            if(request.getParameter("param")!=null){
	              coord = request.getParameter("param");
	              String c[]=coord.split("@");
	              String x=","+c[0];
	              String y=","+c[1];
	              %>
	              <a id="coordX" calss="coordX" name="coordX" value="x" >coordX:<%=c[0] %></a>
	              <a id="coordY" calss="coordY" name="coordY" value="y">coordY:<%=c[1] %></a>
	              <% 
	              //String filepathx="F:/JavaWorkspaceEE/TouristSystemWeb/WebContent/coordX.txt";
	              //String filepathy="F:/JavaWorkspaceEE/TouristSystemWeb/WebContent/coordY.txt";
	              String filepathx=request.getSession().getServletContext().getRealPath("") +"/coordX.txt";
	              String filepathy=request.getSession().getServletContext().getRealPath("")+"/coordY.txt";
	              try{
	            	  FileWriter fwx=new FileWriter(filepathx,true);
	            	  FileWriter fwy=new FileWriter(filepathy,true);
	            	  fwx.write(x);
	            	  fwy.write(y);
	            	  fwx.close();
	            	  fwy.close();
	              }catch(IOException e){
	            	  e.printStackTrace();
	              }
	            }
	            %>
	           <form action="buildAttract" method="post" style="position:raletive;line-height:20px;margin-top:80px;">
	            <table>
	             <div class="row" font-family="华文细黑">
	               <label >New Attraction's Info</label>
	             </div>
	             <div class="row">
	               Name       :  <span class="inputBox">
                   <input type="name" id="txtname" placeholder="attraction" name="name"/>
                   </span>
                 </div><br>
                 <div class="row">
	               Description :  <span class="inputBox">
                   <input type="des" id="txtdes" placeholder="description" name="des"/>
                   </span>
                 </div><br>
                 <div class="row">
	               has Rest  :  <select name="hasRest">
	               <option onselectstart="return false">please choose</option>
	               <option value="1">has</option>
	               <option value="0">No</option>
                   </select>
                 </div><br>
                 <div class="row">
	               has Toilet  :  <select name="hasToilet">
	               <option onselectstart="return false">please choose</option>
	               <option value="1">has</option>
	               <option value="0">No</option>
                   </select>
                 </div><br>
	             <div class="row">
	               <button type="submit" class="aBtn"  style="position:raletive;top:50px;">Add</button>
	             </div>
	            </table>
	           </form>
	           
	            <HR style="border:1px solid; color:#acacac;position:absolute;left:20px;top:940px;width:720px;">
	           <br><br>
	           <!-- 删除一个景点 -->
	           <div>
	             <form role="form" action="deleteAttract" method="post">
	               <div>
	                 <label>Delete a Attraction</label><br>
	                 <font>Please choose:</font>
	                 <select id="deleteAttract" name="delete">
	                 <option onselectstart="return false">please choose</option>
	                    <%
	                   int m=0;
	         		   FileReader infofile3=new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt");
	         		   BufferedReader infobf3=new BufferedReader(infofile3);
	         		
	         		   String attract3;
	         		   while((attract3=infobf3.readLine())!=null){
	         			  String str3=attract3.substring(0,attract3.indexOf(" "));
	                   %>
	                   <option value="<%=str3%>"><%=str3%></option>
	                   <%
	                      m++;
	         		   }
	         		   infobf3.close();
	         		   infofile3.close();
	                    %>
	                 </select><img alt="" src="image/start.png" onClick="markArc3()" style="height:17px;left:8px;top:-1px;">
	               </div>
	               <button type="submit" class="btn btn-primary" >Delete</button>
	             </form>
	           
	           </div> 
	            
		     </section>
		     
		     
		     <!-- 道路管理 -->
		     <section>
		          <h2>Manage Road</h2>
		          <div id="CanvasParent1" style="position:relative; top:20px;height:100%; left:-15px;border-radius:10px;box-shadow:inset 5px 5px 5px rgba(0, 0, 0, 0.1),inset 0 -5px 5px rgba(0, 0, 0, 0.1),inset -5px 0px 5px rgba(0, 0, 0, 0.1);">
	               <!-- 控制背景组件的交互 -->
		           <canvas id="CanvasAdmin1" style="border-radius:10px; border:1px solid #000000; width:100%" width="700px" height="420px">
		           </canvas>
	              </div>
	              <label style="position:relative;top:20px;">Build a road by choosing start and end attractions</label>
	              <form style="position:raletive;margin-top:30px;"action="buildRoad" method="post">
	              <!-- 这里是道路节点的骑士经典 -->
	                 Start Attraction:<select type="start"name="start" id="keyWord">
	                 <option onselectstart="return false">please choose</option>
	                 <%
	                   int i=0;
	         		   FileReader infofile=new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt");
	         		   BufferedReader infobf=new BufferedReader(infofile);
	         		
	         		   String attract;
	         		   while((attract=infobf.readLine())!=null){
	         			  String str=attract.substring(0,attract.indexOf(" "));
	                   %>
	                   <option value="<%=str%>"><%=str%></option>
	                   <%
	                      i++;
	         		   }
	         		   infofile.close();
	         		   infofile.close();
	                    %>
	                 </select>
	                 <img alt="" src="image/start.png" onClick="markArc()" style="height:17px;left:8px;top:-1px;">
	                 <br><br>
	                 <!-- 这里是道路节点的终点 -->
	                 End Attraction :<select name="end" type="end" id="keyWord2">
	                 <option onselectstart="return false">please choose</option>
	                 <%
	                   int j=0;
	         		   FileReader infofile2=new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt");
	         		   BufferedReader infobf2=new BufferedReader(infofile2);
	         		
	         		   String attract2;
	         		   while((attract2=infobf2.readLine())!=null){
	         			  String str2=attract2.substring(0,attract2.indexOf(" "));
	                   %>
	                   <option value="<%=str2%>"><%=str2%></option>
	                   <%
	                      j++;
	         		   }
	                    %>
	                 </select>
	                 <img alt="" src="image/end.png" onClick="markArc2()" style="height:17px;left:8px;top:-1px;">
	                 <br><br>
	                 Distance  :<input type="dist" id="txtdist" placeholder="The length of the road" name="dist">
	                 <br>
	                 <button class="rBtn" type="submit" >Build</button>
	              </form>
	              <HR style="border:1px solid; color:#acacac;position:absolute;left:20px;top:830px;width:720px;">
	              <br><br><br><br>
	              
	              <!-- 删除道路 -->
	              <div >
	                  <form role="form" action="deleteRoad" method="post" >
	                     <label>Delete a road</label><br>
	                     choose the start Attraction:<select type="start"name="deleteStart" id="deleteStart">
	                       <option onselectstart="return false">please choose</option>
	                       <%
	                        
	         		        infofile=new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt");
	         		        infobf=new BufferedReader(infofile);
	         		
	         		        while((attract=infobf.readLine())!=null){
	         			    String str4=attract.substring(0,attract.indexOf(" "));
	                        %>
	                         <option value="<%=str4%>"><%=str4%></option>
	                        <%
	                     
	         		         }
	         		          infofile.close();
	         		          infofile.close();
	                         %>
	                     </select>
	                     <img alt="" src="image/start.png" onClick="" style="height:17px;left:8px;top:-1px;">
	                     <br><br>
	                     <!-- 这里是道路节点的终点 -->
	                     Choose the End Attraction :<select name="deleteEnd" type="end" id="deleteEnd">
	                       <option onselectstart="return false">please choose</option>
	                       <%
	                       
	         		       infofile2=new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt");
	         		       infobf2=new BufferedReader(infofile2);
	         		
	         		       while((attract2=infobf2.readLine())!=null){
	         			   String str2=attract2.substring(0,attract2.indexOf(" "));
	                       %>
	                       <option value="<%=str2%>"><%=str2%></option>
	                       <%
	                        }
	                        %>
	                       </select>
	                       <img alt="" src="image/end.png" onClick="" style="height:17px;left:8px;top:-1px;">
	                       <br>
	                       <button type="submit" class="btn btn-primary" >Delete</button>
	                  </form>
	              </div>
		     </section>
		     
		     
		     <!-- 发布公告 -->
		     <section>
		          <h2>Announcement</h2>
		          <div style="position:relative;border-color:#000;">
		             <font>Post Announcement</font>
		             <form name="regForm" action="doAnnounce.jsp" method="post">
		             <div class="reviewArea clearfix">
                       <textarea class="content comment-input" placeholder="Please enter a comment&hellip;" onkeyup="keyUP(this)" name="area"></textarea>
                       
                       <select class="select" name="select">
                         <!--存放选择的值-->
                         <div class="selectList">
                          <option class="listA" onselectstart="return false"  >-select type-</option>
                          
                              <option  class="listA" value="info">info</option>
                              <option  class="listA" value="warning">warning</option>
                              <option  class="listA" value="success">good news</option>
                          </div>  
                           
                        </select>
                       
                       <input href="javascript:;" id="loginbtn" class="plBtn" type="submit" value="Post"></input>
                     </div>
                     </form>
		          </div>
		     </section>
		     
		     
		     <!-- 道路修建 -->
		     <section>
		          <h2>Recommend Construction</h2>
		          <div id="CanvasParent2" style="position:relative; top:20px;height:100%; left:-15px;border-radius:10px;box-shadow:inset 5px 5px 5px rgba(0, 0, 0, 0.1),inset 0 -5px 5px rgba(0, 0, 0, 0.1),inset -5px 0px 5px rgba(0, 0, 0, 0.1);">
	               <!-- 控制背景组件的交互 -->
		           <canvas id="CanvasAdmin2" style="border-radius:10px; border:1px solid #000000; width:100%" width="700px" height="420px">
		           </canvas>
	            </div>
	             <button class="cBtn" onClick="recommend()" style="position:raletive;margin-top:30px;">Give a recommend</button>
	             <font style="position:relative;top:30px;">(Kruskal MST) </font>
		     </section>    
		  </div>
		  </div>
		
		</div>


      
      

   
</body>
</html>