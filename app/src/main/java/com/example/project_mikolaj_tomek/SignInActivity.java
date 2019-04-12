package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmailText;
    private EditText mPasswordText;
    private TextInputLayout mEmailInput;
    private  TextInputLayout mPasswordInput;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuthHelper authHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmailInput = findViewById(R.id.text_input_email);
        mPasswordInput = findViewById(R.id.text_input_password);

        mEmailText = findViewById(R.id.emailText);
        mEmailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    ValidateEmail(mEmailText.getText().toString());
            }
        });
        mEmailInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        mEmailText.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }
                return false;
            }
        });
        mPasswordText = findViewById(R.id.passwordText);
        mPasswordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    ValidatePassword(mPasswordText.getText().toString());
            }
        });
        mPasswordInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        mPasswordText.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    //Toast.makeText(SignInActivity.this,"Successfully signed in. ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                }
            }
        };
        authHelper = new FirebaseAuthHelper(mAuth,mAuthListener,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void SingIn(View view) {
        if(ValidateEmail(mEmailText.getText().toString()) && ValidatePassword(mPasswordText.getText().toString()))
            authHelper.SignInWithEmail(mEmailText.getText().toString(),mPasswordText.getText().toString());
    }

    public void SignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
    private boolean ValidateEmail(String email)
    {
        if(email == null || email.equals("")) {
            mEmailInput.setError(getString(R.string.emptyEmail));
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailInput.setError(getString(R.string.wrongEmail));
            return false;
        }
        else
            return true;
    }
    private boolean ValidatePassword(String password)
    {
        if(password == null || password.equals(""))
        {
            mPasswordInput.setError(getString(R.string.emptyPassword));
            return false;
        }
        else if(password.length() < 8)
        {

            mPasswordInput.setError(getString(R.string.wrongPassword));
            return false;
        }
        else
            return true;
    }
}

