package com.revature.models;

public class Email {
    private int userID;
    private String email;

    public Email(int userID, String email) {
        this.userID = userID;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }
}
