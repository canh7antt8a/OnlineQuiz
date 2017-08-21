<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Topic"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Exam"%>
<%@page import="vn.fpt.fsoft.intern517.olt.model.bean.Class"%>
<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Danh sách kết quả</title>
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
	
	<div id = "tab-result" class = "container-tab-result tab-result">
		<ul>
			<li><a href = "ResultClassServlet">Lấy kết quả theo lớp</a></li>
			<li><a class="active" href = "ResultTopicServlet">Lấy kết quả theo Topic</a></li>
		</ul>
	</div>
	
	<form action="ResultTopicServlet" method="post" id="resultTopic">
		<div class = "container-bar-2">
			<label>Chọn topic&nbsp;&nbsp;</label>
			<select name="selectTopic" style="text-align: center">
				<option value="0" id="0">---- Chọn topic ----</option>
				<% 
					ArrayList<Topic> listTopic = (ArrayList<Topic>)request.getAttribute("listTopic");
					String selectTopic = (String)request.getAttribute("selectTopic");
					for(Topic topic:listTopic){
				%>
				<option <%if(selectTopic.equals(""+topic.getTopicID())){ %>selected="selected"<%} %> value="<%=topic.getTopicID() %>" style="text-align: left"><%=topic.getTopicName() %></option>
				<%
					}
				%>
			</select>&nbsp;&nbsp;
			<button type="submit" value="submit" name="submitTopic">Xem</button>
			<button style="float:right" type="submit" value="submit" name="submitExcel" id="submitExcel">Xuất Excel</button>
		</div>
	</form>
	
	<script type="text/javascript">
	var inputs = document.forms['resultTopic'].getElementsByTagName('select');
	var run_onchange = false;
	  function valid(){
	    var errors = false;
	   for(var i=0; i<inputs.length; i++){
	    var value = inputs[i].value;
	    var id = inputs[i].getAttribute('id');
	   
	    // Kiểm tra rỗng
	    if(value == 0){
	    	errors = true;
		    run_onchange = true;
		    inputs[i].style.border = '1px solid #ff0000';
		    inputs[i].style.background = '#fffcf9';
	    }else{
	    	inputs[i].style.border = '1px solid #ccc';
		    inputs[i].style.background = '#ffffff';
	    }
	   
	   }// end for
	  
	   return !errors;
	  }// end valid()
	 
	  // Chạy hàm kiểm tra valid()
	  var register = document.getElementById('submitExcel');
	  register.onclick = function(){
	   return valid();
	  }
	</script>
	
	<%
		if("0".equals(selectTopic)){
			return;
		}else{
			ArrayList<Exam> listTopicResult = (ArrayList<Exam>)request.getAttribute("listTopicResult");
	%>
	<div class = "container-table-result table-result">
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
              	  <th>Điểm</th>
            	</tr>
            	<%
            		for(Exam ex:listTopicResult){
            	%>
            	<tr>
              	  <td><%=ex.getStudentID() %></td>
              	  <td><%=ex.getFullNameStudent() %></td>
              	  <td><%=ex.getClassName() %></td>
              	  <td><%=ex.getTopicName() %></td>
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
              	  <td><%=ex.getAddressIP() %></td>
              	  <td><%=ex.getResult() %></td>
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