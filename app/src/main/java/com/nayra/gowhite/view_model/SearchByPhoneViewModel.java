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

public class SearchByPhoneViewModel {
    private static final String TAG = SearchByPhoneViewModel.class.getSimpleName();
    private static SearchByPhoneViewModel instance = new SearchByPhoneViewModel();

    @SerializedName("ErrorMessage")
    private String error_msg = "";

    @SerializedName("Result")
    private UserInfo userInfo;

    private Updatable updatable;

    public static SearchByPhoneViewModel getInstance() {
        if (instance == null) {
            instance = new SearchByPhoneViewModel();
        }
        return instance;
    }

    public void searchByPhone(String phone_num, Context context, final Updatable updatable) {
        //if (instance.cities.size() == 0)
        {
            this.updatable = updatable;
            if (NetworkConnectionUtil.isNetworkAvailable(context)) {
                ProgressUtils.show(context);
                final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);

                final Call<SearchByPhoneViewModel> call = apiService.searchByPhone(phone_num);

                call.enqueue(new Callback<SearchByPhoneViewModel>() {
                    @Override
                    public void onResponse(Call<SearchByPhoneViewModel> call, Response<SearchByPhoneViewModel> response) {
                        instance = response.body();
                        if (instance != null) {
                            Log.d(TAG, instance.toString());
                        } else {
                            Log.d(TAG, "null");
                        }
                        updatable.update(WebServices.SEARCH);
                        ProgressUtils.dismiss();
                    }

                    @Override
                    public void onFailure(Call<SearchByPhoneViewModel> call, Throwable t) {
                        Log.e(TAG, "failure");

                        ProgressUtils.dismiss();
                    }
                });
            } else {
                Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            }
        }
    }


    public String getError_msg() {
        return error_msg;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
