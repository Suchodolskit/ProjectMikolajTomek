package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.project_mikolaj_tomek.Models.UserObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText mNewEmailText;
    private EditText mPasswordText;

    private FirebaseAuthHelper authHelper;
    private FirebaseFirestoreHelper databaseHesper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNewEmailText = findViewById(R.id.newEmailText);
        mPasswordText = findViewById(R.id.newPasswordText);

        authHelper = new FirebaseAuthHelper(this);
        databaseHesper = new FirebaseFirestoreHelper();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    public void SignUp(View view) {
        boolean sigedUp = authHelper.SignUpWithEmail(mNewEmailText.getText().toString(),mPasswordText.getText().toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(sigedUp)
        {
            FirebaseUser fuser = authHelper.GetUser();
        }
    }
}
