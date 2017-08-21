package vn.fpt.fsoft.intern517.olt.controller;

/**
 * WelcomeStudentServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 14, 2017        Nguyen Cong Huong          	Create
 */

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.ClassBO;
import vn.fpt.fsoft.intern517.olt.model.bo.StudentBO;

public class WelcomeStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WelcomeStudentServlet() {
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

		// Kiem tra quyen dang nhap
		if (Constants.ADMIN_RIGHTS.equals(session.getAttribute("type"))
				|| Constants.ADMIN_CHILD_RIGHTS.equals(session
						.getAttribute("type"))) {
			response.sendRedirect("WelcomeAdminServlet");
			return;
		}

		// hien thi ten va lop cua hoc vien
		StudentBO studentBO = new StudentBO();
		request.setAttribute("studentName", studentBO
				.getStudentName((String) session.getAttribute("userName")));

		ClassBO classBO = new ClassBO();
		request.setAttribute("className",
				classBO.getClassName((String) session.getAttribute("userName")));

		// Truyen du lieu qua welcomeStudent.jsp
		RequestDispatcher rd = request
				.getRequestDispatcher("welcomeStudent.jsp");
		rd.forward(request, response);
	}
}
