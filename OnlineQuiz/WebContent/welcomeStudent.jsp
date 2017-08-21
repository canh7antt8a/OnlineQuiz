<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sinh Viên</title>
	<link rel="stylesheet" href="css/style.css" />
</head>
<body>

	<div id = "sidebar" class = "sidebar animate-sidebar" style = "display:none">
		<ul>
			<li class = "active"><a href="WelcomeStudentServlet">Thi Trắc Nghiệm Online</a></li>
			<li><a href="SelectTopicStudentServlet">Chọn Topic thi</a></li>
			<li><a href="EditStudentServlet">Đổi thông tin tài khoản</a></li>
			<li><a href="EditPasswordStudentServlet">Đổi mật khẩu</a></li>
		</ul>
	</div>
	
	<div class = "overlay overlay-sidebar" id = "overlay" onclick="document.getElementById('sidebar').style.display='none'; document.getElementById('overlay').style.display='none';">
	</div>

	<div id = "navbar" class = "container-navbar">
		<ul>
			<li class="active">
				<a onclick="document.getElementById('sidebar').style.display='block'; document.getElementById('overlay').style.display='block';">Thi Trắc Nghiệm Online</a>
			</li>
			<li style="float:right"><a href = "LogoutServlet">Đăng xuất</a></li>
			<li style="float:right">
				<a>Xin Chào: <%=(String)request.getAttribute("studentName") %></a>
			</li>
		</ul>	
	</div>
	<br>
	
	<div class = "container-menu-welcome">
		<h3>Lớp: <%=(String)request.getAttribute("className") %></h3>
		<a href="SelectTopicStudentServlet">
			<button class = "button-menu-welcome">Chọn Topic thi</button>
		</a>
		<a href="EditStudentServlet">
			<button class = "button-menu-welcome">Đổi thông tin tài khoản</button>
		</a>
		<a href="EditPasswordStudentServlet">
			<button class = "button-menu-welcome">Đổi mật khẩu</button>
		</a>
	</div>

</body>
</html>