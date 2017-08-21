package vn.fpt.fsoft.intern517.olt.controller;

/**
 * ManageClassAdminServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 20, 2017        Nguyen Cong Huong          	Create
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

public class ManageClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManageClassServlet() {
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

		// Lay user name cua admin
		request.setAttribute("adminName", session.getAttribute("userName"));

		// Lay danh sach lop
		ClassBO classBO = new ClassBO();
		ArrayList<Class> listClass = classBO.getListClass();
		request.setAttribute("listClass", listClass);

		// Truyen du lieu qua manageAdmin.jsp
		RequestDispatcher rd = request
				.getRequestDispatcher("manageClass.jsp");
		rd.forward(request, response);
	}

}
