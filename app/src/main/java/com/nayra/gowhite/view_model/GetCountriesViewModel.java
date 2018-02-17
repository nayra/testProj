package com.nayra.gowhite.view_model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.nayra.gowhite.Constants;
import com.nayra.gowhite.R;
import com.nayra.gowhite.interfaces.Updatable;
import com.nayra.gowhite.model.Country;
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

public class GetCountriesViewModel {
    private static final String TAG = GetCountriesViewModel.class.getSimpleName();
    private static GetCountriesViewModel instance = new GetCountriesViewModel();

    private Updatable updatable;

    @SerializedName("Result")
    private ArrayList<Country> countries = new ArrayList<>();

    @SerializedName("ErrorMessage")
    private String error_msg = "";

    public static GetCountriesViewModel getInstance() {
        if (instance == null) {
            instance = new GetCountriesViewModel();
        }
        return instance;
    }

    public void getCountries(Context context, final Updatable updatable) {
        if (instance.countries.size() == 0) {
            this.updatable = updatable;
            if (NetworkConnectionUtil.isNetworkAvailable(context)) {
                ProgressUtils.show(context);
                final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);

                final Call<GetCountriesViewModel> call = apiService.getCountries();
                //Log.d(TAG,call.request().body().toString());

                call.enqueue(new Callback<GetCountriesViewModel>() {
                    @Override
                    public void onResponse(Call<GetCountriesViewModel> call, Response<GetCountriesViewModel> response) {
                        instance = response.body();
                        if (instance != null) {
                            Log.d(TAG, instance.toString());
                        } else {
                            Log.d(TAG, "null");
                        }

                        ProgressUtils.dismiss();
                    }

                    @Override
                    public void onFailure(Call<GetCountriesViewModel> call, Throwable t) {
                        Log.e(TAG, "failure");

                        ProgressUtils.dismiss();
                        updatable.onFailure();
                    }
                });
            } else {
                Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            }
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }
}
