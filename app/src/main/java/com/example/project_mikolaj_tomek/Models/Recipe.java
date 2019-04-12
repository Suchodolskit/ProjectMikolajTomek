package com.example.project_mikolaj_tomek.Models;

import android.media.Image;
import android.text.format.DateFormat;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Recipe extends FirestoreObject {

    private String title;
    private Date creationDate;
    private Integer preperationTime;
    private String summary;
    private String description;
    private List<Product> products;
    private Image image;

    public Recipe(String id, String title, Date creationDate, Integer preperationTime, String summary, String description, List<Product> products, Image image) {
        super(id);
        this.title = title;
        this.creationDate = creationDate;
        this.preperationTime = preperationTime;
        this.summary = summary;
        this.description = description;
        this.products = products;
        this.image = image;
    }
}
