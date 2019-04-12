package com.example.project_mikolaj_tomek.Models;

import android.media.Image;

public class UserObject extends FirestoreObject {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mail;
    private Image userPhoto;

    public UserObject(String id, String firstName, String lastName, String phoneNumber, String mail, Image userPhoto) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.userPhoto = userPhoto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Image getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(Image userPhoto) {
        this.userPhoto = userPhoto;
    }
}
