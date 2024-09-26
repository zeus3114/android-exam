package com.example.userprofileandroidexam.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserPicture implements Serializable {

    @SerializedName("large")
    private String large;

    @SerializedName("medium")
    private String medium;

    @SerializedName("thumbnail")
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

}
