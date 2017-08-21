package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;

public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateQuizServlet() {
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

		if ("submit".equals(request.getParameter("submitQuiz"))) {
			request.setAttribute("topicID", request.getParameter("topicID"));
			request.setAttribute("numberOfQuiz",
					request.getParameter("numberOfQuiz"));
			request.setAttribute("numberOfAnswer",
					request.getParameter("numberOfAnswer"));
		} else {
			if(request.getAttribute("topicID") == null){
				request.setAttribute("topicID", request.getParameter("topicID"));
				request.setAttribute("numberOfQuiz", "0");
				request.setAttribute("numberOfAnswer", "0");
			}else{
				request.setAttribute("topicID", request.getAttribute("topicID"));
				request.setAttribute("numberOfQuiz", "0");
				request.setAttribute("numberOfAnswer", "0");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("createQuiz.jsp");
		rd.forward(request, response);

	}
}
