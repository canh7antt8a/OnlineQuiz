package vn.fpt.fsoft.intern517.olt.controller;

/**
 * ManageTopicAdminServlet.java
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
import vn.fpt.fsoft.intern517.olt.model.bean.Topic;
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;

public class ManageTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManageTopicServlet() {
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
		
		// Kiem tra quyen dang nhap
		if (Constants.STUDENT_RIGHTS.equals(session.getAttribute("type"))) {
			response.sendRedirect("WelcomeStudentServlet");
			return;
		}
		
		// Lay danh sach topic
		TopicBO topicBO = new TopicBO();
		ArrayList<Topic> listTopic = topicBO.getListTopic();
		request.setAttribute("listTopic", listTopic);

		// Truyen du lieu qua selectTopicStudent.jsp
		RequestDispatcher rd = request
				.getRequestDispatcher("manageTopic.jsp");
		rd.forward(request, response);
	}

}
