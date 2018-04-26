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
import com.nayra.gowhite.model.UserInfo;
import com.nayra.gowhite.network.ApiServices;
import com.nayra.gowhite.network.RetrofitClient;
import com.nayra.gowhite.utils.NetworkConnectionUtil;
import com.nayra.gowhite.utils.ProgressUtils;
import com.nayra.gowhite.utils.SharedPrefsUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayrael-sayed on 2/18/18.
 */

public class AddAppointmentWithVendorViewModel {
    private static final String TAG = AddAppointmentWithVendorViewModel.class.getSimpleName();
    private static AddAppointmentWithVendorViewModel instance = new AddAppointmentWithVendorViewModel();


    @SerializedName("ErrorMessage")
    private String error_msg = "";

    @SerializedName("Result")
    private String appointmentCode;


    public static AddAppointmentWithVendorViewModel getInstance() {
        if (instance == null) {
            instance = new AddAppointmentWithVendorViewModel();
        }
        return instance;
    }

    public void addAppointment(final Appointment appointment, final UserInfo userInfo, final Context context, final Updatable updatable) {
        //if (instance.areas.size() == 0)
        {
            if (NetworkConnectionUtil.isNetworkAvailable(context)) {
                ProgressUtils.show(context);
                final ApiServices apiService = RetrofitClient.retrofit(Constants.base_url).create(ApiServices.class);
                boolean isWantMaterials = appointment.isWantCleaningMatrial();

                int langIndex = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
                String lang;
                if (langIndex == 0)
                    lang = "en";
                else
                    lang = "ar";

                final Call<AddAppointmentWithVendorViewModel> call = apiService.addAppointmentWithVendor(
                        appointment.getType(),
                        appointment.getStartDate(),
                        appointment.getAddress(),
                        String.valueOf(appointment.getDuration()),
                        Integer.parseInt(appointment.getPrice()),
                        appointment.getPayment_method(),
                        isWantMaterials,
                        appointment.getCleaningInstructions(),
                        appointment.getAreaID(),
                        appointment.getCityId(),
                        appointment.getPhoneNumber(),
                        userInfo.getEmail(),
                        userInfo.getName(),
                        userInfo.getSurname(),
                        Constants.AUTH_BEARER,
                        lang);

                /*
                (@Field("Type") int type, @Field("StartDate") String date,
                @Field("Address") String address, @Field("Duration") int duration,
                @Field("Amount") int amount, @Field("PaymentMethod") int paymentMethod,
                @Field("WantCleaningMatrial") boolean wantCleaningMaterials,
                @Field("CleaningInstructions") String cleaningInstructions, @Field("AreaID") int areaId,
                @Field("CityID") int cityId, @Field("PhoneNumber") String phoneNumber,
                @Field("email") String email, @Field("FirstName") String firstName,
                @Field("LastName") String lastName);

                 */
                call.enqueue(new Callback<AddAppointmentWithVendorViewModel>() {
                    @Override
                    public void onResponse(Call<AddAppointmentWithVendorViewModel> call, Response<AddAppointmentWithVendorViewModel> response) {
                        instance = response.body();
                        if (instance != null) {
                            Log.d(TAG, instance.toString());
                        } else {
                            Log.d(TAG, "null");
                        }
                        updatable.update(WebServices.ADD_APPOINTMENT_WITH_VENDOR);
                        ProgressUtils.dismiss();
                    }

                    @Override
                    public void onFailure(Call<AddAppointmentWithVendorViewModel> call, Throwable t) {
                        Log.e(TAG, "failure " + t.toString());

                        ProgressUtils.dismiss();
                    }
                });
            } else {
                Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getAppointmentCode() {
        return appointmentCode;
    }
}
