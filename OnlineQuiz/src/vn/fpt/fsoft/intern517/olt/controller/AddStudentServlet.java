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
import vn.fpt.fsoft.intern517.olt.model.bo.ClassBO;
import vn.fpt.fsoft.intern517.olt.model.bo.StudentBO;
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddStudentServlet() {
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
		UserBO userBO = new UserBO();

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

		request.setCharacterEncoding("UTF-8");
		StudentBO studentBO = new StudentBO();
		ClassBO classBO = new ClassBO();

		if ("submit".equals(request.getParameter("submit"))) {
			if (userBO.checkUserName(request.getParameter("userName"))) {
				request.setAttribute("notification",
						Constants.MSG_DUPLICATE_USER);
				request.setAttribute("classID", request.getParameter("classID"));
				request.setAttribute("className", classBO
						.getClassNameManage(request.getParameter("classID")));
				RequestDispatcher rdj = request
						.getRequestDispatcher("addStudent.jsp");
				rdj.forward(request, response);
			} else {
				String classID = request.getParameter("classID");
				String userName = request.getParameter("userName").trim();
				String password = request.getParameter("password");
				String fullName = request.getParameter("fullName").trim();
				String sex = request.getParameter("sex");
				String email = request.getParameter("email").trim();
				String phoneNumber = request.getParameter("phoneNumber").trim();

				studentBO.addStudent(userName, password, fullName, sex, email,
						phoneNumber, classID);
				response.sendRedirect("ManageStudentServlet?classID="
						+ classID);
			}
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageStudentServlet?classID="
					+ request.getParameter("classID"));
		} else {
			request.setAttribute("classID", request.getParameter("classID"));
			request.setAttribute("className",
					classBO.getClassNameManage(request.getParameter("classID")));
			RequestDispatcher rdj = request
					.getRequestDispatcher("addStudent.jsp");
			rdj.forward(request, response);
		}

	}
}
