package com.example.nacim.labaraka;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by nacim on 17/04/17.
 */

public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private Intent intent;

    public ProductsRecyclerViewAdapter(Context context, int product_of) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.intent = new Intent(context, ProductSheetActivity.class);
        intent.putExtra("KEY_PRODUCTS_OF", product_of);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View productView = layoutInflater.inflate(R.layout.product_grid_cell, parent, false);

        ImageView productImageView = (ImageView) productView.findViewById(R.id.product_grid_cell_imageview);
        productImageView.setMinimumHeight(parent.getMeasuredHeight() / 2);
        productImageView.setMaxHeight(parent.getMeasuredHeight() / 2);
        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final Product product = ProductsFragment.catalogProducts.get(position);

        Picasso.with(context).load(product.getURLdefaultImage()).into(holder.productImageView);

        holder.nameTextView.setText(product.getName( ));
        holder.priceTextView.setText(Constants.priceFormat.format(product.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Product sheet information -> new activity
                intent.putExtra("KEY_PRODUCT_SERIALIZED", Transitor.gson.toJson(product));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProductsFragment.catalogProducts.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImageView;
        private TextView newTextView;
        private TextView nameTextView;
        private TextView priceTextView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImageView = (ImageView) itemView.findViewById(R.id.product_grid_cell_imageview);
            newTextView = (TextView) itemView.findViewById(R.id.product_new_textview);
            nameTextView = (TextView) itemView.findViewById(R.id.product_name_textview);
            priceTextView = (TextView) itemView.findViewById(R.id.product_price_textview);

        }
    }
}
