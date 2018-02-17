package com.nayra.gowhite.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class UserInfo implements Parcelable {
    @SerializedName("FirstName")
    private String name;
    @SerializedName("LastName")
    private String surname;
    @SerializedName("Address")
    private String Address;
    @SerializedName("UserName")
    private String email;
    @SerializedName("PhoneNumber")
    private String phone;

    @SerializedName("Password")
    private String password;
    //private String fullAddress;
    private String howIn;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHowIn() {
        return howIn;
    }

    public void setHowIn(String howIn) {
        this.howIn = howIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Address='" + Address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", howIn='" + howIn + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeString(this.Address);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.password);
        dest.writeString(this.howIn);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.name = in.readString();
        this.surname = in.readString();
        this.Address = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.password = in.readString();
        this.howIn = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
