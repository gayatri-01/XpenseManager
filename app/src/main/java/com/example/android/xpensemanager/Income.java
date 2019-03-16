package com.example.android.xpensemanager;

public class Income {

    private  String Name;
    private String Amount;
    private int Photo;

    public Income() {
    }

    public Income(String name, String amount, int photo) {
        Name = name;
        Amount = amount;
        Photo = photo;
    }

    //getter

    public String getName() {
        return Name;
    }

    public String getAmount() {
        return Amount;
    }

    public int getPhoto() {
        return Photo;
    }

    //setter

    public void setName(String name) {
        Name = name;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
