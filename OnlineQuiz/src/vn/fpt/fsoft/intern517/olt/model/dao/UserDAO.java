package vn.fpt.fsoft.intern517.olt.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.User;

public class UserDAO extends BaseDAO {
	/*
	 * Ham kiem tra ten dang nhap va mat khau
	 */
	public boolean checkLogin(String userName, String password) {
		String sqlCheckLogin = "SELECT userName FROM [USER] WHERE userName = ? AND password = ?";
		ResultSet rs = null;
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlCheckLogin);
			restmt.setString(1, userName);
			restmt.setString(2, password);
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
	 * Ham tra ve quyen dang nhap: admin=true student=false
	 */
	public String checkAuthorization(String userName) {
		String sql = "SELECT type FROM [USER] WHERE userName = ?";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sql);
			restmt.setString(1, userName);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String result = null;

		try {
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * Ham tra ve danh sach cac admin
	 */
	public ArrayList<User> getListAdmin() {
		String sqlListAdmin = "SELECT userName " + " FROM [USER] "
				+ " WHERE type = '1' or type = '2' "
				+ " ORDER BY type, userName";
		ResultSet rs = null;

		// Ket noi va truy van database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlListAdmin);
			rs = restmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lay ket qua truy van
		ArrayList<User> listAdmin = new ArrayList<User>();
		User userAdmin;
		try {
			while (rs.next()) {
				userAdmin = new User();
				userAdmin.setUserName(rs.getString(1));
				listAdmin.add(userAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listAdmin;
	}

	/*
	 * Ham xoa admin
	 */
	public void deleteAdmin(String userName) {
		String sqlDeleteAdmin = "DELETE FROM [USER] WHERE userName = ?";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlDeleteAdmin);
			restmt.setString(1, userName);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham them user admin
	 */
	public void addAdmin(String userName, String password) {
		String sqlAddAdmin = "INSERT INTO [USER](userName, password, type) VALUES (?,?,'2')";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection.prepareStatement(sqlAddAdmin);
			restmt.setString(1, userName);
			restmt.setString(2, password);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham thay thoi password
	 */
	public void editPassword(String userName, String password) {
		String sqlEditPassword = "UPDATE [USER] SET password = ? WHERE userName = ?";

		// Ket noi va cap nhat database
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlEditPassword);
			restmt.setString(1, password);
			restmt.setString(2, userName);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham xoa tat ca user theo phan quyen
	 */
	public void deleteUser(String type) {
		String sqlDeleteUser = "DELETE FROM [USER] WHERE type = ?";

		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlDeleteUser);
			restmt.setString(1, type);
			restmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ham kiem tra ten username trung
	 */
	public boolean checkUserName(String userName) {
		String sqlCheckUserName = "SELECT userName FROM [USER] WHERE userName = ?";
		ResultSet rs = null;
		try {
			Connection connection = getMyConnection();
			PreparedStatement restmt = connection
					.prepareStatement(sqlCheckUserName);
			restmt.setString(1, userName);
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
