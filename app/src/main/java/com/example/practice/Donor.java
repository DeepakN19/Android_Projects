package com.example.practice;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Donor extends AppCompatActivity {
    DatabaseReference donorRef = FirebaseDatabase.getInstance().getReference();
    Spinner s1,s2;
    ListView ls;
    ArrayAdapter<FirebaseDonor> fdAddapter;
    ArrayList<FirebaseDonor> fd = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor);
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        ls = (ListView) findViewById( R.id.ls );
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Donor.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.blood));
        myAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        s1.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Donor.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.area));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        s2.setAdapter(myAdapter2);
    }
    public void searchDonor(View view){
        final String BloodGroup = s1.getSelectedItem().toString();
        final String Area = s2.getSelectedItem().toString();
        fd.clear();
        donorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot s = snapshot.child("Donors");
                for(DataSnapshot cs : s.getChildren()){
                    if(cs.child("Area").getValue().equals(Area) && cs.child("Blood Group").getValue().equals(BloodGroup)){
                        FirebaseDonor fdnr = new FirebaseDonor();
                        fdnr.name = cs.child("Donor Name").getValue().toString();
                        fdnr.Area = cs.child("Area").getValue().toString();
                        fdnr.Address = cs.child("Address").getValue().toString();
                        fdnr.HealthDetails = cs.child("Health details").getValue().toString();
                        fdnr.Gender = cs.child("Gender").getValue().toString();
                        fdnr.BloodGroup = cs.child("Blood Group").getValue().toString();
                        fd.add(fdnr);

                    }
                }
                fdAddapter = new ArrayAdapter<FirebaseDonor>(Donor.this, R.layout.simple_row, fd);
                ls.setAdapter(fdAddapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseDonor item = (FirebaseDonor) adapterView.getItemAtPosition(i);
                String addressMap = item.Address + "," + item.Area;
                String map = "http://maps.google.co.in/maps?q=" + addressMap;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(intent);
            }
        });
    }
}
