package com.nayra.gowhite.home;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.nayra.gowhite.R;
import com.nayra.gowhite.authentication.LoginActivity;
import com.nayra.gowhite.book_now.BookNowActivity;
import com.nayra.gowhite.databinding.HomeMainBinding;
import com.nayra.gowhite.utils.DialogUtils;
import com.nayra.gowhite.utils.LanguageUtil;
import com.nayra.gowhite.utils.SharedPrefsUtil;
import com.nayra.gowhite.utils.Utils;

public class HomeActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private HomeMainBinding binding;
    private boolean isLoggedIn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int langIndex = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
        LanguageUtil.setLocaleLanguage(langIndex, this);
        binding = DataBindingUtil.setContentView(this, R.layout.home_main);

        final SharedPreferences prefs = SharedPrefsUtil.getPrefs();
        prefs.registerOnSharedPreferenceChangeListener(this);
        setButtonText();

        initUI();


    }

    private void setButtonText() {
        isLoggedIn = SharedPrefsUtil.getBoolean(SharedPrefsUtil.IS_LOGGED_IN);
        if (!isLoggedIn) {
            binding.btnBookedBefore.setText(getString(R.string.booked_before));
        } else {
            binding.btnBookedBefore.setText(getString(R.string.my_account));
        }
    }

    private void initUI() {
        initToolbar();

        binding.btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                BookNowActivity.startActivityToBookNow(HomeActivity.this);
            }
        });

        binding.btnBookedBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLoggedIn) {
                    navigateToLogin();
                } else {
                    navigateToMyAcccount();
                }
            }
        });
    }

    private void navigateToMyAcccount() {

    }

    private void navigateToLogin() {
        Utils.displayNextActivity(this, LoginActivity.class);
    }

    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar_home);
        final ImageButton changeLanguageButton = toolbar.findViewById(R.id.imgBtnChangeLanguage);
        changeLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                DialogUtils.showChooseLanguageAndAreaDialog(HomeActivity.this);
            }
        });

        final ImageButton callButton = toolbar.findViewById(R.id.imgBtnCall);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
            }
        });
    }


    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String s) {
        if (s.equalsIgnoreCase(SharedPrefsUtil.IS_LOGGED_IN)) {
            setButtonText();
        }
    }
}
