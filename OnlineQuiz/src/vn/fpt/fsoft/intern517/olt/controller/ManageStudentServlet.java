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
import vn.fpt.fsoft.intern517.olt.model.bean.Student;
import vn.fpt.fsoft.intern517.olt.model.bo.ClassBO;
import vn.fpt.fsoft.intern517.olt.model.bo.StudentBO;

public class ManageStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManageStudentServlet() {
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
		
		// Kiem quyen dang nhap
		if (Constants.STUDENT_RIGHTS.equals(session.getAttribute("type"))) {
			response.sendRedirect("WelcomeStudentServlet");
			return;
		}

		String classID = request.getParameter("classID");
		ClassBO classBO = new ClassBO();
		request.setAttribute("className", classBO.getClassNameManage(classID));
		request.setAttribute("classID", classID);

		// Lay danh hoc vien
		StudentBO studentBO = new StudentBO();
		ArrayList<Student> listStudent = studentBO.getListStudent(classID);

		// dua ds sv vao request
		request.setAttribute("listStudent", listStudent);

		// goi jsp
		RequestDispatcher rd = request
				.getRequestDispatcher("manageStudent.jsp");
		rd.forward(request, response);

	}
}
