package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthHelper {
    private static final String TAG = "FirebaseAuthHelper";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AppCompatActivity activity;

    //public FirebaseAuthHelper(FirebaseAuth mAuth, FirebaseAuth.AuthStateListener mAuthListener, final AppCompatActivity activity) {
    public FirebaseAuthHelper(final AppCompatActivity activity) {
        this.mAuth = FirebaseAuth.getInstance();
        this.mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                    activity.startActivity(new Intent(activity, MainActivity.class));
            }
        };
        //this.mAuth = mAuth;
        //this.mAuthListener = mAuthListener;
        this.activity = activity;
    }


    private boolean returnSignInWithEmailResult;
    public boolean SignInWithEmail(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            returnSignInWithEmailResult = false;
                        }
                        else{
                            returnSignInWithEmailResult = true;
                        }
                    }
                });
        return returnSignInWithEmailResult;
    }

    private boolean returnSingUpWithEmailResult;
    public boolean SignUpWithEmail(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.w(TAG, "creation successful");
                            returnSingUpWithEmailResult = true;

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            returnSingUpWithEmailResult = false;
                        }
                    }

                    // ...
                });
        return returnSingUpWithEmailResult;
    }

    public void SignOut() {
        mAuth.signOut();
    }

    public FirebaseUser GetUser()
    {
        return mAuth.getCurrentUser();
    }
}
