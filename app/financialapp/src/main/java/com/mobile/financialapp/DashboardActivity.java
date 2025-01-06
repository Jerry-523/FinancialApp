package com.mobile.financialapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private Button addReceitaBtn, addDespesaBtn;

    private BarChart barChart;
    private TextView totalReceitaText, totalDespesaText, saldoText;
    private float totalReceitas = 0; // Inicialização zero para receber valores dinamicamente
    private float totalDespesas = 0;

    // Simulação de valores de exemplo para a base de dados
    private float mockReceitasFromDB = 500;
    private float mockDespesasFromDB = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        barChart = findViewById(R.id.barChart);
        totalReceitaText = findViewById(R.id.totalReceitaText);
        totalDespesaText = findViewById(R.id.totalDespesaText);
        saldoText = findViewById(R.id.saldoText);
        addReceitaBtn = findViewById(R.id.addReceitaBtn);
        addDespesaBtn = findViewById(R.id.addDespesaBtn);

        setupBarChart();
        fetchDataFromDB();
        updateBarChart();
        updateTextViews();

        addReceitaBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AddTransactionActivity.class);
            startActivity(intent);
        });


        addDespesaBtn.setOnClickListener(v -> {
            totalDespesas += 150;
            updateBarChart();
            updateTextViews();
        });

        Button readTrxBtn = findViewById(R.id.ReadTrxBtn);
        readTrxBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ViewTransactionsActivity.class);
            startActivity(intent);
        });

    }

    private void setupBarChart() {
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setFitBars(true);
        barChart.setNoDataText("Adicione valores para ver o gráfico");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
    }

    private void fetchDataFromDB() {
        // Aqui você faria a chamada à base de dados para buscar os valores reais
        // Por enquanto, estamos usando valores mock
        totalReceitas = mockReceitasFromDB;
        totalDespesas = mockDespesasFromDB;
    }

    private void updateBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, totalReceitas));
        entries.add(new BarEntry(1, totalDespesas));

        float balance = totalReceitas - totalDespesas;

        BarDataSet dataSet = new BarDataSet(entries, "Balanço: " + balance);
        dataSet.setColors(new int[]{Color.GREEN, Color.RED});
        dataSet.setValueTextSize(14f);

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.5f);

        String[] labels = {"Receitas", "Despesas"};
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        barChart.setData(data);
        barChart.invalidate();
    }

    private void updateTextViews() {
        totalReceitaText.setText(String.format("Total Receita: %.2f", totalReceitas));
        totalDespesaText.setText(String.format("Total Despesa: %.2f", totalDespesas));
        saldoText.setText(String.format("Saldo Total: %.2f", totalReceitas - totalDespesas));
    }
}
