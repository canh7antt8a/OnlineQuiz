package vn.fpt.fsoft.intern517.olt.controller;

/**
 * DeleteAdminServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 15, 2017        Nguyen Cong Huong          	Create
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

public class DeleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserBO userBO = new UserBO();

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
		if (Constants.ADMIN_CHILD_RIGHTS.equals(session.getAttribute("type"))) {
			response.sendRedirect("WelcomeAdminServlet");
			return;
		}

		//Xoa user admin theo user name
		String deleteUserName = request.getParameter("deleteUserName");
		if ("submit".equals(request.getParameter("submit"))) {
			userBO.deleteAdmin(deleteUserName);
			response.sendRedirect("ManageAdminServlet");
		}else{
			response.sendRedirect("ManageAdminServlet");
		}
	}

}
