package com.nayra.gowhite.view_model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.nayra.gowhite.Constants;
import com.nayra.gowhite.R;
import com.nayra.gowhite.interfaces.Updatable;
import com.nayra.gowhite.interfaces.WebServices;
import com.nayra.gowhite.model.Area;
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

public class GetAreasViewModel {
    private static final String TAG = GetAreasViewModel.class.getSimpleName();
    private static GetAreasViewModel instance = new GetAreasViewModel();

    @SerializedName("Result")
    private ArrayList<Area> areas = new ArrayList<>();

    @SerializedName("ErrorMessage")
    private String error_msg = "";

    private Updatable updatable;

    public static GetAreasViewModel getInstance() {
        if (instance == null) {
            instance = new GetAreasViewModel();
        }
        return instance;
    }

    public void getAreaByCityId(final int city_id, final Context context, final Updatable updatable) {
        //if (instance.areas.size() == 0)
        {
            this.updatable = updatable;
            if (NetworkConnectionUtil.isNetworkAvailable(context)) {
                ProgressUtils.show(context);
                final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);

                final Call<GetAreasViewModel> call = apiService.getAreasByCityId(city_id);

                call.enqueue(new Callback<GetAreasViewModel>() {
                    @Override
                    public void onResponse(Call<GetAreasViewModel> call, Response<GetAreasViewModel> response) {
                        instance = response.body();
                        if (instance != null) {
                            Log.d(TAG, instance.toString());
                        } else {
                            Log.d(TAG, "null");
                        }
                        updatable.update(WebServices.AREAS);
                        ProgressUtils.dismiss();
                    }

                    @Override
                    public void onFailure(Call<GetAreasViewModel> call, Throwable t) {
                        Log.e(TAG, "failure");

                        ProgressUtils.dismiss();
                    }
                });
            } else {
                Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            }
        }
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }
}
