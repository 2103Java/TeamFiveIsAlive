package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Ticket;
import com.revature.service.Service;

public class TicketController {

	public TicketController(Service s) {
	}
	
	public static void getTicket(HttpServletRequest req, HttpServletResponse res) {

		res.setContentType("json/application");
		
		String id = req.getParameter("ticketId");
		
		System.out.println("ticketId = " + id);
		
		Service.getTicketByID(id);
	}

}
