<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.*;"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
        <h1>Welcome To Amazon </h1>
      <input type="button" value="Login" onclick="window.location.href='http://localhost:8080/akshay/amazonlogin.jsp'" />
      <input type="button" value="Sign Up" onclick="window.location.href= 'http://localhost:8080/akshay/amazonsignup.jsp'" />
       <!--   <a href="http://localhost:8080/akshay/signup.jsp">Sign up</a>
        -->
        <!DOCTYPE html>

<style>
table, th, td {
   border: 1px solid black;,
   text-align: center;
}
th, td {
    padding: 15px;
}
</style>
</head>
<body>

<table style="width:50%" align="center">
  <tr>
    <th>Username</th>
     
    <th>Image</th>
  </tr>
<!--   <script>
    document.write("<tr> <th align=\"center">${it.username}"</th>");
     
     document.write("<th align=\"center\"><img src=" ${it.picpath} " alt=\"img\" height=\"150\" width=\"120">"</th></tr>");
  </script>
-->
<script>

var url  = "http://localhost:8080/akshay/webapi/webservice/displayHome";

var xhr  = new XMLHttpRequest();
xhr.open('GET',url, true);
//xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xhr.onreadystatechange = function () {
	var users = JSON.parse(xhr.responseText);

	if (xhr.readyState == 4 && xhr.status == "200")
	{
		
	
				    
			 var x1 = users.username;
			 var x2 = users.password;
			 var x3 = users.mobnumber;
			 var x4 = users.emailid;
			 var x5 = users.picpath;
			 
			 
		
			
	}
	else
	console.error(users);	
}
xhr.send();
</script>



   </table>     
        
    </body>
</html>