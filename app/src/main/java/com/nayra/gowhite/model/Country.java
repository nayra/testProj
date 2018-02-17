package com.nayra.gowhite.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class Country {

    @SerializedName("Name")
    private String en_name;
    @SerializedName("NameAr")
    private String ar_name;
    @SerializedName("CountryID")
    private int countryID;

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
