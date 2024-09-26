package com.example.userprofileandroidexam.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserStreet implements Serializable {
    @SerializedName("number")
    private String streetNumber;

    @SerializedName("name")
    private String streetName;

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }
}
