package com.example.project_mikolaj_tomek.Models;

import android.media.Image;

public class UserObject extends FirestoreObject {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Image userPhoto;

    public UserObject(String pid, String firstName, String lastName) {
        super(pid);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
