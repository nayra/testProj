package com.nayra.gowhite.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class LoginResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("error_description")
    private String error_description;

    private String token;

    public LoginResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(final String error_description) {
        this.error_description = error_description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
