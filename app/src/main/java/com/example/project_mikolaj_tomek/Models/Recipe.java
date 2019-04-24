package com.example.project_mikolaj_tomek.Models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

import java.util.Date;
import java.util.List;

public class Recipe extends FirestoreObject {

    private String title;
    private Date creationDate;
    private Integer preparationTime;
    private String summary;
    private String description;
    private List<Product> products;
    private Drawable image;
    private String author;

    public Recipe(String id, String title, Date creationDate, Integer preparationTime, String summary, String description, List<Product> products, Drawable image, String author) {
        super(id);
        this.title = title;
        this.creationDate = creationDate;
        this.preparationTime = preparationTime;
        this.summary = summary;
        this.description = description;
        this.products = products;
        this.image = image;
        this.author = author;
    }
    public Drawable getImage() {
        return image;
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

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
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


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
