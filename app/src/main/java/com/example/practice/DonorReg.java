package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorReg extends AppCompatActivity {

    Spinner bloodSpin,areaSpin,genderSpin;
    EditText address,health;
    private String userMobile, userName;
    String selectedBlood,selectedGender,selectedArea,healthDetails,addressDetails;
    DatabaseReference donorRef = FirebaseDatabase.getInstance().getReference().child("Donors");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_reg);
        bloodSpin=(Spinner)findViewById(R.id.spin1);
        areaSpin=(Spinner)findViewById(R.id.spin3);
        genderSpin=(Spinner)findViewById(R.id.spin2);
        health=(EditText)findViewById(R.id.medicalText);
        address=(EditText)findViewById(R.id.addressText);

        Bundle extras=getIntent().getExtras();
        userMobile = extras.getString("userMobile");
        userName = extras.getString("userName");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DonorReg.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.blood));
        myAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        bloodSpin.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(DonorReg.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.gender));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_list_item_1);
        genderSpin.setAdapter(myAdapter1);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(DonorReg.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.area));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        areaSpin.setAdapter(myAdapter2);
    }
    public void donorRegister(View view){
        if(address.getText().toString().trim().isEmpty()||health.getText().toString().trim().isEmpty())
        {
            Toast.makeText(DonorReg.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
            return;
        }
        selectedBlood =  bloodSpin.getSelectedItem().toString();
        selectedGender =  genderSpin.getSelectedItem().toString();
        selectedArea =  areaSpin.getSelectedItem().toString();
        healthDetails = health.getText().toString();
        addressDetails = address.getText().toString();

        donorRef.child(userMobile).child("Donor Name").setValue(userName);
        donorRef.child(userMobile).child("Gender").setValue(selectedGender);
        donorRef.child(userMobile).child("Blood Group").setValue(selectedBlood);
        donorRef.child(userMobile).child("Area").setValue(selectedArea);
        donorRef.child(userMobile).child("Health details").setValue(healthDetails);
        donorRef.child(userMobile).child("Address").setValue(addressDetails);
        Toast.makeText(DonorReg.this, "SuccessFully Registered", Toast.LENGTH_SHORT).show();
    }
}