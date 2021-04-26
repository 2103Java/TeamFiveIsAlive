package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	/*
	 * 
	 * 
	 * 
	 * 
	 */
	
	
// Variables
//	private static final String URL = System.getenv("DATABASE_ENDPOINT_URL");
//	private static final String USERNAME = System.getenv("DATABASE_USERNAME");
//	private static final String PASSWORD = System.getenv("DATABASE_PASSWORD");
	private static final String URL = "jdbc:postgresql://anceldatabase-1.c8st6wuntnfn.us-east-2.rds.amazonaws.com:5432/postgres";
	private static final String USERNAME = "DATABASE_USER";
	private static final String PASSWORD = "DATABASE_PASSWORD_ANCEL_NABHOLZ";
	
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
