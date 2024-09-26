package com.example.userprofileandroidexam.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PageInfo implements Serializable {
    @SerializedName("results")
    private int results;
    @SerializedName("page")
    private int page;

    public int getPage() {
        return page;
    }

    public int getResults() {
        return results;
    }
}
