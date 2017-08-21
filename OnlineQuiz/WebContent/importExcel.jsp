<%@page import="vn.fpt.fsoft.intern517.olt.common.Utils"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Thêm tài khoản học viên</title>
	<link rel="stylesheet" href="css/style.css" />
</head>
<body style ="background-color: rgba(0,0,0,0.7)">

	<form action = "UploadServlet" method = "post" enctype = "multipart/form-data" style ="padding-top: 30px">
         <div class = "modal-content-info animate-noti">
			<div class = "container-info-header">
				<label style = "font-size:26px">Thêm tài khoản học viên</label>
			</div>
			
			<div class = "container-info" style="text-align: center;">
				<p>
					<input type = "file" name = "file" size = "50" accept=".xls"/>
					<p style ="color: red; margin: 0px"><%=Utils.getVaildString((String)request.getAttribute("notification")) %></p>
				</p>
			</div>
			
			<div class = "container-info-footer">
				<button type="submit" name ="submit" value="submit">Tải tập tin</button>
	            <button name ="cancel" value="cancel">Hủy</button>
			</div>
	</div>	
     </form>

</body>
</html>