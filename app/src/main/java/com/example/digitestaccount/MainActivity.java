package com.example.digitestaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    String userEmail,userPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View v)
    {
        userEmail = e1.getText().toString();
        userPassword = e2.getText().toString();
        if(TextUtils.isEmpty(userEmail) && TextUtils.isEmpty(userPassword))
        {
            e1.setError("Email required");
            e2.setError("Password required");
        }
        else if(TextUtils.isEmpty(userEmail))
        {
            e1.setError("Email required");
        }
        else if(TextUtils.isEmpty(userPassword))
        {
            e2.setError("Password required");
        }
        else
        {
            System.out.println(userEmail);
            Intent intent = new Intent(MainActivity.this,Dashboard.class);
            startActivity(intent);
            mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                  .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "SignIn Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,Dashboard.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                        }
                       }
                    });
        }

    }

    public void routeToSignUp(View v)
    {
        Intent intent = new Intent(MainActivity.this,signUp.class);
        startActivity(intent);
    }

}
