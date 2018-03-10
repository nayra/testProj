package com.nayra.gowhite;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.nayra.gowhite.home.HomeActivity;
import com.nayra.gowhite.utils.Utils;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 5400;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setFullScreen(this);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.displayNextActivityFinish(SplashActivity.this, HomeActivity.class);
            }
        }, SPLASH_TIME_OUT);
    }
}
