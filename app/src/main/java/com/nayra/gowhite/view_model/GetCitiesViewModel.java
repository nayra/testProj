package com.nayra.gowhite.view_model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.nayra.gowhite.Constants;
import com.nayra.gowhite.R;
import com.nayra.gowhite.interfaces.Updatable;
import com.nayra.gowhite.interfaces.WebServices;
import com.nayra.gowhite.model.City;
import com.nayra.gowhite.network.ApiServices;
import com.nayra.gowhite.network.RetrofitClient;
import com.nayra.gowhite.utils.NetworkConnectionUtil;
import com.nayra.gowhite.utils.ProgressUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class GetCitiesViewModel {
    private static final String TAG = GetCitiesViewModel.class.getSimpleName();
    private static GetCitiesViewModel instance = new GetCitiesViewModel();

    @SerializedName("Result")
    private ArrayList<City> cities = new ArrayList<>();

    @SerializedName("ErrorMessage")
    private String error_msg = "";

    private Updatable updatable;

    public static GetCitiesViewModel getInstance() {
        if (instance == null) {
            instance = new GetCitiesViewModel();
        }
        return instance;
    }

    public void getCities(int country_id, Context context, final Updatable updatable) {
        //if (instance.cities.size() == 0)
        {
            this.updatable = updatable;
            if (NetworkConnectionUtil.isNetworkAvailable(context)) {
                ProgressUtils.show(context);
                final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);

                final Call<GetCitiesViewModel> call = apiService.getCities(country_id);

                call.enqueue(new Callback<GetCitiesViewModel>() {
                    @Override
                    public void onResponse(Call<GetCitiesViewModel> call, Response<GetCitiesViewModel> response) {
                        instance = response.body();
                        if (instance != null) {
                            Log.d(TAG, instance.toString());
                        } else {
                            Log.d(TAG, "null");
                        }
                        updatable.update(WebServices.CITIES);
                        ProgressUtils.dismiss();
                    }

                    @Override
                    public void onFailure(Call<GetCitiesViewModel> call, Throwable t) {
                        Log.e(TAG, "failure");

                        ProgressUtils.dismiss();
                    }
                });
            } else {
                Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            }
        }
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}