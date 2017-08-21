package vn.fpt.fsoft.intern517.olt.controller;

/**
 * ResetAllIPServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 28, 2017        Nguyen Cong Huong          	Create
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
import vn.fpt.fsoft.intern517.olt.model.bean.Class;
import vn.fpt.fsoft.intern517.olt.model.bo.ClassBO;
import vn.fpt.fsoft.intern517.olt.model.bo.ExamBO;

public class ResetAllIPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResetAllIPServlet() {
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
		ClassBO classBO = new ClassBO();
		ExamBO examBO = new ExamBO();

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

		ArrayList<Class> listClass = classBO.getCurrentClass();

		if ("submit".equals(request.getParameter("submit"))) {
			if ("0".equals(request.getParameter("classID"))) {
				request.setAttribute("notification",
						Constants.MSG_NOT_SELECT_CLASS);
			} else {
				examBO.changeAllIP(request.getParameter("classID"));
				request.setAttribute("notification", Constants.MSG_RESETIP);
				request.setAttribute("listClass", listClass);
			}
		} else {
			request.setAttribute("listClass", listClass);
		}
		request.setAttribute("listClass", listClass);
		RequestDispatcher rd = request.getRequestDispatcher("resetAllIP.jsp");
		rd.forward(request, response);

	}

}
