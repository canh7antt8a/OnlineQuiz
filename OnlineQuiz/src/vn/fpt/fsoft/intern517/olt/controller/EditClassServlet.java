package vn.fpt.fsoft.intern517.olt.controller;

/**
 * EditClassAdminServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 20, 2017        Nguyen Cong Huong          	Create
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

public class EditClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditClassServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ClassBO classBO = new ClassBO();

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

		// Thay doi ten lop theo ID lop

		if ("submit".equals(request.getParameter("submit"))) {
			String classID = request.getParameter("classID");
			String editClassName = request.getParameter("editClassName").trim();
			if (classBO.checkClassName(editClassName)) {
				request.setAttribute("notification", Constants.MSG_DUPLICATE_CLASS);
				RequestDispatcher rd = request.getRequestDispatcher("editClass.jsp");
				rd.forward(request, response);
			} else {
				classBO.editClass(classID, editClassName);
				response.sendRedirect("ManageClassServlet");
			}
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageClassServlet");
		} else {
			request.setAttribute("classID", request.getParameter("classID"));
			RequestDispatcher rd = request.getRequestDispatcher("editClass.jsp");
			rd.forward(request, response);
		}
	}

}
