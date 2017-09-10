package com.example.nacim.labaraka;

import com.example.nacim.labaraka.Color;
import com.example.nacim.labaraka.Size;

import java.util.ArrayList;

/**
 * Created by Nacim Arrahmane on 09/09/2017.
 */

public class AttributesProduct {
    private Color color;
    private ArrayList<Size> sizes;

    public Color getColor() {
        return color;
    }

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public Boolean hasColor() {
        return color != null;
    }

    public Boolean hasSizes() {
        return sizes != null && !sizes.isEmpty();
    }
}
