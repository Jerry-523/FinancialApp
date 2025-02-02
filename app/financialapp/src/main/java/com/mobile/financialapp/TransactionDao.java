package com.mobile.financialapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Query("SELECT * FROM transactions WHERE userId = :userId")
    List<Transaction> getAllTransactions(int userId);
}
