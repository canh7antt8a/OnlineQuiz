package vn.fpt.fsoft.intern517.olt.controller;

/**
 * ResetIPServlet.java
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
import vn.fpt.fsoft.intern517.olt.model.bean.Exam;
import vn.fpt.fsoft.intern517.olt.model.bo.ExamBO;

public class ResetIPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResetIPServlet() {
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

		if ("submit".equals(request.getParameter("submit"))) {
			String examID = request.getParameter("examIP");
			if (examBO.checkEndTime(examID) == null) {
				examBO.deleteIP(examID);
			}
			response.sendRedirect("ResetIPServlet");
		} else {
			ArrayList<Exam> listIP = examBO.getListIP();
			request.setAttribute("listIP", listIP);
			RequestDispatcher rd = request.getRequestDispatcher("resetIP.jsp");
			rd.forward(request, response);
		}
	}

}
