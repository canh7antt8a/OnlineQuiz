package vn.fpt.fsoft.intern517.olt.controller;

/**
 * LoginServlet.java
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
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		UserBO userBO = new UserBO();
		if ("submit".equals(request.getParameter("submit"))) {
			if (userBO.checkLogin(userName, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", userName);
				session.setAttribute("type",
						userBO.checkAuthorization(userName));
				if (Constants.STUDENT_RIGHTS.equals(userBO
						.checkAuthorization(userName))) {
					response.sendRedirect("WelcomeStudentServlet");
				} else
					response.sendRedirect("WelcomeAdminServlet");
			} else {
				request.setAttribute("notification", Constants.MSG_LOGIN_FAILED);
				RequestDispatcher rd = request
						.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
