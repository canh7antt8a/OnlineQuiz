package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.QuizBO;

public class AddAnswerAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddAnswerAdminServlet() {
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
		QuizBO quizBO = new QuizBO();

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

		if ("submit".equals(request.getParameter("complete"))) {
			String topicID = request.getParameter("topicID");
			String numberOfQuiz = request.getParameter("numberOfQuiz");
			String numberOfAnswer = request.getParameter("numberOfAnswer");
			String answerID = null;
			String answer = "";
			for (int i = 1; i <= Integer.parseInt(numberOfQuiz); i++) {
				for (int j = 0; j < Integer.parseInt(numberOfAnswer); j++) {
					answerID = "" + i + j;
					if (request.getParameter("" + answerID) == null) {
						continue;
					} else {
						answer += request.getParameter("" + answerID);
					}
				}
				quizBO.addAnswer(i, topicID, answer,
						Integer.parseInt(numberOfAnswer));
				answer = "";
			}

			response.sendRedirect("ManageTopicServlet");
		}
	}
}
