package com.example.practice;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  Userlogin extends AppCompatActivity {
    Button loginBtn,regBtn;
    EditText emailField, passwordField;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.button1);
        regBtn=findViewById(R.id.button2);
        emailField = findViewById(R.id.editText3);
        passwordField = findViewById(R.id.editText4);
    }

    public void loginUser(View view){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        if(email.matches("") || password.matches("")){
            Toast.makeText(Userlogin.this, "Enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(Userlogin.this, Home.class);
                            i.putExtra("userId", user.getUid());
                            startActivity(i);
                        } else {
                            Toast.makeText(Userlogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void goToRegister(View view) {
        Intent i = new Intent(Userlogin.this, MainActivity.class);
        startActivity(i);
    }
}