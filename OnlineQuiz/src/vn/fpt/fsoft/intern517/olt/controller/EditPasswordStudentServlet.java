package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

public class EditPasswordStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditPasswordStudentServlet() {
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
		UserBO userBO = new UserBO();

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

		// Thay doi password admin theo user name
		if ("submit".equals(request.getParameter("submit"))) {
			System.out.println(
					"userName: " + session.getAttribute("userName") + "pass cu: " + request.getParameter("oldPass"));
			if (userBO.checkLogin((String) session.getAttribute("userName"), request.getParameter("oldPass"))) {
				userBO.editPassword((String) session.getAttribute("userName"),
						(String) request.getParameter("newPass"));
				response.sendRedirect("WelcomeStudentServlet");
			} else {
				request.setAttribute("notification", Constants.MSG_WRONG_PASSWORD);
				RequestDispatcher rd = request.getRequestDispatcher("editPassStudent.jsp");
				rd.forward(request, response);
			}
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("WelcomeStudentServlet");
		} else {
			response.sendRedirect("editPassStudent.jsp");
		}
	}

}
