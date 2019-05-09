package com.example.project_mikolaj_tomek;

import android.content.Intent;
import android.graphics.Bitmap;

import java.util.GregorianCalendar;

import android.icu.util.Calendar;
import android.media.Image;
import android.net.Uri;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.project_mikolaj_tomek.Models.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;


public class AddRecipeActivity extends AppCompatActivity {
    public static final int GET_FROM_GALLERY = 1;
    public static final int PICK_USER_PROFILE_IMAGE = 2;

    private Bitmap imageBitmap = null;
    private ImageView imageView;
    private EditText title;
    private EditText summary;
    private EditText description;
    private EditText preparationTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        imageView = findViewById(R.id.new_recipe_image_preview_imageview);
        title = findViewById(R.id.new_recipe_title);
        summary = findViewById(R.id.new_recipe_summary);
        description = findViewById(R.id.new_recipe_description);
        preparationTime = findViewById(R.id.new_recipe_preparation_time);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GET_FROM_GALLERY && resultCode == this.RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        imageView.setImageBitmap(imageBitmap);
    }


    public void addImageFromGallery(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    public void addImageFromCamera(View view) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(cameraIntent, PICK_USER_PROFILE_IMAGE);
        }
    }

    public void CompleteAddRecipe(View view) {

        FirebaseAuthHelper firebaseAuthHelper = new FirebaseAuthHelper(this);

        Recipe recipe = new Recipe(title.getText().toString(),
                title.getText().toString(),
                Calendar.getInstance().getTime(),
                Integer.parseInt(preparationTime.getText().toString()),
                summary.getText().toString(),
                description.getText().toString(),
                ChoseProductsActivity.retProducts,
                null,
                firebaseAuthHelper.GetUser().getUid());
        FirebaseStorageHelper firebaseStorageHelper = new FirebaseStorageHelper();
        FirebaseFirestoreHelper firebaseFirestoreHelper = new FirebaseFirestoreHelper(this);
        firebaseFirestoreHelper.SaveData("recipes", recipe);
        if(imageBitmap!=null) {
            firebaseStorageHelper.saveRecipeImage(title.getText().toString(), imageBitmap);
        }
        this.finish();
    }

    public void addProducts(View view) {
        Intent intent = new Intent(this, ChoseProductsActivity.class);
        startActivity(intent);
    }
}
