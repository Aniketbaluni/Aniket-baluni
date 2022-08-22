package com.bmpl.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface CommonDAO {
	
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
//		Step - 1 : Load Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
//		Step - 2 : Create Connection
		String USER_ID = "root";
		String USER_PWD = "root";
//		3306 is default port of mysql
		String CONNECTION_STRING = "jdbc:mysql://localhost:3307/java_chat_db";
		Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_ID, USER_PWD);
		if(con != null) {
			System.out.println("Connection Created...");
//			con.close();
		}
		return con;
		
	}

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		CommonDAO obj = new CommonDAO();
//		obj.createConnection();
//	}

}
