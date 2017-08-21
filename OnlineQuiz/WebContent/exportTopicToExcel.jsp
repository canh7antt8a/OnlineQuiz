<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Exam"%>
<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Danh Sách Điểm</h2>
	<h4>Topic: <%=request.getAttribute("topicName")%></h4>

	<div >
		<table border="1" width="100%">
			<tbody>
				<tr>
					<th>Tên đăng nhập</th>
					<th>Tên học viên</th>
					<th>Lớp</th>
					<th>Topic</th>
					<th>Thời gian bắt đầu</th>
					<th>Thời gian kết thúc</th>
					<th>Địa chỉ IP</th>
					<th>Điểm thi</th>
				</tr>
				<%
					ArrayList<Exam> listTopicResult = (ArrayList<Exam>) request.getAttribute("listTopicResult");

	                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	                    response.setHeader("Content-Disposition", "inline; filename="
	                                    + "DanhSachDiemHocVienTheoTopic.xls");
					for (Exam ex : listTopicResult) {
				%>
				<tr>
					<td><%=ex.getStudentID()%></td>
					<td><%=ex.getFullNameStudent()%></td>
					<td><%=ex.getClassName()%></td>
					<td><%=ex.getTopicName()%></td>
              	  	<%
              	  		if("".equals(ex.getStartTime())){
              	  	%>
              	  	<td><%=ex.getStartTime() %></td>
              	  	<%
              	  		}else{
              	  	%>
              	  	<td><%=Utils.formatDateTime(ex.getStartTime()) %></td>
              	  	<%
              	  		}
              	  		if("".equals(ex.getEndTime())){
              	  	%>
              	  	<td><%=ex.getEndTime() %></td>
              	  	<%
              	  		}else{
              	  	%>
              	  	<td><%=Utils.formatDateTime(ex.getEndTime()) %></td>
              	  	<%
              	  		}
              	  	%>
					<td><%=ex.getAddressIP()%></td>
					<td><%=ex.getResult()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>