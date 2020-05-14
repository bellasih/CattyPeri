package com.example.cattyperi.Model;

public class Adoption {
    private String id_cat;
    private String name_cat;
    private String loc_found;
    private String img_cat;

    public Adoption(String id_cat, String name_cat, String loc_found, String img_cat){
        this.id_cat = id_cat;
        this.name_cat = name_cat;
        this.loc_found = loc_found;
        this.img_cat = img_cat;
    }

    public String getIdCat(){
        return this.id_cat;
    }

    public String getNameCat(){
        return this.name_cat;
    }

    public String getLocFound(){
        return this.loc_found;
    }

    public String getImgCat(){
        return this.img_cat;
    }
}
