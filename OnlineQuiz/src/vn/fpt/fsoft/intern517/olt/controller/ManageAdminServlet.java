package vn.fpt.fsoft.intern517.olt.controller;

/**
 * ManageAdminStudent.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 15, 2017        Nguyen Cong Huong          	Create
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bean.User;
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

public class ManageAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManageAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// Kiem tra da dang nhap chua
		if (session.getAttribute("userName") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		// Kiem quyen dang nhap
		if (Constants.STUDENT_RIGHTS.equals(session.getAttribute("type"))) {
			response.sendRedirect("WelcomeStudentServlet");
			return;
		}
		
		if (Constants.ADMIN_CHILD_RIGHTS.equals(session.getAttribute("type"))) {
			response.sendRedirect("WelcomeAdminServlet");
			return;
		}

		// Lay danh sach user name cua admin
		UserBO userBO = new UserBO();
		ArrayList<User> listAdmin = userBO.getListAdmin();
		request.setAttribute("listAdmin", listAdmin);

//		request.setAttribute("checkPassword",
//				(String) request.getAttribute("checkPassword"));

		// Truyen du lieu qua manageAdmin.jsp
		RequestDispatcher rd = request.getRequestDispatcher("manageAdmin.jsp");
		rd.forward(request, response);
	}

}
