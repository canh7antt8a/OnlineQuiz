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
import vn.fpt.fsoft.intern517.olt.model.bo.ClassBO;
import vn.fpt.fsoft.intern517.olt.model.bo.DoTestBO;
import vn.fpt.fsoft.intern517.olt.model.bo.ExamBO;

public class ExportClassToExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExportClassToExcelServlet() {
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
		ExamBO examBO = new ExamBO();
		ClassBO classBO = new ClassBO();
		DoTestBO doTestBO = new DoTestBO();

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

		String selectClass = (String) request.getAttribute("selectClass");

		ArrayList<DoTest> listStudent = doTestBO.getListStudent(selectClass);
		ArrayList<DoTest> listAnswer;

		int result = 0;

		for (DoTest dt : listStudent) {
			listAnswer = doTestBO.getListAnswer(dt.getStudentID(),
					"" + dt.getTopicID());
			for (DoTest dt2 : listAnswer) {
				if (Utils.checkString(dt2.getAnswerStudent(), dt2.getAnswer())) {
					result += 1;
				}
			}
			examBO.editResult(dt.getStudentID(), "" + dt.getTopicID(), ""
					+ result);
			result = 0;
		}

		ArrayList<Exam> listClassResult = examBO.getListResult(selectClass);
		request.setAttribute("listClassResult", listClassResult);
		request.setAttribute("className",
				classBO.getClassNameManage(selectClass));

		RequestDispatcher rd = request
				.getRequestDispatcher("exportClassToExcel.jsp");
		rd.forward(request, response);
	}

}
