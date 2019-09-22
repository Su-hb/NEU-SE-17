

var results;
var adminresults;
var tourList;
var tourCycle;
var tourListChoose;
var coord;
//var yPosition = [70,150,140,260,250,280,260,390,380,400];
//var xPosition = [260,140,400,75,240,350,500,210,60,460];
var yPosition=[];
var xPosition=[];
var parkSize;
var first = 0;
var Note;
var more;
var newX;//新的x坐标
var newY;//新的y坐标



//打开页面读取坐标文件
$j10(document).ready(function(){
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

function createGraph(){
	var udata = "";
	$j10.ajax({url : "createGraph",
		data : udata,
		success : function(data){
			updateCanvas();
			
			var canvas = document.getElementById("myCanvas");
			Note=document.getElementById("first");
			Note.style.display="none";
			
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			results = eval(data);

			//绘制原始地图
			paintOriginalGraph(context);

			//开始绘制矩阵
			context.beginPath();
			var rule = createChart(context, 5, results.attractNum+1);
			var chartTitle = ["Name", "Description", "Popular", "Rest", "Toilet"];
			for(i=0; i<5; i++){
				var x = 550 + rule[0]*(i + 0.1);
				var y = 25 + rule[1]*0.6;
				context.fillText(chartTitle[i], x, y);
			}
			
			for(i=0; i<results.attractNum; i++){
				var arcNode = results.nodes[i];
				var nodeInfos = [arcNode.name, arcNode.des, arcNode.pop, arcNode.hasRest==true?"Has":"No", arcNode.hasToilet==true?"Has":"No"];
				for(j=0; j<5; j++){
					var x = 550 + rule[0]*(j + 0.1);
					var y = 25 + rule[1]*(i + 1.6);
					context.fillText(nodeInfos[j], x, y);
				}
			}
			context.closePath();
		},
		error : "error_",
		dataType : "json"
	});
}

function outputGraph(){
	if(results == null){
		showTipDialog("please Initial the graph first!");
		return;
	}

	updateCanvas();
	var canvas = document.getElementById("myCanvas");
	if(canvas == null){
		return false;
	}
	var context = canvas.getContext('2d');
	
	paintOriginalGraph(context);

	var rule = createChart(context, results.attractNum+1, results.attractNum+1);
	var arcNodes = results.nodes;
	//绘制行
	for(i=0; i<results.attractNum; i++){
		var x = 550 + rule[0]*(i+1+0.1);
		var y = 25 + rule[1]*0.6;
		context.fillText(arcNodes[i].name, x, y);
	}
	//绘制列
	for(i=0; i<results.attractNum; i++){
		var x = 550 + rule[0]*0.1;
		var y = 25 + rule[1]*(i+1+0.6);
		context.fillText(arcNodes[i].name, x, y);
	}
	//绘制
	
	for(i=0; i<results.attractNum; i++){
		for(j=0; j<results.attractNum; j++){
			var x = 550 + rule[0]*(j+1+0.1);
			var y = 25 + rule[1]*(i+1+0.6);
			if(i == j){
				context.fillText("0", x, y);
			}else{
				var dis = isContact(i,j);
				context.fillText(dis, x, y);
			}
		}
	}
	context.closePath();
}

function showMapForm(){
	$j10('#tourMapModal').modal();
}

function showTourMapForm(){
	if(results == null){
		showTipDialog("please Initial the graph first!");
		return;
	}
	$j10('#start').val("");
	$j10('#tourListModal').modal();
}

function tourMap(){
	if($j10('#start').val()==""){
		showTipDialog("The start information can't be empty!");
		return;
	}

	var udata = "start=" + $j10('#start').val();
	
	$j10.ajax({url : "output",
		data : udata,
		type : "post",
		success : function(data){
			var listAndCycle = eval(data);
			tourList = listAndCycle.tourList;
			tourCycle = listAndCycle.tourCycle;

			if(tourList.length == 0){
				showTipDialog("You get wrong informaion,please fill the form again!");
				return;
			}
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始地图
			paintOriginalGraph(context);
			
				
			var arrowPic = new Image();
			
			arrowPic.src = "image/arrow.png";
			
			var ind = 0;
			var visIndex = new Array(results.attractNum);
			for(i=0; i<results.attractNum; i++){
				visIndex[i] = 0;
			}
			visIndex[tourList[0]] = 1;
			var inter = setInterval(function(){
				context.beginPath();
				context.strokeStyle = 'rgb(255,0,0)';
				context.lineWidth = 2;
				
				var r = 50;
				var p = 50;
				context.moveTo(xPosition[tourList[ind]], yPosition[tourList[ind]]);
				if(visIndex[tourList[ind+1]] == 1){
					context.quadraticCurveTo(xPosition[tourList[ind]]-r, yPosition[tourList[ind+1]]-r, xPosition[tourList[ind+1]], yPosition[tourList[ind+1]]);
				}else{
					context.quadraticCurveTo(xPosition[tourList[ind]]+r, yPosition[tourList[ind+1]]+r, xPosition[tourList[ind+1]], yPosition[tourList[ind+1]]);
				}
				visIndex[tourList[ind+1]]++;
				context.stroke();
					
				context.strokeStyle = '#000000';
				context.lineWidth = 1;
				
				
				context.fillText(results.nodes[tourList[ind]].name, 740, 45+ind*37);
				context.drawImage(arrowPic,840,20+ind*37,35,25);
				context.fillText(results.nodes[tourList[ind+1]].name, 950, 45+ind*37);

				ind++;
				if(ind >= tourList.length-1){
					clearInterval(inter);
				}
			},500);
		},
		error : "error_",
		dataType : "json"
	});
}

function showChooseMapForm(){
	if(results == null){
		showTipDialog("please Initial the graph first!");
		return;
	}
	$j10('#start1').val("");
	$j10('#end1').val("");
	$j10('#tourListChooseModal').modal();
}

function showTourMapChoose(){
	if($j10('#start1').val()=="" || $('#end1').val()==""){
		showTipDialog("The start or end information cant't be empty");
		return;
	}
	
	var udata = "start1=" + $j10('#start1').val() + "&end1=" + $j10('#end1').val();
	
	$j10.ajax({url:"TourMapChoose",
		data : udata,
		type : "post",
		success : function(data){
			var listAndChoose = eval(data);
			tourList = listAndChoose.tourList;
			if(tourList.length == 0){
				showTipDialog("You get wrong informaion,please fill the form again!");
				return;
			}
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始地图
			paintOriginalGraph(context);
			
				
			var arrowPic = new Image();
			
			arrowPic.src = "image/arrow.png";
			
			var ind = 0;
			var visIndex = new Array(results.attractNum);
			for(i=0; i<results.attractNum; i++){
				visIndex[i] = 0;
			}
			visIndex[tourList[0]] = 1;
			var inter = setInterval(function(){
				context.beginPath();
				context.strokeStyle = 'rgb(255,225,0)';
				context.lineWidth = 2;
				
				var r = 50;
				var p = 50;
				context.moveTo(xPosition[tourList[ind]], yPosition[tourList[ind]]);
				if(visIndex[tourList[ind+1]] == 1){
					context.quadraticCurveTo(xPosition[tourList[ind]]-r, yPosition[tourList[ind+1]]-r, xPosition[tourList[ind+1]], yPosition[tourList[ind+1]]);
				}else{
					context.quadraticCurveTo(xPosition[tourList[ind]]+r, yPosition[tourList[ind+1]]+r, xPosition[tourList[ind+1]], yPosition[tourList[ind+1]]);
				}
				visIndex[tourList[ind+1]]++;
				context.stroke();
					
				context.strokeStyle = '#000000';
				context.lineWidth = 1;
				
				
				context.fillText(results.nodes[tourList[ind]].name, 740, 45+ind*30);
				context.drawImage(arrowPic,840,20+ind*30,37,20);
				context.fillText(results.nodes[tourList[ind+1]].name, 950, 45+ind*30);

				ind++;
				if(ind >= tourList.length-1){
					clearInterval(inter);
				}
			},500);
		},
		error : "error_",
		dataType : "json"
	});
}


function showShortestPathForm(){
	if(results == null){
		showTipDialog("please Initial the graph first!");
		return;
	}
	$j10('#startName').val("");
	$j10('#endName').val("");
	$j10('#shortestPathModal').modal();
}

function shortestPath(){
	if($j10('#startName').val()=="" || $('#endName').val()==""){
		showTipDialog("The start or end information cant't be empty");
		return;
	}

	var udata = "startName=" + $j10('#startName').val() + "&endName=" + $j10('#endName').val();
	
	
	$j10.ajax({url : "Shortestpath",
		data : udata,
		type : "post",
		success : function(data){
			var shortest = eval(data);

			if(shortest.pathDis == -1){
				showTipDialog("You get wrong start name or end name,please fill the form again!");
				return;
			}
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			//缁绘制原始地图
			paintOriginalGraph(context);

			var startPic = new Image();
			var arrowPic = new Image();
			var endPic = new Image();
			startPic.src = "image/start.png";
			arrowPic.src = "image/arrow.png";
			endPic.src = "image/end.png";
			
			var ind = 0;
			var inter = setInterval(function(){
				var sx = xPosition[shortest.nodesIndex[ind]];
				var sy = yPosition[shortest.nodesIndex[ind]];
				var ex = xPosition[shortest.nodesIndex[ind+1]];
				var ey = yPosition[shortest.nodesIndex[ind+1]];
				
				context.beginPath();
				context.strokeStyle = 'rgb(255,0,0)';
				context.lineWidth = 2;
				context.moveTo(sx, sy);
				context.lineTo(ex, ey);
				context.stroke();
				context.strokeStyle = '#000000';
				context.lineWidth = 1;
				
				context.font = '22px Arial';
				context.fillText(results.nodes[shortest.nodesIndex[ind]].name, 700, 70+ind*70);
				context.drawImage(arrowPic,800,35+ind*70,120,40);
				context.fillText(results.nodes[shortest.nodesIndex[ind+1]].name, 980, 70+ind*70);
				
				ind++;
				if(ind == shortest.nodesIndex.length-1){
					context.font = '22px Arial';
					context.fillText("The Shortest length is :"+shortest.pathDis ,750, 60+ind*70);
					context.font = '17px Arial';
				}
				if(ind >= shortest.nodesIndex.length-1){
					clearInterval(inter);
				}
			},500);
		},
		error : "error_",
		dataType : "json"
	});
}

function buildRoad(){
	if(results == null){
		showTipDialog("please Initial the graph first!");
		return;
	}

	var udata = "";
	$j10.ajax({url : "constructRoad",
		data : udata,
		success : function(data){
			var edges = eval(data);
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');

			paintOriginalGraph(context);
			
			
			var startPic = new Image();
			var arrowPic = new Image();
			var endPic = new Image();
			startPic.src = "img/start.png";
			arrowPic.src = "img/double.png";
			endPic.src = "img/end.png";
			
			var ind = 0;
			var inter = setInterval(function(){
				var sx = xPosition[edges.results[ind].start];
				var sy = yPosition[edges.results[ind].start];
				var ex = xPosition[edges.results[ind].end];
				var ey = yPosition[edges.results[ind].end];
				
				context.beginPath();
				context.strokeStyle = 'rgb(255,0,0)';
				context.lineWidth = 2;
				context.moveTo(sx, sy);
				context.lineTo(ex, ey);
				context.stroke();
				context.strokeStyle = '#000000';
				context.lineWidth = 1;
				
				context.drawImage(startPic,700,20+ind*70,50,70);
				context.fillText(results.nodes[edges.results[ind].start].name, 770, 60+ind*70);
				context.drawImage(arrowPic,820,20+ind*70,50,50);
				context.drawImage(endPic,890,20+ind*70,50,70);
				context.fillText(results.nodes[edges.results[ind].end].name, 960, 60+ind*70);
				
				ind++;
				if(ind >= edges.results.length){
					clearInterval(inter);
				}
			},500);
		},
		error : "error_",
		dataType : "json"
	});
}

function showSearchForm(){
	if(results == null){
		showTipDialog("please Initial the graph first!");
		return;
	}
	$j10('#keyWord').val("");
	$j10('#searchModal').modal();
}

function searchArc(){
	if($j10('#keyWord').val()==""){
		showTipDialog("The sort keyword can't be empty!");
		return;
	}

	var udata = "keyWord=" + $('#keyWord').val();
	
	$j10.ajax({url : "findAttract",
		data : udata,
		type : "post",
		success : function(data){
			var searchNodes = eval(data);
			
			if(searchNodes.length == 0){
				showTipDialog("The result of keyword search is empty. Please enter another keyword search! ");
				return;
			}
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始地图
			paintOriginalGraph(context);
			//开始绘制寻路，从点到点
			context.beginPath();
			context.strokeStyle = 'rgb(255,15,0)';
			context.lineWidth = 5;
			for(i=0; i<searchNodes.length; i++){
				context.beginPath();
				context.arc(xPosition[searchNodes[i]],yPosition[searchNodes[i]],30,0,Math.PI*2,true);
				context.stroke();
			}
			context.strokeStyle = '#000000';
			context.lineWidth = 1;
			
			context.font = '25px 华文细黑';
			context.fillText("Find result:", 550, 70);
			context.font = '17px Arial';
			//绘制行
			for(col=0; col <= 5; col++){
				var x = col * 110 + 550;
				context.moveTo(x,100);
				context.lineTo(x,50*(searchNodes.length+1)+100);
			}
			for(var row = 0; row <= searchNodes.length+1; row++){
				var y = row * 50 + 100;
			    context.moveTo(550,y);
			    context.lineTo(550+5*110,y);
			}
			context.stroke();
			//绘制表头，矩阵表头
			var chartTitle = ["Name", "Description", "Popular", "Rest", "Toilet"];
			for(i=0; i<5; i++){
				var x = 550 + 110*(i + 0.1);
				var y = 100 + 50*0.6;
				context.fillText(chartTitle[i], x, y);
			}
			for(i=0; i<searchNodes.length; i++){
				var arcNode = results.nodes[searchNodes[i]];
				var nodeInfos = [arcNode.name, arcNode.des, arcNode.pop, arcNode.hasRest==true?"Has":"No", arcNode.hasToilet==true?"Has":"No"];
				for(j=0; j<5; j++){
					var x = 550 + 110*(j + 0.1);
					var y = 100 + 50*(i + 1.6);
					context.fillText(nodeInfos[j], x, y);
				}
			}
		},
		error : "error_",
		dataType : "json"
	});
}

function showOrderArcForm(){
	if(results == null){
		showTipDialog("please Initial the graph first!");
		return;
	}
	$j10('#orderArcModal').modal();
}

function orderArc(){
	var udata = "type=" + $j10('#selectStyle').val();

	$j10.ajax({url : "orderAttract",
		data : udata,
		type : "post",
		success : function(data){
			var orderResults = eval(data);
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			var label = "[";
			for(i=0; i<orderResults.length-1; i++){
				label = label + "\"" + orderResults[i].name + "\",";
			}
			label = label + "\"" + orderResults[i].name + "\"]";
			var dataset = "[";
			for(i=0; i<orderResults.length-1; i++){
				dataset = dataset + orderResults[i].value + ",";
			}
			dataset = dataset + orderResults[i].value + "]";
			
			var show = {
					labels:eval(label),
					datasets:[
					   {
						   label:"Num",
						   backgroundColor:'rgba(65,230,255,0.6)',
						   borderColor:'rgba(54,72,208,1)',
						   borderWidth:1,
						   data:eval(dataset),
					   }
					]
			};
			var options = "scales: {xAxes: [{stacked: true}],yAxes: [{stacked: true}]}";
			
			
			var chart = new Chart(context,{
				type:'bar',
				data:show,
				options:options
			});
		},
		error : "error_",
		dataType : "json"
	});
}

function showParkingOption(){
	$j10('#parkingModal').modal();
}

function showInitForm(){
	$j10('#parkSize').val("");
	$j10('#initParkingModal').modal();
} 

function initParking(){
	parkSize = $j10('#parkSize').val();
	if(parkSize == ""){
		showTipDialog("you should init the park first!");
		return;
	}
	if(parseInt(parkSize) != parkSize){
		showTipDialog("enter the right number!");
		return;
	}
	if(parkSize<=0 || parkSize>=9){
		showTipDialog("the size not fit.please smaller than 9");
		return;
	}
	
	var udata = "parkSize=" + parkSize;
	
	$j10.ajax({url : "initialPark",
		data : udata,
		type : "post",
		success : function(data){
			first = 1;
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			Note=document.getElementById("first");
			Note.style.display="none";
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');

			paintParkingGraph(context, parkSize);
			
		},
		error : "error_",
		dataType : "json"
	});
}

function showAddParkForm(){
	if(first == 0){
		showTipDialog("please initial the park first!");
		return;
	}
	$j10('#number').val("");
	$j10('#addCarModal').modal();
} 

function addCar(){
	var number = $('#number').val();
	if(number == ""){
		showTipDialog("the plane number can't be empty!");
		return;
	}
	var udata = "number=" + number;
	
	$j10.ajax({url : "addCar",
		data : udata,
		type : "post",
		success : function(data){
			var results = eval(data);
			console.log(results);
			if(results.exist == true){
				showTipDialog("Park plot has the same car, is your car stolen? Should i call the police");
				return;
			}
			
			updateCanvas();
			var canvas = document.getElementById("myCanvas");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			paintParkingGraph(context, parkSize);
			
			var carPic = new Image();
			carPic.src = "image/park.png";
			context.beginPath();
			for(i=0; i<results.tempParkStack.length; i++){
				context.drawImage(carPic,65+100*i,85,42,50);
				context.fillText(results.tempParkStack[i].number, 60+100*i,150);
				context.fillText(new Date(results.tempParkStack[i].ar_time).Format("hh:mm"), 60+100*i,170);
			}
			
			for(i=0; i<results.parkStack.length; i++){
				context.drawImage(carPic,65+100*i,255,42,50);
				context.fillText(results.parkStack[i].number, 60+100*i,320);
				context.fillText(new Date(results.parkStack[i].ar_time).Format("hh:mm"), 60+100*i,340);
			}
			for(i=0; i<results.waitlane.length; i++){
				context.drawImage(carPic,65+100*parkSize+50+100*i,255,42,50);
				context.fillText(results.waitlane[i].number, 60+100*parkSize+50+100*i,320);
				context.fillText(new Date(results.waitlane[i].ar_time).Format("hh:mm"), 60+100*parkSize+50+100*i,340);
			}
		},
		error : "error_",
		dataType : "json"
	});
}

function showDeleteParkForm(){
	if(first == 0){
		showTipDialog("please initial the park first!");
		return;
	}
	$j10('#delNumber').val("");
	$j10('#deleteParkModal').modal();
}

function deletePark(){
	var delNumber = $j10('#delNumber').val();
	if(delNumber == ""){
		showTipDialog("the plane number can't be empty!");
		return;
	}
	var udata = "number=" + delNumber;
	
	$j10.ajax({url : "deletePark",
		data : udata,
		type : "post",
		success : function(data){
			var results = eval(data);
			
			if(results.exist == null){
				showTipDialog("there is no car in park!");
				return;
			}
			if(results.exist == false){
				showTipDialog("there is no this car!");
				return;
			}

			var carPic = new Image();
			carPic.src = "image/park.png";
			
			var index = 1;
			var inter = setInterval(function(){
				updateCanvas();
				var canvas = document.getElementById("myCanvas");
				if(canvas == null){
					return false;
				}
				var context = canvas.getContext('2d');
				
				paintParkingGraph(context, parkSize);
				//alert(index);
				for(i=0; i<results.tempParkStack[index].length; i++){
					context.drawImage(carPic,65+100*i,85,42,50);
					context.fillText(results.tempParkStack[index][i].number, 60+100*i,150 );
					context.fillText(new Date(results.tempParkStack[index][i].ar_time).Format("hh:mm"), 60+100*i,170);
				}
				//alert(index);
				for(i=0; i<results.parkStack[index].length; i++){
					context.drawImage(carPic,65+100*i,255,42,50);
					context.fillText(results.parkStack[index][i].number, 60+100*i,320);
					context.fillText(new Date(results.parkStack[index][i].ar_time).Format("hh:mm"), 60+100*i,340);
				}
				//alert(index);
				for(i=0; i<results.waitlane[index].length; i++){
					context.drawImage(carPic,65+100*parkSize+50+100*i,255,42,50);
					context.fillText(results.waitlane[index][i].number, 60+100*parkSize+50+100*i,320);
					context.fillText(new Date(results.waitlane[index][i].ar_time).Format("hh:mm"), 60+100*parkSize+50+150*i,340 );
				}
				index++;
				//alert(index);
				if(index >= results.length){
					showTipDialog("Car plate:" + delNumber + ".it stays in Park" + results.parkTime + "minutes,cost" + results.cost);
					clearInterval(inter);
				}
			}, 1000);
		},
		error : "error_",
		dataType : "json"
	});
}

function clearAll(){
	results = null;
	first = 0;
	
	var more = document.getElementById("more");
	if(more!==null){
	    $j10('#more').remove();
	}
	
	$j10('#myCanvasParent').append('<img src="image/more.png" id="more" style="position:absolute;top:91px;left:259px;height:27px;" onclick="more()">');
	
	var canvas = document.getElementById("myCanvas");
	if(canvas == null){
		return false;
	}
	var context = canvas.getContext('2d');
	
	
	
	context.clearRect(0,0,canvas.width,canvas.height);
	//将初始组件重新显现
	Note.style.display="";
	more.style.display="";
}

function error_(){
	alert("going wrong");
}

//绘制表格
function createChart(context, cols, rows){
	var grid_cols = cols;
	var grid_rows = rows;
	var cell_width = 620/grid_cols;
	var cell_height = 484/grid_rows;
	context.beginPath();
	for(col=0; col <= grid_cols; col++){
		var x = col * cell_width + 550;
		context.moveTo(x,26);
		context.lineTo(x,510);
	}
  for(var row = 0; row <= grid_rows; row++){
    var y = row * cell_height +26;
    context.moveTo(550,y);
    context.lineTo(1170,y);
  }
  context.stroke();

  var rule = [cell_width, cell_height];
  return rule;
}

//判断是否是连接状态
function isContact(fromIndex, toIndex){
	var dis = 32767;
	var edges = results.nodes[fromIndex].edges;
	for(k=0; k<edges.length; k++){
		if(edges[k].index == toIndex){
			dis = edges[k].dist;
			break;
		}
	}

	return dis;
}

function paintOriginalGraph(context){
	context.font="50px Jokerman";
	context.fillText("Map:",10,70);
	//创造gradient
	var gradient=context.createLinearGradient(0,0,530,0);
	gradient.addColorStop("0","#f17df0");
	gradient.addColorStop("0.25","#57beee");
	gradient.addColorStop("0.5","#54e792");
	gradient.addColorStop("0.75","#baf727");
	gradient.addColorStop("1.0","#f1c735");
	
	for(i=0; i<results.attractNum; i++){
		context.beginPath();
		context.strokeStyle=gradient;
		context.lineWidth=4;
		context.arc(xPosition[i],yPosition[i],30,0,Math.PI*2,true);
		context.closePath();
		context.stroke();
		context.font = '15px Arial';
		context.fillText(results.nodes[i].name,xPosition[i]-23,yPosition[i]+5);
	}
	context.strokeStyle='rgba(0,0,0,1)';
	context.lineWidth=1;
	context.beginPath();
	for(i=0; i<results.attractNum; i++){
		var edges = results.nodes[i].edges;
		for(j=0; j<edges.length; j++){
			if(i < edges[j].index){
				context.moveTo(xPosition[i], yPosition[i]);
				context.lineTo(xPosition[edges[j].index], yPosition[edges[j].index]);
				context.stroke();
				context.font = '15px Arial';
				context.fillText(edges[j].dist, (xPosition[i]+xPosition[edges[j].index])/2+10, (yPosition[i]+yPosition[edges[j].index])/2);
			}
		}
	}
}

function paintParkingGraph(context, parkSize){
	context.beginPath();
	context.font = '20px Arial';
	context.fillText("TEMP",40,70);
	context.moveTo(30, 80);
	context.lineTo(30+100*parkSize, 80);
	context.moveTo(30, 180);
	context.lineTo(30+100*(parkSize-1), 180);
	context.moveTo(30+100*(parkSize-1), 180);
	context.lineTo(30+100*(parkSize-1), 250);
	
	context.font = '19px Arial';
	context.fillText("P",5,275);
    context.fillText("A",5,295);
    context.fillText("R",5,315);
    context.fillText("K",5,335);
	
    context.font = '15px Arial';
	context.moveTo(30, 250);
	context.lineTo(30+100*parkSize, 250);
	context.moveTo(30, 350);
	context.lineTo(30+100*parkSize, 350);
	
	context.moveTo(30+100*parkSize+25,140);
	context.lineTo(30+100*parkSize+25,260);
    context.fillText("G",30+100*parkSize+22,275);
    context.fillText("A",30+100*parkSize+22,295);
    context.fillText("T",30+100*parkSize+22,315);
    context.fillText("E",30+100*parkSize+22,335);
    context.moveTo(30+100*parkSize+25,340);
	context.lineTo(30+100*parkSize+25,460);
	
	//biandao 
	context.moveTo(30+100*parkSize+50, 250);
	context.lineTo(30+100*parkSize+470, 250);
	context.moveTo(30+100*parkSize+50, 350);
	context.lineTo(30+100*parkSize+470, 350);
	context.stroke();
	context.beginPath();
	context.arc(30+100*parkSize+470,550,200,0,-Math.PI/2,true);
	context.stroke();
	context.beginPath();
	context.arc(30+100*parkSize+470,550,300,0,-Math.PI/2,true);
	//停车场
	context.moveTo(30, 80);
	context.lineTo(30, 180);
	context.moveTo(30+parkSize*100,80);
	context.lineTo(30+parkSize*100,250);
	for(i=0; i<=parkSize; i++){
		context.moveTo(30+i*100, 250);
		context.lineTo(30+i*100, 350);
	}
	//便道
	context.moveTo(30+100*parkSize+50,250);
	context.lineTo(30+100*parkSize+50,350);
	context.stroke();
	
	context.font = '17px Arial';
	for(i=1; i<=parkSize; i++){
		context.beginPath();
		context.fillText(i, 40+(i-1)*100,270);
		context.arc(45+(i-1)*100,265,10,0,Math.PI*2,true);
		context.stroke();
	}
	context.beginPath();
	context.stroke();
	context.font = '17px Arial';
	
}

function showTipDialog(message){
	$j10('#tipMessage').html(message);
	$j10('#tipModal').modal();
}

function updateCanvas(){
	$j10('#myCanvas').remove();
	$j10('#more').remove();
	$j10('#myCanvasParent').append('<canvas id="myCanvas" style="border-radius:10px; width:100%" width="1180px" height="525px"></canvas>');
}

Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}



/*
 * 
 */
//下面都是Admin的部分



//显示admin界面的初始地图
function showGraph(){
	var udata = "";
	$.ajax({url : "createGraph",
		data : udata,
		success : function(data){
			updateCanvasAdmin();
			
			var canvasA = document.getElementById("CanvasAdmin");
			var canvasA1 = document.getElementById("CanvasAdmin1");
			var canvasA2 = document.getElementById("CanvasAdmin2");
			
			if(canvasA == null){
				return false;
			}
			if(canvasA1 == null){
				return false;
			}
			var context = canvasA.getContext('2d');
			var context1 = canvasA1.getContext('2d');
			var context2 = canvasA2.getContext('2d');
			
			results = eval(data);
			
			//绘制原始地图
			paintOriginalGraph(context);
			paintOriginalGraph(context1);
			paintOriginalGraph(context2);
			
		},
		error : "error_",
		dataType : "json"
	});
}
//选择想要添加的坐标，显示图像
function chooseAttract(){
	updateCanvasAdmin();
	var isOne=true;
	
	var canvasA = document.getElementById("CanvasAdmin");
	
	if(canvasA == null){
		return false;
	}
	var context = canvasA.getContext('2d');
	
	//绘制
	paintOriginalGraph(context);
	
	
	canvasA.onmousedown=function(ev){
	  if(isOne==true){
		context.beginPath();
		context.strokeStyle = "#000";
		
        context.lineWidth = "3px solid";
        
		//鼠标点击位置
        newX = ev.offsetX;
        newY = ev.offsetY;
        
     
        
        //加入gardient色彩
        var gradient=context.createLinearGradient(0,0,530,0);
    	gradient.addColorStop("0","#f17df0");
    	gradient.addColorStop("0.5","#57beee");
    	gradient.addColorStop("1","#54e792");
       
    	
		context.strokeStyle=gradient;
		context.lineWidth=4;
		context.arc(newX,newY,30,0,Math.PI*2,true);
		context.closePath();
		context.stroke();
		context.font = '20px Arial';
		context.fillText("new ",newX-23,newY+5);
		isOne=false;
	  }
	}
}

//添加坐标进行保存
function Add(){
	if((newX==null) || (newY==null)){
		alert("please choose a new Attraction point!");
		return;
	}

	var p=newX+"@"+newY;
	
	window.location.href="Administrator.jsp?param="+p;
	
	
}
//标记选中景点在图中的坐标
function markArc(){
	var index=document.getElementById("keyWord").selectedIndex;//获取当前选择项的索引.
	var start=document.getElementById("keyWord").options[index].text;
	
	if((start=="please choose")){
		alert("The sort keyword can't be empty!");
		return;
	}


	var udata = "keyWord=" + start;
	
	$.ajax({url : "findAttract",
		data : udata,
		type : "post",
		success : function(data){
			var searchNodes = eval(data);
			
			if(searchNodes.length == 0){
				alert("The result of keyword search is empty. Please enter another keyword search! ");
				return;
			}
			
			updateCanvasAdmin1();
			var canvas = document.getElementById("CanvasAdmin1");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始地图
			paintOriginalGraph(context);
			//开始绘制寻路，从点到点
			context.beginPath();
			context.strokeStyle = 'rgb(255,15,0)';
			context.lineWidth = 5;
			for(i=0; i<searchNodes.length; i++){
				context.beginPath();
				context.arc(xPosition[searchNodes[i]],yPosition[searchNodes[i]],30,0,Math.PI*2,true);
				context.stroke();
			}
			
			
		},
		error : "error_",
		dataType : "json"
	});
}
function markArc2(){
	var index2=document.getElementById("keyWord2").selectedIndex;//获取当前选择项的索引.
	var end=document.getElementById("keyWord2").options[index2].text;
	
	if((end=="please choose")){
		alert("The sort keyword can't be empty!");
		return;
	}

	var udata = "keyWord=" + end;
	
	$.ajax({url : "findAttract",
		data : udata,
		type : "post",
		success : function(data){
			var searchNodes = eval(data);
			
			if(searchNodes.length == 0){
				alert("The result of keyword search is empty. Please enter another keyword search! ");
				return;
			}
			
			updateCanvasAdmin1();
			var canvas = document.getElementById("CanvasAdmin1");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始地图
			paintOriginalGraph(context);
			//开始绘制寻路，从点到点
			context.beginPath();
			context.strokeStyle = 'rgb(255,15,0)';
			context.lineWidth = 5;
			for(i=0; i<searchNodes.length; i++){
				context.beginPath();
				context.arc(xPosition[searchNodes[i]],yPosition[searchNodes[i]],30,0,Math.PI*2,true);
				context.stroke();
			}
			
			
		},
		error : "error_",
		dataType : "json"
	});
}

function markArc3(){
	var index3=document.getElementById("deleteAttract").selectedIndex;//获取当前选择项的索引.
	var end=document.getElementById("deleteAttract").options[index3].text;
	
	if((end=="please choose")){
		alert("The sort keyword can't be empty!");
		return;
	}

	var udata = "keyWord=" + end;
	
	$.ajax({url : "findAttract",
		data : udata,
		type : "post",
		success : function(data){
			var searchNodes = eval(data);
			
			if(searchNodes.length == 0){
				alert("The result of keyword search is empty. Please enter another keyword search! ");
				return;
			}
			
			updateCanvasAdmin2();
			var canvas = document.getElementById("CanvasAdmin");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			context.clearRect(0,0,canvas.width,canvas.height);
			//绘制原始地图
			paintOriginalGraph(context);
			//开始绘制寻路，从点到点
			context.beginPath();
			context.strokeStyle = 'rgb(255,15,0)';
			context.lineWidth = 5;
			for(i=0; i<searchNodes.length; i++){
				context.beginPath();
				context.arc(xPosition[searchNodes[i]],yPosition[searchNodes[i]],30,0,Math.PI*2,true);
				context.stroke();
			}
			
			
		},
		error : "error_",
		dataType : "json"
	});
}

function recommend(){
	var udata = "";
	$.ajax({url : "recommendRoad",
		data : udata,
		success : function(data){
			var edges = eval(data);
			updateCanvasAdmin();
			var canvas = document.getElementById("CanvasAdmin2");
			if(canvas == null){
				return false;
			}
			var context = canvas.getContext('2d');
			
			paintOriginalGraph(context);
			 var ind = 0;
			 var inter = setInterval(function(){
				    var sx = xPosition[edges.results[ind].start];
					var sy = yPosition[edges.results[ind].start];
					var ex = xPosition[edges.results[ind].end];
					var ey = yPosition[edges.results[ind].end];
					
					context.beginPath();
					context.strokeStyle = 'rgb(255,0,0)';
					context.lineWidth = 2;
					context.moveTo(sx, sy);
					context.lineTo(ex, ey);
					context.stroke();
					context.strokeStyle = '#000000';
					context.lineWidth = 1;
				 
				ind++;
				if(ind >= edges.results.length){
					clearInterval(inter);
				}
			 },500);
		  },
		error:"error_",
		dataType : "json"
		});
}

function updateCanvasAdmin(){
		$('#CanvasAdmin').remove();
		$('#CanvasAdmin1').remove();
		$('#CanvasAdmin2').remove();
		$('#CanvasParent').append('<canvas id="CanvasAdmin" style="border-radius:10px; border:1px solid #fff; width:100%" width="700px" height="520px">');
		$('#CanvasParent1').append('<canvas id="CanvasAdmin1" style="border-radius:10px; border:1px solid #fff; width:100%" width="700px" height="520px">');
		$('#CanvasParent2').append('<canvas id="CanvasAdmin2" style="border-radius:10px; border:1px solid #fff; width:100%" width="700px" height="520px">');
}
function updateCanvasAdmin1(){
	$('#CanvasAdmin1').remove();
	$('#CanvasParent1').append('<canvas id="CanvasAdmin1" style="border-radius:10px; border:1px solid #fff; width:100%" width="700px" height="520px">');
}
function updateCanvasAdmin2(){
	$('#CanvasAdmin').remove();
	$('#CanvasParent').append('<canvas id="CanvasAdmin" style="border-radius:10px; border:1px solid #fff; width:100%" width="700px" height="520px">');
}
