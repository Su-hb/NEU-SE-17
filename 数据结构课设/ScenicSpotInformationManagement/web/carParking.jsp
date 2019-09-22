<%@ page import="java.util.Stack" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="car.zanlind"%>


<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- PAGE settings -->
  <link rel="icon" href="https://templates.pingendo.com/assets/Pingendo_favicon.ico">
  <title>Pricing</title>
  <meta name="description" content="Wireframe design of a pricing table by Pingendo">
  <meta name="keywords" content="Pingendo bootstrap example template wireframe pricing table">
  <meta name="author" content="Pingendo">
  <!-- CSS dependencies -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="wireframe.css">

</head>

<body>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container"> <a class="navbar-brand" href="#">
        <i class="fa d-inline fa-lg fa-stop-circle"></i>
        <b>景区信息管理系统</b>
      </a> <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse" data-target="#navbar10">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbar10">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item"> <a class="nav-link" href="#">Features</a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">Pricing</a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">About</a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">FAQ</a> </li>
        </ul>
        <input type="button" value="管理员登录" class=" btn navbar-btn ml-md-2 btn-light text-dark" contenteditable="true" style="	box-shadow: 0px 0px 4px  black;" onclick="javascrtpt:window.location.href='\login.html'">
      </div>
    </div>
  </nav>



        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h3 class="">停车场：<br></h3>
          <br>
          <%
            String aaa = request.getParameter("parkingLot");
            System.out.println(aaa);
          %>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h3 class="">便道：</h3>



  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="row">
        <div class="col-md-12">
            <div class="row">
                <form name="carMethod" action="CarInformationManagement" method="post">
                    <div class="form-group">
                        <label>车牌号</label>
                        <input name="carNumber"type="text" class="form-control">
                    </div>
                    <button name = "methodName" value="1" type="submit" class="btn btn-primary">添 加</button>
                    <button name = "methodName" value="2" type="submit" class="btn btn-primary">删 除</button>
                    <button name = "methodName" value="3" type="submit" class="btn btn-primary" >重 置</button>
                </form>
            </div>
        </div>
      </div>


  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <pingendo onclick="window.open('https://pingendo.com/', '_blank')" style="cursor:pointer;position: fixed;bottom: 20px;right:20px;padding:4px;background-color: #00b0eb;border-radius: 8px; width:220px;display:flex;flex-direction:row;align-items:center;justify-content:center;font-size:14px;color:white">Made with Pingendo Free&nbsp;&nbsp;<img src="https://pingendo.com/site-assets/Pingendo_logo_big.png" class="d-block" alt="Pingendo logo" height="16"></pingendo>
  <div class="py-5" style="">
    <div class="container">
    </div>
    <div class="py-5">
      <div class="container">
        <div class="row pt-5 border-top">
          <div class="col-12 col-md"> <i class="fa fa-lg fa-bullseye d-block mb-2"></i> <small class="d-block mb-3 text-muted">© 2019-2020</small> </div>
          <div class="col-6 col-md">
            <h5><b>Features</b></h5>
            <ul class="list-unstyled">
              <li>
                <a class="text-muted" href="#">Cool stuff</a>
              </li>
              <li>
                <a class="text-muted" href="#">Random feature</a>
              </li>
              <li>
                <a class="text-muted" href="#">Team feature</a>
              </li>
              <li>
                <a class="text-muted" href="#">Stuff for developers</a>
              </li>
              <li>
                <a class="text-muted" href="#">Another one</a>
              </li>
              <li>
                <a class="text-muted" href="#">Last time</a>
              </li>
            </ul>
          </div>
          <div class="col-6 col-md">
            <h5><b>Resources</b></h5>
            <ul class="list-unstyled">
              <li>
                <a class="text-muted" href="#">Resource</a>
              </li>
              <li>
                <a class="text-muted" href="#">Resource name</a>
              </li>
              <li>
                <a class="text-muted" href="#">Another resource</a>
              </li>
              <li>
                <a class="text-muted" href="#">Final resource</a>
              </li>
            </ul>
          </div>
          <div class="col-6 col-md">
            <h5><b>About</b></h5>
            <ul class="list-unstyled">
              <li>
                <a class="text-muted" href="#">Team</a>
              </li>
              <li>
                <a class="text-muted" href="#">Locations</a>
              </li>
              <li>
                <a class="text-muted" href="#">Privacy</a>
              </li>
              <li>
                <a class="text-muted" href="#">Terms</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>
