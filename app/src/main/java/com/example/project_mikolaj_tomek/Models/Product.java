package com.example.project_mikolaj_tomek.Models;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Product extends FirestoreObject {

    private String name;
    private List<String> BarCodes;
    private ProductCategory category;
    private int thumbnail;



    public Product(String id, String name, ProductCategory category, int thumbnail) {
        super(id);
        this.name = name;
        this.category = category;
        this.thumbnail = thumbnail;
        BarCodes = new ArrayList<>();
    }

    public int getThumbnail() { return thumbnail; }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBarCodes() {
        return BarCodes;
    }

    public void setBarCodes(List<String> barCodes) {
        BarCodes = barCodes;
    }

    public boolean addBarcode(String code)
    {
        if(!BarCodes.contains(code)) {
            BarCodes.add(code);
            return true;
        }
        return false;
    }
}
