package com.nayra.gowhite.custom_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        setCompoundDrawables(null, null, icon, null);
    }
}

