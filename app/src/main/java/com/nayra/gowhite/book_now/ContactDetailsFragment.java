package com.nayra.gowhite.book_now;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nayra.gowhite.R;
import com.nayra.gowhite.custom_views.MyEditText;
import com.nayra.gowhite.utils.SharedPrefsUtil;

import java.util.Arrays;

/**
 * Created by nayrael-sayed on 2/14/18.
 */

public class ContactDetailsFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    private static final String EMAIL = "email";
    private Spinner citySpinner, areaSpinner;

    private MyEditText eTxtPhone, eTxtAddress;

    private LinearLayout notLoggedInLinearLayout;
    private boolean isLoggedIn = false;

    private int cityId = 1, areaId = 1;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        initUI(view);
        initSpinners();
        return view;
    }

    private void initSpinners() {
        String[] cities;
        ArrayAdapter<String> cities_adapter;

        String[] area;
        ArrayAdapter<String> areas_adapter;

        int country_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_COUNTRY_INDEX);
        if (country_index == 0) {
            cities = getActivity().getResources().getStringArray(R.array.emirates_cities);
            cities_adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, cities);
        } else if (country_index == 1) {
            cities = getActivity().getResources().getStringArray(R.array.qatar_cities);
            cities_adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, cities);
        } else {
            cities = getActivity().getResources().getStringArray(R.array.bahrain_cities);
            cities_adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, cities);
        }
        citySpinner.setAdapter(cities_adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityId = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                areaId = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initUI(final View view) {
        notLoggedInLinearLayout = view.findViewById(R.id.linear_not_registered);
        isLoggedIn = SharedPrefsUtil.getBoolean(SharedPrefsUtil.IS_LOGGED_IN);
        if (isLoggedIn) {
            notLoggedInLinearLayout.setVisibility(View.GONE);
        } else {
            notLoggedInLinearLayout.setVisibility(View.VISIBLE);
        }
        final CallbackManager callbackManager = CallbackManager.Factory.create();

        final LoginButton loginButton = view.findViewById(R.id.btn_fb_login);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(final FacebookException exception) {
                // App code
            }
        });

        citySpinner = view.findViewById(R.id.spCities);
        areaSpinner = view.findViewById(R.id.spAreas);


        eTxtAddress = view.findViewById(R.id.etAddress);
        eTxtPhone = view.findViewById(R.id.etPhone);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equalsIgnoreCase(SharedPrefsUtil.IS_LOGGED_IN)) {
            isLoggedIn = sharedPreferences.getBoolean(s, false);
            if (isLoggedIn)
                notLoggedInLinearLayout.setVisibility(View.VISIBLE);
            else
                notLoggedInLinearLayout.setVisibility(View.GONE);
        }
    }


    public void getContactDetails() {
        String phone = eTxtPhone.getText().toString();
        String address = eTxtAddress.getText().toString();

        BookNowActivity.appointmentDetails.setCityId(cityId);
        BookNowActivity.appointmentDetails.setAreaID(areaId);
        BookNowActivity.appointmentDetails.setPhoneNumber(phone);
        BookNowActivity.appointmentDetails.setAddress(address);
    }
}
