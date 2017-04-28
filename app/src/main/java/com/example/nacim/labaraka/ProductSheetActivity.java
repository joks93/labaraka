package com.example.nacim.labaraka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.piotrek.customspinner.CustomSpinner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nacim on 18/04/17.
 */

public class ProductSheetActivity extends AppCompatActivity {

    private ArrayList<String> data;
    private String size;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_sheet);

        /* FURTHER INTENT */
        intent = new Intent(this, ScreenSlidePagerActivity.class);


        /* TOOLBAR */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mini jupe short à fleur");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.zzz_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        /* PRINCIPAL RECYCLER VIEW IMAGES */
        RecyclerViewPager recyclerViewPager = (RecyclerViewPager) findViewById(R.id.product_sheet_viewpager);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPager.setLayoutManager(layout);

        adjustImagesViewPager(R.id.product_sheet_viewpager_linearlayout);

        ArrayList<String> data = new ArrayList<>();
        data.add("Image1");
        data.add("Image2");
        data.add("Image3");
        data.add("Image4");
        data.add("Image5");

        ImagesRecyclerViewAdapter imagesRecyclerViewAdapter = new ImagesRecyclerViewAdapter(this, data);
        recyclerViewPager.setAdapter(imagesRecyclerViewAdapter);


        /* SIZE SPINNER */
        List<String> sizes = new ArrayList<>();
        sizes.add("XS");
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        sizes.add("XXL");

        CustomSpinner sizeSpinner = (CustomSpinner) findViewById(R.id.product_sheet_size_spinner);
        sizeSpinner.initializeStringValues(sizes.toArray(new String[0]), getResources().getString(R.string.select_size_spinner));


        /* COLOR SPINNER */
        List<String> colors = new ArrayList<>();
        colors.add("NONE");

        CustomSpinner colorSpinner = (CustomSpinner) findViewById(R.id.product_sheet_color_spinner);
        colorSpinner.initializeStringValues(colors.toArray(new String[0]), "BLUE");
        colorSpinner.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_sheet_appbar, menu);
        return true;
    }

    private void adjustImagesViewPager(int id) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // Get height of the device in dp.
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        //float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        LinearLayout layout = (LinearLayout)findViewById(id);

        android.view.ViewGroup.LayoutParams params = layout.getLayoutParams();

        int pxHeight = Math.round(dpHeight * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        int heightWeight = 3;
        int pxHeightWeight = Math.round(dpHeight / heightWeight * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        params.height = pxHeight - pxHeightWeight;

        layout.setLayoutParams(params);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(intent);
        }
        else {
            String itemTitle = item.getTitle().toString();
            if (itemTitle.equals(getString(R.string.action_favorite))) {
                item.setIcon(R.drawable.zzz_heart);
                item.setTitle(R.string.action_unfavorite);
            }
            else if (itemTitle.equals(getString(R.string.action_unfavorite))) {
                item.setIcon(R.drawable.zzz_heart_outline);
                item.setTitle(R.string.action_favorite);
            }
            else if (itemTitle.equals(getString(R.string.action_shoppingbasket))) {
                intent.putExtra("KEY_INIT_PAGE", Constants.BASKET_FRAGMENT_ID);
                startActivity(intent);
            }
        }
        return true;
    }
}
