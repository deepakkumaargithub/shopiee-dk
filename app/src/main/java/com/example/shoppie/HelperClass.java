package com.example.shoppie;

public class HelperClass {

    String email, phoneNo, password;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public HelperClass(String email, String phoneNo, String password) {
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public HelperClass() {
    }



}
