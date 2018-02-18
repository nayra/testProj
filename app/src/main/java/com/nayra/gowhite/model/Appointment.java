package com.nayra.gowhite.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class Appointment implements Parcelable {

    private int type;
    private String StartDate; //:01-01-2018 13:30
    private String Address;
    private int Duration;
    private int amount;
    private int payment_method = 1;
    private boolean wantCleaningMatrial;
    private String cleaningInstructions;
    private int areaID;
    private int cityId;
    private String PhoneNumber;
    private String price;

    public Appointment() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }

    public boolean isWantCleaningMatrial() {
        return wantCleaningMatrial;
    }

    public void setWantCleaningMatrial(boolean wantCleaningMatrial) {
        this.wantCleaningMatrial = wantCleaningMatrial;
    }

    public String getCleaningInstructions() {
        return cleaningInstructions;
    }

    public void setCleaningInstructions(String cleaningInstructions) {
        this.cleaningInstructions = cleaningInstructions;
    }

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.StartDate);
        dest.writeString(this.Address);
        dest.writeInt(this.Duration);
        dest.writeInt(this.amount);
        dest.writeInt(this.payment_method);
        dest.writeByte(this.wantCleaningMatrial ? (byte) 1 : (byte) 0);
        dest.writeString(this.cleaningInstructions);
        dest.writeInt(this.areaID);
        dest.writeInt(this.cityId);
        dest.writeString(this.PhoneNumber);
        dest.writeString(this.price);
    }

    protected Appointment(Parcel in) {
        this.type = in.readInt();
        this.StartDate = in.readString();
        this.Address = in.readString();
        this.Duration = in.readInt();
        this.amount = in.readInt();
        this.payment_method = in.readInt();
        this.wantCleaningMatrial = in.readByte() != 0;
        this.cleaningInstructions = in.readString();
        this.areaID = in.readInt();
        this.cityId = in.readInt();
        this.PhoneNumber = in.readString();
        this.price = in.readString();
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel source) {
            return new Appointment(source);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    @Override
    public String toString() {
        return "Appointment{" +
                "type=" + type +
                ", StartDate='" + StartDate + '\'' +
                ", Address='" + Address + '\'' +
                ", Duration=" + Duration +
                ", amount=" + amount +
                ", payment_method=" + payment_method +
                ", wantCleaningMatrial=" + wantCleaningMatrial +
                ", cleaningInstructions='" + cleaningInstructions + '\'' +
                ", areaID=" + areaID +
                ", cityId=" + cityId +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
