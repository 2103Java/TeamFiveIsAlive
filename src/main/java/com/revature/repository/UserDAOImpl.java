package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.revature.models.User;
import com.revature.utility.ConnectionFactory;

public class UserDAOImpl implements UserDAO {
	
// SELECT
//	public User selectUserByEmail(String email) {
//		User toReturn = null;
//
//		try(Connection conn = ConnectionFactory.getConnection()) {
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = (?)");
//			ps.setString(1, email);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				int userID = rs.getInt("user_id");
//				String firstName = rs.getString("first_name");
//				String lastName = rs.getString("last_name");
//				String password = rs.getString("passw");
//				String type = rs.getString("user_type");
//
//				toReturn = new User(userID, firstName, lastName, password, type, email);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return toReturn;
//	}
	@Override
	public User selectUserByID(int userID) {
		User toReturn = null;

		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_id = (?)");
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("passw");
				String type = rs.getString("user_type");

				toReturn = new User(userID, firstName, lastName, password, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public HashSet<User> selectAllUsers() {
		HashSet<User> toReturn = new HashSet<>();
				
		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int userID = rs.getInt("user_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("passw");
				String type = rs.getString("user_type");

				toReturn.add(new User(userID, firstName, lastName, password, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;

	}
	
	
// INSERT
	@Override
	public boolean insertUser(User u) {
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO users (user_id, first_name, last_name, passw, user_type) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getUserID());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getUserType());
			ps.execute();

			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
// UPDATE
	@Override
	public boolean updateUser(User u) {
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE users SET first_name = (?), last_name = (?), passw = (?), user_type = (?) WHERE user_id = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getUserType());
			ps.setInt(5, u.getUserID());

			ps.execute();

			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}

// DELETE
	@Override
	public boolean deleteUser(int userID) {
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE user_id = (?)");
			ps.setInt(1, userID);
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
		
		
}
