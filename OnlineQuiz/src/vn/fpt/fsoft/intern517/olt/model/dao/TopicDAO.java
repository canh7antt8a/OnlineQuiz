package vn.fpt.fsoft.intern517.olt.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.common.Utils;
import vn.fpt.fsoft.intern517.olt.model.bean.Topic;

public class TopicDAO extends BaseDAO {
	/*
	 * Tra ve mot danh sach: ma topic va ten topic va so lan clone
	 */
	public ArrayList<Topic> getListTopic() {
		String sqlListTopic = "SELECT * FROM [TOPIC] ORDER BY topicName";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListTopic);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Topic> listTopic = new ArrayList<Topic>();
		Topic topic;
		try {
			while (rs.next()) {
				topic = new Topic();
				topic.setTopicID(rs.getInt(1));
				topic.setTopicName(rs.getString(2));
				topic.setCloneDay(rs.getString(3));
				listTopic.add(topic);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTopic;
	}

	/*
	 * Tra ve mot danh sach: ma topic va ten topic va so lan clone
	 */
	public ArrayList<Topic> getListTopic2() {
		String sqlListTopic = "SELECT * FROM [TOPIC] ORDER BY topicName";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListTopic);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Topic> listTopic = new ArrayList<Topic>();
		Topic topic;
		try {
			while (rs.next()) {
				topic = new Topic();
				QuizDAO quizDAO = new QuizDAO();
				if (quizDAO.checkQuiz(rs.getString(1))) {
					topic.setTopicID(rs.getInt(1));
					topic.setTopicName(rs.getString(2));
					listTopic.add(topic);
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTopic;
	}

	/*
	 * Xoa mot topic theo topicID
	 */
	public void deleteTopic(String topicID) {
		String sqlListTopic = "DELETE FROM [TOPIC] WHERE topicID = ?";

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListTopic);
			restmt.setInt(1, Integer.parseInt(topicID));
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham lay so cloneNumber
	 */
	public String getCloneDay(String topicID) {
		String sqlCloneNumber = "SELECT cloneDay FROM [TOPIC] WHERE topicID = ?";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlCloneNumber);
			restmt.setInt(1, Integer.parseInt(topicID));
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String cloneDay = "";

		try {
			while (rs.next()) {
				cloneDay = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cloneDay;
	}

	/*
	 * Ham lay topicID theo topicName
	 */
	public int getTopicID(String topicName) {
		String sqlTopicID = "SELECT topicID FROM [TOPIC] WHERE topicName = ?";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlTopicID);
			restmt.setString(1, topicName);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int topicID = 0;

		try {
			while (rs.next()) {
				topicID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return topicID;
	}

	/*
	 * Ham them topic
	 */
	public void addTopic(String topicName, String cloneDay) {
		String sqlNewTopic = "INSERT INTO [TOPIC](topicName, cloneDay) VALUES (?,?)";

		// Ket noi va them du lieu trong database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlNewTopic);
			restmt.setString(1, topicName);
			restmt.setString(2, cloneDay);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham clone topic
	 */
	public void cloneTopic(String topicID, String topicName, String date) {
		String sqlCloneTopic = "INSERT INTO [QUIZ](quizName, topicID, answer, maxAnswer) "
				+ " SELECT q.quizName, t.topicID, q.answer, q.maxAnswer "
				+ " FROM [QUIZ] as q, [TOPIC] as t "
				+ " WHERE q.topicID = ? AND t.topicID = ? ";
		PreparedStatement restmt = null;

		String newTopicName;

		try {
			Connection connection = getMyConnection();
//			if ("0".equals(cloneNumber)) {
				newTopicName = Utils.newTopicName(topicName, getCloneDay(topicID), date);

				// Them topic moi
				addTopic(newTopicName, date);

				// Clone
				restmt = connection.prepareStatement(sqlCloneTopic);
				restmt.setString(1, topicID);
				restmt.setInt(2, getTopicID(newTopicName));
				restmt.executeUpdate();
//			} else {
//				newTopicName = Utils.newTopicName(getTopicName(cloneNumber),
//						date);
//				addTopic(Utils.newTopicName(getTopicName(cloneNumber), date),
//						date);
//
//				// Clone
//				restmt = connection.prepareStatement(sqlCloneTopic);
//				restmt.setString(1, cloneNumber);
//				restmt.setInt(2, getTopicID(newTopicName));
//				restmt.executeUpdate();
//			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Ham kiem tra ten topic bi trung: true = trung ten, false = khong trung
	 * ten
	 */
	public boolean checkTopicName(String topicName) {
		String sqlCheckTopicName = "SELECT topicName FROM [TOPIC] WHERE topicName = ?";
		ResultSet rs = null;

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlCheckTopicName);
			restmt.setString(1, topicName);
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
	 * Ham xoa toan bo topic
	 */
	public void deleteTopic() {
		String sqlDeleteTopic = "DELETE FROM [TOPIC] "
				+ " DBCC CHECKIDENT ([TOPIC], RESEED, 0) ";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlDeleteTopic);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham tra ve danh sach topic da lam trong bang exam
	 */
	public ArrayList<Topic> getCurrentTopic() {
		String sqlListTopic = "SELECT DISTINCT ex.topicID, tp.topicName"
				+ " FROM [EXAM] ex "
				+ " INNER JOIN [TOPIC] as tp on tp.topicID = ex.topicID ";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListTopic);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<Topic> listTopic = new ArrayList<Topic>();
		Topic topic;
		try {
			while (rs.next()) {
				topic = new Topic();
				topic.setTopicID(rs.getInt(1));
				topic.setTopicName(rs.getString(2));
				listTopic.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTopic;
	}

	/*
	 * Ham lay topic name theo topic ID
	 */
	public String getTopicName(String topicID) {
		String sqlTopicName = "SELECT topicName FROM [TOPIC] WHERE topicID = ?";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlTopicName);
			restmt.setString(1, topicID);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String topicName = "";

		try {
			while (rs.next()) {
				topicName = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return topicName;
	}

	/*
	 * Ham sua ten topic
	 */
	public void editTopicName(String topicID, String topicName) {
		String sqlEditTopic = "UPDATE [TOPIC] SET topicName = ? WHERE topicID = ?";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlEditTopic);
			restmt.setString(1, topicName);
			restmt.setString(2, topicID);
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
	public boolean checkIP(String studentID, String topicID) {
		String checkIP = "SELECT addressIP FROM [EXAM] WHERE studentID = ? AND topicID = ?";
		ResultSet rs = null;
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(checkIP);
			restmt.setString(1, studentID);
			restmt.setString(2, topicID);

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
}
