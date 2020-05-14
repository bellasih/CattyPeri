package com.example.cattyperi.Model;

public class History{
    private String id_history;
    private String user;
    private String msg;
    private String date;

    public History(String id_history, String user, String msg, String date){
        this.id_history = id_history;
        this.user       = user;
        this.msg        = msg;
        this.date       = date;
    }

    public String getIdHistory(){
        return this.id_history;
    }

    public String getUser(){
        return this.user;
    }

    public String getMsg(){
        return this.msg;
    }

    public String getDate(){
        return this.date;
    }
}
