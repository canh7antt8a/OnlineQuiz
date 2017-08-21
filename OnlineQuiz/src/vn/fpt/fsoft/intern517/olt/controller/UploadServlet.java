package vn.fpt.fsoft.intern517.olt.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import vn.fpt.fsoft.intern517.olt.common.Constants;
import vn.fpt.fsoft.intern517.olt.common.Utils;
import vn.fpt.fsoft.intern517.olt.model.bo.ClassBO;
import vn.fpt.fsoft.intern517.olt.model.bo.StudentBO;
import vn.fpt.fsoft.intern517.olt.model.bo.UserBO;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
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
		UserBO userBO = new UserBO();
		StudentBO studentBO = new StudentBO();
		ClassBO classBO = new ClassBO();

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

		if ("submit".equals(request.getParameter("submit"))) {
			Part filePart = request.getPart("file");
			InputStream fileContent = filePart.getInputStream();
			// ... (do your job here)
			System.out.println("Upload successful");
			try {
				POIFSFileSystem fs = new POIFSFileSystem(fileContent);
				HSSFWorkbook wb = new HSSFWorkbook(fs);
				HSSFSheet sheet = wb.getSheetAt(0);
				HSSFRow row;

				int rows; // No of rows
				rows = sheet.getPhysicalNumberOfRows();

				int cols = 0; // No of columns
				int tmp = 0;
				System.out.println("Row dau: " + rows);
				for (int i = 0; i < 10 || i < rows; i++) {
					row = sheet.getRow(i);
					if (row != null) {
						tmp = sheet.getRow(i).getPhysicalNumberOfCells();
						if (tmp > cols)
							cols = tmp;
					}
				}
				System.out.println("Row sau: " + rows);

				for (int r = 6; r < rows; r++) {
					row = sheet.getRow(r);
					if (row != null) {
						if (Utils.checkString(row.getCell(0)
								.getStringCellValue(), row.getCell(1)
								.getStringCellValue(), row.getCell(2)
								.getStringCellValue())) {
							if (userBO.checkUserName(row.getCell(0)
									.getStringCellValue())) {
								String notification = "Tên đăng nhập "
										+ row.getCell(0).getStringCellValue()
										+ " đã tồn tại!";
								request.setAttribute("notification",
										notification);
								RequestDispatcher rd = request
										.getRequestDispatcher("importExcel.jsp");
								rd.forward(request, response);
								return;
							} else if (!Utils.checkGender(row.getCell(2)
									.getStringCellValue())) {
								continue;
							} else {
								if (!classBO.checkClassName(row.getCell(3)
										.getStringCellValue())) {
									classBO.addClass(row.getCell(3)
											.getStringCellValue());
								}
								studentBO.addStudent(row.getCell(0)
										.getStringCellValue(), row.getCell(0)
										.getStringCellValue(), row.getCell(1)
										.getStringCellValue(), row.getCell(2)
										.getStringCellValue(), "", "", classBO
										.getClassID(row.getCell(3)
												.getStringCellValue()));
							}
						} else
							continue;
					}
				}
				response.sendRedirect("ManageClassServlet");
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		} else if ("cancel".equals(request.getParameter("cancel"))) {
			response.sendRedirect("ManageClassServlet");
		} else
			response.sendRedirect("importExcel.jsp");
	}
}
