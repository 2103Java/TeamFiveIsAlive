package com.revature.repository;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;

import com.revature.models.Ticket;
import com.revature.utility.ConnectionFactory;

public class TicketDAOImpl implements TicketDAO {
// SELECT
	@Override
	public Ticket selectTicket(int id) {
		Ticket toReturn = null;

		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets WHERE ticket_id = (?)");
			ps.setInt(1, id);
			ResultSet rs= ps.executeQuery();
			if (rs.next()) {
				int ticketID = rs.getInt("ticket_id");
				double amount = rs.getDouble("amount");
				String status = rs.getString("status");
				int userID = rs.getInt("user_id");
				String type = rs.getString("ticket_type");
				String desc = rs.getString("description");
				toReturn = new Ticket(ticketID, amount, status, userID, type, desc);
				Timestamp timestamp = rs.getTimestamp("timest");
				LocalDateTime currentTime = timestamp.toLocalDateTime();
				toReturn.setTimestamp(currentTime.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public HashSet<Ticket> selectAllTickets() {
		HashSet<Ticket> toReturn = new HashSet<>();

		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ticketID = rs.getInt("ticket_id");
				double amount = rs.getDouble("amount");
				String status = rs.getString("status");
				int userID = rs.getInt("user_id");
				String type = rs.getString("ticket_type");
				String desc = rs.getString("description");
				Ticket ticket = new Ticket(ticketID, amount, status, userID, type, desc);
				Timestamp timestamp = rs.getTimestamp("timest");
				LocalDateTime currentTime = timestamp.toLocalDateTime();
				ticket.setTimestamp(currentTime.toString());
				toReturn.add(ticket);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public HashSet<Ticket> selectTicketsByUser(int userID) {
		HashSet<Ticket> toReturn = new HashSet<>();
		Ticket a = null;

		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets WHERE user_id = (?)");
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int ticketID = rs.getInt("ticket_id");
				double amount = rs.getDouble("amount");
				String status = rs.getString("status");
				String type = rs.getString("ticket_type");
				String desc = rs.getString("description");

				Ticket ticket = new Ticket(ticketID, amount, status, userID, type, desc);
				Timestamp timestamp = rs.getTimestamp("timest");
				LocalDateTime currentTime = timestamp.toLocalDateTime();
				ticket.setTimestamp(currentTime.toString());
				toReturn.add(ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public HashSet<Ticket> selectTicketsByStatus(String status) {
		HashSet<Ticket> toReturn = new HashSet<>();

		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets WHERE status = (?)");
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int ticketID = rs.getInt("ticket_id");
				double amount = rs.getDouble("amount");
				int userID = rs.getInt("user_id");
				String type = rs.getString("ticket_type");
				String desc = rs.getString("description");

				Ticket ticket = new Ticket(ticketID, amount, status, userID, type, desc);
				Timestamp timestamp = rs.getTimestamp("timest");
				LocalDateTime currentTime = timestamp.toLocalDateTime();
				ticket.setTimestamp(currentTime.toString());
				toReturn.add(ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public int selectTicketID() {
		int toReturn = 0;
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT MAX(ticket_id) FROM tickets";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				toReturn = rs.getInt(1);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return toReturn;
	}

// INSERT
	@Override
	public boolean insertTicket(Ticket a) {
		PreparedStatement ps = null;
		boolean toReturn = false;

		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO tickets (ticket_id, amount, status, user_id, ticket_type, description, timest) VALUES " +
					"(?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getTicketID());
			ps.setDouble(2, a.getAmount());
			ps.setString(3, a.getStatus());
			ps.setInt(4, a.getUserID());
			ps.setString(5, a.getType());
			ps.setString(6, a.getDesc());
			ps.setTimestamp(7,  Timestamp.from(Instant.now()));

			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

// UPDATE
	@Override
	public boolean updateTicket(Ticket a) {
		boolean toReturn = false;

		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE tickets SET " +
					"amount=(?), " +
					"status=(?), " +
					"user_id=(?), " +
					"ticket_type=(?), " +
					"description=(?) " +
					"WHERE ticket_id = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, a.getAmount());
			ps.setString(2, a.getStatus());
			ps.setInt(3, a.getUserID());
			ps.setString(4, a.getType());
			ps.setString(5, a.getDesc());
			ps.setInt(6, a.getTicketID());

			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

// DELETE
	@Override
	public boolean deleteTicket(int ticketID) {
		boolean toReturn = false;

		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM tickets WHERE ticket_id = (?)");
			ps.setLong(1, ticketID);
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return toReturn;
	}


}
