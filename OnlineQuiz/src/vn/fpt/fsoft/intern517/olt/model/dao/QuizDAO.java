package vn.fpt.fsoft.intern517.olt.model.dao;

/**
 * QuizDAO.java
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

import vn.fpt.fsoft.intern517.olt.model.bean.Quiz;

public class QuizDAO extends BaseDAO {

	/*
	 * Ham kiem tra quiz cua topic co ton tai: true=da ton tai, false=chua ton
	 * tai
	 */
	public boolean checkQuiz(String topicID) {
		String sqlCheckQuiz = "SELECT * FROM [QUIZ] WHERE topicID = ?";
		ResultSet rs = null;
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlCheckQuiz);
			restmt.setString(1, topicID);
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
	 * Ham tra ve dap an theo topic
	 */
	public ArrayList<Quiz> getListAnswer(String topicID) {
		String sqlListAnswer = "SELECT answer, maxAnswer, quizName FROM [QUIZ] WHERE topicID = ?";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListAnswer);
			restmt.setString(1, topicID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
		Quiz quiz;
		try {
			while (rs.next()) {
				quiz = new Quiz();
				quiz.setAnswer(rs.getString(1));
				quiz.setMaxAnswer(rs.getInt(2));
				quiz.setQuizName(rs.getInt(3));
				listQuiz.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listQuiz;
	}

	/*
	 * Ham them dap an
	 */
	public void addAnswer(int quizName, String topicID, String answer,
			int maxAnswer) {
		String sqlAddAnswer = "INSERT INTO [QUIZ](quizName, topicID, answer, maxAnswer) VALUES (?,?,?,?)";

		// Ket noi va them du lieu trong database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlAddAnswer);
			restmt.setInt(1, quizName);
			restmt.setString(2, topicID);
			restmt.setString(3, answer);
			restmt.setInt(4, maxAnswer);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham cap nhat dap an
	 */
	public void editAnswer(int quizName, String topicID, String answer) {
		String sqlEditAnswer = "UPDATE [QUIZ] SET answer = ? WHERE topicID = ? AND quizName = ?";

		// Ket noi va them du lieu trong database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlEditAnswer);
			restmt.setString(1, answer);
			restmt.setString(2, topicID);
			restmt.setInt(3, quizName);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Dat
	 */
	public ArrayList<Quiz> getListQuiz(String topicID) {
		String sqlgetListQuestion = "SELECT * FROM QUIZ Where topicID = "
				+ topicID;
		ResultSet rs = null;

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlgetListQuestion);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Quiz> list = new ArrayList<Quiz>();
		try {
			while (rs.next()) {
				Quiz question = new Quiz();
				question.setQuizName(rs.getInt("quizName"));
				question.setTopicID(rs.getString("topicID"));
				question.setAnswer(rs.getString("answer"));
				question.setMaxAnswer(rs.getInt("maxAnswer"));
				list.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public void doTest(String topicID, int quizName, String answerStudent,
			String studentID) {

		String sqldoTest = "INSERT INTO DO_TEST(topicID, quizName, answerStudent, studentID) VALUES (?,?,?,?)";
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqldoTest);
			restmt.setString(1, topicID);
			restmt.setInt(2, quizName);
			restmt.setString(3, answerStudent);
			restmt.setString(4, studentID);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
