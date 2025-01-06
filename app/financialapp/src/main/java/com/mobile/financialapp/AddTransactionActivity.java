package com.mobile.financialapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddTransactionActivity extends AppCompatActivity {

    private EditText transactionTitle, transactionAmount;
    private Spinner categorySpinner;
    private Button saveTransactionBtn, cancelTransactionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        transactionTitle = findViewById(R.id.transactionTitle);
        transactionAmount = findViewById(R.id.transactionAmount);
        categorySpinner = findViewById(R.id.categorySpinner);
        saveTransactionBtn = findViewById(R.id.saveTransactionBtn);
        cancelTransactionBtn = findViewById(R.id.cancelTransactionBtn);

        loadCategories();

        saveTransactionBtn.setOnClickListener(v -> saveTransaction());
        cancelTransactionBtn.setOnClickListener(v -> finish());
    }

    private void loadCategories() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.close();

        String[] categories = {"Alimentação", "Transporte", "Eletricidade", "Agua", "Salario", "Outros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    private void saveTransaction() {
        String title = transactionTitle.getText().toString().trim();
        String amountStr = transactionAmount.getText().toString().trim();
        Category selectedCategory = (Category) categorySpinner.getSelectedItem();

        if (title.isEmpty() || amountStr.isEmpty() || selectedCategory == null) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float amount = Float.parseFloat(amountStr);
            int userId = 1; // Substitua pela lógica para obter o userId corretamente.
            Transaction transaction = new Transaction(userId, title, amount, selectedCategory.getId(), ""); // userId deve ser passado da atividade de Dashboard
            DatabaseHelper db = new DatabaseHelper(this);
            db.insertTransaction(transaction);
            db.close();

            Toast.makeText(this, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddTransactionActivity.this, DashboardActivity.class));
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Insira um valor válido para o campo de valor.", Toast.LENGTH_SHORT).show();
        }
    }
}
