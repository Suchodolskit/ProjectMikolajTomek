package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText mEmailText;
    private EditText mPasswordText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuthHelper authHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailText = findViewById(R.id.emailText);
        mPasswordText = findViewById(R.id.passwordText);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    Toast.makeText(MainActivity.this,"Successfully signed in. ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, SelectItemsActivity.class));
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

    private void startLogIn() {
        String login = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"You cannot sign in with this email and password",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    public void SingIn(View view) {
        authHelper.SignInWithEmail(mEmailText.getText().toString(),mPasswordText.getText().toString());
    }

    public void SignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
