package com.example.nacim.labaraka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flyco.pageindicator.indicator.RoundCornerIndicaor;
import com.piotrek.customspinner.CustomSpinner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nacim on 18/04/17.
 */

public class ProductSheetActivity extends AppCompatActivity {

    private ArrayList<String> data;
    private ArrayList<String> sizes;
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


        /* PRINCIPAL VIEW PAGER IMAGES */
        data = new ArrayList<>();
        data.add("Image1");
        data.add("Image2");
        data.add("Image3");
        data.add("Image4");
        data.add("Image5");

        ViewPager viewPager = (ViewPager) findViewById(R.id.product_sheet_images_viewpager);
        ImagesViewPagerAdapter imagesViewPagerAdapter = new ImagesViewPagerAdapter(getSupportFragmentManager(), data);
        viewPager.setAdapter(imagesViewPagerAdapter);
        adjustImagesViewPager(R.id.product_sheet_viewpager_linearlayout);

        /* VIEW PAGER INDICATORS */
        RoundCornerIndicaor viewPagerIndicator = (RoundCornerIndicaor) findViewById(R.id.product_sheet_viewpager_indicator);
        viewPagerIndicator.setViewPager(viewPager);



        /* SIZE SPINNER */
        sizes = new ArrayList<>();
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

        /* ASSOCIATED PRODUCT RECYCLER VIEW */
        RecyclerView associatedProductsRecyclerView = (RecyclerView) findViewById(R.id.product_sheet_associated_product_recycler_view);
        LinearLayoutManager associatedProductsLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        associatedProductsRecyclerView.setLayoutManager(associatedProductsLayoutManager);

        ArrayList<String> productsAssociated = new ArrayList<>();
        productsAssociated.add("Associated 1");
        productsAssociated.add("Associated 2");
        productsAssociated.add("Associated 3");
        productsAssociated.add("Associated 4");
        productsAssociated.add("Associated 5");
        productsAssociated.add("Associated 6");

        ((TextView) findViewById(R.id.product_sheet_associated_product_counter)).setText(Integer.toString(productsAssociated.size()) + " ");

        AssociatedProductsRecyclerViewAdapter associatedProductsRecyclerViewAdapter = new AssociatedProductsRecyclerViewAdapter(getBaseContext(), associatedProductsLayoutManager, productsAssociated);
        associatedProductsRecyclerView.setAdapter(associatedProductsRecyclerViewAdapter);

        /* INIT TEXTVIEW CLICKABLE */
        initOnclickListenersTextview();
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

    private void initOnclickListenersTextview() {
        TextView sizeguideButton = (TextView) findViewById(R.id.product_sheet_sizeguide);
        sizeguideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SizeGuideActivity.class));
            }
        });
    }

    private static class ImagesViewPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<String> data;

        public ImagesViewPagerAdapter(FragmentManager fm, ArrayList<String> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return new ImageProductSheetFragment();
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }
}
