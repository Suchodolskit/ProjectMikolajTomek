package com.example.project_mikolaj_tomek;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_mikolaj_tomek.Models.Product;

import java.util.List;

public class ProductListAdapter  extends
        RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private final List<Product> mProductList;
    private final LayoutInflater mInflater;

    class ProductViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView wordItemView;
        final ProductListAdapter mAdapter;

        public ProductViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.product);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();

            Product element = mProductList.get(mPosition);

            //.set(mPosition, "Clicked! " + element);
            mAdapter.notifyDataSetChanged();
        }
    }

    public ProductListAdapter(Context context, List<Product> productList) {
        mInflater = LayoutInflater.from(context);
        this.mProductList = productList;
    }

    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.products_list, parent, false);
        return new ProductViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        Product mCurrent = mProductList.get(position);
        // Add the data to the view holder.
        holder.wordItemView.setText(mCurrent.getName());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void SetList(List<Product> list)
    {
        mProductList.clear();
        for(Product p : list)
        {
            mProductList.add(p);
        }
        notifyDataSetChanged();
    }
}