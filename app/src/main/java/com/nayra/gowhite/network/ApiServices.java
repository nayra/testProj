package com.nayra.gowhite.network;

import com.nayra.gowhite.model.LoginResponse;
import com.nayra.gowhite.view_model.GetCountriesViewModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public interface ApiServices {

    @FormUrlEncoded
    @POST("token")
    Call<LoginResponse> login(@Field("grant_type") String type, @Field("password") String password, @Field("username") String email);


    @GET("api/Country")
    Call<GetCountriesViewModel> getCountries();




}
