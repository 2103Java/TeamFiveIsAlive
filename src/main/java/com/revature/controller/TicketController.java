package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.service.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

public class TicketController {

	public TicketController(Service s) {
	}

	public static void getTicket(HttpServletRequest req, HttpServletResponse res) {
		Service s = new Service();

		res.setContentType("json/application");

		String id = req.getParameter("ticketId");

		System.out.println("ticketId = " + id);

		s.getTicketByID(Integer.parseInt(id));
	}

	public static void getAllUserTickets(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Getting all tickets");
		Service s = new Service();
		String status = req.getParameter("status");
		if(status != null) {
			System.out.println("Getting status tickets!");
			HashSet<Ticket> tickets = s.getTicketsByType(status);
			res.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();

			try {
				Writer writer = res.getWriter();
				writer.write(om.writeValueAsString(tickets));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			HashSet<Ticket> tickets = s.getTickets();
			res.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();

			try {
				Writer writer = res.getWriter();
				writer.write(om.writeValueAsString(tickets));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getTicketsByUser(HttpServletRequest req, HttpServletResponse res) {
		Service s = new Service();
		int userID = Integer.parseInt(req.getParameter("userID"));
		HashSet<Ticket> tickets = s.getTicketsByOwner(userID);
		res.setContentType("application/json");

		ObjectMapper om = new ObjectMapper();

		try {
			Writer writer = res.getWriter();
			writer.write(om.writeValueAsString(tickets));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addTicket(HttpServletRequest req, HttpServletResponse res) {
		Service s = new Service();
		int ticketID = s.getNewTicketID();
		double amount = Double.parseDouble(req.getParameter("amount"));
		String type = req.getParameter("ticketType");
		String description = req.getParameter("description");

		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		System.out.println(u);
		Ticket t;
		if(description != null) {
			t = new Ticket(ticketID, amount, "PENDING", u.getUserID(), type, description);
		} else {
			t = new Ticket(ticketID, amount, "PENDING", u.getUserID(), type);
		}

		s.addTicket(t);

		try {
			res.sendRedirect("/ViewTicketsUser.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void approveTicket(HttpServletRequest req, HttpServletResponse res) {
		Service s = new Service();
		int ticketID = Integer.parseInt(req.getParameter("ticketID"));
		Ticket ticket = s.getTicketByID(ticketID);
		ticket.approve();
		s.updateTicket(ticket);
	}

	public static void rejectTicket(HttpServletRequest req, HttpServletResponse res) {
		Service s = new Service();
		int ticketID = Integer.parseInt(req.getParameter("ticketID"));
		Ticket ticket = s.getTicketByID(ticketID);
		ticket.reject();
		s.updateTicket(ticket);
	}
}
