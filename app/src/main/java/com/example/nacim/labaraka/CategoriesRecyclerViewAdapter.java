package com.example.nacim.labaraka;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nacim.labaraka.API.CategoryAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nacim on 17/04/17.
 */

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CategoryViewHolder>{

    public static Map<Integer, ArrayList<Category>> subcategories = new HashMap<>();

    private Intent intent;
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<Category, Boolean> subcategoriesClicked;
    public int currentOpen = -1;
    private LinearLayoutManager linearLayoutManager;

    private Gson gson;
    private Retrofit retrofit;
    private CategoryAPI service;

    public CategoriesRecyclerViewAdapter(Context context, LinearLayoutManager layoutManager) {
        this.intent = new Intent(context, ScreenSlidePagerActivity.class);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.subcategoriesClicked = new HashMap<>();
        for (Category category : CategoriesFragment.rootCategories)
            subcategoriesClicked.put(category, false);

        this.linearLayoutManager = layoutManager;

        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:80/labaraka/android_api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(CategoryAPI.class);
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
        final RecyclerView subcategoriesRecyclerView = (RecyclerView) holder.itemView.findViewById(R.id.category_list_cell_subcategories_recyclerview);

        final String categoryName = CategoriesFragment.rootCategories.get(position).getName();
        holder.nameTextView.setText(categoryName.toUpperCase());

        //initCategoryIllustration(holder, position);

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
                    updateSubCategories(CategoriesFragment.rootCategories.get(position), subcategoriesRecyclerView);
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

    private void updateSubCategories(final Category category, final RecyclerView subcategoriesRecyclerView) {
        ArrayList<Category> subcats = subcategories.get(category.getId());
        if (! subcats.isEmpty())
            return;

        service.extractSubCategoriesOf(category.getId()).enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response){
                if (response.isSuccessful()) {
                    for (Category category : response.body()) {
                        Log.d("RETROFIT", "SUCCESSFUL --> ID_PARENT = " + category.getId_parent() + " ID = " + category.getId() + " NAME = " + category.getName() + " " + category.getChildren());
                    }
                    subcategories.get(category.getId()).clear();
                    subcategories.get(category.getId()).addAll(response.body());
                    subcategoriesRecyclerView.setAdapter(new SubcategoriesRecyclerViewAdapter(context, category, intent));
                }
                else {
                    subcategories.get(category.getId()).clear();
                    Log.d("RETROFIT", "FAILURE -->  " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.d("RETROFIT", "FAILED -->  " + t.getLocalizedMessage());
                subcategories.get(category.getId()).clear();
            }
        });
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
