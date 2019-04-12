package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.project_mikolaj_tomek.Models.UserObject;
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

    public FirebaseAuthHelper(final AppCompatActivity activity) {
        this.mAuth = FirebaseAuth.getInstance();
        this.mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                    activity.startActivity(new Intent(activity, MainActivity.class));
            }
        };
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

    private UserObject returnUser;
    public UserObject SignUpWithEmail(final String email, String password, final String firsName, final String lastName, final String phoneNumber){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.w(TAG, "creation successful");
                            FirebaseFirestoreHelper helper = new FirebaseFirestoreHelper();
                            returnUser = new UserObject(user.getUid(),firsName,lastName,phoneNumber,email,null);
                            helper.AddUser(returnUser);
                            activity.finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            returnUser = null;
                            //TODO: handle message from firebase and inform user
                        }
                    }
                });
        return returnUser;
    }

    public void SignOut() {
        mAuth.signOut();
    }

    public FirebaseUser GetUser()
    {
        return mAuth.getCurrentUser();
    }
}
