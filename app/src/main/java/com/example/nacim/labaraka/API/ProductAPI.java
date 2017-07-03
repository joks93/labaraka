package com.example.nacim.labaraka.API;

import com.example.nacim.labaraka.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nacim Arrahmane on 01/07/2017.
 */

public interface ProductAPI {
    @GET("extractProducts.php")
    Call<ArrayList<Product>> extractProductsOf(@Query("category") int id_category);
}
