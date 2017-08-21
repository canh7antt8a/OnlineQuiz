package vn.fpt.fsoft.intern517.olt.controller;

/**
 * DeleteClassAdminServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 20, 2017        Nguyen Cong Huong          	Create
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.ClassBO;

public class DeleteClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteClassServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		ClassBO classBO = new ClassBO();
		
		HttpSession session = request.getSession();
		// Kiem tra da dang nhap chua
		if (session.getAttribute("userName") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		// Kiem tra quyen dang nhap
		if (Constants.STUDENT_RIGHTS.equals(session.getAttribute("type"))) {
			response.sendRedirect("WelcomeStudentServlet");
			return;
		}
		
		//Xoa user lop theo ID lop
		String classID = request.getParameter("classID");
		if ("submit".equals(request.getParameter("submit"))) {
			classBO.deleteClass(classID);
			response.sendRedirect("ManageClassServlet");
		}else {
			response.sendRedirect("ManageClassServlet");
		}
	}

}
