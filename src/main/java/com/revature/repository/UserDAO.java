package com.revature.repository;

import com.revature.models.User;

import java.util.HashSet;

public interface UserDAO {
    public User selectUserByID(int userID);

    public HashSet<User> selectAllUsers();

    public boolean insertUser(User u);

    public boolean updateUser(User u);

    public boolean deleteUser(int userID);
}
