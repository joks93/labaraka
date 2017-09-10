package com.example.nacim.labaraka;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by nacim on 28/04/17.
 */

public class Constants {
    public final static int NUMBERS_FRAGMENTS = 4;
    public final static int CATEGORIES__FRAGMENT_ID = 0;
    public final static int PRODUCTS_FRAGMENT_ID = 1;
    public final static int BASKET_FRAGMENT_ID = 2;
    public final static int ACCOUNT_FRAGMENT_ID = 3;

    public final static int ACCUEIL_CATEGORY_ID = 2;
    public final static int WOMAN_CATEGORY_ID = 3;
    public final static int MAN_CATEGORY_ID = 12;
    public final static int GIRL_CATEGORY_ID = 4;
    public final static int BOY_CATEGORY_ID = 5;
    public final static int HOME_CATEGORY_ID = 6;

    public final static String urlAPI = "http://10.0.2.2:80/labaraka/android_api/";

    public final static NumberFormat priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
    public final static String EURO = "€";
}