<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazon Home page</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script> 
$(function(){
  $("#header").load("header.jsp"); 
  $("#footer").load("footer.jsp"); 
});
</script> 
</head>
<body style="padding-top:90px">
<jsp:include page="header.jsp" />
<h1>Success</h1>

<input type="button" value="Home" onclick="window.location.href='http://localhost:8080/akshay/amazonhomepage.jsp'" />
<jsp:include page="footer.jsp" />
</body>
</html>

