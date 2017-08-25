package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost/library?useSSL=false";
	public static String password = "0131";
	public static String user = "root";
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url,user,password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}
}
