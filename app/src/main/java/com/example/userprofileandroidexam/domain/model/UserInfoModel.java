package com.example.userprofileandroidexam.domain.model;

import android.widget.ImageView;

public class UserInfoModel {

    private String firstName;
    private String lastName;
    private ImageView userImage;

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

    public ImageView getUserImage() {
        return userImage;
    }

    public void setUserImage(ImageView userImage) {
        this.userImage = userImage;
    }

    public UserInfoModel(String firstName, String lastName, ImageView userImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userImage = userImage;
    }
}
