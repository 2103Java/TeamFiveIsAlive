package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.repository.TicketDAO;
import com.revature.service.Service;

public class RequestHelper {
	
	TicketDAO ticketDAO = new TicketDAO();
	Service service = new Service(ticketDAO);
	TicketController ticketControl = new TicketController(service);

	public void process(HttpServletRequest req, HttpServletResponse res) {
		
//		System.out.println(req.getRequestURI());
		
		String endpoint = req.getRequestURI();
		String method = req.getMethod();
		
		switch (endpoint) {
		case "/TicketAPI/api/ticket":
			System.out.println("Inside ticket logic");
			switch (method) {
			case "GET":
				TicketController.getTicket(req,res);
				break;
			case "POST":
				//
				break;
			case "PUT":
				//
				break;
			case "DELETE":
				//
				break;
			default:
				res.setStatus(405);
				break;
			}
			break;
		case "/TicketAPI/api/user":
			System.out.println("Inside user logic");
			break;
		default:
			res.setStatus(418);
			break;
		}
	}
}
