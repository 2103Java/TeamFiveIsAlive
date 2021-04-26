package com.revature;

import com.revature.presentation.UserInterface;
import com.revature.utility.ConnectionFactory;

public class MainDriver {

	/*
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		UserInterface atm = new UserInterface();
		
		try {
			Class.forName("org.postgresql.Driver");
			if (ConnectionFactory.getConnection() != null) {
				System.out.println("Successful Connection!");
				atm.startEngine();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		atm.startEngine();
	}
}
