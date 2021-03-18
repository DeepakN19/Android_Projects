package com.example.practice;

public class BloodBank {
    String name;
    String contact;
    String mobile;
    String email;
    String website;
    String category;
    String address;
    String city;
    String district;
    String state;
    String bloodComponentAvailable;

    @Override
    public String toString() {
        return
                "Name : " + name + '\n' +
                        "Contact : " + contact + '\n' +
                        "Mobile : " + mobile + '\n' +
                        "Email : " + email + '\n' +
                        "Website : " + website + '\n' +
                        "Category : " + category + '\n' +
                        "Address : " + address + '\n' +
                        "City : " + city + '\n' +
                        "District : " + district + '\n' +
                        "BloodComponentAvailable : " + bloodComponentAvailable + '\n';
    }
}
