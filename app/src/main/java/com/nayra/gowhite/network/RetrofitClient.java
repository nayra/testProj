package com.nayra.gowhite.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class RetrofitClient {


    private static final String TAG = RetrofitClient.class.getName();
    private static Retrofit retrofit;

    /**
     * @param baseUrl
     * @return retrofit object used to create the requests
     */
    public static Retrofit retrofit(final String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private static OkHttpClient getHttpClient() {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.build();

    }
}

