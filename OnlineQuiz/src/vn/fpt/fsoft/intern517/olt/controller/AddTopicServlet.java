package vn.fpt.fsoft.intern517.olt.controller;

/**
 * CreateTopicAdminServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 22, 2017        Nguyen Cong Huong          	Create
 */

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;

public class AddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddTopicServlet() {
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
		TopicBO topicBO = new TopicBO();

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
		
		// Kiem tra nut them da duoc an chua
		if ("submit".equals(request.getParameter("submit"))) {
			String topicName = request.getParameter("topicName").trim();
			// Ten topic da ton tai
			if (topicBO.checkTopicName(topicName)) {
				request.setAttribute("notification", Constants.MSG_DUPLICATE_TOPIC);
				RequestDispatcher rd = request
						.getRequestDispatcher("addTopic.jsp");
				rd.forward(request, response);
			} else {
				DateFormat fm =new SimpleDateFormat("HH:mm:ss a dd/MM/yyyy");
				String time = fm.format(new Date());
				topicBO.addTopic(topicName, time);
				request.setAttribute("topicID", topicBO.getTopicID(topicName.trim()));
				RequestDispatcher rd = request
						.getRequestDispatcher("CreateQuizServlet");
				rd.forward(request, response);
			}
			// Kiem tra nut huy da duoc an chua
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageTopicServlet");
		} else
			response.sendRedirect("addTopic.jsp");

	}

}
