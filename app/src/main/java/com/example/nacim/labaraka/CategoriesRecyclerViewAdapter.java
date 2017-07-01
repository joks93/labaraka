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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nacim on 17/04/17.
 */

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CategoryViewHolder>{

    private Intent intent;
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<Category, Boolean> subcategoriesClicked;
    public int currentOpen = -1;
    private LinearLayoutManager linearLayoutManager;

    public CategoriesRecyclerViewAdapter(Context context, LinearLayoutManager layoutManager) {
        this.intent = new Intent(context, ScreenSlidePagerActivity.class);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.subcategoriesClicked = new HashMap<>();
        for (Category category : CategoriesFragment.rootCategories)
            subcategoriesClicked.put(category, false);

        this.linearLayoutManager = layoutManager;
    }

    private void initCategoryIllustration(CategoryViewHolder holder, int idCategory) {
        if (idCategory == Constants.WOMAN_CATEGORY_ID)
            holder.categoryIllustration.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.jupe, null));
        else if (idCategory == Constants.MAN_CATEGORY_ID)
            holder.categoryIllustration.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.jupe, null));
        else if (idCategory == Constants.GIRL_CATEGORY_ID)
            holder.categoryIllustration.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.jupe, null));
        else if (idCategory == Constants.BOY_CATEGORY_ID)
            holder.categoryIllustration.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.jupe, null));
        else if (idCategory == Constants.HOME_CATEGORY_ID)
            holder.categoryIllustration.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.jupe, null));
    }

        @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View categoryView = layoutInflater.inflate(R.layout.category_list_cell, parent, false);

        ImageView categoryImageView  = (ImageView) categoryView.findViewById(R.id.category_list_cell_illustration);
        categoryImageView.setMinimumHeight(parent.getMeasuredHeight() / 3);
        categoryImageView.setMaxHeight(parent.getMeasuredHeight() / 3);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        final String categoryName = CategoriesFragment.rootCategories.get(position).getName();
        holder.nameTextView.setText(categoryName.toUpperCase());

        //initCategoryIllustration(holder, position);

        final RecyclerView subcategoriesRecyclerView = (RecyclerView) holder.itemView.findViewById(R.id.category_list_cell_subcategories_recyclerview);

        subcategoriesRecyclerView.setVisibility(View.GONE);
        subcategoriesClicked.put(CategoriesFragment.rootCategories.get(position), Boolean.FALSE);

        final RecyclerView categoriesRecyclerView = (RecyclerView) holder.itemView.findViewById(R.id.categories_fragment_recyclerview);

        holder.itemView.findViewById(R.id.category_list_cell_illustration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (subcategoriesClicked.get(CategoriesFragment.rootCategories.get(position))) {
                    //Close subcategories
                    subcategoriesRecyclerView.setVisibility(View.GONE);
                    currentOpen = -1;
                }
                else {
                    //Open subcategories
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                    subcategoriesRecyclerView.setLayoutManager(layoutManager);
                    subcategoriesRecyclerView.setAdapter(new SubcategoriesRecyclerViewAdapter(context, CategoriesFragment.rootCategories.get(position), intent));
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

                subcategoriesClicked.put(CategoriesFragment.rootCategories.get(position), ! subcategoriesClicked.get(CategoriesFragment.rootCategories.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return CategoriesFragment.rootCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIllustration;
        private TextView nameTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryIllustration = (ImageView) itemView.findViewById(R.id.category_list_cell_illustration);
            nameTextView = (TextView) itemView.findViewById(R.id.category_name_textview);
        }
    }
}
