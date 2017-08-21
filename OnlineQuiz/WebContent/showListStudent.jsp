<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Danh sách toàn bộ học viên</title>
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
	
	<div class = "container-bar-1">
		<label style="color:white">Danh sách tất cả học viên</label>
	</div>
	<br>
	
	<div class = "container-table-result table-result">
		<table border="1" width="100%">
           	<tbody>
           	
           	<tr>
           		<th width = "33%">
           			<label>Tên đăng nhập</label>
           		</th>
           		<th width = "33%">
           			<label>Tên học viên</label>
           		</th>
           		<th>
           			<label>Lớp</label>
           		</th>
           	</tr>
           	
           	<%
           		ArrayList<Student> listStudent = (ArrayList<Student>)request.getAttribute("listAllStudent");
           		for(Student student:listStudent){
           	%>
          	 	<tr>
              	  <td width = "33%">
              	  	<label><%=student.getStudentID() %></label>
              	  </td>
              	  <td width = "33%">
              	  	<label><%=student.getFullName() %></label>					
              	  </td>
              	  <td>
              	  	<label><%=student.getClassName() %></label>
              	  </td>
            	</tr>
            <%
           		}
            %>
           	</tbody>
       	</table>
	</div>
	
</body>
</html>