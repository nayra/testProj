package com.nayra.gowhite.utils;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.nayra.gowhite.R;
import com.nayra.gowhite.custom_views.MyErrorEditText;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class ErrorUtils {

    public static void setEditTextError(Context context, MyErrorEditText editText) {
        final Drawable errorDrawable = context.getResources().getDrawable(R.drawable.ic_error);
        errorDrawable.setBounds(new Rect(0, 0, errorDrawable.getIntrinsicWidth(), errorDrawable.getIntrinsicHeight()));
        editText.setError("", errorDrawable);
    }

    public static void setPasswordEditTextError(Context context, MyErrorEditText editText) {
        //final Drawable errorDrawable = context.getResources().getDrawable(R.drawable.ic_error);
        //errorDrawable.setBounds(new Rect(0, 0, errorDrawable.getIntrinsicWidth(), errorDrawable.getIntrinsicHeight()));
        editText.setError(context.getResources().getString(R.string.error_password_msg));
    }

    public static void removeEditTextError(Context context, MyErrorEditText editText) {
        // final Drawable errorDrawable = context.getResources().getDrawable(R.drawable.ic_error);
        // errorDrawable.setBounds(new Rect(0, 0, 0, 0));
        editText.setError("", null);
    }
}
