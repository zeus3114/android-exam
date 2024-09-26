package com.example.userprofileandroidexam.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserName implements Serializable {

    @SerializedName("first")
    private String firstName;

    @SerializedName("last")
    private String lastName;

    @SerializedName("title")
    private String title;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
