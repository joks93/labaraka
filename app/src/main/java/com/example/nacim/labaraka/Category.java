package com.example.nacim.labaraka;

import java.util.ArrayList;

/**
 * Created by Nacim Arrahmane on 01/07/2017.
 */

public class Category {
    private int id_parent;
    private int id;
    private String name;
    private ArrayList<Integer> children;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getChildren() {
        return children;
    }

    public int getId_parent() {
        return id_parent;
    }
}
