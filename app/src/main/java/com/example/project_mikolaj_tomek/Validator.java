package com.example.project_mikolaj_tomek;

import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

public class Validator{
    
    public static boolean ValidatePassword(String password, TextInputLayout textInputLayout) {
        if(password == null || password.equals("")) {
            textInputLayout.setError("Password field can't be empty.");
            return false;
        }
        textInputLayout.setError(null);
        return true;
    }

    public static boolean ValidateEmail(String email, TextInputLayout textInputLayout) {
        if(email == null || email.equals("")) {
            textInputLayout.setError("Email field can't be empty.");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputLayout.setError("Invalid email format.");
            return false;
        }
        textInputLayout.setError(null);
        return true;
    }

    public static boolean ValidateName(String name, TextInputLayout textInputLayout) {
        if(name == null || name.equals(""))
        {
            textInputLayout.setError("Name field can't be empty.");
            return false;
        }
        else if(name.trim().contains(" ")) {
            textInputLayout.setError("Name can't contain any blank spaces.");
            return false;
        }
        textInputLayout.setError(null);
        return true;
    }

    public static boolean ValidateRepeatedPassword(String password, String repeatedPassword, TextInputLayout textInputLayout) {
        if(repeatedPassword == null || repeatedPassword.equals("")) {
            textInputLayout.setError("Repeat password field can't be empty.");
            return false;
        }
        else if(!repeatedPassword.equals(password))
        {
            textInputLayout.setError("Passwords must be identical.");
            return false;
        }
        textInputLayout.setError(null);
        return true;
    }
}
