package com.nayra.gowhite.my_account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nayra.gowhite.R;

public class MyAccountActivity extends AppCompatActivity {

    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        initToolbar();

        initUI();
    }

    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        txtTitle = toolbar.findViewById(R.id.tvTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initUI() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            /*switch (item.getItemId()) {
                case R.id.navigation_shop:
                    toolbar.setTitle("Shop");
                    return true;
                case R.id.navigation_gifts:
                    toolbar.setTitle("My Gifts");
                    return true;
                case R.id.navigation_cart:
                    toolbar.setTitle("Cart");
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    return true;
            }*/
            return false;
        }
    };
}
