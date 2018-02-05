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
<jsp:include page="header.jsp" /><br><br><br>
<h1 align="center">Your Account </h1>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<center>
<div align="center" class="w3-container">

  <button  onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black">Open Modal</button>

  <div  align="justify" id="id01" class="w3-modal">
    <div class="w3-modal-content">
      <div class="w3-container">
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <form action="webapi/webservice/upload" method="post" enctype="multipart/form-data">

	   <p>
		Select a file : <input type="file" name="uploadFile" size="20" />
	   </p>

	   <input type="submit" value="Upload It" />
	</form>
      </div>
    </div>
  </div>
</div>

</center>

<jsp:include page="footer.jsp" />
</body>
</html>

