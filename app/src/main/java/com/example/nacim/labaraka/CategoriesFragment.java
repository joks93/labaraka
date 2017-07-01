package com.example.nacim.labaraka;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nacim.labaraka.API.CategoryAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nacim on 17/04/17.
 */

public class CategoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private LinearLayoutManager layoutManager;

    private Gson gson;
    private Retrofit retrofit;
    private CategoryAPI service;

    private ArrayList<Category> rootCategories;

    public CategoriesFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.categories_fragment, container, false);
        recyclerView = (RecyclerView) viewRoot.findViewById(R.id.categories_fragment_recyclerview);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:80/labaraka/android_api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(CategoryAPI.class);

        recyclerViewAdapter = new CategoriesRecyclerViewAdapter(getContext(), layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        return viewRoot;
    }

    ArrayList<Category> getRootCategories() {
        if (rootCategories != null)
            return rootCategories;

        service.extractRootCategories().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response){
                if (response.isSuccessful()) {
                    for (Category category : response.body()) {
                        Log.d("RETROFIT", "SUCCESSFUL --> ID = " + category.getId() + " NAME = " + category.getName() + " " + category.getChildren());
                    }
                    rootCategories = response.body();
                }
                else
                    Log.d("RETROFIT", "FAILURE -->  " + response.errorBody());
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.d("RETROFIT", "FAILED -->  " + t.getLocalizedMessage());
            }
        });

        return rootCategories;
    }
}
