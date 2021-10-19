package com.example.digitestaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Expenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
    }

    public void recordExpense(View v)
    {
        switch(v.getId())
        {
            case R.id.education:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Education");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.household:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Household");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.entertainment:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Entertainment");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.fuel:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Fuel");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.shopping:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Shopping");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.rental:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Rental");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.medical:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Medical");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.utility:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Utility");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            case R.id.otherExpenses:
            {
                Intent intent = new Intent(Expenses.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Other");
                intent.putExtra("type","Expense");
                startActivity(intent);
                break;
            }
            default:
                break;
        }

    }
}
