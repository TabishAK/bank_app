package com.example.digitestaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RecordExpenseAndIncome extends AppCompatActivity {

    TextView text1;
    Button button1;
    EditText editText1;
    String userID, amount = "00", type, label;
    int currentAmount,sum=0;
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        setContentView(R.layout.activity_record_expense_and_income);
        text1 = (TextView) findViewById(R.id.text1);
        button1 = (Button) findViewById(R.id.amountSubmitButton);
        editText1 = (EditText) findViewById(R.id.enterAmount);
        Intent intent = getIntent();
        label = intent.getStringExtra("label");
        type = intent.getStringExtra("type");
        if(type.equals("Income")){
            text1.setText(label + " Income");
        }
        else {
            text1.setText(label + " Expense");
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = editText1.getText().toString();
                int cost = Integer.parseInt(amount);
                System.out.println("submitted" + cost);

                if(type.equals("Income")){
                    label =cost + " Rs For "+label+" Income";
                }
                else {
                    label =cost + " Rs For "+label+" Expense";
                }
                String pattern = "MM/dd/yyyy";
                DateFormat df = new SimpleDateFormat(pattern);
                Date today = Calendar.getInstance().getTime();
                String todayAsString = df.format(today);

                MyHelper helper = new MyHelper(RecordExpenseAndIncome.this);
                SQLiteDatabase database = helper.getWritableDatabase();
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
                if(type.equals("Expense") && cost>sum)
                {
                    Toast.makeText(RecordExpenseAndIncome.this, "Sorry You dont have cash to record this expense!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecordExpenseAndIncome.this, Dashboard.class);
                    startActivity(intent);

                }
                else{
                    helper.insertData(cost,type, label, todayAsString, database);
                    Intent intent = new Intent(RecordExpenseAndIncome.this, Dashboard.class);
                    startActivity(intent);
                }

            }
        });
    }
}
