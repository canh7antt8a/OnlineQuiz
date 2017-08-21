<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Class"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Quản Lý Lớp</title>
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
		<label style="color:white">Quản lý lớp</label>
		<a href="ShowListStudentServlet"><button style="float:right; width: auto">Danh sách tất cả học viên</button></a>
		<a href="UploadServlet"><button style="float:right; width: auto">Nhập từ tập tin Excel</button></a>
		<a href="AddClassServlet"><button style="float:right">Thêm lớp</button></a>
	</div>
	
	<br>
	
	<div class ="container-table-select table-select">
		<table border="1" width="100%">
           	<tbody>
           	<%
           		ArrayList<Class> listClass = (ArrayList<Class>)request.getAttribute("listClass");
           		for(Class classes:listClass){
           	%>
          	 	<tr>
              	  <td>
              	  	<label><%=classes.getClassName() %></label>
					<button style="float:right" onclick="document.getElementById('deleteClass<%=classes.getClassID() %>').style.display='block'">Xóa</button>
              	  	<a style="float:right" href="EditClassServlet?classID=<%=classes.getClassID()%>">
              	  		<button>Đổi tên</button>
              	  	</a>
              	  	<a style="float:right" href="ManageStudentServlet?classID=<%=classes.getClassID()%>">
              	  		<button style = "width: auto">Quản lý học viên</button>
              	  	</a>
              	  </td>
            	</tr>
            	<%
           			}
            	%>
           	</tbody>
       	</table>
	</div>
	
	<%
      	for(Class classes:listClass){
    %>

	<form id = "deleteClass<%=classes.getClassID() %>" class = "modal" action="DeleteClassServlet" method="post">
		<div class ="modal-content-noti animate-noti">
			<div class = "container-noti-header">
			</div>
			<div class = "container-noti">
				<p>Bạn có muốn xóa?</p>
				<input name="classID" value="<%=classes.getClassID() %>" style="display: none;">
			</div>
			<div class = "container-noti-footer">
				<button type="submit" value="submit" name="submit">OK</button>
				<input type="button" value="Hủy" onclick="document.getElementById('deleteClass<%=classes.getClassID() %>').style.display='none'"/>
			</div>
		</div>
	</form>	
	<%
      	}
	%>

</body>
</html>