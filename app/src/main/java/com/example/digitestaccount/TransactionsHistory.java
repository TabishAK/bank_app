package com.example.digitestaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransactionsHistory extends AppCompatActivity {

    ArrayList<String> arrayList1,arrayList2;
    ArrayAdapter arrayAdapter1,arrayAdapter2;
    ListView listView1,listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_history);

        listView1 = (ListView)findViewById(R.id.list1);
        arrayList1 = new ArrayList<String>();
        arrayAdapter1 = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,arrayList1);
        listView1.setAdapter(arrayAdapter1);

        listView2 = (ListView)findViewById(R.id.list2);
        arrayList2 = new ArrayList<String>();
        arrayAdapter2 = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,arrayList2);
        listView2.setAdapter(arrayAdapter2);


        MyHelper helper = new MyHelper(TransactionsHistory.this);
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT DESCRIPTION,CURRENTDATE FROM CREDENTIALS",null);

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                String desc=cursor.getString(0);
                String date=cursor.getString(1);
                arrayList1.add(desc);
                arrayList2.add(date);
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(TransactionsHistory.this, "No Record Of Expenses And Incomes", Toast.LENGTH_LONG).show();
        }

    }
}
