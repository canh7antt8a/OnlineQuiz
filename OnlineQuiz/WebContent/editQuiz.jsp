<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sửa đáp án</title>
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
		<label style="color: white;">Sửa Topic: <%=request.getAttribute("topicName") %></label>
	</div>
	<br>
	
	<%
		String numberOfQuiz = (String)request.getAttribute("numberOfQuiz");
		String numberOfAnswer = (String)request.getAttribute("numberOfAnswer");
	%>
	<form action="EditQuizServlet" method="post">
		<div class = "container-table-quiz table-quiz">
			<table border="1" width="100%">
	           	<tbody id="checkbox-quiz">
	           		<tr>
	           		  <th>Câu hỏi</th>
	           		  <th>Đáp án</th>
	           		</tr>
					<%
						for(int i=1; i<=Integer.parseInt(numberOfQuiz); i++){
					%>
	          	 	<tr>
	              	  <td>Câu <%=i %></td>
	              	  <td>
	              	  <%
	              		for(int j=0; j<Integer.parseInt(numberOfAnswer); j++){
	              			String alphabet = Constants.ALPHABET[j];
	              	  %>
	              	  <label><%=alphabet %>&nbsp;<input type = "checkbox" <%if(alphabet.equals((String)request.getAttribute(""+i+j))){ %> checked="checked" <%} %> value="<%=alphabet %>" name="<%=i %><%=j %>"><span></span></label>&nbsp;&nbsp;
	              	  <%
	              		}
	              	  %>
	              	  </td>          	  
	            	</tr>
					<%
						}
					%>
	           	</tbody>
	       	</table>
	       	<br>
	       	<input name="topicID" value="<%=request.getAttribute("topicID") %>" style=" display:none">
	       	<input name="numberOfQuiz" value="<%=request.getAttribute("numberOfQuiz") %>" style=" display:none">
	       	<input name="numberOfAnswer" value="<%=request.getAttribute("numberOfAnswer") %>" style=" display:none">
	       	<button style="float:center" type="submit" value="submit" name="complete">Hoàn tất</button>
		</div>
	</form>

</body>
</html>