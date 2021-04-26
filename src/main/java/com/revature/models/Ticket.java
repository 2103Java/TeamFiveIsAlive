package com.revature.models;

public class Ticket {

	// Variables
	private String id; // Ticket Number
	private String owner; // usernames of all owners on the Ticket
	private String descrip;
	private double amount;
	
	
// Methods
	public Ticket() {
		
	}
	
	public Ticket(String owner, String descrip, double amount) { // new Ticket
		this.owner = owner;
		this.descrip = descrip;
		this.amount = amount;
	}
	
	public Ticket(String id, String owner, String descrip, double amount) { // load Ticket
		this.id = id;
		this.owner = owner;
		this.descrip = descrip;
		this.amount = amount;
	}
		
		
	
	public boolean verifyOwner(String username) {
		boolean toReturn = false;
		
		if (owner.equals(username)) 
			toReturn = true; 
		
		return toReturn;
	}
	
	// Getters
	public String getID() {
		return id;
	}
	public String getOwner() {
		return owner;
	}
	public String getDescrip() {
		return descrip;
	}
	public double getAmount() {
		return amount;
	}
	
	
	// Setters
	public void setID(String id) {
		this.id = id;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
