package com.nayra.gowhite.book_now;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.nayra.gowhite.R;
import com.nayra.gowhite.adapters.SpinnerAreaCustomAdapter;
import com.nayra.gowhite.adapters.SpinnerCityCustomAdapter;
import com.nayra.gowhite.custom_views.MyEditText;
import com.nayra.gowhite.interfaces.Updatable;
import com.nayra.gowhite.interfaces.WebServices;
import com.nayra.gowhite.model.Area;
import com.nayra.gowhite.model.City;
import com.nayra.gowhite.utils.ErrorUtils;
import com.nayra.gowhite.utils.SharedPrefsUtil;
import com.nayra.gowhite.utils.Utils;
import com.nayra.gowhite.view_model.GetAreasViewModel;
import com.nayra.gowhite.view_model.GetCitiesViewModel;

import java.util.ArrayList;

/**
 * Created by nayrael-sayed on 2/14/18.
 */

public class ContactDetailsFragment extends Fragment implements Updatable /*implements SharedPreferences.OnSharedPreferenceChangeListener */ {


    //private static final String EMAIL = "email";
    private Spinner citySpinner, areaSpinner;

    private MyEditText eTxtPhone, eTxtAddress;

    //private LinearLayout notLoggedInLinearLayout;
    //private boolean isLoggedIn = false;

    private int cityId = 1, areaId = 1;
    private MyEditText eTxtName, eTxtSurName;
    private EditText eTxtPassword, eTxtEmail;

    private String phone;
    private String address;
    private String name;
    private String surname;
    private String email, password;

    private TextInputLayout passwordTextInputLayout, emailTextInputLayout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        initUI(view);
        //  initSpinners();
        loadCities();
        return view;
    }

    private void loadCities() {
        final int country_id = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_COUNTRY_ID);
        GetCitiesViewModel.getInstance().getCities(country_id, getActivity(), this);
    }

    private void loadAreasByCities(int cityId) {
        GetAreasViewModel.getInstance().getAreaByCityId(cityId, getActivity(), this);
    }

    private void displayCities() {
        final ArrayList<City> cities = GetCitiesViewModel.getInstance().getCities();

        final SpinnerCityCustomAdapter adapter = new SpinnerCityCustomAdapter(getActivity(), cities);
        citySpinner.setAdapter(adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityId = cities.get(i).getCity_id();

                int lang = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
                if (lang == 0) {
                    SharedPrefsUtil.setString(SharedPrefsUtil.SELECTED_CITY_NAME, cities.get(i).getEn_name());
                } else {
                    SharedPrefsUtil.setString(SharedPrefsUtil.SELECTED_CITY_NAME, cities.get(i).getAr_name());
                }
                loadAreasByCities(cityId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cityId = cities.get(0).getCity_id();
            }
        });

        citySpinner.setSelection(0);
    }

    private void displayAreas() {
        final ArrayList<Area> areas = GetAreasViewModel.getInstance().getAreas();
        final SpinnerAreaCustomAdapter adapter = new SpinnerAreaCustomAdapter(getActivity(), areas);

        areaSpinner.setAdapter(adapter);

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                areaId = areas.get(i).getAreaId();

                int lang = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
                if (lang == 0) {
                    SharedPrefsUtil.setString(SharedPrefsUtil.SELECTED_AREA_NAME, areas.get(i).getEn_name());
                } else {
                    SharedPrefsUtil.setString(SharedPrefsUtil.SELECTED_AREA_NAME, areas.get(i).getAr_name());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaId = areas.get(0).getAreaId();
            }
        });

        areaSpinner.setSelection(0);
    }


    private void initUI(final View view) {
        /*notLoggedInLinearLayout = view.findViewById(R.id.linear_not_registered);
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
*/
        citySpinner = view.findViewById(R.id.spCities);
        areaSpinner = view.findViewById(R.id.spAreas);


        eTxtAddress = view.findViewById(R.id.etAddress);
        eTxtPhone = view.findViewById(R.id.etPhone);

        eTxtName = view.findViewById(R.id.etName);

        eTxtSurName = view.findViewById(R.id.etSurname);

        eTxtEmail = view.findViewById(R.id.etEmail);

        eTxtPassword = view.findViewById(R.id.etPassword);

        passwordTextInputLayout = view.findViewById(R.id.tilPassword);

        emailTextInputLayout = view.findViewById(R.id.tilEmail);

    }

    /*@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equalsIgnoreCase(SharedPrefsUtil.IS_LOGGED_IN)) {
            isLoggedIn = sharedPreferences.getBoolean(s, false);
            if (isLoggedIn)
                notLoggedInLinearLayout.setVisibility(View.VISIBLE);
            else
                notLoggedInLinearLayout.setVisibility(View.GONE);
        }
    }*/


    public void getContactDetails() {
        phone = eTxtPhone.getText().toString();
        address = eTxtAddress.getText().toString();
        name = eTxtName.getText().toString();
        surname = eTxtSurName.getText().toString();
        email = eTxtEmail.getText().toString();
        password = eTxtPassword.getText().toString();

        BookNowActivity.appointmentDetails.setCityId(cityId);
        BookNowActivity.appointmentDetails.setAreaID(areaId);
        BookNowActivity.appointmentDetails.setPhoneNumber(phone);
        BookNowActivity.appointmentDetails.setAddress(address);

        BookNowActivity.userInfo.setName(name);
        BookNowActivity.userInfo.setSurname(surname);
        BookNowActivity.userInfo.setEmail(email);
        BookNowActivity.userInfo.setPhone(phone);

        BookNowActivity.userInfo.setPassword(password);

    }

    public boolean validateInfo() {
        boolean canNavigate = true;
        if (phone.isEmpty()) {
            ErrorUtils.setEditTextError(getActivity(), eTxtPhone);
            canNavigate = false;
        } else {
            ErrorUtils.removeEditTextError(getActivity(), eTxtPhone);
        }

        if (address.isEmpty()) {
            ErrorUtils.setEditTextError(getActivity(), eTxtAddress);
            canNavigate = false;
        } else {
            ErrorUtils.removeEditTextError(getActivity(), eTxtAddress);
        }

        if (name.isEmpty()) {
            ErrorUtils.setEditTextError(getActivity(), eTxtName);
            canNavigate = false;
        } else {
            ErrorUtils.removeEditTextError(getActivity(), eTxtName);
        }

        if (surname.isEmpty()) {
            ErrorUtils.setEditTextError(getActivity(), eTxtSurName);
            canNavigate = false;
        } else {
            ErrorUtils.removeEditTextError(getActivity(), eTxtSurName);
        }

        if (email.isEmpty() || !Utils.isValidEmail(email)) {
            //ErrorUtils.setEditTextError(getActivity(), eTxtEmail);
            //eTxtEmail.setError(getResources().getString(R.string.error_is_valid_email));
            emailTextInputLayout.setError(getResources().getString(R.string.error_is_valid_email));
            canNavigate = false;
        }

        if (password.isEmpty() || password.length() < 6) {
            passwordTextInputLayout.setError(getResources().getString(R.string.error_password_msg));
            //eTxtPassword.setError(getResources().getString(R.string.error_password_msg));
            canNavigate = false;
        }

        return canNavigate;
    }

    @Override
    public void update(final WebServices api) {
        switch (api) {
            case CITIES:
                displayCities();
                break;
            case AREAS:
                displayAreas();
                break;
        }
    }

    @Override
    public void onFailure() {

    }
}
