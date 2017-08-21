<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Exam"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Topic"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Class"%>
<%@page errorPage="error.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Kiểm tra IP</title>
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
	
	<form action="CheckIPServlet" method="post">
		<div class = "container-bar-1">
			<label style="color:white">Kiểm tra IP gian lận(trùng)</label>
			<button style="float:right" name="submit" value="submit">Kiểm tra</button>
		</div>
	</form>
	<br>
	
	<%
		if("0".equals((String)request.getAttribute("check"))){
			return;
		}else{
			ArrayList<Exam> listIP = (ArrayList<Exam>) request.getAttribute("listIP");
	%>
	<div class = "container-table-result table-result">
		<table border="1" width="100%">
           	<tbody>
          	 	<tr>
              	  <th>Tên học viên</th>
              	  <th>Lớp</th>
              	  <th>Tên topic</th>
              	  <th>Địa chỉ IP</th>
            	</tr>
            	<%
            		for(Exam ex:listIP){
            	%>
            	<tr>
              	  <td><%=ex.getFullNameStudent() %></td>
              	  <td><%=ex.getClassName() %></td>
              	  <td><%=ex.getTopicName() %></td>
              	  <td><%=ex.getAddressIP() %></td>
            	</tr>
            	<%
            		}
            	%>
           	</tbody>
       	</table>
	</div>
	<%
		}
	%>
</body>
</html>