package com.bmpl.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bmpl.chatapp.dto.UserDTO;

public class UserDAO {
	public int register(UserDTO dto) throws ClassNotFoundException, SQLException {
		Connection conn =  null;
		Statement stmt = null;
		
		try {
			conn = CommonDAO.createConnection();
			stmt = conn.createStatement();
			String user_id = dto.getUserId();
			String pwd = new String(dto.getPassword());
			String query = "insert into users (email_id, password) values('"+user_id+"','"+pwd+"')";
			int record = stmt.executeUpdate(query);
			return record;
		}
		finally {
//			Resource cleaning
//			Finally will always execute
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public boolean login(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select email_id from users where email_id=? and password=?";
		try {
			String userId = userDTO.getUserId();
			String userPwd = new String(userDTO.getPassword());
			
			conn = CommonDAO.createConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			return rs.next();
		}
		finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
	}
}
