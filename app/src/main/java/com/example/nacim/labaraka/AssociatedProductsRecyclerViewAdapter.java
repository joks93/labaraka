package com.example.nacim.labaraka;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Nacim Arrahmane on 01/05/2017.
 */

public class AssociatedProductsRecyclerViewAdapter extends RecyclerView.Adapter<AssociatedProductsRecyclerViewAdapter.AssocitaedProductViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> data;

    public AssociatedProductsRecyclerViewAdapter(Context context, LinearLayoutManager linearLayoutManager, ArrayList<String> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public AssociatedProductsRecyclerViewAdapter.AssocitaedProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View associatedProductView = layoutInflater.inflate(R.layout.product_sheet_associated_product_cell, parent, false);
        return new AssocitaedProductViewHolder(associatedProductView);
    }

    @Override
    public void onBindViewHolder(AssociatedProductsRecyclerViewAdapter.AssocitaedProductViewHolder holder, int position) {
        holder.associatedProductImage.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.sweat, null));
        holder.associatedProductName.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductSheetActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AssocitaedProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView associatedProductImage;
        private TextView associatedProductPrice;
        private TextView associatedProductName;

        public AssocitaedProductViewHolder(View itemView) {
            super(itemView);
            this.associatedProductImage = (ImageView) itemView.findViewById(R.id.product_sheet_associated_product_image);
            this.associatedProductPrice = (TextView) itemView.findViewById(R.id.product_sheet_associated_product_price);
            this.associatedProductName = (TextView) itemView.findViewById(R.id.product_sheet_associated_product_name);
        }
    }
}
