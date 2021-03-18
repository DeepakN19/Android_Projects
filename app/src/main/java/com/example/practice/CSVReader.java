package com.example.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    InputStream inputStream;

    public CSVReader (InputStream is)
    {
        this.inputStream = is;
    }

    public List<String[]> read(String City) {
        List resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String csvLine = "";

        try {
            while((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");

                String s1=row[4].trim().toString();

                if(s1.equalsIgnoreCase(City))
                {
                    resultList.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
