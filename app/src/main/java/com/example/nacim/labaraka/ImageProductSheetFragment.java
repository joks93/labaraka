package com.example.nacim.labaraka;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by nacim on 28/04/17.
 */

public class ImageProductSheetFragment extends Fragment{

    public ImageProductSheetFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = (ViewGroup) inflater.inflate(R.layout.product_sheet_image, container, false);
        ImageView imageView = (ImageView) viewRoot.findViewById(R.id.product_sheet_image);
        imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.jupe, null));
        return viewRoot;
    }
}
