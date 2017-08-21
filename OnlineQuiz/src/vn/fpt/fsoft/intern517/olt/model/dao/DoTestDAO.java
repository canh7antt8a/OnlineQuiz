package vn.fpt.fsoft.intern517.olt.model.dao;

/**
 * DoTestDAO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 29, 2017        Nguyen Cong Huong          	Create
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.DoTest;

public class DoTestDAO extends BaseDAO {
	/*
	 * Ham tra ve danh sach sinh vien, va topic da duoc lam
	 */
	public ArrayList<DoTest> getListStudent(String classID) {
		String sqlListStudent = "SELECT DISTINCT dt.studentID, dt.topicID "
				+ " FROM [DO_TEST] dt "
				+ " INNER JOIN [STUDENT] as st on st.studentID = dt.studentID "
				+ " INNER JOIN [CLASS] as cl on st.classID = cl.classID "
				+ " WHERE cl.classID = ? ";
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
		ArrayList<DoTest> listStudent = new ArrayList<DoTest>();
		DoTest dt;
		try {
			while (rs.next()) {
				dt = new DoTest();
				dt.setStudentID(rs.getString(1));
				dt.setTopicID(rs.getInt(2));
				listStudent.add(dt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStudent;
	}

	/*
	 * Ham tra ve danh sach dap an cua hoc vien va ap an dung
	 */
	public ArrayList<DoTest> getListAnswer(String studentID, String topicID) {
		String sqlListStudent = "SELECT dt.quizName, dt.answerStudent, q.answer "
				+ " FROM [DO_TEST] dt "
				+ " INNER JOIN [QUIZ] as q on dt.topicID = q.topicID AND dt.quizName=q.quizName "
				+ " WHERE dt.studentID = ? AND dt.topicID = ?  ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListStudent);
			restmt.setString(1, studentID);
			restmt.setString(2, topicID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<DoTest> listAnswer = new ArrayList<DoTest>();
		DoTest dt;
		try {
			while (rs.next()) {
				dt = new DoTest();
				dt.setAnswerStudent(rs.getString(2));
				dt.setAnswer(rs.getString(3));
				listAnswer.add(dt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listAnswer;
	}
	
	/*
	 * Ham tra ve danh sach sinh vien, va topic da duoc lam
	 */
	public ArrayList<DoTest> getListStudentInTopic(String topicID) {
		String sqlListStudent = "SELECT DISTINCT dt.studentID, dt.topicID "
				+ " FROM [DO_TEST] dt "
				+ " INNER JOIN [TOPIC] as t on t.topicID = dt.topicID "
				+ " WHERE t.topicID = ?";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListStudent);
			restmt.setString(1, topicID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<DoTest> listStudent = new ArrayList<DoTest>();
		DoTest dt;
		try {
			while (rs.next()) {
				dt = new DoTest();
				dt.setStudentID(rs.getString(1));
				dt.setTopicID(rs.getInt(2));
				listStudent.add(dt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStudent;
	}

}
