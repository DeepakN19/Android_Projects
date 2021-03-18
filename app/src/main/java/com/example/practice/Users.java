package com.example.practice;


public class Users {
    private String uname;
    private String email;
    private long phone;
    private String pass;

    public Users() {
        this.uname=uname;
        this.email=email;
        this.phone=phone;
        this.pass=pass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
