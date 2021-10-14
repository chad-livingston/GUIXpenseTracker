package com.example.guipersonalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickExpenses(View view){
        Intent expenses = new Intent(this, Expenses.class);
        startActivity(expenses);
    }

    public void onClickBudget(View view){
        Intent budget = new Intent(this, Budget.class);
        startActivity(budget);
    }
    public void onClickBank(View view){
        Intent bank = new Intent(this, Bank.class);
        startActivity(bank);
    }
    public void onClickInvestments(View view){
        Intent investments = new Intent(this, Investments.class);
        startActivity(investments);
    }
    public void onClickExitApp(View view){
        finish();
        System.exit(0);
    }

}