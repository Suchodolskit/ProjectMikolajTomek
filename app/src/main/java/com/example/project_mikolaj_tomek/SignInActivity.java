
package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    private EditText mEmailText;
    private EditText mPasswordText;

    private FirebaseAuthHelper authHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmailText = findViewById(R.id.emailText);
        mPasswordText = findViewById(R.id.passwordText);
        authHelper = new FirebaseAuthHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void SingIn(View view) {
        authHelper.SignInWithEmail(mEmailText.getText().toString(), mPasswordText.getText().toString());
    }

    public void SignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}

