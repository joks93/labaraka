package com.example.nacim.labaraka;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nacim on 18/04/17.
 */

public class ProductImageFragment extends Fragment {

    private String data;

    public ProductImageFragment(String data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_sheet_image, container, false);
/*
        View viewRoot = inflater.inflate(R.layout.product_sheet_image, container, false);
        TextView imageTextView = (TextView) viewRoot.findViewById(R.id.product_sheet_image_textview);
        imageTextView.setText(data);
        return viewRoot;
*/
    }
}
