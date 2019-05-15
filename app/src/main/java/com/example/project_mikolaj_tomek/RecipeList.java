package com.example.project_mikolaj_tomek;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_mikolaj_tomek.Models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeList extends Fragment {

    private static final String TAG = "Recipe List Fragment";
    private RecyclerView mRecipeList;
    private List<Recipe> recipes;

    private OnFragmentInteractionListener mListener;

    public RecipeList() {
        // Required empty public constructor
        recipes = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        mRecipeList = view.findViewById(R.id.recipesRecycleView);
        RecipeAdapter recipeAdapter = new RecipeAdapter(getActivity(), recipes);
        FirebaseFirestoreHelper helper = new FirebaseFirestoreHelper(getActivity());
        helper.GetRecipes(recipeAdapter);
        mRecipeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecipeList.setAdapter(recipeAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}