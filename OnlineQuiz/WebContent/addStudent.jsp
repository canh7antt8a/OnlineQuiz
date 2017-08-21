<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
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
<body style ="background-color: rgba(0,0,0,0.7)">
<form style ="padding-top: 20px" action="AddStudentServlet" method="post" id="addStudent">
	<div class = "modal-content-info animate-noti">
		<div class = "container-info-header">
			<label style = "font-size:26px">Thêm Học Viên</label>
		</div>
		<div class = "container-info">
			<p>
				<label>Lớp</label>
				<input style = "display:none" name = "classID" value="<%=(String)request.getAttribute("classID") %>">
				<input class="input-info" readonly="readonly" value="<%=(String)request.getAttribute("className") %>">
			</p>
			
			<p style ="margin-bottom: 0px">
				<label>Tên Đăng Nhập<label style="color: red">*</label></label>
				<input class="input-info" type = "text" name ="userName" id="userName" onkeyup="createPass()" maxlength="50">
				<p style ="color: red; margin: 0px"><%=Utils.getVaildString((String)request.getAttribute("notification")) %></p>
			</p>

			<p style ="margin-bottom: 0px">
				<label>Mật khẩu<label style="color: red">*</label></label>
				<input class="input-info" type = "password" name = "password" id="password" maxlength="250">
			</p>

			<p style ="margin-bottom: 0px">
				<label>Xác nhận mật khẩu<label style="color: red">*</label></label>
				<input class="input-info" type = "password" id="comfirmPass" maxlength="250">
			</p>

			<p>
				<label>Họ và tên<label style="color: red">*</label></label>
				<input class="input-info" type = "text" name = "fullName" id="fullName" maxlength="250">
			</p>
			
			<p>
				<label>Giới tính</label><br>
				<select class = "select-sex-info" name = "sex">
					<option value="1">Nam</option>
					<option value="0">Nữ</option>
				</select>
			</p>
			
			<p>
				<label>Email</label>
				<input class="input-info" type = "text" maxlength="250" name = "email" id="email">
			</p>
			
			<p>
				<label>Số điện thoại</label>
				<input class="input-info" type = "text" name = "phoneNumber" maxlength="11" id="phoneNumber">
				
			</p>
		</div>
		<div class = "container-info-footer">
			<button type="submit" value="submit" name="submit" id="submit">Thêm</button>
            <button type="submit" value="cancel" name="cancel">Hủy</button>
		</div>
	</div>
	
	<script type="text/javascript">
	var inputs = document.forms['addStudent'].getElementsByTagName('input');
	var run_onchange = false;
	  function valid(){
	    var errors = false;
	    var reg_mail = /^[A-Za-z0-9]+([_\.\-]?[A-Za-z0-9])*@[A-Za-z0-9]+([\.\-]?[A-Za-z0-9]+)*(\.[A-Za-z]+)+$/;
	   for(var i=0; i<inputs.length; i++){
	    var value = inputs[i].value;
	    var id = inputs[i].getAttribute('id');
	   
	    // Tạo phần tử span lưu thông tin lỗi
	    var span = document.createElement('span');
	    // Nếu span đã tồn tại thì remove
	    var p = inputs[i].parentNode;
	    if(p.lastChild.nodeName == 'SPAN') {p.removeChild(p.lastChild);}
	    value2 = value.trim();
	    // Kiểm tra rỗng
	    if(id != 'email' && id != 'phoneNumber'&& value == '' && value2==''){
	     span.innerHTML ='Thông tin này cần được điền vào.';
	    }else{
	    	
	    	if(id == 'email' && value !=''){
				if(reg_mail.test(value) == false){ span.innerHTML ='Email không hợp lệ!';}
	    			var email =value;
	    	}

	    	if(id == 'password' && value2 == ''){
	    		span.innerHTML ='Thông tin này cần được điền vào.';}
	    	else if(id == 'password' && value2 != ''){
		    	var pass =value;
		    }
		    // Kiểm tra password nhập lại
		    if(id == 'comfirmPass' && value != pass){span.innerHTML ='Mật khẩu nhập lại chưa đúng!';}
	    	
	    	if(id == 'phoneNumber' && isNaN(value) == true){span.innerHTML ='Số điện thoại không hợp lệ';}
	    	}


	    // Nếu có lỗi thì chèn span vào hồ sơ, chạy onchange, submit return false, highlight border
	    if(span.innerHTML != ''){
	     inputs[i].parentNode.appendChild(span);
	     errors = true;
	     run_onchange = true;
	     inputs[i].style.border = '1px solid #c6807b';
	     inputs[i].style.background = '#fffcf9';
	    }else{
	    	inputs[i].style.border = '1px solid #ccc';
		    inputs[i].style.background = '#ffffff';
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

</form>
</body>
</html>