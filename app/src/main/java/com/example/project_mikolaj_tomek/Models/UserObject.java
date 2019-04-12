package com.example.project_mikolaj_tomek.Models;

public class UserObject extends FirestoreObject {

    private String firstName;
    private String lastName;

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
