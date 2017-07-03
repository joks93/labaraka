package com.example.nacim.labaraka;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nacim.labaraka.API.CategoryAPI;
import com.example.nacim.labaraka.API.ProductAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nacim Arrahmane on 03/07/2017.
 */

public class Transitor {
    private static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.urlAPI)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private static CategoryAPI serviceCategory = retrofit.create(CategoryAPI.class);
    private static ProductAPI serviceProduct = retrofit.create(ProductAPI.class);


    public static void updateRootCategories(int id_parent, final RecyclerView recyclerView, final RecyclerView.Adapter recyclerViewAdapter) {
        if (!CategoriesFragment.rootCategories.isEmpty())
            return;

        serviceCategory.extractSubCategoriesOf(id_parent).enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    for (Category category : response.body()) {
                        CategoriesRecyclerViewAdapter.subcategories.put(category.getId(), new ArrayList<Category>());
                        Log.d("RETROFIT", "SUCCESSFUL --> ID_PARENT = " + category.getId_parent() + " ID = " + category.getId() + " NAME = " + category.getName() + " " + category.getChildren());
                    }
                    CategoriesFragment.rootCategories.clear();
                    CategoriesFragment.rootCategories.addAll(response.body());
                    recyclerView.setAdapter(recyclerViewAdapter);
                } else {
                    CategoriesFragment.rootCategories.clear();
                    Log.d("RETROFIT", "FAILURE -->  " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.d("RETROFIT", "FAILED -->  " + t.getLocalizedMessage());
                CategoriesFragment.rootCategories.clear();
            }
        });
    }

    public static void updateSubCategories(final Context context, final Intent intent, final Category category, final RecyclerView subcategoriesRecyclerView) {
        ArrayList<Category> subcats = CategoriesRecyclerViewAdapter.subcategories.get(category.getId());
        if (!subcats.isEmpty())
            return;

        serviceCategory.extractSubCategoriesOf(category.getId()).enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    for (Category category : response.body()) {
                        Log.d("RETROFIT", "SUCCESSFUL --> ID_PARENT = " + category.getId_parent() + " ID = " + category.getId() + " NAME = " + category.getName() + " " + category.getChildren());
                    }
                    CategoriesRecyclerViewAdapter.subcategories.get(category.getId()).clear();
                    CategoriesRecyclerViewAdapter.subcategories.get(category.getId()).addAll(response.body());
                    //subcategoriesRecyclerView.setAdapter(new SubcategoriesRecyclerViewAdapter(context, category, intent));
                } else {
                    CategoriesRecyclerViewAdapter.subcategories.get(category.getId()).clear();
                    Log.d("RETROFIT", "FAILURE -->  " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.d("RETROFIT", "FAILED -->  " + t.getLocalizedMessage());
                CategoriesRecyclerViewAdapter.subcategories.get(category.getId()).clear();
            }
        });
    }

    public static void updateCatalogProducts(int id_category, final RecyclerView recyclerView, final RecyclerView.Adapter recyclerViewAdapter) {
        if (! ProductsFragment.catalogProducts.isEmpty())
            return;

        serviceProduct.extractProductsOf(id_category).enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (response.isSuccessful()) {
                    for (Product product : response.body()) {
                        Log.d("RETROFIT-PRODUCTS", "SUCCESSFUL --> ID = " + product.getId() + " NAME = " + product.getName() + " priceHT = " + product.getPriceHT() + " qty = " + product.getQuantity() + " url = " + product.getURLdefaultImage() );
                    }
                    ProductsFragment.catalogProducts.clear();
                    ProductsFragment.catalogProducts.addAll(response.body());
                    recyclerView.setAdapter(recyclerViewAdapter);
                } else {
                    ProductsFragment.catalogProducts.clear();
                    Log.d("RETROFIT-PRODUCTS", "FAILURE -->  " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.d("RETROFIT-PRODUCTS", "FAILED -->  " + t.getLocalizedMessage());
                ProductsFragment.catalogProducts.clear();
            }
        });
    }
}
