package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Ticket;
import com.revature.repository.TicketDAO;
import com.revature.repository.TicketDAOImpl;
import com.revature.service.Service;

public class RequestHelper {

	TicketDAO ticketDAOImpl = new TicketDAOImpl();
	private Service service = new Service();
	TicketController ticketControl = new TicketController(service);

	public void process(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getRequestURI());

		String endpoint = req.getRequestURI();
		String method = req.getMethod();

		switch (endpoint) {
		case "/api/ticket":
			System.out.println("Inside Ticket logic.");
			switch (method) {
			case "GET":
				TicketController.getTicket(req,res);
				break;
			case "POST":
				System.out.println("Inside post logic!");
				TicketController.addTicket(req, res);
				break;
			default:
				res.setStatus(405);
				break;
			}
			break;
		case "/api/ticket/create":
			System.out.println("Inside post logic...");
			TicketController.addTicket(req, res);
			break;
		case "/api/ticket/approve":
			TicketController.approveTicket(req, res);
			break;
		case "/api/ticket/reject":
			TicketController.rejectTicket(req, res);
			break;
		case "/api/tickets":
			TicketController.getAllUserTickets(req, res);
			break;
			case "/api/tickets/id":
				TicketController.getTicketsByUser(req, res);
				break;
		case "/api/user/login":
			System.out.println("Inside user logic");
			System.out.println(method);
			switch(method) {
				case "POST":
					UserController.loginUser(req, res);
					break;
			}
			break;
		case "/api/user/register":
			switch (method) {
				case "POST":
					UserController.createUser(req, res);
					break;
			}
			break;
			case "/api/user/id":
				UserController.getUserId(req, res);
				break;
			case "/api/user/logout":
				UserController.logoutUser(req, res);
				break;
		default:
			res.setStatus(418);
			break;
		}
	}
}
