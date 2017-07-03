package com.example.nacim.labaraka;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nacim on 17/04/17.
 */

public class ProductsFragment extends Fragment {
    public static ArrayList<Product> catalogProducts = new ArrayList<>();

    private static final int NUM_COLUMNS = 2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static Bundle bundleRecycleViewState;

    public ProductsFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.products_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.products_recycler_view);
        layoutManager = new GridLayoutManager(getContext(), NUM_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> data = new ArrayList<>();
        data.add("A");
        data.add("B");
        data.add("C");
        data.add("D");
        data.add("E");
        data.add("F");
        data.add("G");
        data.add("H");
        data.add("I");
        data.add("K");
        data.add("L");
        data.add("M");
        data.add("N");
        data.add("O");
        data.add("P");
        data.add("Q");
        data.add("R");

        recyclerViewAdapter = new ProductsRecyclerViewAdapter(getContext(), data);
        recyclerView.setAdapter(recyclerViewAdapter);
        Transitor.updateCatalogProducts(getArguments().getInt("KEY_PRODUCTS_OF", 2), recyclerView, recyclerViewAdapter);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        //Save recyclerView state
        bundleRecycleViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        bundleRecycleViewState.putParcelable("KEY_RECYCLER_STATE", listState);
    }

    @Override
    public void onResume() {
        super.onResume();

        //Restore recyclerView state
        if (bundleRecycleViewState != null) {
            Parcelable listSate = bundleRecycleViewState.getParcelable("KEY_RECYCLER_STATE");
            recyclerView.getLayoutManager().onRestoreInstanceState(listSate);
        }
    }


}
