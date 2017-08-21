package vn.fpt.fsoft.intern517.olt.model.dao;

/**
 * ExamDAO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 27, 2017        Nguyen Cong Huong          	Create
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.Exam;

public class ExamDAO extends BaseDAO {

	/*
	 * Ham dat lai gia tri cua examID
	 */
	public void resetID() {
		String sqlResetID = "DBCC CHECKIDENT ([EXAM], RESEED, 0)";

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlResetID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham tra ve danh sach cac sinh vien da va dang lam bai
	 */
	public ArrayList<Exam> getListIP() {
		String sqlListIP = " SELECT ex.examID, st.fullName, ex.addressIP, ex.endTime, tp.topicName "
				+ " FROM [EXAM] ex "
				+ " INNER JOIN [STUDENT] as st on ex.studentID = st.studentID "
				+ " INNER JOIN [TOPIC] as tp on ex.topicID = tp.topicID "
				+ " WHERE ex.status = 1 ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlListIP);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Exam> listStudent = new ArrayList<Exam>();
		Exam exam;
		try {
			while (rs.next()) {
				exam = new Exam();
				exam.setExamID(rs.getInt(1));
				exam.setFullNameStudent(rs.getString(2));
				exam.setAddressIP(rs.getString(3));
				exam.setEndTime(rs.getString(4));
				exam.setTopicName(rs.getString(5));
				listStudent.add(exam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStudent;
	}

	/*
	 * Ham reset IP cho 1 hoc vien
	 */
	public void deleteIP(String examID) {
		String sqlDeleteIP = "DELETE FROM [EXAM] WHERE examID = ?";

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlDeleteIP);
			restmt.setString(1, examID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham cap nhat lai trang thai hoat dong cua mot dia chi ip
	 */
	public void changeAllIP(String classID) {
		String sqlchangeAllIP = " UPDATE [EXAM] SET status = 0 "
				+ " WHERE examID IN (SELECT ex.examID FROM [STUDENT] st "
				+ " INNER JOIN [CLASS] as cl on cl.classID = st.classID "
				+ " INNER JOIN [EXAM] as ex on ex.studentID = st.studentID "
				+ " WHERE cl.classID = ? AND ex.status = 1)";

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlchangeAllIP);
			restmt.setString(1, classID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham lay tat ca dia chi IP trung
	 */
	public ArrayList<Exam> getListIPDuplicate() {
		String sqlIPDuplicate = "SELECT t.fullName, t.className, t.topicName, t.addressIP "
				+ " FROM ( select st.fullName, cl.className, tp.topicName, ex.addressIP, COUNT(addressIP) "
				+ " over(Partition by addressIP) as cnt "
				+ " FROM [EXAM] ex "
				+ " INNER JOIN [STUDENT] as st on ex.studentID = st.studentID "
				+ " INNER JOIN [CLASS] as cl on cl.classID = st.classID "
				+ " INNER JOIN [TOPIC] as tp on tp.topicID = ex.topicID "
				+ " WHERE ex.status = 1) t "
				+ " WHERE t.cnt> 1 "
				+ " ORDER BY t.topicName, t.fullName ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlIPDuplicate);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Exam> listIPDuplicate = new ArrayList<Exam>();
		Exam exam;
		try {
			while (rs.next()) {
				exam = new Exam();
				exam.setFullNameStudent(rs.getString(1));
				exam.setClassName(rs.getString(2));
				exam.setTopicName(rs.getString(3));
				exam.setAddressIP(rs.getString(4));
				listIPDuplicate.add(exam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listIPDuplicate;
	}

	/*
	 * Ham cap nhat ket qua lam bai cua hoc vien
	 */
	public void editResult(String studentID, String topiID, String result) {
		String sqlResult = "UPDATE [EXAM] SET result = ? WHERE studentID = ? AND topicID = ? ";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlResult);
			restmt.setString(1, result);
			restmt.setString(2, studentID);
			restmt.setString(3, topiID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham tra ve danh sach diem theo lop
	 */
	public ArrayList<Exam> getListResult(String classID) {
		String sqlListResult = " SELECT st.studentID, st.fullName, cl.className, t.topicName, ex.startTime, ex.endTime, ex.addressIP, ex.result "
				+ " FROM [STUDENT] st "
				+ " INNER JOIN [CLASS] as cl on cl.classID = st.classID "
				+ " LEFT JOIN [EXAM] as ex on st.studentID = ex.studentID "
				+ " LEFT JOIN [TOPIC] as t on t.topicID = ex.topicID "
				+ " WHERE cl.classID = ? "
				+ " ORDER BY t.topicName, st.studentID ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListResult);
			restmt.setString(1, classID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Exam> listResult = new ArrayList<Exam>();
		Exam exam;
		try {
			while (rs.next()) {
				exam = new Exam();
				exam.setStudentID(rs.getString(1));
				exam.setFullNameStudent(rs.getString(2));
				exam.setClassName(rs.getString(3));

				if (rs.getString(4) == null) {
					exam.setTopicName("");
				} else
					exam.setTopicName(rs.getString(4));

				if (rs.getString(5) == null) {
					exam.setStartTime("");
				} else
					exam.setStartTime(rs.getString(5));

				if (rs.getString(6) == null) {
					exam.setEndTime("");
				} else
					exam.setEndTime(rs.getString(6));

				if (rs.getString(7) == null) {
					exam.setAddressIP("");
				} else
					exam.setAddressIP(rs.getString(7));

				if (rs.getString(8) == null) {
					exam.setResult("");
				} else
					exam.setResult(rs.getString(8));
				listResult.add(exam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listResult;
	}

	/*
	 * Ham tra ve danh sach diem theo topic
	 */
	public ArrayList<Exam> getListResult(int topicID) {
		String sqlListResult = " SELECT st.studentID, st.fullName, cl.className, t.topicName, ex.startTime, ex.endTime, ex.addressIP, ex.result "
				+ " FROM [STUDENT] st "
				+ " INNER JOIN [CLASS] as cl on cl.classID = st.classID "
				+ " LEFT JOIN [EXAM] as ex on st.studentID = ex.studentID "
				+ " LEFT JOIN [TOPIC] as t on t.topicID = ex.topicID "
				+ " WHERE t.topicID = ? "
				+ " ORDER BY t.topicName, st.studentID ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListResult);
			restmt.setString(1, "" + topicID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Exam> listResult = new ArrayList<Exam>();
		Exam exam;
		try {
			while (rs.next()) {
				exam = new Exam();
				exam.setStudentID(rs.getString(1));
				exam.setFullNameStudent(rs.getString(2));
				exam.setClassName(rs.getString(3));

				if (rs.getString(4) == null) {
					exam.setTopicName("");
				} else
					exam.setTopicName(rs.getString(4));

				if (rs.getString(5) == null) {
					exam.setStartTime("");
				} else
					exam.setStartTime(rs.getString(5));

				if (rs.getString(6) == null) {
					exam.setEndTime("");
				} else
					exam.setEndTime(rs.getString(6));

				if (rs.getString(7) == null) {
					exam.setAddressIP("");
				} else
					exam.setAddressIP(rs.getString(7));

				if (rs.getString(8) == null) {
					exam.setResult("");
				} else
					exam.setResult(rs.getString(8));
				listResult.add(exam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listResult;
	}

	public String checkEndTime(String examID) {
		String checkEndTime = "SELECT endTime FROM [EXAM] WHERE examID = ? ";
		ResultSet rs = null;
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(checkEndTime);

			restmt.setString(1, examID);

			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String endTime1 = "";
		try {
			while (rs.next()) {
				endTime1 = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endTime1;
	}

	/*
	 * Cua dat
	 */
	public void addTimeIP(String studentID, String topicID, String startTime,
			String addressIP) {

		String addTimeIP = " INSERT INTO EXAM(studentID, topicID, startTime, addressIP, result, status) " +
				" values (?,?,?,?,0,'1') ";

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(addTimeIP);
			restmt.setString(1, studentID);
			restmt.setString(2, topicID);
			restmt.setString(3, startTime);
			restmt.setString(4, addressIP);

			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkIP(String studentID, String topicID, String addressIP) {
		String checkIP = "SELECT addressIP FROM [EXAM] WHERE studentID = ? AND topicID = ? AND addressIP=? ";
		ResultSet rs = null;
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(checkIP);
			restmt.setString(1, studentID);
			restmt.setString(2, topicID);
			restmt.setString(3, addressIP);

			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateTime(String studentID, String topicID, String endTime) {
		String updateTime = "UPDATE EXAM SET endTime = ? WHERE studentID = ? AND topicID = ?";
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(updateTime);
			restmt.setString(1, endTime);
			restmt.setString(2, studentID);
			restmt.setString(3, topicID);

			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String checkEndTime(String endTime, String studentID, String topicID) {
		String checkEndTime = "SELECT endTime FROM [EXAM] WHERE studentID = ? AND topicID = ?";
		ResultSet rs = null;
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(checkEndTime);

			restmt.setString(1, studentID);
			restmt.setString(2, topicID);

			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String endTime1 = "";
		try {
			while (rs.next()) {
				endTime1 = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endTime1;
	}

	public String getAddressIP(String studentID) {
		String getAddressIP = "SELECT addressIP FROM EXAM WHERE studentID = ? ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(getAddressIP);
			restmt.setString(1, studentID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String addressIP = "";

		try {
			while (rs.next()) {
				addressIP = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return addressIP;
	}
}
