package com.example.project_mikolaj_tomek.Models;

import android.media.Image;

import java.util.List;

public class Recipe extends FirestoreObject {
    private String title;
    private String content;
    private List<Product> products;
    private Image image;


    public Recipe(String id, String title, String content, List<Product> products, Image image) {
        super(id);
        this.title = title;
        this.content = content;
        this.products = products;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Image getImage() {
        return image;
    }
}
