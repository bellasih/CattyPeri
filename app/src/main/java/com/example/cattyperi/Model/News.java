package com.example.cattyperi.Model;

public class News {
    private String id_news;
    private String title;
    private String content;
    private String date;

    public News(String id_news, String title, String content, String date){
        this.id_news    = id_news;
        this.title      = title;
        this.content    = content;
        this.date       = date;
    }

    public String getIdNews(){
        return this.id_news;
    }

    public String getTitles(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public String getDate(){
        return this.date;
    }
}
