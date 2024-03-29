package com.example.project_mikolaj_tomek.Models;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Product extends FirestoreObject {

    private String name;
    private List<String> BarCodes;
    private Image icon;


    public Product() {}
    public Product(String id, String name, Image icon) {
        super(id);
        this.name = name;
        this.icon = icon;
        BarCodes = new ArrayList<>();
    }

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

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
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
