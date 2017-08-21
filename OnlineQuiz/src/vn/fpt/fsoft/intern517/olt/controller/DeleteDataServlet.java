package vn.fpt.fsoft.intern517.olt.controller;

/**
 * DeleteDataServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 29, 2017        Nguyen Cong Huong          	Create
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
import vn.fpt.fsoft.intern517.olt.model.bo.ExamBO;
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

public class DeleteDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDataServlet() {
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
		ClassBO classBO = new ClassBO();
		TopicBO topicBO = new TopicBO();
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
		if (Constants.ADMIN_CHILD_RIGHTS.equals(session.getAttribute("type"))) {
			response.sendRedirect("WelcomeAdminServlet");
			return;
		}

		if ("submit".equals(request.getParameter("submit"))) {
			if (userBO.checkLogin((String) session.getAttribute("userName"),
					request.getParameter("password"))) {
				userBO.deleteUser("0");
				userBO.deleteUser("2");
				classBO.deleteClass();
				topicBO.deleteTopic();
				examBO.resetID();

				request.setAttribute("notification", Constants.MSG_DELETE_DATA);
				RequestDispatcher rd = request
						.getRequestDispatcher("deleteData.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("notification",
						Constants.MSG_WRONG_PASSWORD);
				RequestDispatcher rd = request
						.getRequestDispatcher("deleteData.jsp");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect("deleteData.jsp");
		}

	}
}
