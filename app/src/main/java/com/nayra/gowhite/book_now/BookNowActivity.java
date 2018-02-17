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

import com.nayra.gowhite.R;
import com.nayra.gowhite.model.Appointment;
import com.nayra.gowhite.utils.FragmentUtils;
import com.nayra.gowhite.utils.Utils;

public class BookNowActivity extends AppCompatActivity {

    private static final String TAG = BookNowActivity.class.getSimpleName();

    private static final String IS_ALREADY_BOOKED = "IS_ALREADY_BOOKED";
    private boolean isAlreadyBooked = true;

    private int booking_step_number = 1;
    public static Appointment appointmentDetails;

    private TextView txtTitle;
    private DateTimeFragment dateTimeFragment;
    private ContactDetailsFragment contactDetailsFragment;
    private CleaningDetailsFragment cleaningDetailsFragment;
    private PaymentFragment paymentFragment;
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
            Log.d(TAG, appointmentDetails.toString());
            paymentFragment = new PaymentFragment();
            txtTitle.setText(getResources().getString(R.string.title_payment));
            FragmentUtils.addFragment(R.id.frame, this, paymentFragment, PaymentFragment.class.getSimpleName());
            booking_step_number += 1;
        } else if (booking_step_number == 4) {
            paymentFragment.getPaymentDetails();
            orderSummaryFragment = new OrderSummaryFragment();
            txtTitle.setText(getResources().getString(R.string.title_summary));
            FragmentUtils.addFragment(R.id.frame, this, orderSummaryFragment, OrderSummaryFragment.class.getSimpleName());
            booking_step_number += 1;
        }
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
}
