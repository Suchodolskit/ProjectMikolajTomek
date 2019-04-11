package com.example.project_mikolaj_tomek;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.project_mikolaj_tomek.Models.FirestoreObject;
import com.example.project_mikolaj_tomek.Models.Product;
import com.example.project_mikolaj_tomek.Models.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseFirestoreHelper {

    private String TAG = "FirebaseFirestoreHelper";

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseFirestore store;

    public FirebaseFirestoreHelper()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        store = FirebaseFirestore.getInstance();
    }

    public void SaveData(String collection, FirestoreObject object)
    {
        DocumentReference ref = store.collection(collection).document(object.getId());
        ref.set(object);
    }

    public List<Object> GetCollection(String collection)
    {
        final ArrayList<Object> Objects = new ArrayList<>();
        store.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Objects.add(document);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return Objects;
    }

    public UserObject AddUser(FirebaseUser user, String firstName, String lastName)
    {
        UserObject userObject = new UserObject(user.getUid(),firstName,lastName);
        SaveData("Users",userObject);
        return userObject;
    }
}
