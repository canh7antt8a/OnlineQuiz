package vn.fpt.fsoft.intern517.olt.controller;

/**
 * AddClassAdminServlet.java
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

public class AddClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddClassServlet() {
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

		// Them mot lop
		
		if ("submitClass".equals(request.getParameter("submitClass"))) {
			String addClassName = request.getParameter("addClassName").trim();
			if(classBO.checkClassName(addClassName)){
				request.setAttribute("notification", Constants.MSG_DUPLICATE_CLASS);
				RequestDispatcher rd = request
						.getRequestDispatcher("addClass.jsp");
				rd.forward(request, response);
			}else{
				classBO.addClass(addClassName.trim());
				response.sendRedirect("ManageClassServlet");
			}
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageClassServlet");
		} else {
			response.sendRedirect("addClass.jsp");
		}
	}

}
