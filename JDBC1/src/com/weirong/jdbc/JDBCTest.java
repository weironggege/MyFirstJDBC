package com.weirong.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class JDBCTest {

	@Test
	public void test() throws SQLException {
		Driver driver = new Driver();
		
		String url = "jdbc:mysql://localhost:3306/mysql";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "bottle19920314");
		
		Connection connection = (Connection) driver.connect(url, info);
		System.out.println(connection);
		
	}
	
	public Connection testDriverManager() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String password = "bottle19920314";
		String driverClass = "com.mysql.jdbc.Driver";
		
		Class.forName(driverClass);
		
		return  (Connection) DriverManager.getConnection(url, user, password);
	}
	
	/*
	 * 使用statement插入语句
	 */
	@Test
	public void testStatement() throws ClassNotFoundException, SQLException {
		//1. 获取数据库连接
		Connection conn = testDriverManager();
		
		//2. 准备sql语句
		//String sql = "insert into customer values (1, 'weirong', 'com@qq.com', '1993-03-14')";
		String sql = "delete from customer where ID=1";
		
		
		//3. 执行插入
		Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
		
		//4. 关闭statement对象
		statement.close();
		
		//5. 关闭连接
		conn.close();
		
	}
	/*
	 * 通用的更新的方法, 包括添加， 修改和删除
	 * 
	 */
	public void update(String sql) {
		Connection conn = null;
		Statement statement = null;
		
		try {
			conn = testDriverManager();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCTools.release(statement, conn);
		}
	}
}
