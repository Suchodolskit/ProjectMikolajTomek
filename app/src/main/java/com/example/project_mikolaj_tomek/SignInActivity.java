
package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    private EditText mEmailText;
    private EditText mPasswordText;
    private TextInputLayout mEmailInput;
    private TextInputLayout mPasswordInput;

    private FirebaseAuthHelper authHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmailText = findViewById(R.id.emailText);
        mPasswordText = findViewById(R.id.passwordText);
        mEmailInput = findViewById(R.id.text_input_email);
        mPasswordInput = findViewById(R.id.text_input_password);

        mEmailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    Validator.ValidateEmail(mEmailText.getText().toString(), mEmailInput);
            }
        });

        mPasswordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    Validator.ValidatePassword(mPasswordText.getText().toString(), mPasswordInput);
            }
        });
        mPasswordText.setOnKeyListener(new View.OnKeyListener() {
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

        authHelper = new FirebaseAuthHelper(this);

        if(authHelper.GetUser()!=null) finish();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(authHelper.GetUser()!=null) finish();
    }

    public void SingIn(View view) {
        if(Validator.ValidatePassword(mPasswordText.getText().toString(), mPasswordInput) &&
                Validator.ValidateEmail(mEmailText.getText().toString(), mEmailInput))
        authHelper.SignInWithEmail(mEmailText.getText().toString(), mPasswordText.getText().toString());
    }

    public void SignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

}

