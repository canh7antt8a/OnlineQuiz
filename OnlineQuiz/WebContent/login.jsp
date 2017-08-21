<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Đăng Nhập</title>
	<link rel="stylesheet" href="css/styleLogin.css">
</head>
<body>

	<img alt="imgfpt2" src="image/FPTcomplex.jpg" style = "width: 100%; height: 100%; top : 0; position: fixed">
	<form action="LoginServlet" method="post" style="position: relative">
		<div class="container">
			<h3 align ="center">
				<%
					DateFormat fm =new SimpleDateFormat("dd/MM/yyyy");
					String ngay = fm.format(new Date());
				%>
				Xin Chào, Hôm nay là:
				<%=ngay %>
				<br>
				Mời bạn đăng nhập
			</h3>
		</div>
		<div class="imglogin" align ="center">
			<img src="image/imgfpt.png" alt ="imgfpt" class="imgfpt">
		</div>
		
		<div class="container" align ="center">
			<input type="text" name="userName" placeholder="Enter username" required/><br>
			<input type="password" name="password" placeholder="Enter password" required/><br>
			<p style ="color: red; margin: 0px"><%=Utils.getVaildString((String)request.getAttribute("notification")) %></p><br>	
		</div>
		<br>
		
		<div class="container" align ="center">
			<button class="loginbtn" type="submit" value="submit" name="submit">Login</button>
			<button class="cancelbtn" type="reset" value="reset" name="reset">Cancel</button>
		</div>			
  	</form>	
  	
</body>
</html>