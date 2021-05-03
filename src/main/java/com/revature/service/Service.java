package com.revature.service;

import java.util.HashSet;

import com.revature.models.Email;
import com.revature.repository.*;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.revature.models.Ticket;
import com.revature.models.User;

public class Service {

// Variables
	//private static Logger log = LogManager.getLogger("Service");
	private static TicketDAO ticketDAOImpl;
	private static UserDAO userDAOImpl;
	private static EmailDAO emailDAOImpl;



// Methods
	public Service() {
		ticketDAOImpl = new TicketDAOImpl();
		userDAOImpl = new UserDAOImpl();
		emailDAOImpl = new EmailDAOImpl();
	}

	// Ticket Methods
	public HashSet<Ticket> getTickets() {
		HashSet<Ticket> toReturn = ticketDAOImpl.selectAllTickets();
		return toReturn;
	}

	public Ticket getTicketByID(int id) {
		Ticket toReturn = ticketDAOImpl.selectTicket(id);
		return toReturn;
	}

	public void addTicket(Ticket t) {
		ticketDAOImpl.insertTicket(t);
	}

	public void saveTicket(Ticket t) {
		ticketDAOImpl.updateTicket(t);
	}

	public void removeTicket(Ticket t) {
		ticketDAOImpl.deleteTicket(t.getTicketID());
	}

    public boolean updateTicket(Ticket t) {
        boolean toReturn = ticketDAOImpl.updateTicket(t);
        return toReturn;
    }

	public HashSet<Ticket> getTicketsByOwner(int userID) {
		HashSet<Ticket> toReturn = ticketDAOImpl.selectTicketsByUser(userID);
		return toReturn;
	}

	public HashSet<Ticket> getTicketsByType(String status) {
		HashSet<Ticket> toReturn = ticketDAOImpl.selectTicketsByStatus(status);
		return toReturn;
	}
	public int getNewTicketID() {
		int ticketID = ticketDAOImpl.selectTicketID();
		return ticketID + 1;
	}


	// User Methods
	public HashSet<User> getUsers() {
		HashSet<User> toReturn = userDAOImpl.selectAllUsers();
		return toReturn;
	}

	public User getUser(int userID) {
		User u = userDAOImpl.selectUserByID(userID);
		System.out.println("Getting user!");
		return u;
	}

	public User getUserByEmail(String email) {
		System.out.println("getUserByEmail");
        int userID = emailDAOImpl.selectUserIDByEmail(email);
        User u = userDAOImpl.selectUserByID(userID);
        return u;
    }

    public int getNewUserID() {
		int userID = userDAOImpl.selectUserID();
		return userID + 1;
	}

	public void addUser(User u) {
		userDAOImpl.insertUser(u);
	}

	public boolean saveUser(User u) {
		boolean toReturn = userDAOImpl.updateUser(u);

		return toReturn;
	}

	public void removeUser(User u) {
		userDAOImpl.deleteUser(u.getUserID());
	}

	public boolean verifyPassword(String email, String password) {
		int userID = emailDAOImpl.selectUserIDByEmail(email);
		User u = userDAOImpl.selectUserByID(userID);

		if(password.equals(u.getPassword()))
		    return true;
		return false;
	}
	//Email methods
	public boolean addEmail(String email, int userID) {
		Email e = new Email(userID, email);
		emailDAOImpl.insertEmail(e);
		return true;
	}

}
