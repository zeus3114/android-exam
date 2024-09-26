package com.example.userprofileandroidexam.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {
    @SerializedName("results")
    private List<User> userList;

    @SerializedName("info")
    private PageInfo pageInfo;


    public UserResponse(List<User> userList, PageInfo pageInfo) {
        this.userList = userList;
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<User> getUserList() {
        return userList;
    }
}
