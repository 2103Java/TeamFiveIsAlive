package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Email;
import com.revature.models.User;
import com.revature.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class UserController {

    public UserController() {
    }

    public static void getUser(HttpServletRequest req, HttpServletResponse res) {
        Service s = new Service();

        res.setContentType("json/application");

        String id = req.getParameter("ticketId");

        System.out.println("ticketId = " + id);

        s.getTicketByID(Integer.parseInt(id));
    }

    public static void loginUser(HttpServletRequest req, HttpServletResponse res) {
        Service s = new Service();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(email);

        User u = s.getUserByEmail(email);
        if(password.equals(u.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", u);

        }
        if(u.getUserType().equals("Employee")) {
            try {
                res.sendRedirect("/ViewTicketsUser.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(u.getUserType().equals("Admin")) {
            try {
                res.sendRedirect("/ViewTicketsAdmin.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createUser(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Inside createUser");
        Service s = new Service();
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");
        int userID = s.getNewUserID();

        User u = new User(userID, firstName, lastName, password, "Employee");

        s.addUser(u);
        s.addEmail(email, userID);

        HttpSession session = req.getSession();
        session.setAttribute("user", u);

        try {
            res.sendRedirect("/ViewTicketsUser.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logoutUser(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        session.invalidate();
    }

    public static void getUserId(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        res.setContentType("application/json");
        u.setPassword("???");
        ObjectMapper om = new ObjectMapper();


        try {
            Writer writer = res.getWriter();
            writer.write(om.writeValueAsString(u));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
