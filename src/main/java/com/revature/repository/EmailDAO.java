package com.revature.repository;

import com.revature.models.Email;

public interface EmailDAO {
    public Email selectEmailByUserID(int userID);

    public int selectUserIDByEmail(String email);

    public boolean insertEmail(Email email);
}
