package vn.fpt.fsoft.intern517.olt.controller;

/**
 * CloneTopicServlet.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 23, 2017        Nguyen Cong Huong          	Create
 */

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;

public class CloneTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CloneTopicServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
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
		
		//Clone topic
		String topicID = request.getParameter("topicID");
		String topicName = request.getParameter("topicName");
		DateFormat fm =new SimpleDateFormat("HH:mm:ss a dd/MM/yyyy");
		String time = fm.format(new Date());
		if ("submit".equals(request.getParameter("submit"))) {
			topicBO.cloneTopic(topicID, topicName, time);
			response.sendRedirect("ManageTopicServlet");
		}else{
			response.sendRedirect("ManageTopicServlet");
		}
	}

}
