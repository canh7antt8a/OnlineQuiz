<%@page import="vn.fpt.fsoft.intern517.olt.model.bo.TopicBO"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Topic"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Topic</title>
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
			<li class="active"><a onclick="document.getElementById('sidebar').style.display='block'; document.getElementById('overlay').style.display='block';">Thi Trắc Nghiệm Online</a></li>
			<%
				String userName = request.getParameter("username");
			%>
			<li style="float:right"><a href = "LogoutServlet">Đăng xuất</a></li>
			<li style="float:right">
				<a>Xin Chào: <%=(String)request.getAttribute("studentName") %></a>
			</li>
		</ul>	
	</div>
	
	<div class = "container-bar-1">
		<label style="color:white">Lớp: <%=(String)request.getAttribute("className") %></label>
	</div>
	<br>
	
	<div class ="container-table-select table-select">
		<h3 style="color:#7e7e7e">Chọn Topic</h3>
		<table border="1" width="100%">
            <tbody>
            <%
	            ArrayList<Topic> listTopic = (ArrayList<Topic>)request.getAttribute("listTopic");
	            for (Topic topic:listTopic){
	            	TopicBO topicBO = new TopicBO();
	            	if (topicBO.checkIP((String)session.getAttribute("userName"), ""+topic.getTopicID()) && ((String)request.getAttribute("addressIP1") !=(String)request.getAttribute("addressIP"))){
	            		continue;   		
	            	}
	            	else{
            %>
           	 <tr>
              	 <td>
              	  	<label><%=topic.getTopicName() %></label>
	              	<input name="topicID" value="<%=topic.getTopicID()%>" style=" display:none">
	              	<button style="float:right" onclick="document.getElementById('selectTopic<%=topic.getTopicID() %>').style.display='block'">Làm bài</button>
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
		for(Topic topic:listTopic){
    %>
    
	<form id = "selectTopic<%=topic.getTopicID() %>" class = "modal" action="ExamServlet" method="post">
		<div class ="modal-content-noti animate-noti">
			<div class = "container-noti-header">
			</div>
			<input name="topicID" value="<%=topic.getTopicID() %>" style="display: none;">
			<div class = "container-noti">
				<p>Bạn có muốn làm bài <%=topic.getTopicName()%>?</p>
			</div>
			<div class = "container-noti-footer">
				<button type="submit" value="submit" name="submit">OK</button>
				<input type="button" value="Hủy" onclick="document.getElementById('selectTopic<%=topic.getTopicID() %>').style.display='none'">
			</div>
		</div>
	</form>
	<%
		}
	%>
</body>
</html>