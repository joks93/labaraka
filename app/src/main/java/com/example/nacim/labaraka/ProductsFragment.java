package com.example.nacim.labaraka;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private int product_of;

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
        product_of = getArguments().getInt("KEY_PRODUCTS_OF");
        Log.d("ON CREATE VIEW", product_of + "");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.products_recycler_view);
        layoutManager = new GridLayoutManager(getContext(), NUM_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapter = new ProductsRecyclerViewAdapter(getContext(), product_of);
        recyclerView.setAdapter(recyclerViewAdapter);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        //Save recyclerView state
        bundleRecycleViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        bundleRecycleViewState.putParcelable("KEY_RECYCLER_STATE", listState);
        bundleRecycleViewState.putInt("KEY_PRODUCTS_OF", product_of);
        Log.d("ON PAUSE", product_of + "");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Restore recyclerView state
        if (bundleRecycleViewState != null) {
            int product_of_saved = bundleRecycleViewState.getInt("KEY_PRODUCTS_OF");
            Log.d("ON RESUME", product_of_saved + "");
            if (product_of_saved != product_of) {
                Log.d("ON RESUME", "TRANSITOR");
                catalogProducts.clear();
                Transitor.updateCatalogProducts(product_of, recyclerView, recyclerViewAdapter);
            }
            else {
                Parcelable listSate = bundleRecycleViewState.getParcelable("KEY_RECYCLER_STATE");
                recyclerView.getLayoutManager().onRestoreInstanceState(listSate);
            }
        }
        else {
            catalogProducts.clear();
            Transitor.updateCatalogProducts(product_of, recyclerView, recyclerViewAdapter);
        }
    }


}
