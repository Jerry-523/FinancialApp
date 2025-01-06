package com.mobile.financialapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query(value = "SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User authenticateUser(String username, String password);
}
