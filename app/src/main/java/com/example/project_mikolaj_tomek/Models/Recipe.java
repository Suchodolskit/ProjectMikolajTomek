package com.example.project_mikolaj_tomek.Models;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;
import java.util.List;

public class Recipe extends FirestoreObject {

    private String title;
    private Date creationDate;
    private long preparationTime;
    private String summary;
    private String description;
    private List<Product> products;
    private Bitmap image;
    private String author;

    public Recipe(String id, String title, Date creationDate, long preparationTime, String summary, String description, List<Product> products, Bitmap image, String author) {
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

    public long getPreparationTime() {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
