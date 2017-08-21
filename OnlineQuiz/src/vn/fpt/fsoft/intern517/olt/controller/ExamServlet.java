package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;

public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExamServlet() {
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

		// Lay IP may tinh
		String addressIP = request.getRemoteAddr();

		// Lay thoi gian
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat(
				"yyyy-MM-dd kk:mm:ss");
		String startTime = timeFormat.format(today.getTime());
		System.out.println(startTime);

		String topicID = request.getParameter("topicID");
		QuizBO quizBO = new QuizBO();
		// Luu thoi gian va IP luc bat dau
		ExamBO examBO = new ExamBO();
		if ("submit".equals(request.getParameter("submit"))) {
			if (!examBO.checkIP((String) session.getAttribute("userName"),
					topicID, addressIP)) {
				examBO.addTimeIP((String) session.getAttribute("userName"),
						topicID, startTime, addressIP);
				ArrayList<Quiz> listQuiz;

				listQuiz = quizBO.getListQuiz(topicID);
				request.setAttribute("listQuiz", listQuiz);
				request.setAttribute("topicID", topicID);
				TopicBO topicBO = new TopicBO();
				request.setAttribute("topicName", topicBO.getTopicName(topicID));
				RequestDispatcher rd = request.getRequestDispatcher("exam.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request
						.getRequestDispatcher("WelcomeStudentServlet");
				rd.forward(request, response);
			}
		}
	}

}
