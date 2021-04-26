package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.revature.models.Ticket;
import com.revature.utility.ConnectionFactory;

public class TicketDAO {


	// SELECT
		public Ticket selectTicket(String id) {
			PreparedStatement ps = null;
			Ticket toReturn = null;

			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("SELECT * FROM Tickets WHERE acc_id = (?)");
				ps.setLong(1, Long.valueOf(id));
				ResultSet accResults = ps.executeQuery();
				if (accResults.next()) {
					String aID = Long.toString(accResults.getLong(1));
					String type = accResults.getString(2);
					String flag = accResults.getString(3);
					double balance = accResults.getDouble(4);
					toReturn = new Ticket(selectTicketOwners(id), aID, type, flag, balance);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
		
		public HashSet<String> selectTicketOwners(String id) {
			PreparedStatement ps = null;
			HashSet<String> toReturn = new HashSet<String>();
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("SELECT username FROM TicketOwners WHERE acc_id = (?)");
				ps.setLong(1, Long.valueOf(id));
				ResultSet ownerResults = ps.executeQuery();
				while (ownerResults.next()) {
					toReturn.add(ownerResults.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
		
		public HashSet<Ticket> selectAllTickets() {
			PreparedStatement ps = null;
			HashSet<Ticket> toReturn = new HashSet<Ticket>();
					
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("SELECT acc_id FROM Tickets");
				ResultSet accResults = ps.executeQuery();
				while (accResults.next()) {
					Ticket a = selectTicket(Long.toString(accResults.getLong(1)));
					toReturn.add(a);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
		
		public HashSet<Ticket> selectTicketsByOwner(String owner) {
			PreparedStatement ps = null;
			HashSet<Ticket> toReturn = new HashSet<Ticket>();
			Ticket a = null;
			HashSet<String> accIDs = new HashSet<String>();
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("SELECT acc_id FROM TicketOwners WHERE username = (?)");
				ps.setString(1, owner);
				ResultSet accIDResults = ps.executeQuery();
				while (accIDResults.next())
					accIDs.add(Long.toString(accIDResults.getLong(1)));
				for (String s : accIDs) {
					a = selectTicket(s);
					toReturn.add(a);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
		
		public HashSet<Ticket> selectTicketsByType(String accType) {
			PreparedStatement ps = null;
			HashSet<Ticket> toReturn = new HashSet<Ticket>();
			Ticket a = null;
			HashSet<String> accIDs = new HashSet<String>();
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("SELECT acc_id FROM Tickets WHERE acc_type = (?)");
				ps.setString(1, accType);
				ResultSet accIDResults = ps.executeQuery();
				while (accIDResults.next())
					accIDs.add(Long.toString(accIDResults.getLong(1)));
				for (String s : accIDs) {
					a = selectTicket(s);
					toReturn.add(a);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
		
		public HashSet<Ticket> selectTicketsByFlag(String accFlag) {
			PreparedStatement ps = null;
			HashSet<Ticket> toReturn = new HashSet<Ticket>();
			Ticket a = null;
			HashSet<String> accIDs = new HashSet<String>();
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("SELECT acc_id FROM Tickets WHERE acc_flag = (?)");
				ps.setString(1, accFlag);
				ResultSet accIDResults = ps.executeQuery();
				while (accIDResults.next())
					accIDs.add(Long.toString(accIDResults.getLong(1)));
				for (String s : accIDs) {
					a = selectTicket(s);
					toReturn.add(a);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
		
	// INSERT
		public boolean insertTicket(Ticket a) {
			PreparedStatement ps = null;
			boolean toReturn = false;
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("INSERT INTO Tickets(acc_type, acc_flag, balance) VALUES (?, ?, ?)");
				ps.setString(1, a.getType());
				ps.setString(2, a.getFlag());
				ps.setDouble(3, a.getBalance());
				ps.execute();
				ps = conn.prepareStatement("SELECT currval('Tickets_acc_id_seq')");
				ResultSet idResult = ps.executeQuery();
				if (idResult.next()) {
					a.setID(Long.toString(idResult.getLong(1)));
					for (String s : a.getOwners())
						if (insertTicketOwner(a.getID(), s))
							toReturn = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return toReturn;
		}
		
		public boolean insertTicketOwner(String id, String username) {
			PreparedStatement ps = null;
			boolean toReturn = false;
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("INSERT INTO TicketOwners(acc_id, username) VALUES (?, ?)");
				ps.setLong(1, Long.valueOf(id));
				ps.setString(2, username);
				ps.execute();
				toReturn = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return toReturn;
		}
		
	// UPDATE
		public int updateTicket(Ticket a) {
			PreparedStatement ps = null;
			int toReturn = 0;
			HashSet<String> owners = selectTicketOwners(a.getID());
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("UPDATE Tickets SET acc_type = (?), acc_flag = (?), balance = (?) WHERE acc_id = (?)");
				ps.setString(1, a.getType());
				ps.setString(2, a.getFlag());
				ps.setDouble(3, a.getBalance());
				ps.setLong(4, Long.valueOf(a.getID()));
				toReturn = ps.executeUpdate();
				if (!a.getOwners().equals(owners)) {
					for (String s : a.getOwners()) 
						if (!owners.contains(s)) // if TicketOwners does not contain a record from a.owners insert that record into database
							insertTicketOwner(a.getID(), s);
					for (String s : owners)
						if (!a.getOwners().contains(s)) // if a.owners does not contain a record from TicketOwners delete that record from database
							deleteTicketOwner(a.getID(), s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return toReturn;
		}

	// DELETE
		public boolean deleteTicket(String id) {
			PreparedStatement ps = null;
			boolean toReturn = false;
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("DELETE FROM Tickets WHERE acc_id = (?)");
				ps.setLong(1, Long.valueOf(id));
				ps.execute();
				toReturn = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return toReturn;
		}
		
		public boolean deleteTicketOwner(String id, String owner) {
			PreparedStatement ps = null;
			boolean toReturn = false;
			
			try(Connection conn = ConnectionFactory.getConnection()) {
				ps = conn.prepareStatement("DELETE FROM TicketOwners WHERE acc_id = (?) and username = (?)");
				ps.setLong(1, Long.valueOf(id));
				ps.setString(2, owner);
				ps.execute();
				toReturn = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return toReturn;
		}
		
		
		
}
