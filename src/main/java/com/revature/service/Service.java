package com.revature.service;

import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.repository.TicketDAO;
import com.revature.repository.UserDAO;

public class Service {
	
// Variables
	private static Logger log = LogManager.getLogger("Service");
	private static TicketDAO ticketDAO;
	private static UserDAO userDAO;
	
	
	
// Methods
	public Service() {
		ticketDAO = new TicketDAO();
		userDAO = new UserDAO();
	}
	
	public Service(TicketDAO ticketDAO) {
		// TODO Auto-generated constructor stub
	}

	// Ticket Methods
	protected static HashSet<Ticket> getTickets() {
		HashSet<Ticket> toReturn = ticketDAO.selectAllTickets();
		return toReturn;
	}
	
	public static Ticket getTicketByID(String id) {
		Ticket toReturn = ticketDAO.selectTicket(id);		
		return toReturn;
	}
	
	protected static void addTicket(Ticket a) {
		ticketDAO.insertTicket(a);
	}
	
	protected static void saveTicket(Ticket a) {
		ticketDAO.updateTicket(a);
	}
	
	protected static void removeTicket(Ticket a) {
		ticketDAO.deleteTicket(a.getID());
	}
	
	protected static HashSet<Ticket> getTicketsByOwner(String username) {
		HashSet<Ticket> toReturn = ticketDAO.selectTicketsByOwner(username);
		return toReturn;
	}
	
	protected static HashSet<Ticket> getTicketsByType(String type) {
		HashSet<Ticket> toReturn = ticketDAO.selectTicketsByType(type);		
		return toReturn;
	}
	
	protected static HashSet<Ticket> getTicketsByFlag(String flag) {
		HashSet<Ticket> toReturn = ticketDAO.selectTicketsByFlag(flag);
		return toReturn;
	}
	
	// User Methods
	protected static HashSet<User> getUsers() {
		HashSet<User> toReturn = userDAO.selectAllUsers();
		return toReturn;
	}

	protected static User getUser(String username) {
		User toReturn = null;
		
		toReturn = userDAO.selectUser(username);
		
		return toReturn;
	}
	
	protected static void addUser(User u) {
		userDAO.insertUser((User) u);
	}
	
	protected static int saveUser(User u) {
		int toReturn = 0;

		toReturn = userDAO.updateUser((User) u);
		
		return toReturn;
	}

	protected static void removeUser(User u) {
		userDAO.deleteUser(u.getUserName());
	}

	protected static boolean verifyUsername(String username) {
		boolean toReturn = false;
		
		if(userDAO.verifyUsername(username))
			toReturn = true;
		
		return toReturn;
	}
	
	protected static boolean verifyPassword(String username, String password) {
		boolean toReturn = false;

		if(userDAO.verifyPassword(username, password))
			toReturn = true;
		
		return toReturn;
	}
	
	
	
	// Ticket Transactions
	// Inquiry
	protected static double getBalance(Ticket Ticket) {
		log.info("Balance - Ticket: " + Ticket.getID() + ", Balance: $" + Ticket.getBalance());
		return Ticket.getBalance();
	}
	
	// Withdraw
	protected static boolean withdraw(Ticket Ticket, double amount) {
		boolean toReturn = false;
		
		if (Ticket.getBalance() >= amount) {
			log.info("Withdraw - Ticket: " + Ticket.getID() + ", Old balance: $" + Ticket.getBalance() + ", New Balance: $" + (Ticket.getBalance() - amount));
			Ticket.setBalance(Ticket.getBalance() - amount);
			ticketDAO.updateTicket(Ticket);
			toReturn = true;
		}
		else
			log.info("Withdraw - Ticket: " + Ticket.getID() + ", Old balance: $" + Ticket.getBalance() + ", Transaction Failed: Not enough money to complete transaction");
			
		return toReturn;
	}

	// Deposit
	protected static void deposit(Ticket Ticket, double amount) {
		log.info("Deposit - Ticket: " + Ticket.getID() + ", Old balance: $" + Ticket.getBalance() + ", New Balance: $" + (Ticket.getBalance() + amount));
		Ticket.setBalance(Ticket.getBalance() + amount);
		ticketDAO.updateTicket(Ticket);
	}
	
}
