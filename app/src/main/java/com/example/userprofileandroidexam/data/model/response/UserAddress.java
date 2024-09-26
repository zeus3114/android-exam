package com.example.userprofileandroidexam.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserAddress implements Serializable {
    @SerializedName("city")
    private String city;

    @SerializedName("postcode")
    private String postCode;

    @SerializedName("state")
    private String state;

    @SerializedName("street")
    private UserStreet street;

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getState() {
        return state;
    }

    public UserStreet getStreet() {
        return street;
    }
}
