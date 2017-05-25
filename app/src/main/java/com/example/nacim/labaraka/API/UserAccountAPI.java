package com.example.nacim.labaraka.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nacim Arrahmane on 24/05/2017.
 */

public interface UserAccountAPI {

    @POST("authentification.php")
    @FormUrlEncoded
    Call<Boolean> signInUser(@Field("email") String email, @Field("password") String passwd);
}
