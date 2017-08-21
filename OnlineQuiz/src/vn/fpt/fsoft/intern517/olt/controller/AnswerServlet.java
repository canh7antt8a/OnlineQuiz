package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bean.Quiz;
import vn.fpt.fsoft.intern517.olt.model.bo.ExamBO;
import vn.fpt.fsoft.intern517.olt.model.bo.QuizBO;
import vn.fpt.fsoft.intern517.olt.model.bo.StudentBO;

public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AnswerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// Kiem tra dang nhap
		HttpSession session = request.getSession();
		if (session.getAttribute("userName") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// Kiem tra quyen dang nhap
		if (Constants.ADMIN_RIGHTS.equals(session.getAttribute("type"))
				|| Constants.ADMIN_CHILD_RIGHTS.equals(session
						.getAttribute("type"))) {
			response.sendRedirect("WelcomeAdminServlet");
			return;
		}

		// hien thi ten va lop cua hoc vien
		StudentBO studentBO = new StudentBO();
		request.setAttribute("studentName", studentBO
				.getStudentName((String) session.getAttribute("userName")));

		// Lay tong so cau tra loi va so cau hoi
		QuizBO quizBO = new QuizBO();
		ExamBO examBO = new ExamBO();
		String topicID = request.getParameter("topicID");
		ArrayList<Quiz> listQuiz = quizBO.getListQuiz(topicID);
		String maxAnswer = "";
		String maxQuiz = "";
		for (Quiz quiz : listQuiz) {
			maxAnswer = "" + quiz.getMaxAnswer();
			maxQuiz = "" + quiz.getQuizName();
		}

		// Lay bai lam cua hoc vien luu vao co so du lieu
		if ("hoantat".equals(request.getParameter("action"))) {

			// Lay thoi gian ket thuc
			Date today = new Date(System.currentTimeMillis());
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd kk:mm:ss");
			String endTime = timeFormat.format(today.getTime());

			if (examBO.checkEndTime(endTime,
					(String) session.getAttribute("userName"), topicID) == null) {

				examBO.updateTime((String) session.getAttribute("userName"),
						topicID, endTime);

				String answer = "";
				String answerName = null;

				for (int i = 1; i <= Integer.parseInt(maxQuiz); i++) {
					for (int j = 0; j < Integer.parseInt(maxAnswer); j++) {
						answerName = "answer_" + i + "_" + j;

						if (request.getParameter("" + answerName) == null) {
							continue;
						} else {
							answer += request.getParameter("" + answerName);
						}

					}
					quizBO.doTest(topicID, i, answer,
							(String) session.getAttribute("userName"));
					answer = "";
				}
			}

			response.sendRedirect("WelcomeStudentServlet");
		}
	}
}
