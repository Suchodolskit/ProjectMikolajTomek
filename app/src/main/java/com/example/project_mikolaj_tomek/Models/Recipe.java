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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getPreperationTime() {
        return preperationTime;
    }

    public void setPreperationTime(Integer preperationTime) {
        this.preperationTime = preperationTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
