<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sửa tên topic</title>
	<link rel="stylesheet" href="css/style.css" />
</head>
<body style ="background-color: rgba(0,0,0,0.7)">

	<form id="editTopic" style ="padding-top: 30px" action="EditTopicServlet" method="post">
		<div class = "modal-content-info animate-noti">
			<div class = "container-info-header">
				<label style = "font-size:26px">Sửa tên topic</label>
			</div>
			<div class = "container-info">
				<p>
					<label>Nhập tên topic</label>
					<input style="display: none;" class="input-info" type = "text" name = "topicID" value="<%=request.getAttribute("topicID") %>">
					<input maxlength="250" id="topicName" class="input-info" type = "text" name = "topicName" value="<%=request.getAttribute("topicName") %>">
					<p style ="color: red; margin: 0px"><%=Utils.getVaildString((String)request.getAttribute("notification")) %></p>
				</p>
			</div>
			<div class = "container-info-footer">
				<button type="submit" value="submit" name="submit" id="submit">Sửa</button>
	            <button name="cancel" value="cancel">Hủy</button>
			</div>
		</div>	
	</form>
	
	<script type="text/javascript">
	var inputs = document.forms['editTopic'].getElementsByTagName('input');
	var run_onchange = false;
		function valid(){
	    	var errors = false;
	  		for(var i=0; i<inputs.length; i++){
		    	var value = inputs[i].value.trim();
		   		var id = inputs[i].getAttribute('id');
	   
			    // Tạo phần tử span lưu thông tin lỗi
			    var span = document.createElement('span');
			    // Nếu span đã tồn tại thì remove
			    var p = inputs[i].parentNode;
			    if(p.lastChild.nodeName == 'SPAN') {p.removeChild(p.lastChild);}
	   
		   	 	// Kiểm tra rỗng
			    if(value == ''){
			     span.innerHTML ='Thông tin này cần được điền vào.';
			    }
		   
		    // Nếu có lỗi thì chèn span vào hồ sơ, chạy onchange, submit return false, highlight border
			    if(span.innerHTML != ''){
				    inputs[i].parentNode.appendChild(span);
				    errors = true;
				    run_onchange = true;
				    inputs[i].style.border = '1px solid #c6807b';
				    inputs[i].style.background = '#fffcf9';
			    }
	   		}// end for
	  
	  		return !errors;
	  }// end valid()
	 
	  // Chạy hàm kiểm tra valid()
	  var register = document.getElementById('submit');
	  register.onclick = function(){
		  return valid();
	  }
	</script>

</body>
</html>