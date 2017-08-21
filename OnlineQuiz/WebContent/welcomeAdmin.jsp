<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Administrator</title>
	<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id = "sidebar" class = "sidebar animate-sidebar" style = "display:none">
		<ul>
			<li class = "active"><a href="WelcomeAdminServlet">Thi Trắc Nghiệm Online</a></li>
			<%
				if(Constants.ADMIN_RIGHTS.equals(""+session.getAttribute("type"))){
			%>
			<li><a href="ManageAdminServlet">Quản lý Admin</a></li>
			<%
				}else if (Constants.ADMIN_CHILD_RIGHTS.equals(""+session.getAttribute("type"))){
			%>
			<li><a href="EditPasswordChildAdminServlet">Đổi mật khẩu</a></li>
			<%
				}
			%>
			<li><a href="ManageClassServlet">Quản lý lớp/học viên</a></li>
			<li><a href="ManageTopicServlet">Quản lý Topic</a></li>
			<li><a href="ResultClassServlet">Lấy kết quả</a></li>
			<li><a href="CheckIPServlet">Kiểm tra IP</a></li>
			<li><a href="ResetIPServlet">Reset IP/Xóa dữ liệu</a></li>
		</ul>
	</div>
	
	<div class = "overlay overlay-sidebar" id = "overlay" onclick="document.getElementById('sidebar').style.display='none'; document.getElementById('overlay').style.display='none';">
	</div>
	
	<div id = "navbar" class = "container-navbar">
		<ul>
			<li class = "active">
				<a onclick="document.getElementById('sidebar').style.display='block'; document.getElementById('overlay').style.display='block';">Thi Trắc Nghiệm Online</a>
			</li>
			<li style="float:right"><a href = "LogoutServlet">Đăng xuất</a></li>
			<li style="float:right">
				<a>Xin Chào: <%=(String)session.getAttribute("userName") %></a>
			</li>
		</ul>	
	</div>
	<br>
	<div class = "container-menu-welcome">
		<%
			if(Constants.ADMIN_RIGHTS.equals(""+session.getAttribute("type"))){
		%>
		<a href="ManageAdminServlet">
			<button class = "button-menu-welcome">Quản lý Admin</button>
		</a>
		<%
			}else if (Constants.ADMIN_CHILD_RIGHTS.equals(""+session.getAttribute("type"))){
		%>
		<a href="EditPasswordChildAdminServlet">
			<button class = "button-menu-welcome">Đổi mật khẩu</button>
		</a>
		<%
			}
		%>
		<a href="ManageClassServlet">
			<button class = "button-menu-welcome">Quản lý lớp/học viên</button>
		</a>
		<a href="ManageTopicServlet">
			<button class = "button-menu-welcome">Quản lý Topic</button>
		</a>
		<a href="ResultClassServlet">
			<button class = "button-menu-welcome">Lấy kết quả</button>
		</a>
		<a href="CheckIPServlet">
			<button class = "button-menu-welcome">Kiểm tra IP</button>
		</a>
		<a href="ResetIPServlet">
			<button class = "button-menu-welcome">Reset IP/Xóa dữ liệu</button>
		</a>
	</div>
	
</body>
</html>