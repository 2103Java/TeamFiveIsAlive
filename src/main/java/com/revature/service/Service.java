//package com.revature.service;
//
//import java.util.HashSet;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.revature.models.Ticket;
//import com.revature.models.User;
//import com.revature.repository.TicketDAOImpl;
//import com.revature.repository.UserDAOImpl;
//
//public class Service {
//
//// Variables
//	private static Logger log = LogManager.getLogger("Service");
//	private static TicketDAOImpl ticketDAOImpl;
//	private static UserDAOImpl userDAOImpl;
//
//
//
//// Methods
//	public Service() {
//		ticketDAOImpl = new TicketDAOImpl();
//		userDAOImpl = new UserDAOImpl();
//	}
//
//	public Service(TicketDAOImpl ticketDAOImpl) {
//		// TODO Auto-generated constructor stub
//	}
//
//	// Ticket Methods
//	protected static HashSet<Ticket> getTickets() {
//		HashSet<Ticket> toReturn = ticketDAOImpl.selectAllTickets();
//		return toReturn;
//	}
//
//	public static Ticket getTicketByID(int id) {
//		Ticket toReturn = ticketDAOImpl.selectTicket(id);
//		return toReturn;
//	}
//
//	protected static void addTicket(Ticket a) {
//		ticketDAOImpl.insertTicket(a);
//	}
//
//	protected static void saveTicket(Ticket a) {
//		ticketDAOImpl.updateTicket(a);
//	}
//
//	protected static void removeTicket(Ticket a) {
//		ticketDAOImpl.deleteTicket(a.getTicketID());
//	}
//
//	protected static HashSet<Ticket> getTicketsByOwner(String username) {
//		HashSet<Ticket> toReturn = ticketDAOImpl.selectTicketsByOwner(username);
//		return toReturn;
//	}
//
//	protected static HashSet<Ticket> getTicketsByType(String type) {
//		HashSet<Ticket> toReturn = ticketDAOImpl.selectTicketsByType(type);
//		return toReturn;
//	}
//
//	protected static HashSet<Ticket> getTicketsByFlag(String flag) {
//		HashSet<Ticket> toReturn = ticketDAOImpl.selectTicketsByFlag(flag);
//		return toReturn;
//	}
//
//	// User Methods
//	protected static HashSet<User> getUsers() {
//		HashSet<User> toReturn = userDAOImpl.selectAllUsers();
//		return toReturn;
//	}
//
//	protected static User getUser(String username) {
//		User toReturn = null;
//
//		toReturn = userDAOImpl.selectUser(username);
//
//		return toReturn;
//	}
//
//	protected static void addUser(User u) {
//		userDAOImpl.insertUser(u);
//	}
//
//	protected static boolean saveUser(User u) {
//		boolean toReturn = false;
//
//		toReturn = userDAOImpl.updateUser(u);
//
//		return toReturn;
//	}
//
//	protected static void removeUser(User u) {
//		userDAOImpl.deleteUser(u.getUserName());
//	}
//
//	protected static boolean verifyPassword(String email, String password) {
//		boolean toReturn = false;
//
//		if(userDAOImpl.verifyPassword(username, password))
//			toReturn = true;
//
//		return toReturn;
//	}
//
//
//
//	// Ticket Transactions
//	// Inquiry
//	protected static double getBalance(Ticket Ticket) {
//		log.info("Balance - Ticket: " + Ticket.getID() + ", Balance: $" + Ticket.getBalance());
//		return Ticket.getBalance();
//	}
//
//	// Withdraw
//	protected static boolean withdraw(Ticket Ticket, double amount) {
//		boolean toReturn = false;
//
//		if (Ticket.getBalance() >= amount) {
//			log.info("Withdraw - Ticket: " + Ticket.getID() + ", Old balance: $" + Ticket.getBalance() + ", New Balance: $" + (Ticket.getBalance() - amount));
//			Ticket.setBalance(Ticket.getBalance() - amount);
//			ticketDAOImpl.updateTicket(Ticket);
//			toReturn = true;
//		}
//		else
//			log.info("Withdraw - Ticket: " + Ticket.getID() + ", Old balance: $" + Ticket.getBalance() + ", Transaction Failed: Not enough money to complete transaction");
//
//		return toReturn;
//	}
//
//	// Deposit
//	protected static void deposit(Ticket Ticket, double amount) {
//		log.info("Deposit - Ticket: " + Ticket.getID() + ", Old balance: $" + Ticket.getBalance() + ", New Balance: $" + (Ticket.getBalance() + amount));
//		Ticket.setBalance(Ticket.getBalance() + amount);
//		ticketDAOImpl.updateTicket(Ticket);
//	}
//
//}
