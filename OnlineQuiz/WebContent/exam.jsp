<%@page import="java.util.ArrayList"%>
<%@page import="vn.fpt.fsoft.intern517.olt.common.Constants"%>
<%@page import= "vn.fpt.fsoft.intern517.olt.model.bean.Quiz" %>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome</title>
	<link rel="stylesheet" href="css/style.css" />
	<script src="js/javascript.js" type="text/javascript"/></script>
</head>
<body>
	
	<div id = "navbar" class = "container-navbar">
		<ul>
			<li class = "active"><a>Thi Trắc Nghiệm Online</a></li>
			<li style="float:right"><a href = "LogoutServlet">Đăng xuất</a></li>
			<%
				String userName = (String)session.getAttribute("userName");
			%>
			<li style="float:right"><a>Xin Chào: <%=(String)request.getAttribute("studentName")%></a></li>
		</ul>	
	</div>
	<%
    	 ArrayList<Quiz> listQuiz = (ArrayList<Quiz>)request.getAttribute("listQuiz");        
     %>


<form action="AnswerServlet" method="post" id="submit" onsubmit="return confirm ('Bạn có muốn nộp bài không?')">
	<div class = "container-table-quiz table-quiz" >
		<b style="font-size:20px"><%=request.getAttribute("topicName") %>&nbsp;(<%=listQuiz.size() %> câu)</b><br><br>
		<table border="1" width="100%">
           	<tbody>
           		<tr>
           		  <th>Câu hỏi</th>
           		  <th>Đáp án</th>
           		  <th>Đánh dấu</th>
           		</tr>
          	 	<%
          	 		int quizCount = 1;
                	for(Quiz quiz:listQuiz){
                		
                %>
                <tr>
              	  <td>Câu <%=quiz.getQuizName() %></td>
              	 
              	  <td >
              	  	<%
              	  	
              	  	for(int i=0 ; i< quiz.getMaxAnswer(); i++) {
              	  		
              	  		String alphabet = Constants.ALPHABET[i];
              	  		 
              	  		if (quiz.getAnswer().length() == 1){
              	 	%>
              	 	
              	  	<label><%= alphabet%></label> <label><input type="radio" value="<%=alphabet %>" name="answer_<%=quizCount%>_<%=1 %>"><span></span></label>&nbsp;&nbsp;&nbsp;
              	  	<%}
              	  	else{
         			%>	
         			<label><%= alphabet%></label> <label id = "checkbox-quiz"><input type="checkbox" value="<%=alphabet %>" name="answer_<%=quizCount %>_<%=i%>" ><span></span></label>&nbsp;&nbsp;&nbsp;&nbsp;
         			<%}
              	  	}%>
              	  </td>
              	  <td id = "flag-quiz"><label><input type = "checkbox"><span></span></label></td>    	  
            	</tr>
            	<%
            		quizCount++;
            	} 
            	%>
           	</tbody>
       	</table>
       	<br>
       	<button><a href="javascript:{}" style="align:center; color:white" onclick="checkSubmit()">Hoàn tất</a></button>
       	<input name="action" value="hoantat" style=" display:none"> 
	</div>
	<input name="topicID" value="<%= request.getAttribute("topicID") %>" style=" display:none">
	
	
</form>

</body>
</html>