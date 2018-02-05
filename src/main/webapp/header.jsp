<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>header</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body   >

<nav  class="navbar navbar-default navbar-fixed-top" style="background-color:#000000;">
  <div class="container-fluid" style="background-color:#000000;" >
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header" style="background-color:#000000;border: none;">
    
    <nav class="navbar navbar-default"  style="background-color:#000000;border: none;">
  <div class="container-fluid" style="background-color:#000000;border: none;">
    <div class="navbar-header" style="background-color:#000000;border: none;	">
    <a href="http://localhost:8080/akshay/amazonhomepage.jsp">
      <img src="http://127.0.0.1:8887/upload/amazon_logo.jpg"  style="height:80px;border: none; color: Orange" />
       <!--  <img alt="Brand" src="..."> -->
      </image>
    </div>
  </div>
</nav>
    </div>
    <div style="padding-top:15px;">
  <form  action="/action_page.php" >
   
      <div class="input-group">
        
      <div style="float: left;background-color:#f9f9f9;padding:6px;height:35px;">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">All <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Electronics</a></li>
            <li><a href="#">Computers</a></li>
            <li><a href="#">Women's Clothing</a></li>
            <li><a href="#">Men's Clothing</a></li>        
          </ul>
        </li>
        </div>
       <div style="float: left;width:600px;" >
        <input type="text" class="form-control" placeholder="Search" name="search"  >
          </div>
            <div class="input-group-btn" style="float: left;height:23px;" >
          <button class="btn btn-default" type="submit" style="background-color:orange;height:150%">
            <i class="glyphicon glyphicon-search"></i>
          </button>
          </div>
      </div>
    </form>
</div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="margin-right:20px;">
   <!--    <ul class="nav navbar-nav">
        <li ><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
       
      </ul> -->
     
      <ul class="nav navbar-nav navbar-right">
       <button type="button" class="btn btn-default navbar-btn" onclick="window.location.href='http://localhost:8080/akshay/amazonlogin.jsp'" >Sign in</button>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="http://localhost:8080/akshay/youraccount.jsp">Account</a></li>
            <li><a href="#">Your Account</a></li>
            <li><a href="#">Your Cart</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Your Wish List</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

</body>
</html>