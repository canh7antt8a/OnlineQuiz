package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bean.Quiz;
import vn.fpt.fsoft.intern517.olt.model.bo.QuizBO;
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;

public class EditQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditQuizServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		QuizBO quizBO = new QuizBO();
		TopicBO topicBO = new TopicBO();

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
				quizBO.editAnswer(i, topicID, answer);
				answer = "";
			}
			response.sendRedirect("ManageTopicServlet");
		} else {
			String topicID = (String) request.getParameter("topicID");
			if (quizBO.checkQuiz(topicID)) {
				request.setAttribute("topicID", topicID);
				request.setAttribute("topicName", topicBO.getTopicName(topicID));

				ArrayList<Quiz> listAnswer = quizBO.getListAnswer(topicID);
				request.setAttribute("numberOfQuiz", "" + listAnswer.size());
				int numberOfAnswer = 0;
				for (Quiz quiz : listAnswer) {
					numberOfAnswer = quiz.getMaxAnswer();
					for (int i = 0; i < 26; i++) {
						if ("".equals(quiz.getAnswer())) {
							continue;
						} else {
							for (int j = 0; j < quiz.getAnswer().length(); j++) {
								String temp = "" + quiz.getAnswer().charAt(j);
								if (temp.equals(Constants.ALPHABET[i])) {
									request.setAttribute("" + quiz.getQuizName() + i, temp);
								} else
									continue;
							}
						}
					}
				}
				request.setAttribute("numberOfAnswer", "" + numberOfAnswer);
				RequestDispatcher rd = request.getRequestDispatcher("editQuiz.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("CreateQuizServlet?topicID=" + topicID);
				rd.forward(request, response);
			}
		}
	}

}
