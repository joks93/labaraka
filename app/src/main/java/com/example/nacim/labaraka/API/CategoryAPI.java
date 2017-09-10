package com.example.nacim.labaraka.API;

import com.example.nacim.labaraka.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nacim Arrahmane on 01/07/2017.
 */

public interface CategoryAPI{
    @GET("extractCategories.php")
    Call<ArrayList<Category>> extractSubCategoriesOf(@Query("parent") int id_parent);
}
