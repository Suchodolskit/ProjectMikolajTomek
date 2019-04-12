package com.example.project_mikolaj_tomek.Models;

public class FirestoreObject {
    protected String id;

    public FirestoreObject(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
