package com.example.digitestaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Incomes extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);
    }

    public void recordIncome(View v)
    {
        switch(v.getId())
        {
            case R.id.salary:
            {
                Intent intent = new Intent(Incomes.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Salary");
                intent.putExtra("type","Income");
                startActivity(intent);
                break;
            }
            case R.id.business:
            {
                Intent intent = new Intent(Incomes.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Business");
                intent.putExtra("type","Income");
                startActivity(intent);
                break;
            }
            case R.id.property:
            {
                Intent intent = new Intent(Incomes.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Property");
                intent.putExtra("type","Income");
                startActivity(intent);
                break;
            }
            case R.id.otherIncomes:
            {
                Intent intent = new Intent(Incomes.this,RecordExpenseAndIncome.class);
                intent.putExtra("label","Other");
                intent.putExtra("type","Income");
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }
}
