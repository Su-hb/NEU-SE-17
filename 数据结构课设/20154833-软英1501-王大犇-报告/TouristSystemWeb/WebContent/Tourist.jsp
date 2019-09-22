<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tour System</title>
     <link rel="stylesheet" href="css/jquery.toast.css" type="text/css" />
     <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
     <link href="css/dock.css" rel="stylesheet">
     <noscript>
        <style type="text/css">
          #dock { top: 0; left: 100px; }
          a.dock-item { position: relative; float: left; margin-right: 10px; }
          .dock-item span { display: block; }
        </style>
     </noscript>
      
      <script src="js/jquery-1.8.3.min.js"></script>
      <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
      <script type="text/javascript" src="js/jquery.toast.js"></script>
     
     <script src="js/bootstrap.js" type="text/javascript"></script>
     <script src="js/chart.js" type="text/javascript"></script>
     <script type="text/javascript" src="js/toursystem.js"></script>
     <script type="text/javascript" src="js/new_ajax.js"></script>
     
     <script type="text/javascript" src="js/fisheye-iutil.min.js"></script>
     <script type="text/javascript" src="js/dock-example1.js"></script>
     <script type="text/javascript" src="js/jquery.jqDock.min.js"></script>
     
     <script type="text/javascript">
         $(function(){var jqDockOpts = {align: 'left', duration: 200, labels: 'tc', size: 48, distance: 85};
			$('#jqDock').jqDock(jqDockOpts);});
     </script>
     <script language="javascript">
    var l=true;
     
     //打开页面的时候显示消息组件note
     function showNote(){
    
    	 
    	 document.getElementById('noteshow').style.display="none";
    	 document.getElementById('notebtn').click();
     }
     //更多消息的显示组件
     function more(){
    	 document.getElementById('item').click();
    	 var more=document.getElementById('more');
    	 if(l){
    		 more.src="image/close.png";
    		 l=false;
    	 }else{
    		 more.src="image/more.png";
    		 l=true;
    	 }
    	 
     }
	
     </script>
</head>
<body onload="showNote()"> 
<div>
<img src="image/TourMenu.jpg"  height="100%" width="100%" style="position:absolute; left:0;top:0;">
</div>




<div style=" overflow:fixed;" >
<div  style="height:173px;">
     <font color="white"  face="Jokerman" style="position:absolute;font-size:40px;left:325px;top:75px;"> everywhere</font>
     <font color="black" face="华文细黑" style="position:absolute;font-size:40px;left:125px;top:130px;">Welcome ! Enjoy yourself here!</font>
     <font color="white" face="chiller" style="position:absolute;font-size:180px;left:100px;top:0px;"> Tour</font>
    
