package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText unameField,emailField,passField,phoneField;
    Button signUpBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("Users");
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        signUpBtn = findViewById(R.id.registerbtn);
        emailField = findViewById(R.id.email);
        passField = findViewById(R.id.pwd);
        unameField = findViewById(R.id.uname);
        phoneField = findViewById(R.id.phone);
        
    }

    public void createUser(View view){
        final String email = emailField.getText().toString();
        final String password = passField.getText().toString();
        final String name = unameField.getText().toString();
        final String mobileNumber = phoneField.getText().toString();

        
        //VALIDATING FIELDS
        if(email.matches("") || password.matches("") || name.matches("") || mobileNumber.matches("")){
            Toast.makeText(MainActivity.this, "Enter all the fields.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(!name.matches("^[a-z]{2,}\\d*$"))
        {
            unameField.setError("Enter a valid Name");
            return;
        }
        if(!email.matches("^[a-zA-Z0-9\\.]+@[a-zA-Z0-9]+(\\.)?[a-zA-Z0-9]{2,6}?\\.[a-zA-Z]{2,6}$"))
        {
            emailField.setError("Enter a valid email");
            return;
        }
        if(password.length()<6)
        {
            passField.setError("Password should be minimum 6 characters");
            return;
        }
        if(!mobileNumber.matches("^([6-9][0-9]{9})$")||mobileNumber.length()!=10)
        {
            phoneField.setError("Enter a valid mobile number");
            return;
        }

        //CREATING USER
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            //STORING USERDETAILS TO DATABASE
                            usersRef.child(user.getUid()).child("name").setValue(name);
                            usersRef.child(user.getUid()).child("MobileNumber").setValue(mobileNumber);
                            usersRef.child(user.getUid()).child("Email").setValue(email);
                            Toast.makeText(MainActivity.this, "Created User Succesfully!", Toast.LENGTH_SHORT).show();

                            Intent homeIntent = new Intent(MainActivity.this, Home.class);
                            homeIntent.putExtra("userId", user.getUid());
                            startActivity(homeIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void goToLogin(View view) {
        Intent i = new Intent(MainActivity.this, Userlogin.class);
        startActivity(i);
    }
}