package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bean.Student;
import vn.fpt.fsoft.intern517.olt.model.bo.StudentBO;

/**
 * Servlet implementation class EditStudentServlet
 */
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditStudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		// Kiem tra da dang nhap chua
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

		request.setCharacterEncoding("UTF-8");
		StudentBO studentBO = new StudentBO();

		String studentID = (String) session.getAttribute("userName");
		if ("submit".equals(request.getParameter("submit"))) {

			String fullName = request.getParameter("fullName").trim();
			String sex = request.getParameter("sex");
			String email = request.getParameter("email").trim();
			String phoneNumber = request.getParameter("phoneNumber").trim();

			studentBO.editStudent(studentID, fullName, sex, email, phoneNumber);
			RequestDispatcher rds = request
					.getRequestDispatcher("WelcomeStudentServlet");
			rds.forward(request, response);
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("WelcomeStudentServlet");
		} else {
			Student student = studentBO.getInformationStudent(studentID);
			request.setAttribute("student", student);
			RequestDispatcher rdj = request
					.getRequestDispatcher("editStudent.jsp");
			rdj.forward(request, response);
		}

	}

}
