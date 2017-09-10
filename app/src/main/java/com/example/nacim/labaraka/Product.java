package com.example.nacim.labaraka;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Nacim Arrahmane on 03/07/2017.
 */

public class Product {
    private int id;
    private String name;
    private double priceHT;
    private double taxRate;
    private String URLdefaultImage;
    private ArrayList<String> URLImages;
    private AttributesProduct attributes;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPriceHT() {
        return priceHT;
    }

    public double getTaxRate()
    {
        return taxRate;
    }

    public String getURLdefaultImage() {
        return URLdefaultImage;
    }

    public ArrayList<String> getURLImages() {
        return URLImages;
    }

    public String getImage(int position) {
        return URLImages.get(position);
    }

    public double getPrice()
    {
        return priceHT + priceHT * taxRate / 100;
    }

    public AttributesProduct getAttributes() {
        return attributes;
    }
}
