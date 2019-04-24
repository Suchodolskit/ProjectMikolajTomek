package com.example.project_mikolaj_tomek;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_mikolaj_tomek.Models.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>
{
    private Context context;
    private List<Recipe> recipies;

    public RecipeAdapter(Context context, List<Recipe> recipies) {
        this.context = context;
        this.recipies = recipies;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_item, null);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        Recipe recipe = recipies.get(i);
        recipeViewHolder.title.setText(recipe.getTitle());
        recipeViewHolder.summary.setText(recipe.getSummary());
        recipeViewHolder.image.setImageDrawable(recipe.getImage());
    }

    @Override
    public int getItemCount() {
        return recipies.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title, summary;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recipe_image);
            title = itemView.findViewById(R.id.recipe_title);
            summary = itemView.findViewById(R.id.recipe_summary);
        }
    }
}
