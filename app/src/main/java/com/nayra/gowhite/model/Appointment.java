package com.nayra.gowhite.model;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class Appointment {

    private int type;
    private String StartDate; //:01-01-2018 13:30
    private String Address;
    private int Duration;
    private int amount;
    private int payment_method;
    private boolean wantCleaningMatrial;
    private String cleaningInstructions;
    private int areaID;
    private int cityId;
    private String PhoneNumber;

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
                '}';
    }
}
