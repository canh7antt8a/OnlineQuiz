package vn.fpt.fsoft.intern517.olt.controller;

/**
 * AddAdminServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 16, 2017        Nguyen Cong Huong          	Create
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		// Them user admin
		if ("submit".equals(request.getParameter("submit"))) {
			String userName = request.getParameter("userName").trim();
			if (userBO.checkUserName(userName.trim())) {
				request.setAttribute("notification", Constants.MSG_DUPLICATE_USER);
				RequestDispatcher rd = request.getRequestDispatcher("addAdmin.jsp");
				rd.forward(request, response);
			} else {
				String password = request.getParameter("password");
				userBO.addAdmin(userName, password);
				response.sendRedirect("ManageAdminServlet");
			}

		}else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageAdminServlet");
		} else {
			response.sendRedirect("addAdmin.jsp");
		}

	}

}
