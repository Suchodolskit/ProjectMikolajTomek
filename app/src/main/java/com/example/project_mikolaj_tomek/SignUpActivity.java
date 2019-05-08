package com.example.project_mikolaj_tomek;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout mFirstNameInput;
    private TextInputLayout mLastNameInput;
    private TextInputLayout mEmailInput;
    private TextInputLayout mPhoneNumberInput;
    private TextInputLayout mPasswordInput;
    private TextInputLayout mRepeatPasswordInput;
    private EditText mFirstNameText;
    private EditText mLastNameText;
    private EditText mEmailText;
    private EditText mPhoneNumberText;
    private EditText mPasswordText;
    private EditText mRepeatPasswordText;

    private FirebaseAuthHelper authHelper;
    private FirebaseFirestoreHelper databaseHesper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirstNameInput = findViewById(R.id.first_name_input);
        mLastNameInput = findViewById(R.id.last_name_input);
        mEmailInput = findViewById(R.id.email_input);
        mPasswordInput = findViewById(R.id.password_input);
        mRepeatPasswordInput = findViewById(R.id.repeat_password_input);

        mFirstNameText = findViewById(R.id.new_user_first_name);
        mFirstNameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    Validator.ValidateName(mFirstNameText.getText().toString(), mFirstNameInput);
            }
        });
        mLastNameText = findViewById(R.id.new_user_last_name);
        mLastNameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    Validator.ValidateName(mLastNameText.getText().toString(), mLastNameInput);
            }
        });
        mEmailText = findViewById(R.id.new_user_email);
        mEmailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    Validator.ValidateEmail(mEmailText.getText().toString(), mEmailInput);
            }
        });
        mPasswordText = findViewById(R.id.new_user_password);
        mPasswordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    Validator.ValidatePassword(mPasswordText.getText().toString(), mPasswordInput);
            }
        });
        mRepeatPasswordText = findViewById(R.id.new_user_repeat_password);
        mRepeatPasswordText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        mRepeatPasswordText.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }
                return false;
            }
        });
        mRepeatPasswordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    Validator.ValidateRepeatedPassword(mPasswordText.getText().toString(),
                            mRepeatPasswordText.getText().toString(), mRepeatPasswordInput);
            }
        });

        mPhoneNumberInput = findViewById(R.id.phone_number_input);
        mPhoneNumberText = findViewById(R.id.new_user_phone_number);


        authHelper = new FirebaseAuthHelper(this);
        databaseHesper = new FirebaseFirestoreHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    public void SignUp(View view) {
        if( Validator.ValidateName(mFirstNameText.getText().toString(), mFirstNameInput) &&
                Validator.ValidateName(mLastNameText.getText().toString(), mLastNameInput) &&
                Validator.ValidateEmail(mEmailText.getText().toString(), mEmailInput) &&
                Validator.ValidatePassword(mPasswordText.getText().toString(), mPasswordInput) &&
                Validator.ValidateRepeatedPassword(mPasswordText.getText().toString(),
                mRepeatPasswordText.getText().toString(), mRepeatPasswordInput))
        {
            authHelper.SignUpWithEmail(mEmailText.getText().toString(), mPasswordText.getText().toString(),
                    mFirstNameText.getText().toString(), mLastNameText.getText().toString(),
                    mPhoneNumberText.getText().toString());
        }
    }
}
