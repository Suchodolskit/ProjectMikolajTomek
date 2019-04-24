package com.example.project_mikolaj_tomek;

import android.graphics.Bitmap;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class FirebaseStorageHelper {
    private StorageReference storageReference;

    public FirebaseStorageHelper() {
        this.storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void saveRecipeImage(final String recipeId, final Bitmap bitmap)
    {
        new Thread(new Runnable() {
            public void run() {
                StorageReference tmpref = storageReference.child("images/recipes/"+recipeId+".png");
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();
                ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
                tmpref.putStream(bs);
            }
        }).start();
    }
}
