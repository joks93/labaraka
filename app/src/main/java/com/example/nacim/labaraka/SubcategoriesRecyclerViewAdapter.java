package com.example.nacim.labaraka;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nacim on 18/04/17.
 */

public class SubcategoriesRecyclerViewAdapter extends RecyclerView.Adapter<SubcategoriesRecyclerViewAdapter.SubcategoryViewHolder> {

    private Intent intent;
    private Context context;
    private LayoutInflater layoutInflater;
    private Category parent;

    public SubcategoriesRecyclerViewAdapter(Context context, Category parent, Intent intent) {
        this.intent = intent;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.parent = parent;
    }

    @Override
    public SubcategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View subcategoryView = layoutInflater.inflate(R.layout.subcategory_list_item, parent, false);
        return new SubcategoryViewHolder(subcategoryView);
    }

    @Override
    public void onBindViewHolder(SubcategoryViewHolder holder, int position) {
        holder.nameTextView.setText(CategoriesRecyclerViewAdapter.subcategories.get(parent.getId()).get(position).getName().toUpperCase());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("KEY_INIT_PAGE", Constants.PRODUCTS_FRAGMENT_ID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CategoriesRecyclerViewAdapter.subcategories.get(parent.getId()).size();
    }

    public class SubcategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;

        public SubcategoryViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = (TextView) itemView.findViewById(R.id.subcategory_list_item_name);
        }
    }
}
