package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText mNewEmailText;
    private EditText mPasswordText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuthHelper authHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNewEmailText = findViewById(R.id.newEmailText);
        mPasswordText = findViewById(R.id.newPasswordText);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                    startActivity(new Intent(SignUpActivity.this, SelectItemsActivity.class));
            }
        };
        authHelper = new FirebaseAuthHelper(mAuth,mAuthListener,this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void SignUp(View view) {
        authHelper.SignUpWithEmail(mNewEmailText.getText().toString(),mPasswordText.getText().toString());
    }
}
