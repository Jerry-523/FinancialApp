package com.mobile.financialapp;


public class Transaction {
    private int id;
    private int userId;
    private String title;
    private float amount;
    private int categoryId;
    private String date;

    public Transaction(int id, int userId, String title, float amount, int categoryId, String date) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
    }

    public Transaction(int userId, String title, float amount, int id, String s) {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public float getAmount() {
        return amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDate() {
        return date;
    }
}

