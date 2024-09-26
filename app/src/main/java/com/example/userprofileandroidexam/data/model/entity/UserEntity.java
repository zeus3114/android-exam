package com.example.userprofileandroidexam.data.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblUser")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "age")
    private int age;

    @NonNull
    @ColumnInfo(name = "address")
    private String address;

    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;

    @NonNull
    @ColumnInfo(name = "birthday")
    private String birthday;

    @NonNull
    @ColumnInfo(name = "picture")
    private String picture;

    @NonNull
    @ColumnInfo(name = "page")
    private int page;

    public UserEntity(int id,
                      @NonNull String name,
                      @NonNull String email,
                      @NonNull int age,
                      @NonNull String address,
                      @NonNull String phone,
                      @NonNull String birthday,
                      @NonNull String picture,
                      int page) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.picture = picture;
        this.page = page;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public int getAge() {
        return age;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @NonNull
    public String getBirthday() {
        return birthday;
    }

    @NonNull
    public String getPicture() {
        return picture;
    }

    public int getPage() {
        return page;
    }
}
