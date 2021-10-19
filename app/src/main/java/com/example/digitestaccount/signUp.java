package com.example.digitestaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity {
    DatePickerDialog picker;
    ImageView img;
    EditText eUserName,ePassword,eEmail,eBirthday,eConfirmPassword;
    String userID,userName,password,email,birthday,confirmPassword;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        eUserName = (EditText)findViewById(R.id.user);
        eEmail = (EditText)findViewById(R.id.email);
        eBirthday = (EditText)findViewById(R.id.birthday);
        ePassword = (EditText)findViewById(R.id.password);
        eConfirmPassword = (EditText)findViewById(R.id.confirmPassword);
        img = (ImageView)findViewById(R.id.userImage);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        eBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(signUp.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }



    public void upload(View v)
    {
        System.out.println("sajjad");
        userName = eUserName.getText().toString();
        email = eEmail.getText().toString();
        birthday = eBirthday.getText().toString();
        password = ePassword.getText().toString();

        if(!TextUtils.isEmpty(userName) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(birthday) || !TextUtils.isEmpty(password))
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                userID =  mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("Users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("USER NAME", userName);
                                user.put("EMAIL", email);
                                user.put("BIRTHDAY", birthday);
                                user.put("PASSWORD", password);
                                fstore.collection("users")
                                        .add(user)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(signUp.this, "User Registered", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(signUp.this,MainActivity.class);
                                                startActivity(intent);
                                                Log.d("user","DocumentSnapshot added with ID: " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(signUp.this, "Error Occured ! Tryagain later", Toast.LENGTH_SHORT).show();
                                           //     Log.w(TAG, "Error adding document", e);
                                            }
                                        });
                            }
                            else
                            {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(signUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }
        else
        {
            Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT).show();
        }



    }

}
