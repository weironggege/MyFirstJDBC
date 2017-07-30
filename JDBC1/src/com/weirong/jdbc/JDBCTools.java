package com.weirong.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class JDBCTools {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String password = "bottle19920314";
		String driverClass = "com.mysql.jdbc.Driver";
		
		Class.forName(driverClass);
		
		return  (Connection) DriverManager.getConnection(url, user, password);
	}
	
	public static void release(Statement statement, Connection connection) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
