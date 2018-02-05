<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <html> 
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>JSP Page</title>
 </head>
 <body>

 <h1>Login Page</h1>
 <center>

<form action="http://localhost:8080/akshay/webapi/webservice/login" method="post">
 <br/>
Username:<input type="text" name="username"> <br><br><br>
Password:<input type="password" name="password"> <br><br><br>
<input type="submit" value="Submit"> 
<input type="button" value="Home" onclick="window.location.href='http://localhost:8080/akshay/home.jsp'" />
</form> 
</center> 
</body>
 </html>
