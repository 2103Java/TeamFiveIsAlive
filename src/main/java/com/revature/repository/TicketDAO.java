package com.revature.repository;

import com.revature.models.Ticket;

import java.util.HashSet;

public interface TicketDAO {
    public Ticket selectTicket(int id);

    public HashSet<Ticket> selectAllTickets();

    public HashSet<Ticket> selectTicketsByUser(int userID);

    public HashSet<Ticket> selectTicketsByStatus(String status);

    public boolean insertTicket(Ticket a);

    public boolean updateTicket(Ticket a);

    public boolean deleteTicket(int ticketID);

    public int selectTicketID();
}
