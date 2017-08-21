<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Student"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Class"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Quản Lý Học Sinh</title>
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
		<label style="color:white">Quản lý Học viên lớp: <%=(String)request.getAttribute("className") %></label>

		<a href="AddStudentServlet?classID=<%=(String)request.getAttribute("classID") %>"><button style="float:right; width: auto">Thêm Học Viên</button></a>

	</div>
	<br>
	
	<div class ="container-table-select table-select-student">
		<table width="100%">
           	<tbody>
           	<%
           		ArrayList<Student> listStudent = (ArrayList<Student>)request.getAttribute("listStudent");
           		for(Student student:listStudent){
           	%>
          	 	<tr>
              	  <td width = "30%">
              	  	<label><%=student.getStudentID() %></label>
              	  </td>
              	  <td width = "35%">
              	  	<label><%=student.getFullName() %></label>					
              	  </td>
              	  <td>
              	  	<button style="float:right; width: auto;" onclick="document.getElementById('resetPass<%=student.getStudentID() %>').style.display='block'">Đặt lại mật khẩu</button>
              	  	<button style="float:right" onclick="document.getElementById('deleteStudent<%=student.getStudentID() %>').style.display='block'">Xóa</button>
              	  	<a href="AdminEditStudentServlet?studentID=<%=student.getStudentID() %>&&classID=<%=(String)request.getAttribute("classID") %>"><button style="float:right">Sửa</button></a>
              	  </td>
            	</tr>
            <%
           		}
            %>
           	</tbody>
       	</table>
	</div>

	<%
	    for(Student student:listStudent){
	%>
	<form id = "deleteStudent<%=student.getStudentID() %>" class = "modal" action="DeleteStudentServlet" method="post">
		<div class ="modal-content-noti animate-noti">
			<div class = "container-noti-header">
				<input type="text" name="deleteUserName" value="<%=student.getStudentID() %>" style="display: none;">
			</div>
			<div class = "container-noti">
				<p>Bạn có muốn xóa?</p>
			</div>
			<div class = "container-noti-footer">
				<button type="submit" value="submit" name="submit">OK</button>
				<input type="button" value="Hủy" onclick="document.getElementById('deleteStudent<%=student.getStudentID() %>').style.display='none'">
			</div>
		</div>
	</form>	
	
	<form id = "resetPass<%=student.getStudentID() %>" class = "modal" action="ResetPassServlet" method="post">
		<div class ="modal-content-noti animate-noti">
			<div class = "container-noti-header">
				<input type="text" name="studentID" value="<%=student.getStudentID() %>" style="display: none;">
				<input type="text" name="classID" value="<%=(String)request.getAttribute("classID") %>" style="display: none;">
			</div>
			<div class = "container-noti">
				<p>Bạn có muốn đặt lại mật khẩu?</p>
			</div>
			<div class = "container-noti-footer">
				<button type="submit" value="submit" name="submit">OK</button>
				<input type="button" value="Hủy" onclick="document.getElementById('resetPass<%=student.getStudentID() %>').style.display='none'">
			</div>
		</div>
	</form>	
	<%
	    }
	%>
</body>
</html>