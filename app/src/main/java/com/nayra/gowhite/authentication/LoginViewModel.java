package com.nayra.gowhite.authentication;

import android.util.Log;

import com.nayra.gowhite.Constants;
import com.nayra.gowhite.model.LoginResponse;
import com.nayra.gowhite.network.ApiServices;
import com.nayra.gowhite.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class LoginViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private static LoginViewModel instance = new LoginViewModel();

    private static LoginResponse loginResponse;

    public static LoginViewModel getInstance() {
        if (instance == null) {
            instance = new LoginViewModel();
        }
        return instance;
    }

    public void login(final String grant_type, final String password, final String email) {
        final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);

        final Call<LoginResponse> call = apiService.login(grant_type, password, email);
        //Log.d(TAG,call.request().body().toString());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse = response.body();
                if (loginResponse != null) {
                    Log.d(TAG, loginResponse.toString());
                } else {
                    Log.d(TAG, "null");
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "failure");
            }
        });
    }
}
