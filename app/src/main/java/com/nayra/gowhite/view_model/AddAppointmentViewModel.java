package com.nayra.gowhite.view_model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.nayra.gowhite.Constants;
import com.nayra.gowhite.R;
import com.nayra.gowhite.interfaces.Updatable;
import com.nayra.gowhite.interfaces.WebServices;
import com.nayra.gowhite.model.Appointment;
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

public class AddAppointmentViewModel {
    private static final String TAG = AddAppointmentViewModel.class.getSimpleName();
    private static AddAppointmentViewModel instance = new AddAppointmentViewModel();


    @SerializedName("ErrorMessage")
    private String error_msg = "";

    @SerializedName("Result")
    private String appointmentCode;

    private Updatable updatable;

    public static AddAppointmentViewModel getInstance() {
        if (instance == null) {
            instance = new AddAppointmentViewModel();
        }
        return instance;
    }

    public void addAppointment(final Appointment appointment, final Context context, final Updatable updatable) {
        //if (instance.areas.size() == 0)
        {
            this.updatable = updatable;
            if (NetworkConnectionUtil.isNetworkAvailable(context)) {
                ProgressUtils.show(context);
                final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);
                boolean isWantMaterials = appointment.isWantCleaningMatrial();
                int needMaterial = 0;
                if (isWantMaterials)
                    needMaterial = 1;

                final Call<AddAppointmentViewModel> call = apiService.addAppointment(appointment.getType(), appointment.getStartDate(),
                        appointment.getAddress(), appointment.getDuration(), appointment.getAmount(), needMaterial, appointment.getCleaningInstructions(),
                        appointment.getAreaID(), appointment.getCityId(), appointment.getPhoneNumber());

                call.enqueue(new Callback<AddAppointmentViewModel>() {
                    @Override
                    public void onResponse(Call<AddAppointmentViewModel> call, Response<AddAppointmentViewModel> response) {
                        instance = response.body();
                        if (instance != null) {
                            Log.d(TAG, instance.toString());
                        } else {
                            Log.d(TAG, "null");
                        }
                        updatable.update(WebServices.ADD_APPOINTMENT);
                        ProgressUtils.dismiss();
                    }

                    @Override
                    public void onFailure(Call<AddAppointmentViewModel> call, Throwable t) {
                        Log.e(TAG, "failure");

                        ProgressUtils.dismiss();
                    }
                });
            } else {
                Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            }
        }
    }
}
