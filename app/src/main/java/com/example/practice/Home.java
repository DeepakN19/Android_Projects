package com.example.practice;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {
    private CardView donorCard,bankCard,accountCard;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle extras=getIntent().getExtras();
        userId=extras.getString("userId");
        donorCard = (CardView) findViewById(R.id.donor_card);
        bankCard = (CardView) findViewById(R.id.bank_card);
        accountCard = (CardView) findViewById(R.id.account_card);
    }
    public void goToDonor(View view) {
        Intent i = new Intent(Home.this, Donor.class);
        startActivity(i);
    }
    public void goToBbank(View view) {
        Intent i = new Intent(Home.this, Bbank.class);
        startActivity(i);
    }
    public void goToAccount(View view) {
        Intent i = new Intent(Home.this, Account.class);
        i.putExtra("userId",userId);
        startActivity(i);
    }
}