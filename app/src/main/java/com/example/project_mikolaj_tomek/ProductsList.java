package com.example.project_mikolaj_tomek;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_mikolaj_tomek.Models.Product;
import com.example.project_mikolaj_tomek.Models.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductsList extends Fragment {

    private static final String TAG = "Product List Fragment";
    private RecyclerView mProductList;
    private List<Product> products;

    private OnFragmentInteractionListener mListener;

    public ProductsList() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        products = new ArrayList<>();
        products.add(new Product("0", "Apple", ProductCategory.Fruit, R.drawable.apple_1));
        products.add(new Product("1", "Asparagus", ProductCategory.Vegetable, R.drawable.asparagus));
        products.add(new Product("2", "Aubergine", ProductCategory.Vegetable, R.drawable.aubergine));
        products.add(new Product("3", "Avocado", ProductCategory.Vegetable, R.drawable.avocado));
        products.add(new Product("4", "Bacon", ProductCategory.Meat, R.drawable.bacon));
        products.add(new Product("5", "Baguette", ProductCategory.Bread, R.drawable.baguette));
        products.add(new Product("6","Banana",ProductCategory.Fruit,R.drawable.banana));
        products.add(new Product("7","Beans", ProductCategory.Vegetable, R.drawable.beans));
        products.add(new Product("8", "Blueberries", ProductCategory.Fruit, R.drawable.blueberries));
        products.add(new Product("9", "White Bread", ProductCategory.Bread, R.drawable.bread));
        products.add(new Product("10", "Brown Bread", ProductCategory.Bread, R.drawable.bread_1));
        products.add(new Product("11", "Brococoli", ProductCategory.Vegetable, R.drawable.broccoli));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);
        mProductList = view.findViewById(R.id.productsRecyclerView);
        ProductAdapter productAdapter = new ProductAdapter(getActivity(), products);
        mProductList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mProductList.setAdapter(productAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
