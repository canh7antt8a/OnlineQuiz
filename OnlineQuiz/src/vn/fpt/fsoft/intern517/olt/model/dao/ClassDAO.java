package vn.fpt.fsoft.intern517.olt.model.dao;

/**
 * ClassDAO.java
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

import vn.fpt.fsoft.intern517.olt.model.bean.Class;

public class ClassDAO extends BaseDAO {
	/*
	 * Ham tra ve ten ten lop theo username
	 */
	public String getClassNameSelect(String userName) {
		String sqlClassName = "select cl.className from [STUDENT] st INNER JOIN [CLASS] as cl on cl.classID = st.classID where st.userName = ?";
		ResultSet rs = null;
		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlClassName);
			restmt.setString(1, userName);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		String className = null;
		try {
			while (rs.next()) {
				className = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return className;
	}

	public String getClassNameManage(String classID) {
		String sqlClassName = "SELECT className FROM [CLASS] WHERE classID = ?";
		ResultSet rs = null;
		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlClassName);
			restmt.setString(1, classID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		String className = null;
		try {
			while (rs.next()) {
				className = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return className;
	}

	/*
	 * Ham tra ve ten ten lop theo username
	 */
	public String getClassName(String userName) {
		String sqlClassName = "SELECT cl.className " +
				" FROM [STUDENT] st " +
				" INNER JOIN [CLASS] as cl on cl.classID = st.classID " +
				" WHERE st.studentID = ? ";
		ResultSet rs = null;
		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlClassName);
			restmt.setString(1, userName);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		String className = null;
		try {
			while (rs.next()) {
				className = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return className;
	}

	/*
	 * Ham tra ve danh sach lop
	 */
	public ArrayList<Class> getListClass() {

		String sqlClassName = "SELECT * FROM [CLASS] ORDER BY className ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlClassName);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Class> listClass = new ArrayList<Class>();
		Class classes;
		try {
			while (rs.next()) {
				classes = new Class();
				classes.setClassID(rs.getInt(1));
				classes.setClassName(rs.getString(2));
				listClass.add(classes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listClass;
	}

	/*
	 * Ham them mot lop cua admin
	 */
	public void addClass(String className) {
		String sqlAddClass = "INSERT INTO [CLASS](className) VALUES (?)";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlAddClass);
			restmt.setString(1, className);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham sua ten mot lop
	 */
	public void editClass(String classID, String className) {

		String sqlEditClass = "UPDATE [CLASS] SET className = ? WHERE classID = ?";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlEditClass);
			restmt.setString(1, className);
			restmt.setInt(2, Integer.parseInt(classID));
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham xoa mot class theo ID class
	 */
	public void deleteClass(String classID) {
		String sqlDeleteStudent = " DELETE FROM [USER] "
				+ " WHERE userName IN ( " + " SELECT st.studentID "
				+ " FROM [STUDENT] st "
				+ " INNER JOIN [CLASS] as cl on cl.classID = st.classID "
				+ " WHERE cl.classID = ?) ";
		String sqlDeleteClass = " DELETE FROM [CLASS] WHERE classID = ? ";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = null;

			restmt = connection.prepareStatement(sqlDeleteStudent);
			restmt.setString(1, classID);
			restmt.executeUpdate();

			restmt = connection.prepareStatement(sqlDeleteClass);
			restmt.setString(1, classID);
			restmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham xoa toan bo class va dat lai gia tri classID
	 */
	public void deleteClass() {
		String sqlDeleteClass = "DELETE FROM [CLASS] "
				+ " DBCC CHECKIDENT ([CLASS], RESEED, 0) ";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlDeleteClass);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham lay danh sach lop da thi
	 */
	public ArrayList<Class> getCurrentClass() {
		String sqlListClass = "SELECT DISTINCT cl.classID, cl.className "
				+ " FROM [EXAM] ex "
				+ " INNER JOIN [STUDENT] as st on ex.studentID = st.studentID "
				+ " INNER JOIN [CLASS] as cl on cl.classID = st.classID "
				+ " WHERE ex.status = 1 ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListClass);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Class> listClass = new ArrayList<Class>();
		Class classes;
		try {
			while (rs.next()) {
				classes = new Class();
				classes.setClassID(rs.getInt(1));
				classes.setClassName(rs.getString(2));
				listClass.add(classes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listClass;
	}

	/*
	 * Ham kiem tra ten class bi trung: true = trung ten, false = khong trung
	 * ten
	 */
	public boolean checkClassName(String className) {
		String sqlCheckClassName = "SELECT className FROM [CLASS] WHERE className = ?";
		ResultSet rs = null;

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlCheckClassName);
			restmt.setString(1, className);
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

	/*
	 * Ham lay classID theo className
	 */
	public String getClassID(String className) {
		String sqlClassID = "SELECT classID " +
				" FROM [CLASS] " +
				" WHERE className = ? ";
		ResultSet rs = null;
		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlClassID);
			restmt.setString(1, className);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
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
}
