package vn.fpt.fsoft.intern517.olt.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import vn.fpt.fsoft.intern517.olt.common.Constants;

public class BaseDAO {

	public static Connection getMyConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName(Constants.LIBRARY_NAME);

		Connection conn = DriverManager.getConnection(Constants.URL_CONNECTION,
				Constants.USERNAME, Constants.PASSWORD);

		return conn;
	}

}
