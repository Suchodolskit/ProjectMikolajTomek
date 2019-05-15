package com.example.project_mikolaj_tomek;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        recipes = new LinkedList<Recipe>();

        recipeView = findViewById(R.id.recyclerView);
        recipeView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(this, recipes);
        recipeView.setAdapter(recipeAdapter);

        FirebaseUser user = authHelper.GetUser();
        if(user == null)
        {
            startActivity(new Intent(this,SignInActivity.class));
        }

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void SignOut(View view) {
        authHelper.SignOut();
        startActivity(new Intent(this, SignInActivity.class));
    }

    public void AddRecipe(View view) {
//
//        LinkedList<Product> products = new LinkedList<>();
//
//        products.add(new Product("Flour","Flour",null));
//        products.add(new Product("Sugar","Sugar",null));
//        products.add(new Product("Oat","Oat",null));
//        products.add(new Product("desiccated coconut","desiccated coconut",null));
//        products.add(new Product("honey","honey",null));
//        products.add(new Product("butter","butter",null));
//        products.add(new Product("baking soda","baking soda",null));
//        products.add(new Product("butter","butter",null));
//
//
//
//        Recipe recipe = new Recipe("Anzac Biscuits",
//                "Anzac Biscuits",
//                Calendar.getInstance().getTime(),
//                35,
//                "    Using brown sugar instead of white sugar gives more colour and flavour\n" +
//                        "    You can replace coconut with more syrup and oats.\n" +
//                        "    Don't omit the baking soda and water. They give the biscuits crispness and colour.\n" +
//                        "    See https://www.awm.gov.au/articles/encyclopedia/anzac/biscuit/recipe for the official ANZAC recipe",
//                "    Mix the flour, sugar, oats, and coconut in a bowl\n" +
//                        "    Melt the syrup and butter in a saucepan. Add the baking soda and water to the syrup mix.\n" +
//                        "    Mix the wet and dry ingredients, adding water if necessary.\n" +
//                        "    Separate and roll the mixture into small balls, and flatten them on oven trays.\n" +
//                        "    Bake at 150°C (300°F) for about 20 minutes or until golden brown.\n" +
//                        "\n" +
//                        "The finished biscuits are quite chewy and crisp, and have a long shelf-life. " +
//                        "Ingredients\n" +
//                        "\n" +
//                        "    2 cups plain (non-self raising) flour\n" +
//                        "    1 cup white or brown sugar\n" +
//                        "    2 cups rolled or instant oats\n" +
//                        "    1 cup desiccated coconut\n" +
//                        "    4 tablespoons golden syrup (cane syrup) or honey\n" +
//                        "    225 grams butter or margarine\n" +
//                        "    1 teaspoon baking soda\n" +
//                        "    2 tablespoons of water",
//                products,
//                null,
//                "Wikibooks");


        LinkedList<Product> products = new LinkedList<>();

        products.add(new Product("Flour","Flour",null));
        products.add(new Product("baking powder","baking powder",null));
        products.add(new Product("lard","lard",null));
        products.add(new Product("milk","milk",null));

        Recipe recipe = new Recipe("Biscuit",
                "Biscuit",
                Calendar.getInstance().getTime(),
                35,
                "Biscuits are a type of quickbread sometimes served with lunch or dinner, especially for an informal or casual meal. Sometimes biscuits are served with gravy. Often biscuits are served with breakfast. Eaters typically apply butter, honey, jam, or jelly to their biscuits. For breakfast, an eater might add a fried egg, fried ham, sausage or cheese.\n" +
        "\n" +
        "Biscuits come in two forms, cut and dropped. Cut biscuits use a fairly normal dough that is rolled out and cut with a circular cookie cutter. Drop biscuits use a somewhat more liquid dough that is dropped onto a cookie sheet with a spoon. ",
                "    Mix the dry ingredients.\n" +
                        "    Cut (mix) in the fat, preferably as you would for an apple crisp or traditional pie crust.\n" +
                        "    Add the milk, mixing only as needed to wet the dough or batter.\n" +
                        "    If doing drop biscuits, you'll plop the batter by spoonfuls (large spoonfuls) onto the cookie sheet. Otherwise, for cut biscuits:\n" +
                        "        Give the dough a quick knead.\n" +
                        "        Roll the dough out to 1/2 \", using flour as needed to avoid sticking.\n" +
                        "        Cut circles with a 2.5\" cookie cutter, or carve hexagons (a honeycomb pattern) with a knife.\n" +
                        "    Space the biscuits 1\" apart on the cookie sheet.\n" +
                        "    Bake 10 to 12 minutes in a 450°F oven, stopping when lightly browned on top.\n" +
                        "    Serve hot." +
                        "Ingredients" +
                        "(240 mL) flour" +
                        "(15 mL) baking powder" +
                        "(2 mL) salt" +
                        "(80 mL) lard or shortening" +
                        "(240 mL) milk for drop biscuits, or (180 mL) milk for cut biscuits ",
                products,
                null,
                "Wikibooks");



        FirebaseStorageHelper firebaseStorageHelper = new FirebaseStorageHelper();
        FirebaseFirestoreHelper firebaseFirestoreHelper = new FirebaseFirestoreHelper(this);
        firebaseFirestoreHelper.SaveData("Recipes", recipe);

        for(Product p :products)
            firebaseFirestoreHelper.SaveData("Products",p);
        //Intent intent = new Intent(this, AddRecipeActivity.class);
        //startActivity(intent);
    }

    public void SearchRecipes(View view) {
        Intent intent = new Intent(this,ChoseProductsActivity.class);
        startActivityForResult(intent,REQUEST_CODE_SEARCH_PRODUCTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_CODE_SEARCH_PRODUCTS:
                if(resultCode == Activity.RESULT_OK)
                {
                    firebaseFirestoreHelper.RecipesWithProducts(recipeAdapter,ChoseProductsActivity.retProducts);
                }
        }
    }

    public void AllRecipes(View view) {
        firebaseFirestoreHelper.GetRecipes(recipeAdapter);
    }
}