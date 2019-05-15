package com.example.project_mikolaj_tomek;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_mikolaj_tomek.Models.Product;
import com.example.project_mikolaj_tomek.Models.Recipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static int REQUEST_CODE_SEARCH_PRODUCTS = 1;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuthHelper authHelper;
    private List list;
    private List recipes;
    private RecyclerView recipeView;
    private RecipeAdapter recipeAdapter;
    private FirebaseFirestoreHelper firebaseFirestoreHelper;
    private ViewPager mViewPager;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        authHelper = new FirebaseAuthHelper(this);

        firebaseFirestoreHelper = new FirebaseFirestoreHelper(this);
        firebaseFirestoreHelper.FoodProductsInitialise();
        list = firebaseFirestoreHelper.GetCollection("FoodProduct");

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.fragment_container);
        SetupViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

        FirebaseUser user = authHelper.GetUser();
        if(user == null)
        {
            startActivity(new Intent(this,SignInActivity.class));
        }

    }
    private void SetupViewPager(ViewPager viewPager)
    {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.AddFragment(new ProductsList());
        fragmentAdapter.AddFragment(new RecipeList());
        viewPager.setAdapter(fragmentAdapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void SignOut(View view) {
//        authHelper.SignOut();
//        startActivity(new Intent(this, SignInActivity.class));
//    }
//
//    public void AddRecipe(View view) {
//        Intent intent = new Intent(this, AddRecipeActivity.class);
//        startActivity(intent);
//    }
//
//    public void SearchRecipes(View view) {
//        Intent intent = new Intent(this,ChoseProductsActivity.class);
//        startActivityForResult(intent,REQUEST_CODE_SEARCH_PRODUCTS);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch(requestCode) {
//            case REQUEST_CODE_SEARCH_PRODUCTS:
//                if(resultCode == Activity.RESULT_OK)
//                {
//                    firebaseFirestoreHelper.RecipesWithProducts(recipeAdapter,ChoseProductsActivity.retProducts);
//                }
//        }
//    }

    public void AllRecipes(View view) {
        firebaseFirestoreHelper.GetRecipes(recipeAdapter);
    }
}