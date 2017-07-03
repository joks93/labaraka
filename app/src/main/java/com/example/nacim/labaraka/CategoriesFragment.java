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

import java.io.IOException;
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

    public static ArrayList<Category> rootCategories = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private LinearLayoutManager layoutManager;

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

        recyclerViewAdapter = new CategoriesRecyclerViewAdapter(getContext(), layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        Transitor.updateRootCategories(Constants.ACCUEIL_CATEGORY_ID, recyclerView, recyclerViewAdapter);

        return viewRoot;
    }

}
