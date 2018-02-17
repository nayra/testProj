package com.nayra.gowhite;

import android.app.Application;

import com.nayra.gowhite.utils.SharedPrefsUtil;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class GoWhiteApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefsUtil.init(this);

    }
}
