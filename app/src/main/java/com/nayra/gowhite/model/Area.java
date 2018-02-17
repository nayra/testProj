package com.nayra.gowhite.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class Area extends City {
    @SerializedName("AreaID")
    private int areaId;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                '}';
    }
}
