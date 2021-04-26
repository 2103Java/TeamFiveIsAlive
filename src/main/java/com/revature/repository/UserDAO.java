package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.revature.models.User;
import com.revature.utility.ConnectionFactory;

public class UserDAO {
	
	public boolean verifyUsername(String username) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT username FROM users WHERE username = (?)");
			ps.setString(1, username);
			ResultSet verResults = ps.executeQuery();
			if (verResults.isBeforeFirst())
				toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}

	public boolean verifyPassword(String username, String password) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT password FROM users WHERE username = (?)");
			ps.setString(1, username);
			ResultSet verResults = ps.executeQuery();
			if (verResults.next())
				if (verResults.getString(1).equals(password))
					toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
// SELECT
	public User selectUser(String userName) {
		PreparedStatement ps = null;
		User toReturn = null;

		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM users WHERE username = (?)");
			ps.setString(1, userName);
			ResultSet cusResults = ps.executeQuery();
			if (cusResults.next()) {
				String username = cusResults.getString(1);
				String password = cusResults.getString(2);
				String firstName = cusResults.getString(3);
				String lastName = cusResults.getString(4);
				String address = cusResults.getString(5);
				String phone = cusResults.getString(6);
				String email = cusResults.getString(7);
				String flag = cusResults.getString(8);
				char userType = cusResults.getString(9).charAt(0);
				toReturn = new User(username, password, firstName, lastName, address, phone, email, flag, userType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public HashSet<User> selectAllUsers() {
		PreparedStatement ps = null;
		HashSet<User> toReturn = new HashSet<User>();
				
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT username FROM users");
			ResultSet cusResults = ps.executeQuery();
			while (cusResults.next()) {
				toReturn.add(selectUser(cusResults.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public HashSet<User> selectUsersByAccount(String id) {
		PreparedStatement ps = null;
		HashSet<User> toReturn = new HashSet<User>();
		HashSet<String> Users = new HashSet<String>();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT username FROM accountOwners WHERE acc_id = (?)");
			ps.setString(1, id);
			ResultSet cusAccResults = ps.executeQuery();
			while (cusAccResults.next())
				Users.add(cusAccResults.getString(1));
			for (String s : Users)
				toReturn.add(selectUser(s));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	
// INSERT
	public boolean insertUser(User c) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO users(username, password, firstName, lastName, address, phone, email, flag, userType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			ps.setString(5, c.getAddress());
			ps.setString(6, c.getPhone());
			ps.setString(7, c.getEmail());
			ps.setString(8, c.getFlag());
			ps.setString(9, String.valueOf(c.getUserType()));
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
// UPDATE
	public int updateUser(User c) {
		PreparedStatement ps = null;
		int toReturn = 0;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("UPDATE users SET username = (?), password = (?), firstName = (?), lastName = (?), address = (?), phone = (?), email = (?), flag = (?), userType = (?) WHERE username = (?)");
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			ps.setString(5, c.getAddress());
			ps.setString(6, c.getPhone());
			ps.setString(7, c.getEmail());
			ps.setString(8, c.getFlag());
			ps.setString(9, String.valueOf(c.getUserType()));
			ps.setString(10, c.getUserName());
			toReturn = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}

// DELETE
	public boolean deleteUser(String username) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("DELETE FROM users WHERE username = (?)");
			ps.setString(1, username);
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
		
		
}
