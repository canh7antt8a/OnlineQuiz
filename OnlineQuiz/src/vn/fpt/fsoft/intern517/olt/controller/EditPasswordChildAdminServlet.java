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

public class EditPasswordChildAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditPasswordChildAdminServlet() {
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
		if(Constants.ADMIN_RIGHTS.equals(session.getAttribute("type"))){
			response.sendRedirect("WelcomeAdminServlet");
			return;
		}
		
		UserBO userBO = new UserBO();
		if ("submit".equals(request.getParameter("submit"))) {
			if (userBO.checkLogin((String) session.getAttribute("userName"),
					request.getParameter("oldPass"))) {
				userBO.editPassword((String) session.getAttribute("userName"),
						(String) request.getParameter("newPass"));
				response.sendRedirect("ManageAdminServlet");
			} else {
				request.setAttribute("notification",
						Constants.MSG_WRONG_PASSWORD);
				RequestDispatcher rd = request
						.getRequestDispatcher("editPassChildAdmin.jsp");
				rd.forward(request, response);
			}
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageAdminServlet");
		} else {
			response.sendRedirect("editPassChildAdmin.jsp");
		}
		
	}

}
