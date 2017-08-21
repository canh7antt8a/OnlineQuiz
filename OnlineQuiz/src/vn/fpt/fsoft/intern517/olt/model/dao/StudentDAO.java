package vn.fpt.fsoft.intern517.olt.model.dao;

/**
 * StudentDAO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 14, 2017        Nguyen Cong Huong          	Create
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.Student;

public class StudentDAO extends BaseDAO {
	/*
	 * Ham tra ve ten hoc sinh theo username
	 */
	public String getStudentName(String userName) {
		String sqlStudentName = "SELECT fullName FROM [STUDENT] WHERE studentID = ?";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlStudentName);
			restmt.setString(1, userName);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Lay ket qua truy van
		String studentName = null;
		try {
			while (rs.next()) {
				studentName = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentName;
	}

	/*
	 * Ham lay danh sach sinh vien
	 */
	public ArrayList<Student> getListStudent(String classID) {
		String sqlListStudent = "SELECT st.studentID, st.fullName "
				+ " FROM [STUDENT] st "
				+ " INNER JOIN [CLASS] as cl on cl.classID=st.classID "
				+ " WHERE st.classID = ?" + " ORDER BY st.fullName ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListStudent);
			restmt.setString(1, classID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Student> listStudent = new ArrayList<Student>();
		Student st;
		try {
			while (rs.next()) {
				st = new Student();
				st.setStudentID(rs.getString(1));
				st.setFullName(rs.getString(2));
				listStudent.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStudent;
	}

	/*
	 * Ham xoa mot hoc vien
	 */
	public void deleteStudent(String studentID) {
		String sqlDeleteStudent = "DELETE FROM [USER] WHERE userName = ?";

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlDeleteStudent);
			restmt.setString(1, studentID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham lay danh sach tat ca sinh vien
	 */
	public ArrayList<Student> getListAllStudent() {
		String sqlListStudent = " SELECT st.studentID, st.fullName, cl.className "
				+ " FROM [STUDENT] st "
				+ " LEFT JOIN [CLASS] as cl on cl.classID=st.classID "
				+ " ORDER BY cl.className, st.fullName ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListStudent);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Student> listStudent = new ArrayList<Student>();
		Student st;
		try {
			while (rs.next()) {
				st = new Student();
				st.setStudentID(rs.getString(1));
				st.setFullName(rs.getString(2));
				st.setClassName(rs.getString(3));
				listStudent.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStudent;
	}

	/*
	 * Ham tra ve classID theo userName
	 */
	public String getClassID(String userName) {
		String sqlClassID = "SELECT cl.classID FROM [CLASS] cl "
				+ " INNER JOIN [STUDENT] as st on st.classID = cl.classID "
				+ " WHERE st.studentID = ? ";
		ResultSet rs = null;

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlClassID);
			restmt.setString(1, userName);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String classID = null;
		try {
			while (rs.next()) {
				classID = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return classID;
	}

	public Student getInformationStudent(String studentID) {

		String sqlEditStudent = "SELECT st.studentID, st.fullName, st.sex, st.email, st.phoneNumber, cl.classID, cl.className"
				+ " FROM [STUDENT] as st"
				+ " INNER JOIN [CLASS] as cl on cl.classID = st.classID"
				+ " WHERE studentID = ?";
		ResultSet rs = null;
		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlEditStudent);
			restmt.setString(1, studentID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Student student = new Student();
		try {
			while (rs.next()) {
				student.setStudentID(rs.getString(1));
				student.setFullName(rs.getString(2));
				student.setSex(rs.getString(3));
				student.setEmail(rs.getString(4));
				student.setPhoneNumber(rs.getString(5));
				student.setClassID(rs.getString(6));
				student.setClassName(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	public void addStudent(String userName, String password, String fullName,
			String sex, String phoneNumber, String email, String classID) {
		String sqlUser = "INSERT INTO [USER] (userName, password, type) VALUES (?, ?, ?)";
		String sqlStudent = "INSERT INTO [STUDENT] (studentID, fullName, sex, email, phoneNumber, classID) VALUES (?, ?, ?, ?, ?, ?)";
		userName = userName.trim();
		password = password.trim();
		fullName = fullName.trim();
		sex = sex.trim();
		phoneNumber = phoneNumber.trim();
		email = email.trim();
		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlUser);
			restmt.setString(1, userName);
			restmt.setString(2, password);
			restmt.setString(3, "0");
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlStudent);
			restmt.setString(1, userName);
			restmt.setString(2, fullName);
			restmt.setString(3, sex);
			restmt.setString(4, email);
			restmt.setString(5, phoneNumber);
			restmt.setString(6, classID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editStudent(String studentID, String fullName, String sex,
			String phoneNumber, String email) {

		String sqlEditStudent = "UPDATE [STUDENT] "
				+ " SET fullName = ?, sex = ?, email = ?, phoneNumber = ? "
				+ " WHERE studentID = ? ";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlEditStudent);
			restmt.setString(1, fullName);
			restmt.setString(2, sex);
			restmt.setString(3, email);
			restmt.setString(4, phoneNumber);
			restmt.setString(5, studentID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
