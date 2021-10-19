package com.example.digitestaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    Button b1;
    TextView t1;
    int currentAmount,sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        b1= (Button)findViewById(R.id.history);
        t1= (TextView)findViewById(R.id.currentAmount);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,TransactionsHistory.class);
                startActivity(intent);
            }
        });

        MyHelper helper = new MyHelper(Dashboard.this);
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT AMOUNT,TYPE FROM CREDENTIALS",null);


        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                currentAmount=cursor.getInt(0);
                String currentType = cursor.getString(1);
                if(currentType.equals("Income")){
                    sum=sum+currentAmount;
                }
                else if(currentType.equals("Expense")){
                    sum=sum-currentAmount;
                }
            }while (cursor.moveToNext());
        }
        t1.setText(sum+" Rs");

    }

    public void routeToRecordIncome(View v)
    {
        Intent intent = new Intent(Dashboard.this,Incomes.class);
        startActivity(intent);
    }

    public void routeToRecordExpense(View v)
    {
        Intent intent = new Intent(Dashboard.this,Expenses.class);
        startActivity(intent);
    }

    public void routeToMap(View v)
    {
        Intent intent = new Intent(Dashboard.this,MapsActivity.class);
        startActivity(intent);
    }

}
