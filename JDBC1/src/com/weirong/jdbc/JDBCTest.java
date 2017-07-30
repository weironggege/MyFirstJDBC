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
	 * ʹ��statement�������
	 */
	@Test
	public void testStatement() throws ClassNotFoundException, SQLException {
		//1. ��ȡ���ݿ�����
		Connection conn = testDriverManager();
		
		//2. ׼��sql���
		//String sql = "insert into customer values (1, 'weirong', 'com@qq.com', '1993-03-14')";
		String sql = "delete from customer where ID=1";
		
		
		//3. ִ�в���
		Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
		
		//4. �ر�statement����
		statement.close();
		
		//5. �ر�����
		conn.close();
		
	}
	/*
	 * ͨ�õĸ��µķ���, ������ӣ� �޸ĺ�ɾ��
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
