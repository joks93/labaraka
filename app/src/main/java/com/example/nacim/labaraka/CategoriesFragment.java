package com.example.nacim.labaraka;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nacim on 17/04/17.
 */

public class CategoriesFragment extends Fragment {

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

        Log.d("HEIGHT-", "--------------------------" + viewRoot.findViewById(R.id.categories_fragment_linear_layout).getHeight());

        recyclerViewAdapter = new CategoriesRecyclerViewAdapter(getContext(), layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        return viewRoot;
    }
}
