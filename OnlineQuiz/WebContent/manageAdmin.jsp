<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Quản Lý Administrator</title>
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
	
	<form action="AddAdminServlet" method="post">
		<div class = "container-bar-1">
			<label style="color:white">Quản lý Admin</label>
			<button style="float:right" >Thêm Admin</button>
		</div>
		<br>
	</form>
	
	<div class ="container-table-select table-select">
		<table border="1" width="100%">
           	<tbody>
			<%
				ArrayList<User> listAdmin = (ArrayList<User>)request.getAttribute("listAdmin");
				for(User userAdmin:listAdmin){
					if(userAdmin.getUserName().equals((String)session.getAttribute("userName"))){
				
			%>
				<tr>
              	  <td>
              	  	<label><%=userAdmin.getUserName() %></label>
              	  	<a href="EditPasswordAdminServlet?userName=<%=userAdmin.getUserName() %>"><button style="float:right" >Đổi mật khẩu</button></a>
              	  </td>
            	</tr>
			<%
					}else{
			%>
          	 	<tr>
              	  <td>
              	  	<label><%=userAdmin.getUserName() %></label>
              	  	<a href="EditPasswordAdminServlet?userName=<%=userAdmin.getUserName() %>"><button style="float:right" >Đổi mật khẩu</button></a>
					<button style="float:right" onclick="document.getElementById('deleteAdmin<%=userAdmin.getUserName() %>').style.display='block'">Xóa</button>
              	  </td>
            	</tr>
			<%
					}
				}
			%>
           	</tbody>
       	</table>
	</div>
	
	<%
		for(User userAdmin:listAdmin){
	%>
	<form id = "deleteAdmin<%=userAdmin.getUserName() %>" class = "modal" action="DeleteAdminServlet" method="post">
		<div class ="modal-content-noti animate-noti">
			<div class = "container-noti-header">
				<input type="text" name="deleteUserName" value="<%=userAdmin.getUserName()%>" style="display: none;">
			</div>
			<div class = "container-noti">
				<p>Bạn có muốn xóa?</p>
			</div>
			<div class = "container-noti-footer">
				<button type="submit" value="submit" name="submit">OK</button>
				<input type="button" value="Hủy" onclick="document.getElementById('deleteAdmin<%=userAdmin.getUserName() %>').style.display='none'">
			</div>
		</div>
	</form>				
	<%
		}
	%>	
		
</body>
</html>