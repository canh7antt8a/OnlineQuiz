<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page errorPage="error.jsp"%>
<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Class"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Đặt lại toàn bộ địa chỉ IP</title>
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
			<li><a class="active"  href = "ResetAllIPServlet">Reset all IP</a></li>
			<li><a href = "DeleteDataServlet">Xoá dữ liệu</a></li>
		</ul>
	</div>
	<br>
	<%
		}else if (Constants.ADMIN_CHILD_RIGHTS.equals(""+session.getAttribute("type"))){
	%>
	<div id = "tab-reset2" class = "container-tab-reset">
		<ul>
			<li><a href = "ResetIPServlet">Reset IP</a></li>
			<li><a class="active" href = "ResetAllIPServlet">Reset all IP</a></li>
		</ul>
	</div>
	<br>
	<%
		}
		ArrayList<Class> listClass = (ArrayList<Class>)request.getAttribute("listClass");
	%>
	<form id = "resetAllIP" action="ResetAllIPServlet" method="post">
		<div class = "container-table-result table-result" style = "text-align:center">
			<br>
			<label>Chọn lớp</label>
			<br><br>
			<select name="classID" style="text-align: center; width: 25%">
				<option value="0"> ---------- Chọn lớp ---------- </option>
			<%
				for(Class classes:listClass){
			%>
				<option value="<%=classes.getClassID() %>" style="text-align: left;"><%=classes.getClassName() %></option>
			<%
				}
			%>
			</select><br>
			<p style ="color: red; margin: 0px"><%=Utils.getVaildString((String)request.getAttribute("notification")) %></p>
			<br>
			<button name="submit" value="submit" >Reset</button>
			<br>
			<br>	
		</div>
	</form>
	
</body>
</html>