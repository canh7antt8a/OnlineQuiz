package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;

public class EditTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditTopicServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		String topicID = request.getParameter("topicID");
		// Kiem tra nut them da duoc an chua
		if ("submit".equals(request.getParameter("submit"))) {
			// Ten topic da ton tai
			if (topicBO.checkTopicName(request.getParameter("topicName"))) {
				request.setAttribute("topicName", topicBO.getTopicName(topicID));
				request.setAttribute("notification", Constants.MSG_DUPLICATE_TOPIC);
				request.setAttribute("topicID", topicID);
				RequestDispatcher rd = request
						.getRequestDispatcher("editTopic.jsp");
				rd.forward(request, response);
			} else {
				String topicName = request.getParameter("topicName").trim();
				topicBO.editTopicName(request.getParameter("topicID"),
						topicName);
				response.sendRedirect("ManageTopicServlet");
			}
			// Kiem tra nut huy da duoc an chua
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageTopicServlet");
		} else {
			request.setAttribute("topicID", topicID);
			request.setAttribute("topicName", topicBO.getTopicName(topicID));
			RequestDispatcher rd = request
					.getRequestDispatcher("editTopic.jsp");
			rd.forward(request, response);
		}

	}

}