</div>
  <div >
     
     <!-- 导航栏部分，dock -->
     <div class="mainbox" style="position:fixed;float:left; left:20px;top:215px;">
     </div>
     
         <div class="dock" id="dockContainer"> 
		     <ul id="jqDock">
		       <li><a class="dockItem" onClick="createGraph()"><img src="image/dock/Initial.png" alt="home" title="Initial Graph"/></a></li>
		       <li><a class="dockItem" onClick="outputGraph()"><img src="image/dock/OutGraph.png" alt="contact" title="OutPut:Graph"/></a></li>
		       <li><a class="dockItem" onClick="showMapForm()"><img src="image/dock/TourMap.png" alt="portfolio" title="OutPut:TourMap"/></a></li>
		       <li><a class="dockItem" onClick="showShortestPathForm()"><img src="image/dock/Shortest2.png" alt="video" title="Shortest:Path" /></a></li>
		       <li><a class="dockItem" onClick="showSearchForm()"><img src="image/dock/Find.png" alt="calendar" title="Find"/></a></li>
		       <li><a class="dockItem" onClick="showOrderArcForm()"><img src="image/dock/Order.png" alt="links" title="Order" /></a></li>
		       <li><a class="dockItem" onClick="showParkingOption()"><img src="image/dock/Park.png" alt="rss" title="Park"/></a></li>
		       <li><a class="dockItem" onClick="clearAll()"><img src="image/dock/clean.png" alt="rss" title="Clean Up"/></a></li>
		       <li><a class="dockItem" href="Index.html"><img src="image/Home.png" alt="rss" title="Home"/></a></li>
		     </ul>
		    
	      </div>
	      
	      
	      <!-- 显示界面，Canvas上显示画图 -->
	      <div class="mainbox2" style="position:absolute;float:left; margin-left:150px;top:200px;border-radius:10px; ">
	         <div id="first">
	           <%
	           List<String> note=new ArrayList<String>();
	             try{
	                BufferedReader bufferReader = new BufferedReader(new FileReader("F:/JavaWorkspaceEE/TouristSystemWeb/Note.txt"));
	                
	                String line=null;
	                int i=0;
	                while((line=bufferReader.readLine())!=null){
	                	//System.out.println(line);
	            	  note.add(line);
	            	  i++;
	                }
	                String noteshow[]=note.get(i-1).split("@");
	             %>
	             <div id="noteshow" class="code-runner" style="margin-left: 0px; margin-right: 0px;  display:none;">
                     <button id="notebtn" class="eval-js"  href="#">Run code</button>
                     <pre><code>$j10.toast({
                     heading: '<%=note.get(0) %>',
                     text:'<%=noteshow[0] %> '+'<a><button href="#" onclick="more()">See More Notes</button></a>.',
                     icon: '<%=noteshow[1] %>',
                     position: {
                       right: 20,
                        top: 40
                     },
                     stack: 4,
                     showHideTransition:'plain',
                     hideAfter:14000 
                     })</code></pre>
                 </div>
                 <!-- 可以显示公告，隐藏公告 -->
                 <div class="itembox" style="position:absolute; top:20px; left:40px;width:500px;">
                   <div id="item" class="item" style="position:absolute; top:0px; left:0px;">
                      <font  face="Gill Sans MT" style="position:relative;text-align:center;font-size:50px;left:65px" ><%=note.get(0) %></font><br>
                      <font face="Raleway" style="position:relative;font-size:28px;">Earlier This Month</font>
                   </div>
	               <p style="position:relative; top:100px; left:0px;width:500px;">
	               <%
	                for(int j=i-1;j>0;j--){
	                	String moreNote[]=note.get(j).split("@");
	                	String more1=moreNote[0];
	               %>
	                 <font face="Montserrat" style="position:relative; hight:1000px; weight:1000px" ><%=more1 %></font><br><br>
	                 <% 
	                   }
	                 %>
	               </p>
	             </div>
	             <%
	                bufferReader.close();
	             }catch(IOException e){
	            	 e.printStackTrace();
	             }
	           %>
	           <!-- guide部分 -->
	           <div  style="position:absolute; top:20px; left:540px;width:500px;">
	             <div  style="position:absolute; top:0px; left:0px;">
	               <font  face="Gill Sans MT" style="position:relative;text-align:center;font-size:50px;left:250px" >Guide</font>
	             </div> 
	             <img alt="guide" src="image/guide.png" style="position:absolute;left:50px;top:60px;width:500px;height:400px;"> 
	           </div>
	         </div>
          </div>
          
          
          <!-- Cavas部分，画布，盖在背景上方 -->
	      <div id="myCanvasParent" style="position:absolute; top:190px;height:60%; margin-left:170px">
	       <!-- 控制背景组件的交互 -->
	       <img src="image/more.png" id="more" style="position:absolute;top:91px;left:259px;height:27px;" onclick="more()">
		    <canvas id="myCanvas" style="border-radius:10px; width:100%" width="1120px" height="515px">
		    </canvas>
	      </div>




    <!-- 提示框 -->
	<div class="modal fade" id="tipModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		Hint
		</h4>
		</div>
		<div class="modal-body" id="tipMessage">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">close
			</button>
		</div>
		</div>
		</div>
	</div>
	<!-- 填写起始点和终止点对话框 -->
	<div class="modal fade" id="shortestPathModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		enter in >start and >end
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="startName">start name</label>
					<input type="text" class="form-control" id="startName" 
						   placeholder="please enter start name">
				</div>
				<div class="form-group">
					<label for="endName">start name</label>
					<input type="text" class="form-control" id="endName" 
						   placeholder="please enter end name">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="shortestPath()">submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
		</div>
		</div>
	</div>
	<!-- 填写要搜索的关键字对话框 -->
	<div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		enter the keyword to find
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="keyWord">keyWord</label>
					<input type="text" class="form-control" id="keyWord" 
						   placeholder="please enter in keyword">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="searchArc()">submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
		</div>
		</div>
		</div>
	</div>
	<!-- 填写路线图起点 -->
	<div class="modal fade" id="tourListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		enter the start of the Map
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="start">start name</label>
					<input type="text" class="form-control" id="start" 
						   placeholder="please enter the start point">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="tourMap()">submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
		</div>
		</div>
		</div>
	</div>
	<!-- 选择 -->
	<div class="modal fade" id="tourMapModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		Choose
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="start">start type</label>
					<button type="button" class="btn btn-success" data-dismiss="modal" onClick="showTourMapForm()">Out Put TourMap</button>
			        <button type="button" class="btn btn-alert" data-dismiss="modal" onClick="Cycle()">Cycle</button>
			        <button type="button" class="btn btn-alert" data-dismiss="modal" onClick="showChooseMapForm()">A to B</button>
				</div>
			</form>
		</div>
		<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
		</div>
		</div>
		</div>
	</div>
	<!-- 填写路线图起点和终点 -->
	<div class="modal fade" id="tourListChooseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		enter the start of the Map
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="start1">start name</label>
					<input type="text" class="form-control" id="start1" 
						   placeholder="please enter the start point">
				    <label for="end1">end name</label>
					<input type="text" class="form-control" id="end1" 
						   placeholder="please enter the end point">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="showTourMapChoose()()">submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
		</div>
		</div>
		</div>
	</div>
	<!-- 选择景点排序方式 -->
	<div class="modal fade" id="orderArcModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				x
				</button>
				<h4 class="modal-title" id="myModalLabel">
				choose the way to order
				</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div class="form-group">
					    <label for="name">order type</label>
					    <select class="form-control" id="selectStyle">
					      <option>popular</option>
					      <option>number of branch</option>
					    </select>
					  </div>
					</form>
				</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="orderArc()">submit</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
			</div>
		</div>
		</div>
	</div>
	<!-- 停车场操作选择 -->
	<div class="modal fade" id="parkingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                 x
				</button>
				<h4 class="modal-title" id="myModalLabel">
				choose
				</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group">
					<label>First:</label><br><br>
				    <button type="button" class="btn btn-default" data-dismiss="modal" onClick="showInitForm()">Initial</button><br></br><br>
				    <label>Operation:</label><br><br>
				    <button type="button" class="btn btn-success" data-dismiss="modal" onClick="showAddParkForm()">Arrive</button>
				    <button type="button" class="btn btn-alert" data-dismiss="modal" onClick="showDeleteParkForm()">leave</button>
					</div>
				</div>
		</div>
		</div>
	</div>
	<!-- 填写停车场最大容量 -->
	<div class="modal fade" id="initParkingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		enter the maxSiz of park
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="keyWord">max Size(smaller than 9):</label>
					<input type="text" class="form-control" id="parkSize" 
						   placeholder="please max size of plot">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="initParking()">submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
		</div>
		</div>
		</div>
	</div>
	<!-- 填写到达车辆信息 -->
	<div class="modal fade" id="addCarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		Fill the information of car
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="keyWord">Number Plate</label>
					<input type="text" class="form-control" id="number" 
						   placeholder="please enter the number of your plate">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="addCar()">submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
		</div>
		</div>
		</div>
	</div>
    <!-- 填写离开车辆信息 -->
	<div class="modal fade" id="deleteParkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		x
		</button>
		<h4 class="modal-title" id="myModalLabel">
		enter in the leave car's plate
		</h4>
		</div>
		<div class="modal-body">
			<form role="form">
				<div class="form-group">
					<label for="keyWord">plate of the car leaving</label>
					<input type="text" class="form-control" id="delNumber" 
						   placeholder="please enter the number ">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary" data-dismiss="modal" onClick="deletePark()">submit</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
		</div>
		</div>
		</div>
	</div>
</div>
     
    
     
  </div>
</div>

<img src="image/">

<!-- 消息框的隐藏和显示，css中默认'p'为隐藏 -->
<script type="text/javascript">

	(function ($j10) {
		'use strict';
		$j10('.item').on("click", function () {
			$j10(this).next().slideToggle(100);
			$j10('p').not($j10(this).next()).slideUp('fast');
		});
	}($j10));
	

</script>
<script type="text/javascript" src="js/toast.js"></script>
</body>
</html>