package com.nayra.gowhite.view_model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.nayra.gowhite.Constants;
import com.nayra.gowhite.R;
import com.nayra.gowhite.interfaces.Updatable;
import com.nayra.gowhite.interfaces.WebServices;
import com.nayra.gowhite.model.UserInfo;
import com.nayra.gowhite.network.ApiServices;
import com.nayra.gowhite.network.RetrofitClient;
import com.nayra.gowhite.utils.NetworkConnectionUtil;
import com.nayra.gowhite.utils.ProgressUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class RegisterViewModel {
    private static final String TAG = RegisterViewModel.class.getSimpleName();
    private static RegisterViewModel instance = new RegisterViewModel();

    private Updatable updatable;


    @SerializedName("ErrorMessage")
    private String error_msg = "";

    @SerializedName("Result")
    private UserInfo userInfo;

    public static RegisterViewModel getInstance() {
        if (instance == null) {
            instance = new RegisterViewModel();
        }
        return instance;
    }

    public void register(UserInfo userInfo, int country_id, final Context context, final Updatable updatable) {
        this.updatable = updatable;
        if (NetworkConnectionUtil.isNetworkAvailable(context)) {
            ProgressUtils.show(context);
            final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);

            final Call<RegisterViewModel> call = apiService.register(userInfo.getEmail(), userInfo.getPassword(), userInfo.getPassword(),
                    userInfo.getEmail(), userInfo.getName(), userInfo.getSurname(), country_id, userInfo.getPhone());

            call.enqueue(new Callback<RegisterViewModel>() {
                @Override
                public void onResponse(Call<RegisterViewModel> call, Response<RegisterViewModel> response) {
                    instance = response.body();
                    if (instance != null) {
                        Log.d(TAG, instance.toString());
                    } else {
                        Log.d(TAG, "null");
                    }

                    ProgressUtils.dismiss();
                    updatable.update(WebServices.REGISTER);
                }

                @Override
                public void onFailure(Call<RegisterViewModel> call, Throwable t) {
                    Log.e(TAG, "failure");

                    ProgressUtils.dismiss();
                    updatable.onFailure();
                }
            });
        } else {
            Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
        }
    }

    public String getError_msg() {
        return error_msg;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
