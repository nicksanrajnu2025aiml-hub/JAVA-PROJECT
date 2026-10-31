package com.shashi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.shashi.beans.TrainException;
import com.shashi.constant.ResponseCode;

// Database connectivity class (MySQL via JDBC).
// Reads settings from src/application.properties
public class DBUtil {
	private static Connection con;
	private static String url;
	private static String user;
	private static String pass;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("application");
		url = rb.getString("connectionString");
		user = rb.getString("username");
		pass = rb.getString("password");

		try {
			Class.forName(rb.getString("driverName"));
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found. Add mysql-connector-j jar to WEB-INF/lib");
			e.printStackTrace();
		}
		connect();
	}

	private static void connect() {
		try {
			System.out.println("Connecting to: " + url + " as " + user);
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Database Connection Success!!");
		} catch (SQLException e) {
			con = null;
			System.out.println("Database Connection FAILED: " + e.getMessage());
		}
	}

	public static synchronized Connection getConnection() throws TrainException {
		try {
			// reconnect if the connection was dropped or timed out
			if (con == null || con.isClosed() || !con.isValid(2)) {
				connect();
			}
		} catch (SQLException e) {
			connect();
		}
		if (con == null)
			throw new TrainException(ResponseCode.DATABASE_CONNECTION_FAILURE);
		return con;
	}
}
