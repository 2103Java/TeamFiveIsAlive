package com.revature.models;

import com.revature.models.User;

public class User {

	
	// Variables
	protected String username; 
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String address;
	protected String phone;
	protected String email;
	protected String flag;
	protected char userType; // employee or admin
	
	
	

	// Methods
	public User() {
		username = "";
		password = "";
		firstName = "";
		lastName = "";
		address = "";
		phone = "";
		email = "";
		flag = "";
		userType = 'b'; // b is blank
	}
	
	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}
	
	public User(String u, String p, String f, String l, String a, String ph, String e, String fl, char t) {
		username = u;
		password = p;
		firstName = f;
		lastName = l;
		address = a;
		phone = ph;
		email = e;
		flag = fl;
		userType = t;
	}
	
	
	protected void copyUser(User oldUser, User newUser) {
		
		newUser.username = oldUser.username;
		newUser.password = oldUser.password;
		newUser.firstName = oldUser.firstName;
		newUser.lastName = oldUser.lastName;
		newUser.address = oldUser.address;
		newUser.phone = oldUser.phone;
		newUser.email = oldUser.email;
		newUser.flag = oldUser.flag;
		newUser.userType = oldUser.userType;
		
	}
	
	
	
	// Getter Methods
	public String getUserName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getFlag() {
		return flag;
	}
	public char getUserType() {
		return userType;
	}


	
	// Setter Methods
	public void setUserName(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String f) {
		firstName = f;
	}
	public void setLastName(String l) {
		lastName = l;
	}
	public void setAddress(String a) {
		address = a;
	}
	public void setPhone(String p) {
		phone = p;
	}
	public void setEmail(String e) {
		email = e;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setUserType(char t) {
		userType = t;
	}

}
