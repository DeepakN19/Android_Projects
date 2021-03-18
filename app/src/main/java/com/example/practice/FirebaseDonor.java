package com.example.practice;

public class FirebaseDonor {
    public String name;
    public String Area;
    public String Address;
    public String BloodGroup;
    public String Gender;
    public String HealthDetails;

    @Override
    public String toString() {
        return "Name : " + name + "\n" +
                "Gender : " + Gender + "\n" +
                "Blood Group : " + BloodGroup + "\n" +
                "Area : " + Area + "\n" +
                "HealthDetails : " + HealthDetails + "\n" +
                "Address : " + Address + "\n";
    }
}
