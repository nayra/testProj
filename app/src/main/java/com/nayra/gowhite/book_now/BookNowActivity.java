package com.nayra.gowhite.book_now;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nayra.gowhite.R;
import com.nayra.gowhite.interfaces.Updatable;
import com.nayra.gowhite.interfaces.WebServices;
import com.nayra.gowhite.model.Appointment;
import com.nayra.gowhite.model.UserInfo;
import com.nayra.gowhite.utils.FragmentUtils;
import com.nayra.gowhite.utils.SharedPrefsUtil;
import com.nayra.gowhite.utils.Utils;
import com.nayra.gowhite.view_model.AddAppointmentViewModel;
import com.nayra.gowhite.view_model.RegisterViewModel;
import com.nayra.gowhite.view_model.SearchByPhoneViewModel;

public class BookNowActivity extends AppCompatActivity implements Updatable {

    private static final String TAG = BookNowActivity.class.getSimpleName();

    private static final String IS_ALREADY_BOOKED = "IS_ALREADY_BOOKED";
    private boolean isAlreadyBooked = true;

    private int booking_step_number = 1;
    public static Appointment appointmentDetails;
    public static UserInfo userInfo;

    private TextView txtTitle;
    private DateTimeFragment dateTimeFragment;
    private ContactDetailsFragment contactDetailsFragment;
    private CleaningDetailsFragment cleaningDetailsFragment;
    // private PaymentFragment paymentFragment;
    private OrderSummaryFragment orderSummaryFragment;

    public static void startActivityAsAlreadyBooked(final AppCompatActivity appCompatActivity) {
        final Intent intent = new Intent(appCompatActivity, BookNowActivity.class);
        intent.putExtra(IS_ALREADY_BOOKED, true);

        Utils.startIntent(appCompatActivity, intent);
    }

    public static void startActivityToBookNow(final AppCompatActivity appCompatActivity) {
        final Intent intent = new Intent(appCompatActivity, BookNowActivity.class);
        intent.putExtra(IS_ALREADY_BOOKED, false);

        Utils.startIntent(appCompatActivity, intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appointmentDetails = new Appointment();
        userInfo = new UserInfo();

        getIntentValue();

        initView();
    }

    private void getIntentValue() {
        isAlreadyBooked = getIntent().getBooleanExtra(IS_ALREADY_BOOKED, true);
    }


    private void initView() {
        initToolbar();
        initFooter();
        if (isAlreadyBooked) {

        } else {
            cleaningDetailsFragment = new CleaningDetailsFragment();
            txtTitle.setText(getResources().getString(R.string.title_cleaning_details));
            FragmentUtils.addFragment(R.id.frame, this, cleaningDetailsFragment, CleaningDetailsFragment.class.getSimpleName());
        }
    }

    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        txtTitle = toolbar.findViewById(R.id.tvTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                back();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initFooter() {
        final LinearLayout footerLinear = findViewById(R.id.footer);
        final Button nextButton = footerLinear.findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                navigateToNextStep();
            }
        });
    }

    private void navigateToNextStep() {
        if (booking_step_number == 1) {
            cleaningDetailsFragment.getCleaningDetails();
            Log.d(TAG, appointmentDetails.toString());
            dateTimeFragment = new DateTimeFragment();
            txtTitle.setText(getResources().getString(R.string.title_date_time));
            FragmentUtils.addFragment(R.id.frame, this, dateTimeFragment, DateTimeFragment.class.getSimpleName());
            booking_step_number += 1;
        } else if (booking_step_number == 2) {
            dateTimeFragment.getDateAndTimeDetails();
            Log.d(TAG, appointmentDetails.toString());
            contactDetailsFragment = new ContactDetailsFragment();
            txtTitle.setText(getResources().getString(R.string.title_contact_details));
            FragmentUtils.addFragment(R.id.frame, this, contactDetailsFragment, ContactDetailsFragment.class.getSimpleName());
            booking_step_number += 1;
        } else if (booking_step_number == 3) {
            contactDetailsFragment.getContactDetails();
            boolean canNavigate = contactDetailsFragment.validateInfo();
            Log.d(TAG, appointmentDetails.toString());
            if (canNavigate) {
                orderSummaryFragment = new OrderSummaryFragment();
                final Bundle bundle = new Bundle();
                bundle.putParcelable(OrderSummaryFragment.appointmentStr, appointmentDetails);
                bundle.putParcelable(OrderSummaryFragment.userInfoStr, userInfo);
                orderSummaryFragment.setArguments(bundle);
                txtTitle.setText(getResources().getString(R.string.title_summary));
                FragmentUtils.addFragment(R.id.frame, this, orderSummaryFragment, OrderSummaryFragment.class.getSimpleName());
                booking_step_number += 1;
            }
        } else if (booking_step_number == 4) {
            searchByPhone();
        }
    }

    private void searchByPhone() {
        SearchByPhoneViewModel.getInstance().searchByPhone(userInfo.getPhone(), this, this);
    }


    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final int backStack = fragmentManager.getBackStackEntryCount();

        if (backStack > 1) {
            fragmentManager.popBackStack();
            booking_step_number -= 1;
        } else {
            appointmentDetails = new Appointment();
            finish();
        }
    }

    private void registerOrContinue() {
        UserInfo result_userInfo = SearchByPhoneViewModel.getInstance().getUserInfo();
        if (result_userInfo == null) {
            int country_id = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_COUNTRY_ID);
            RegisterViewModel.getInstance().register(userInfo, country_id, this, this);
        } else {
            addAppointment();
        }
    }

    private void addAppointment() {
        UserInfo result_userInfo = RegisterViewModel.getInstance().getUserInfo();
        if (result_userInfo == null) {
            Toast.makeText(this, RegisterViewModel.getInstance().getError_msg(), Toast.LENGTH_LONG).show();
        } else {
            AddAppointmentViewModel.getInstance().addAppointment(appointmentDetails, this, this);
        }
    }

    @Override
    public void update(WebServices api) {
        switch (api) {
            case SEARCH:
                registerOrContinue();
                break;

            case REGISTER:
                addAppointment();
                break;

            case ADD_APPOINTMENT:
                Toast.makeText(this, "Appointment added", Toast.LENGTH_LONG).show();
                this.finish();
                break;
        }
    }

    @Override
    public void onFailure() {

    }
}
