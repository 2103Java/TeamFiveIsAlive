package com.revature.models;

import java.sql.Timestamp;

public class Ticket {
    private int ticketID;
    private double amount;
    private String status;
    private int userID;
    private String type;
    private String desc;
    private String timestamp;

    public Ticket(int ticketID, double amount, String status, int userID, String type) {
        this(ticketID, amount, status, userID, type, "");
    }

    public Ticket(int ticketID, double amount, String status, int userID, String type, String desc) {
        this.ticketID = ticketID;
        this.amount = amount;
        this.status = status;
        this.userID = userID;
        this.type = type;
        this.desc = desc;
    }

    public String getStatus() {
        return this.status;
    }

    public void approve() {
        this.status = "APPROVED";
    }

    public void reject() {
        this.status = "REJECTED";
    }

    public int getTicketID() {
        return ticketID;
    }

    public double getAmount() {
        return amount;
    }

    public int getUserID() {
        return userID;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp.toString();
    }
}