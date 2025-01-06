package com.mobile.financialapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public String title;
    public float amount;
    public int categoryId;
    public String date;
}
