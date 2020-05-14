package com.example.cattyperi.Model;

public class Donation {
    private String id_donation;
    private String user;
    private String nominal;

    public Donation(String id_donation, String user, String nominal){
        this.id_donation = id_donation;
        this.user = user;
        this.nominal = nominal;
    }

    public String getIdDonation(){
        return this.id_donation;
    }

    public String getUser(){
        return this.user;
    }

    public String nominal(){
        return this.nominal;
    }
}
