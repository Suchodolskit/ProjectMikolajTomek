package com.example.project_mikolaj_tomek;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_mikolaj_tomek.Models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViwHolder>
{
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList)
    {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override

    public ProductViwHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item, null);
        return new ProductViwHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViwHolder productViwHolder, int i) {
        Product product = productList.get(i);
        productViwHolder.text.setText( product.getName());
        productViwHolder.image.setImageResource(product.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViwHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView text;

        public ProductViwHolder(@NonNull View itemView)
        {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            text = itemView.findViewById(R.id.product_text);
        }
    }
}
