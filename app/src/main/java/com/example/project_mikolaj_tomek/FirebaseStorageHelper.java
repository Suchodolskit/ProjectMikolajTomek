package com.example.project_mikolaj_tomek;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;


import com.example.project_mikolaj_tomek.Models.Recipe;
import com.google.android.gms.tasks.OnSuccessListener;
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

    public void SetImage(final Recipe r, final Context context)
    {
        StorageReference ref = storageReference.child("images/recipes/"+r.getId()+".png");
        int TWENTY_MEGABYTES = 1024*1024*20;
        ref.getBytes(TWENTY_MEGABYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                r.setImage(bmp);
            }
        });
    }
}
