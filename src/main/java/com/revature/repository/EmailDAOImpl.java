package com.revature.repository;

import com.revature.models.Email;
import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailDAOImpl implements EmailDAO {
    //SELECT
    @Override
    public Email selectEmailByUserID(int userID) {
        Email toReturn = null;
        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM emails WHERE user_id = (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String email = rs.getString("email");
                toReturn = new Email(userID, email);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public int selectUserIDByEmail(String email) {
        int userID = 0;
        System.out.println("SelectUserIdByEmail");
        try(Connection conn = ConnectionFactory.getConnection()) {
            System.out.println("Got connection");
            String sql = "SELECT user_id FROM emails WHERE email = (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                userID = rs.getInt("user_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userID;
    }

    //INSERT
    @Override
    public boolean insertEmail(Email email) {
        boolean toReturn = false;
        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO emails(email, user_id) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email.getEmail());
            ps.setInt(2, email.getUserID());
            ps.execute();

            toReturn = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return toReturn;
    }
}
