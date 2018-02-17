package com.nayra.gowhite.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class City extends Country {
    /* @SerializedName("Name")
     private String en_name;
     @SerializedName("NameAr")
     private String ar_name;
     @SerializedName("CountryID")
     private int country_id;*/
    @SerializedName("CityID")
    private int city_id;

   /* public String getEn_name() {
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

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }*/

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

   /* @Override
    public String toString() {
        return "City{" +
                "en_name='" + en_name + '\'' +
                ", ar_name='" + ar_name + '\'' +
                ", country_id=" + country_id +
                ", city_id=" + city_id +
                '}';
    }*/
}
