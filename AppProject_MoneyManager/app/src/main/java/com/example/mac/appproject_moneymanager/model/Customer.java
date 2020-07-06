package com.example.mac.appproject_moneymanager.model;

public class Customer {
    private int mID;
    private String Username;
    private String Password;
    private String Repassword;


    public Customer() {
    }
    public Customer(int mID, String username, String password, String repassword) {
        this.mID = mID;
        Username = username;
        Password = password;
        Repassword = repassword;
    }

    public Customer(String username, String password, String repassword) {
        Username = username;
        Password = password;
        Repassword = repassword;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRepassword() {
        return Repassword;
    }

    public void setRepassword(String repassword) {
        Repassword = repassword;
    }
}
