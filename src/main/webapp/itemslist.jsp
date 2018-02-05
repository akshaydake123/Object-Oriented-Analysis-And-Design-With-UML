<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="http://127.0.0.1:8887/upload/w3.css">
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
<body style="padding-top:100px">

<jsp:include page="header.jsp" />
<h1 align="center">Data</h1>

   <style>
table, th, td {
   border: 1px solid black;,
   text-align: center;
}
th, td {
    padding: 15px;
}
</style>

  <table class="w3-table-all w3-centered" style="width:50%" align="center">
  <tr>
    <th align="center">Username</th>
     
    <th align="center" >Image</th>
  </tr>
  

   

   <c:forEach var="entry" items="${it.share}">
    <tr><td align="center"><c:out value="${entry.key}"/></td> 
    <td align="center"><img src="${entry.value}" height="140" width="128"/> </td></tr> 
    </c:forEach> 
</table>
<jsp:include page="footer.jsp" />

</body>
</html>