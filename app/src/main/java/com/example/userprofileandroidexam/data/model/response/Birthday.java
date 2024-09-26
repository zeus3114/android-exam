package com.example.userprofileandroidexam.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Birthday implements Serializable {

    @SerializedName("date")
    private String birthday;

    @SerializedName("age")
    private int age;

    public String getBirthday() {
        return birthday;
    }
    public int getAge() {
        return age;
    }
}
