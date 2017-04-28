package com.example.nacim.labaraka;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.Titles;

public class ScreenSlidePagerActivity extends FragmentActivity {

    //TEST
    @Titles
    private static String[] mTitles;// = {, "Categories", "Products", "Basket", "Login"};

    @NorIcons
    private static final int[] mNormalIcons = {R.drawable.zzz_view_headline, R.drawable.zzz_tshirt_crew, R.drawable.zzz_basket, R.drawable.ic_action_user};

    //ViewPager's attributes
    private static final int NUM_PAGES = Constants.NUMBERS_FRAGMENTS;
    private int INIT_PAGE;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitles = new String[] {getString(R.string.tab_categories), getString(R.string.tab_products), getString(R.string.tab_basket), getString(R.string.tab_login)};

        setContentView(R.layout.main_view_pager);

        INIT_PAGE = getIntent().getIntExtra("KEY_INIT_PAGE", Constants.PRODUCTS_FRAGMENT_ID);

        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(INIT_PAGE); //Set initial fragment as the middle one

        JPTabBar mTabbar = (JPTabBar) findViewById(R.id.tabbar);
        mTabbar.setContainer(viewPager);
        mTabbar.setSelectTab(INIT_PAGE);

    }

    private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case Constants.CATEGORIES__FRAGMENT_ID:
                    return new CategoriesFragment();
                case Constants.PRODUCTS_FRAGMENT_ID:
                    return new ProductsFragment();
                case Constants.BASKET_FRAGMENT_ID:
                    return new BasketFragment();
                case Constants.ACCOUNT_FRAGMENT_ID:
                    return new AccountFragment();
                default:
                    return new ProductsFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
