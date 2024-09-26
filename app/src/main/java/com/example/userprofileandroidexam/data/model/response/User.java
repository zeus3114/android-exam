package com.example.userprofileandroidexam.data.model.response;

import com.example.userprofileandroidexam.helper.Utils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("name")
    private UserName userName;

    @SerializedName("dob")
    private Birthday birthday;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("location")
    private UserAddress userAddress;

    @SerializedName("picture")
    private UserPicture userPicture;

    @SerializedName("info")
    private PageInfo pageInfo;

    private String title;
    private String firstName;
    private String lastName;
    private String date;
    private int age;
    private String streetName;
    private String streetNumber;
    private String city;
    private String state;
    private String large;
    private String medium;
    private String thumbnail;
    private String fullname;
    private String fulladdress;

    public User() {
        this.title = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.date = "";
        this.streetName = "";
        this.streetNumber = "";
        this.city = "";
        this.state = "";
        this.large = "";
        this.medium = "";
        this.thumbnail = "";

    }

    public User(String title,
                String firstName,
                String lastName,
                String email,
                String phone,
                String date,
                String city,
                String streetName,
                String streetNumber,
                String state,
                String large,
                String medium,
                String thumbnail) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.state = state;
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public UserName getUserName() {
        return userName;
    }

    public Birthday getDob() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserPicture getUserPicture() {
        return userPicture;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public String getFullname() {
        return fullname = firstName + " " + lastName;
    }

    public String getFullAddress() {
        return fulladdress = streetNumber +
                " " +
                streetName +
                " " +
                city +
                " " +
                state;
    }


    public int getAge() {
        int age = Utils.getActualAge(date);
        return age;
    }

    public String getDate() {
        date = Utils.getdob(date);
        return date;
    }
}
