package com.example.project_mikolaj_tomek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_mikolaj_tomek.Models.Product;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ChoseProductsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductListAdapter mAdapter;
    public static List<Product> retProducts;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_products);

        retProducts= new LinkedList<>();

        products = new LinkedList<>();

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.productsRecyclerView);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ProductListAdapter(this, products);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseFirestoreHelper helper = new FirebaseFirestoreHelper(this);
        helper.GetProducts(mAdapter);
    }

    public void selectCheckBox(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox)view).isChecked();
        String s = ((TextView)((LinearLayout)view.getParent()).getChildAt(0)).getText().toString();
        if(checked){
            for(Product p : products)
            {
                if(p.getName().equals(s))
                {
                    retProducts.add(p);
                }
            }
        } else {

            for(int i=0;i<retProducts.size();i++) {

                if(retProducts.get(i).getName().equals(s)) {

                    retProducts.remove(i);
                }
            }
        }
    }
}
