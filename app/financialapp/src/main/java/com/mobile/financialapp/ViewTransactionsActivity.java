package com.mobile.financialapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewTransactionsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch transactions from Room database
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            List<Transaction> transactions = db.transactionDao().getAllTransactions(1); // Replace 1 with the actual user ID
            runOnUiThread(() -> {
                adapter = new TransactionAdapter(transactions);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}
