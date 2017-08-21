<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Xóa dữ liệu</title>
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
			<li class = "active"><a onclick="document.getElementById('sidebar').style.display='block'; document.getElementById('overlay').style.display='block';">Thi Trắc Nghiệm Online</a></li>
			<li style="float:right"><a href = "LogoutServlet">Đăng xuất</a></li>
			<li style="float:right">
				<a>Xin Chào: <%=(String)session.getAttribute("userName") %></a>
			</li>
		</ul>	
	</div>
	
	<%
		if(Constants.ADMIN_RIGHTS.equals(""+session.getAttribute("type"))){
	%>
	<div id = "tab-reset" class = "container-tab-reset">
		<ul>
			<li><a href = "ResetIPServlet">Reset IP</a></li>
			<li><a href = "ResetAllIPServlet">Reset all IP</a></li>
			<li><a class="active" href = "DeleteDataServlet">Xoá dữ liệu</a></li>
		</ul>
	</div>
	<br>
	<%
		}else if (Constants.ADMIN_CHILD_RIGHTS.equals(""+session.getAttribute("type"))){
	%>
	<div id = "tab-reset2" class = "container-tab-reset">
		<ul>
			<li><a class="active" href = "ResetIPServlet">Reset IP</a></li>
			<li><a href = "ResetAllIPServlet">Reset all IP</a></li>
		</ul>
	</div>
	<br>
	<%
		}
	%>
	<form action="DeleteDataServlet" method="post" onsubmit="return confirm ('Bạn có muốn xóa toàn bộ dữ liệu?')">
		<div class = "container-table-result table-result" style = "text-align:center">
			<label>Xác nhận mật khẩu</label><br><br>
			<input type="password" class = "input-delete-data" name="password" placeholder="Nhập mật khẩu" maxlength="250" required/><br>
			<p style ="color: red; margin: 0px"><%=Utils.getVaildString((String)request.getAttribute("notification")) %></p><br>
			<button value="submit" name="submit" >Xóa dữ liệu</button>
		</div>
	</form>	
	
</body>
</html>