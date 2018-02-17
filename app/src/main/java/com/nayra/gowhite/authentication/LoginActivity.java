package com.nayra.gowhite.authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nayra.gowhite.R;
import com.nayra.gowhite.custom_views.MyEditText;
import com.nayra.gowhite.utils.ErrorUtils;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private MyEditText etxtEmail, etxtPassword;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();

    }

    private void initUI() {
        etxtEmail = findViewById(R.id.etEmailLogin);
        etxtPassword = findViewById(R.id.etPasswordLogin);

        final Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                loginValidation();
            }
        });

        initFbLogin();
    }

    private void initFbLogin() {
        final String EMAIL = "email";

        LoginButton loginButton = findViewById(R.id.btn_fb_login);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        CallbackManager callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    private void loginValidation() {
        final String email = etxtEmail.getText().toString();
        final String password = etxtPassword.getText().toString();

        if (email.isEmpty()) {
            ErrorUtils.setEditTextError(this, etxtEmail);
        }

        if (password.isEmpty()) {
            ErrorUtils.setEditTextError(this, etxtPassword);
        }

        if (!email.isEmpty() && !password.isEmpty()) {
            LoginViewModel.getInstance().login("password", password, email);
        }
    }
}
