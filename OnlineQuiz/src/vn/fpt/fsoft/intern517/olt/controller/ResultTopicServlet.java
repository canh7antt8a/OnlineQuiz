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
import vn.fpt.fsoft.intern517.olt.common.Utils;
import vn.fpt.fsoft.intern517.olt.model.bean.DoTest;
import vn.fpt.fsoft.intern517.olt.model.bean.Exam;
import vn.fpt.fsoft.intern517.olt.model.bo.DoTestBO;
import vn.fpt.fsoft.intern517.olt.model.bo.ExamBO;
import vn.fpt.fsoft.intern517.olt.model.bo.TopicBO;

public class ResultTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResultTopicServlet() {
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
		DoTestBO doTestBO = new DoTestBO();
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

		if ("submit".equals(request.getParameter("submitTopic"))) {
			String selectTopic = request.getParameter("selectTopic");
			if ("0".equals(selectTopic)) {
				request.setAttribute("listTopic", topicBO.getListTopic());
				request.setAttribute("selectTopic", "0");
				RequestDispatcher rd = request
						.getRequestDispatcher("resultTopic.jsp");
				rd.forward(request, response);
			} else {
				ArrayList<DoTest> listStudent = doTestBO
						.getListStudentInTopic(selectTopic);
				ArrayList<DoTest> listAnswer;

				int result = 0;

				for (DoTest dt : listStudent) {
					listAnswer = doTestBO.getListAnswer(dt.getStudentID(), ""
							+ dt.getTopicID());
					for (DoTest dt2 : listAnswer) {
						if (Utils.checkString(dt2.getAnswerStudent(),
								dt2.getAnswer())) {
							result += 1;
						}
					}
					examBO.editResult(dt.getStudentID(), "" + dt.getTopicID(),
							"" + result);
					result = 0;
				}

				ArrayList<Exam> listTopicResult = examBO.getListResult(Integer
						.parseInt(selectTopic));
				request.setAttribute("listTopicResult", listTopicResult);
				request.setAttribute("listTopic", topicBO.getListTopic());
				request.setAttribute("selectTopic", selectTopic);
				RequestDispatcher rd = request
						.getRequestDispatcher("resultTopic.jsp");
				rd.forward(request, response);
			}
		} else if ("submit".equals(request.getParameter("submitExcel"))) {
			String selectTopic = request.getParameter("selectTopic");
			request.setAttribute("selectTopic", selectTopic);
			RequestDispatcher rd = request
					.getRequestDispatcher("ExportTopicToExcelServlet");
			rd.forward(request, response);
		} else {
			request.setAttribute("listTopic", topicBO.getListTopic());
			request.setAttribute("selectTopic", "0");
			RequestDispatcher rd = request
					.getRequestDispatcher("resultTopic.jsp");
			rd.forward(request, response);
		}
	}

}
