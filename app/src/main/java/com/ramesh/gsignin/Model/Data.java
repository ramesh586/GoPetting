package com.ramesh.gsignin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by thrymr on 8/2/17.
 */

public class Data {
    @SerializedName("endDate")
    @Expose
    private String endDate;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("icon")
    @Expose
    private String iconURL;






    public Data(String endDate, String name, String icon) {
        this.endDate = endDate;
        this.name = name;
        this.iconURL = icon;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }


}


