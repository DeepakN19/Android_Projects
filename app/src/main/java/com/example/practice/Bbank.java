package com.example.practice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bbank extends AppCompatActivity {
    ArrayAdapter<BloodBank> adapter;
    Spinner s1;
    ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbank);
        s1=(Spinner)findViewById(R.id.spinner2);
        ls = (ListView) findViewById( R.id.bloodBanks );

        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(Bbank.this,
                android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.area));
        myAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        s1.setAdapter(myAdapter);
    }
    private List<BloodBank> hospitals = new ArrayList<>();
    private void readData(String city){
        hospitals.clear();
        InputStream is = getResources().openRawResource(R.raw.bloodbanks);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";

        try {
            reader.readLine();
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                BloodBank bloodBank = new BloodBank();

                if(row.length >= 27) {
                    String givenCity = row[3];
                    if (givenCity.equalsIgnoreCase(city)) {
                        if (row[1].length() > 0) {
                            bloodBank.name = row[1];
                        } else {
                            bloodBank.name = "Not Available";
                        }
                        if (row[1].length() > 0) {
                            bloodBank.name = row[1];
                        } else {
                            bloodBank.name = " Not Available";
                        }
                        if (row[8].length() > 0) {
                            bloodBank.mobile = row[8];
                        } else {
                            bloodBank.mobile = "Not Available ";
                        }
                        if (row[7].length() > 0) {
                            bloodBank.contact = row[7];
                        } else {
                            bloodBank.contact = "Not Available";
                        }
                        if (row[18].length() > 0) {
                            bloodBank.category = row[18];
                        } else {
                            bloodBank.category = "Not Available";
                        }
                        if (row[18].length() > 0) {
                            bloodBank.category = row[18];
                        } else {
                            bloodBank.category = "Not Available";
                        }
                        if (row[11].length() > 0) {
                            bloodBank.email = row[11];
                        } else {
                            bloodBank.email = "Not Available";
                        }
                        if (row[12].length() > 0) {
                            bloodBank.website = row[12];
                        } else {
                            bloodBank.website = "Not Available";
                        }
                        if (row[4].length() > 0) {
                            bloodBank.city = row[4];
                        } else {
                            bloodBank.city = "Not Available";
                        }
                        if (row[3].length() > 0) {
                            bloodBank.district = row[3];
                        } else {
                            bloodBank.district = "Not Available";
                        }
                        if (row[2].length() > 0) {
                            bloodBank.state = row[2];
                        } else {
                            bloodBank.state = "Not Available";
                        }
                        if (row[8].length() > 0) {
                            bloodBank.address = row[5];
                        } else {
                            bloodBank.address = "";
                        }
                        if (row[19].length() > 0) {
                            bloodBank.bloodComponentAvailable = row[19];
                        } else {
                            bloodBank.bloodComponentAvailable = "Not Available";
                        }
                        hospitals.add(bloodBank);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<BloodBank>(Bbank.this, R.layout.simple_row,hospitals);
        ls.setAdapter(adapter);
    }

    public void searchBloodBanks(View view){
        String city = s1.getSelectedItem().toString();
        readData(city);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BloodBank item = (BloodBank) adapterView.getItemAtPosition(i);
                String addressMap = item.name + ","+ item.address + ","+ item.city + item.district + item.state;
                String map = "http://maps.google.co.in/maps?q=" + addressMap;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(intent);
            }
        });
    }
}
