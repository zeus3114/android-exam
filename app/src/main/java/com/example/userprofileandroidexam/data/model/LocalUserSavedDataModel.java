package com.example.userprofileandroidexam.data.model;

import java.io.Serializable;

public class LocalUserSavedDataModel implements Serializable {

    private String name;
    private String email;
    private String age;
    private String address;
    private String phone;
    private String birthday;
    private String picture;
    private int page;

    public LocalUserSavedDataModel(String name,
                                   String email,
                                   String age,
                                   String address,
                                   String phone,
                                   String birthday,
                                   String picture,
                                   int page) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.picture = picture;
        this.page = page;
    }

    public LocalUserSavedDataModel() {
        this.name = "";
        this.email = "";
        this.age = "";
        this.address = "";
        this.phone = "";
        this.birthday = "";
        this.picture = "";
        this.page = 0;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return String.valueOf(age);
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPicture() {
        return picture;
    }

    public int getPage() {
        return page;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
