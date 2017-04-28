package com.example.nacim.labaraka;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nacim on 17/04/17.
 */

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CategoryViewHolder>{

    private Intent intent;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> data;
    private Map<String, Boolean> subcategoriesClicked;
    public int currentOpen = -1;


    private LinearLayoutManager linearLayoutManager;

    public CategoriesRecyclerViewAdapter(Context context, LinearLayoutManager layoutManager) {
        this.intent = new Intent(context, ScreenSlidePagerActivity.class);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.CATEGORIES)));
        this.subcategoriesClicked = new HashMap<>();
        for (String category : data)
            subcategoriesClicked.put(category, false);

        this.linearLayoutManager = layoutManager;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View categoryView = layoutInflater.inflate(R.layout.category_list_cell, parent, false);
        categoryView.findViewById(R.id.category_list_cell_textview).setMinimumHeight(parent.getMeasuredHeight() / 3);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        final String categoryName = data.get(position);
        holder.nameTextView.setText(categoryName);

        final RecyclerView subcategoriesRecyclerView = (RecyclerView) holder.itemView.findViewById(R.id.category_list_cell_subcategories_recyclerview);

        subcategoriesRecyclerView.setVisibility(View.GONE);
        subcategoriesClicked.put(holder.nameTextView.getText().toString(), Boolean.FALSE);

        final RecyclerView categoriesRecyclerView = (RecyclerView) holder.itemView.findViewById(R.id.categories_fragment_recyclerview);

        holder.itemView.findViewById(R.id.category_list_cell_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (subcategoriesClicked.get(holder.nameTextView.getText().toString())) {
                    //Close subcategories
                    subcategoriesRecyclerView.setVisibility(View.GONE);
                    currentOpen = -1;
                }
                else {
                    //Open subcategories
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                    subcategoriesRecyclerView.setLayoutManager(layoutManager);
                    subcategoriesRecyclerView.setAdapter(new SubcategoriesRecyclerViewAdapter(context, position, intent));
                    if (position == getItemCount() - 1) {
                        linearLayoutManager.scrollToPositionWithOffset(position, 0);
                        subcategoriesRecyclerView.setVisibility(View.VISIBLE);
                    }
                    else {
                        subcategoriesRecyclerView.setVisibility(View.VISIBLE);
                        linearLayoutManager.scrollToPosition(position);
                    }

                    int previous = currentOpen;
                    currentOpen = position;
                    if(previous != -1) {
                        notifyItemChanged(previous);
                    }
                }

                subcategoriesClicked.put(holder.nameTextView.getText().toString(), ! subcategoriesClicked.get(categoryName));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryTextView;
        private TextView nameTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.category_list_cell_textview);
            nameTextView = (TextView) itemView.findViewById(R.id.category_name_textview);
        }
    }
}
